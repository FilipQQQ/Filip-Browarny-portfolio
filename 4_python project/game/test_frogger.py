from life_layout import HeadLine
from enemies_friends_frog import Car, Friend, Frog
from run import Logic
import pygame


def test_base_life():
    logic = Logic()
    assert logic.life.lives == 5


def test_lose_life():
    logic = Logic()
    logic.lose_life()
    assert logic.life.lives == 4


def test_score_reset():
    screen = pygame.display.set_mode((650, 720))
    logic = Logic()
    logic.player = Frog(screen, 0, 310, 650)
    logic.life = HeadLine(screen, 5)
    logic.life.score = 5
    logic.game_over()
    assert logic.life.score == 0


def test_rand_int():
    logic = Logic()
    assert 0 < logic.rand_number() < 10000


def test_direction_choice():
    logic = Logic()
    assert logic.direction_choice(10) == 1
    assert logic.direction_choice(19) == -1


def test_max_score():
    screen = pygame.display.set_mode((650, 720))
    logic = Logic()
    logic.life = HeadLine(screen, 5)
    logic.life.score = 10
    logic.life.max_score = 5
    logic.max_score()
    assert logic.life.max_score == 10


def test_check_win():
    screen = pygame.display.set_mode((650, 720))
    logic = Logic()
    logic.life = HeadLine(screen, 5)
    logic.player = Frog(screen, 0, 300, 110)
    logic.life.score = 10
    logic.chceck_win()
    assert logic.life.score == 15
    assert logic.player.position_x == 310
    assert logic.player.position_y == 680


def test_check_win_no_win():
    screen = pygame.display.set_mode((650, 720))
    logic = Logic()
    logic.life = HeadLine(screen, 5)
    logic.player = Frog(screen, 0, 300, 150)
    logic.life.score = 10
    logic.chceck_win()
    assert logic.life.score == 10
    assert logic.player.position_x == 300
    assert logic.player.position_y == 150


def test_friends_init():
    logic = Logic()
    logic.friends_init()
    assert len(logic.friends) == 24
    assert logic.friends[9].position_y == 170


def test_car_init():
    logic = Logic()
    logic.car_init()
    assert len(logic.cars) == 9
    for car in range(9):
        assert logic.cars[car].position_y == 410+30*car


def test_car_collision_same_position():
    screen = pygame.display.set_mode((650, 720))
    logic = Logic()
    logic.player = Frog(screen, 0, 310, 650)
    logic.cars = [
        Car(screen, 1, 310, 650, (1, 1, 1)),
        Car(screen, 1, 230, 200, (1, 1, 1))
    ]
    logic.colision_detect()
    assert logic.life.lives == 4


def test_car_collision():
    screen = pygame.display.set_mode((650, 720))
    logic = Logic()
    logic.player = Frog(screen, 0, 310, 650)
    logic.cars = [
        Car(screen, 1, 330, 670, (1, 1, 1)),
        Car(screen, 1, 230, 200, (1, 1, 1))
    ]
    logic.colision_detect()
    assert logic.life.lives == 4


def test_car_no_collision():
    screen = pygame.display.set_mode((650, 720))
    logic = Logic()
    logic.player = Frog(screen, 0, 310, 650)
    logic.cars = [
        Car(screen, 1, 390, 670, (1, 1, 1)),
        Car(screen, 1, 230, 200, (1, 1, 1))
    ]
    logic.colision_detect()
    assert logic.life.lives == 5


def test_car_touch_corner():
    screen = pygame.display.set_mode((650, 720))
    logic = Logic()
    logic.player = Frog(screen, 0, 310, 650)
    logic.cars = [
        Car(screen, 1, 340, 680, (1, 1, 1)),
        Car(screen, 1, 230, 200, (1, 1, 1))
    ]
    logic.colision_detect()
    assert logic.life.lives == 5


def test_car_touch_side():
    screen = pygame.display.set_mode((650, 720))
    logic = Logic()
    logic.player = Frog(screen, 0, 310, 650)
    logic.cars = [
        Car(screen, 1, 340, 650, (1, 1, 1)),
        Car(screen, 1, 230, 200, (1, 1, 1))
    ]
    logic.colision_detect()
    assert logic.life.lives == 5


def test_friend_collision_same_position():
    screen = pygame.display.set_mode((650, 720))
    logic = Logic()
    logic.player = Frog(screen, 0, 310, 650)
    logic.friends = [
        Friend(screen, 1, 310, 650, (1, 1, 1), 2),
        Friend(screen, 1, 677, 880, (1, 1, 1), 2),
    ]
    logic.friend_colision()
    assert logic.life.lives == 5


def test_friend_collision():
    screen = pygame.display.set_mode((650, 720))
    logic = Logic()
    logic.player = Frog(screen, 0, 281, 650)
    logic.friends = [
        Friend(screen, 1, 310, 650, (1, 1, 1), 2),
        Friend(screen, 1, 677, 880, (1, 1, 1), 2),
    ]
    logic.friend_colision()
    assert logic.life.lives == 5


def test_friend_no_collision():
    screen = pygame.display.set_mode((650, 720))
    logic = Logic()
    logic.player = Frog(screen, 0, 280, 650)
    logic.friends = [
        Friend(screen, 1, 310, 650, (1, 1, 1), 2),
        Friend(screen, 1, 677, 880, (1, 1, 1), 2),
    ]
    logic.friend_colision()
    assert logic.life.lives == 4


def test_friend_touch_corner():
    screen = pygame.display.set_mode((650, 720))
    logic = Logic()
    logic.player = Frog(screen, 0, 310, 650)
    logic.friends = [
        Friend(screen, 1, 340, 680, (1, 1, 1), 2),
        Friend(screen, 1, 677, 880, (1, 1, 1), 2),
    ]
    logic.friend_colision()
    assert logic.life.lives == 4


def test_friend_colision_right_side():
    screen = pygame.display.set_mode((650, 720))
    logic = Logic()
    logic.player = Frog(screen, 0, 310, 650)
    logic.friends = [
        Friend(screen, 1, 101, 650, (1, 1, 1), 3),
        Friend(screen, 1, 677, 880, (1, 1, 1), 2),
    ]
    logic.friend_colision()
    assert logic.life.lives == 5


def test_check_if_car_in_right():
    screen = pygame.display.set_mode((650, 720))
    car = Car(screen, 1, 641, 650, (1, 1, 1))
    car.chceck_if_car_in()
    assert car.position_x == -20


def test_check_if_car_in_left():
    screen = pygame.display.set_mode((650, 720))
    car = Car(screen, -1, -21, 650, (1, 1, 1))
    car.chceck_if_car_in()
    assert car.position_x == 640


def test_car_move_left():
    screen = pygame.display.set_mode((650, 720))
    car = Car(screen, -1, 555, 650, (1, 1, 1))
    car.car_move()
    assert car.position_x == 554


def test_car_move_right():
    screen = pygame.display.set_mode((650, 720))
    car = Car(screen, 1, 553, 650, (1, 1, 1))
    car.car_move()
    assert car.position_x == 554


def test_frog_moves():
    screen = pygame.display.set_mode((650, 720))
    frog = Frog(screen, 1, 500, 500)
    frog.move_down()
    assert frog.position_x == 500
    assert frog.position_y == 530
    frog.move_up()
    assert frog.position_x == 500
    assert frog.position_y == 500
    frog.move_left()
    assert frog.position_x == 470
    assert frog.position_y == 500
    frog.move_right()
    assert frog.position_x == 500
    assert frog.position_y == 500
