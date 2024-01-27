import pygame


class Car(object):
    """
    Klasa odpowiedzialna za generowanie samochodow.
    Przyjmowane argumenty:
    game - rozmiar ekranu
    direction - kierunek ruchu auta (1 = prawo, -1 = lewo)
    x - pozycja  osi x
    y - pozycja osi y
    color - kolor w formacie RGB
    """
    def __init__(self, screen, direction, x, y, color):
        self.position_x = x
        self.position_y = y
        self.direction = direction
        self.size = (30, 30)
        self.game = screen
        self.color = color

    def car_size(self):
        return self.size

    def chceck_if_car_in(self):
        if self.position_x > 640 and self.direction > 0:
            self.position_x = -self.size[0]+10
        elif self.position_x < -self.size[0]+10 and self.direction < 0:
            self.position_x = 640

    def car_move(self):
        self.position_x += self.direction
        self.chceck_if_car_in()

    def car_position(self):
        frog_position = pygame.Vector2(self.position_x, self.position_y)
        return pygame.Rect(frog_position, self.car_size())

    def draw(self):
        pygame.draw.rect(self.game, self.color, self.car_position())


class Buldozer(Car):
    """
    Klasa odpowiedzialna za generowanie buldozerow,
    czyli 2 razy wiekszych samochodow
    Dziedziczy po objekcie Car
    Przyjmowane argumenty:
    game - rozmiar ekranu
    direction - kierunek ruchu auta (1 = prawo, -1 = lewo)
    x - pozycja  osi x
    y - pozycja osi y
    color - kolor w formacie RGB
    """
    def __init__(self, screen, direction, x, y, color):
        super().__init__(screen, direction, x, y, color)
        self.size = (60, 30)


class Race_car(Car):
    """
    Klasa odpowiedzialna za generowanie wyscigowek,
    czyli 2 razy szybszych samochodow
    Dziedziczy po objekcie Car
    Przyjmowane argumenty:
    game - rozmiar ekranu
    direction - kierunek ruchu auta (1 = prawo, -1 = lewo)
    x - pozycja  osi x
    y - pozycja osi y
    color - kolor w formacie RGB
    """
    def __init__(self, screen, direction, x, y, color):
        super().__init__(screen, direction, x, y, color)
        self.direction = 2*direction


class Friend(Car):
    """
    Klasa odpowiedzialna za generowanie przyjaciol,
    czyli pomocnikow w przechodzeniu rzeki
    Dziedziczy po objekcie Car
    Przyjmowane argumenty:
    game - rozmiar ekranu
    direction - kierunek ruchu auta (1 = prawo, -1 = lewo)
    x - pozycja  osi x
    y - pozycja osi y
    color - kolor w formacie RGB
    size - rozmiar, jako wielokrotnosc (n*70, 30)
    """
    def __init__(self, screen,  direction, x, y, color, size):
        super().__init__(screen, direction, x, y, color)
        self.size = (size*70, 30)


# frog color
def frog_color():
    return (109, 157, 114)


# frog size
def frog_size():
    return pygame.Vector2(30, 30)


class Frog(Car):
    """
    Klasa odpowiedzialna za generowanie gracza.
    Dziedziczy po objekcie Car
    Przyjmowane argumenty:
    game - rozmiar ekranu
    direction - kierunek ruchu (1 = prawo, -1 = lewo), (domyslnie 0)
    x - pozycja  osi x (domyslna 310)
    y - pozycja osi y (domyslna 680)
    """
    def __init__(self, screen, direction=0, x=310, y=680):
        self.position_x = x
        self.position_y = y
        self.size = frog_size()
        self.game = screen
        self.color = frog_color()
        self.direction = direction

    def frog_size(self):
        return self.size

    def frog_position(self):
        frog_position = pygame.Vector2(self.position_x, self.position_y)
        return pygame.Rect(frog_position, self.size)

    def draw(self):
        pygame.draw.rect(self.game, self.color, self.frog_position())

    def move_left(self):
        self.position_x -= self.size[0]

    def move_right(self):
        self.position_x += self.size[0]

    def move_up(self):
        self.position_y -= self.size[1]

    def move_down(self):
        self.position_y += self.size[1]
