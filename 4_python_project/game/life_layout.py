import pygame


# game time (always -1)
def game_time():
    return 30


# ramka kolor
def frame_color():
    return (155, 150, 255)


# rozmiar wewnetrzny mapy
def map_size():
    return (630, 600)


# ramka kolor
def zone_color():
    return (255, 193, 0)


# woda kolor
def water_color():
    return (0, 255, 255)


class HeadLine(object):
    """
    Klasa odpowiedzialna za generowanie naglowka,
    z informacjami o stanie gry.
    Przyjmowane argumenty:
    game - rozmiar ekranu
    base_lives - podstawowa ilosc zyc (domyslnie 5)
    """
    def __init__(self, game, base_lives=5):
        self.lives = base_lives
        self.game = game
        self.time = 0
        self.score = 0
        self.max_score = 0
        self.level = 0

    def level_up(self):
        return self.level + 1

    def time_remaining(self):
        time = int(game_time()-self.time)
        return time

    def draw_lives(self):
        font = pygame.font.Font("ARCADECLASSIC.TTF", 50)
        text = f'Lives {self.lives}'
        text_render = font.render(text, 1, (250, 250, 250))
        self.game.blit(text_render, (10, 0))

    def draw_time(self):
        font = pygame.font.Font("ARCADECLASSIC.TTF", 50)
        text = f'Time {self.time_remaining()}'
        text_render = font.render(text, 1, (250, 250, 250))
        self.game.blit(text_render, (10, 45))

    def draw_score(self):
        font = pygame.font.Font("ARCADECLASSIC.TTF", 50)
        text = f'Score        {self.score}'
        text_render = font.render(text, 1, (250, 250, 250))
        self.game.blit(text_render, (300, 0))

    def draw_max_score(self):
        font = pygame.font.Font("ARCADECLASSIC.TTF", 50)
        text = f'High score {self.max_score}'
        text_render = font.render(text, 1, (250, 250, 250))
        self.game.blit(text_render, (300, 45))

    def draw(self):
        self.draw_lives()
        self.draw_time()
        self.draw_score()
        self.draw_max_score()


class Frame(object):
    """
    Klasa odpowiedzialna za generowanie ramki
    Przyjmowane argumenty:
    game - rozmiar ekranu
    """
    def __init__(self, game):
        self.begin_x = 0
        self.begin_y = 100
        self.map_size = map_size()
        self.size_top_bottom = pygame.Vector2(self.map_size[0]+20, 10)
        self.size_right_left = pygame.Vector2(10, self.map_size[1])
        self.game = game
        self.frame_color = frame_color()

    def top_position(self):
        begin = pygame.Vector2(self.begin_x, self.begin_y)
        rect_position = pygame.Rect(begin, self.size_top_bottom)
        return rect_position

    def bottom_position(self):
        y = self.begin_y + self.map_size[1] + 10
        begin = pygame.Vector2(self.begin_x, y)
        rect_position = pygame.Rect(begin, self.size_top_bottom)
        return rect_position

    def left_position(self):
        begin = pygame.Vector2(self.begin_x, self.begin_y + 10)
        rect_position = pygame.Rect(begin, self.size_right_left)
        return rect_position

    def right_position(self):
        x = self.begin_x + self.map_size[0] + 10
        begin = pygame.Vector2(x, self.begin_y + 10)
        rect_position = pygame.Rect(begin, self.size_right_left)
        return rect_position

    def draw(self):
        pygame.draw.rect(self.game, self.frame_color, self.top_position())
        pygame.draw.rect(self.game, self.frame_color, self.bottom_position())
        pygame.draw.rect(self.game, self.frame_color, self.left_position())
        pygame.draw.rect(self.game, self.frame_color, self.right_position())


class Safe_Zone_Water(object):
    """
    Klasa odpowiedzialna za generowanie:
    -zoltych bezpiecznych stref
    -wody
    game - rozmiar ekranu
    """
    def __init__(self, game):
        self.size = pygame.Vector2(map_size()[0], 30)
        self.water_size = pygame.Vector2(map_size()[0], 240)
        self.game = game
        self.zone_color = zone_color()
        self.water_color = water_color()

    def water(self):
        begin = pygame.Vector2(10, 140)
        rect_position = pygame.Rect(begin, self.water_size)
        return rect_position

    def top_position(self):
        begin = pygame.Vector2(10, 110)
        rect_position = pygame.Rect(begin, self.size)
        return rect_position

    def middle_position(self):
        begin = pygame.Vector2(10, 380)
        rect_position = pygame.Rect(begin, self.size)
        return rect_position

    def bottom_position(self):
        begin = pygame.Vector2(10, 680)
        rect_position = pygame.Rect(begin, self.size)
        return rect_position

    def draw(self):
        pygame.draw.rect(self.game, self.water_color, self.water())
        pygame.draw.rect(self.game, self.zone_color, self.top_position())
        pygame.draw.rect(self.game, self.zone_color, self.bottom_position())
        pygame.draw.rect(self.game, self.zone_color, self.middle_position())
