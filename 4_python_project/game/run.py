import pygame
import sys
from logic import Logic


class Game(object):
    """
    Objekt tworzacy okno gry
    """
    def __init__(self):
        self.logic = Logic()
        pygame.display.set_caption("Frogger")
        pygame.init()
        self.screen = self.logic.screen
        while True:
            self.events()
            self.logic.car_tick()
            self.logic.life.time = self.logic.clock_time()
            self.draw()
            self.logic.colision_detect()
            if 370 > self.logic.player.position_y > 130:
                self.logic.friend_colision()
                if int((self.logic.player.position_y - 130)//30) % 2:
                    self.logic.player.direction = -1
                else:
                    self.logic.player.direction = 1
                self.logic.player.speed = self.logic.life.level_up()
            else:
                self.logic.player.direction = 0
            self.logic.chceck_win()
            for car in self.logic.cars:
                car.speed = self.logic.life.level_up()
            for friend in self.logic.friends:
                friend.speed = self.logic.life.level_up()
            if -20 > self.logic.player.position_y > 640:
                self.logic.lose_life()

    def events(self):
        """
        Funkcja przechwytujaca eventy
        """
        for event in pygame.event.get():
            key_down = event.type == pygame.KEYDOWN
            if event.type == pygame.QUIT:
                sys.exit(0)
            elif key_down and event.key == pygame.K_ESCAPE:
                sys.exit(0)
            elif key_down and event.key == pygame.K_UP:
                if self.logic.player.position_y != 110:
                    self.logic.player.move_up()
                    self.logic.chceck_position()
            elif key_down and event.key == pygame.K_LEFT:
                if self.logic.player.position_x != 10:
                    self.logic.player.move_left()
            elif key_down and event.key == pygame.K_RIGHT:
                if self.logic.player.position_x != 610:
                    self.logic.player.move_right()
            elif key_down and event.key == pygame.K_DOWN:
                if self.logic.player.position_y != 680:
                    self.logic.player.move_down()

    def draw(self):
        """
        Funkcja rysujaca obiekty na ekranie
        """
        self.screen.fill((0, 0, 0))
        self.logic.zones.draw()
        self.logic.life.draw()
        for car in self.logic.cars:
            car.draw()
        for friend in self.logic.friends:
            friend.draw()
        self.logic.player.draw()
        self.logic.frame.draw()
        pygame.display.flip()


if __name__ == "__main__":
    Game()
