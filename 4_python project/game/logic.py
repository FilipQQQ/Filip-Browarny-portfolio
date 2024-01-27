import pygame
from life_layout import HeadLine, Frame, Safe_Zone_Water, game_time
from enemies_friends_frog import Buldozer, Car, Friend, Frog, Race_car
from random import randint


class Logic(object):
    """
    Klasa odpowiadajaca za logike.
    """
    def __init__(self):
        self.screen = pygame.display.set_mode((650, 720))
        self.tps_clock = pygame.time.Clock()
        self.tps_delta = 0.0
        self.car_delta = 0.0
        self.car_clock = pygame.time.Clock()
        self.life = HeadLine(self.screen)
        self.player = Frog(self.screen)
        self.max_position = self.player.position_y
        self.frame = Frame(self.screen)
        self.zones = Safe_Zone_Water(self.screen)
        self.car_init()
        self.friends_init()

    def rand_number(self):
        self.rand = randint(1, 10000)
        return self.rand

    def direction_choice(self, number):
        """
        Funkcja zwracajaca kierunek z liczby,
        ktora przyjmuje jako argument
        """
        if number % 2 == 0:
            return 1
        else:
            return -1

    def rand_color(self):
        r = randint(0, 255)
        g = randint(0, 255)
        b = randint(0, 255)
        return (r, g, b)

    def x_position(self):
        return (self.rand_number() % 21)*30+10

    def car_init(self):
        self.cars = []
        for line in range(9):
            rand = randint(1, 10000)
            direction = self.direction_choice(rand)
            color = self.rand_color()
            x = self.x_position()
            y = 410 + line*30
            if rand % 5 == 2:
                car = Buldozer(self.screen, direction, x, y, color)
            elif rand % 5 == 3:
                car = Race_car(self.screen, direction, x, y, color)
            else:
                car = Car(self.screen, direction, x, y, color)
            self.cars.append(car)

    def friends_init(self):
        self.friends = []
        for row in range(3):
            for line in range(8):
                rand = randint(1, 10000)
                direction = self.direction_choice(line)
                size = rand % 4 + 1
                color = (255, 99, 71)
                x = self.x_position()
                y = 140 + line*30
                screen = self.screen
                self.friend = Friend(screen, direction, x, y, color, size)
                self.friends.append(self.friend)

    def lose_life(self):
        self.life.lives -= 1
        self.reset()
        if self.life.lives == 0:
            self.game_over()

    def game_over(self):
        self.life.score = 0
        self.life.lives = 5
        self.life.level = 0
        self.max_position = self.player.position_y

    def clock_time(self):
        self.tps_delta += self.tps_clock.tick()/1000.0
        if int(self.tps_delta) == game_time():
            self.lose_life()
            self.tps_delta = 0
            return self.tps_delta
        return self.tps_delta

    def car_tick(self):
        self.car_delta += self.car_clock.tick()/1000.0
        while self.car_delta > 1/(self.life.level_up()*10.0):
            for car in self.cars:
                car.car_move()
            for friend in self.friends:
                friend.car_move()
            self.player.car_move()
            self.car_delta -= 1/(self.life.level_up()*10.0)

    def chceck_position(self):
        if self.max_position > self.player.position_y:
            self.max_position = self.player.position_y
            self.life.score += 1
            self.max_score()

    def max_score(self):
        self.life.max_score = max(self.life.score, self.life.max_score)

    def chceck_win(self):
        if self.player.position_y == 110:
            self.life.score += 5
            self.life.level += 1
            self.reset()
            self.car_init()
            self.max_position = self.player.position_y

    def reset(self):
        self.tps_delta = 0
        self.player.position_x = 310
        self.player.position_y = 680
        self.max_score()

    def check_colision(self, list):
        """
        Funkcja sprawdzajaca kolizje,
        ktora przyjmuje jako argument liste obiektow
        """
        frog = self.player.frog_position()
        for object in list:
            if frog.colliderect(object.car_position()):
                return True
        return False

    def colision_detect(self):
        if self.check_colision(self.cars):
            self.lose_life()

    def friend_colision(self):
        if not self.check_colision(self.friends):
            self.lose_life()
