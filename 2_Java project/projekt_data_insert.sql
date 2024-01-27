-- stacje
SELECT connection_station FROM STATIONS WHERE NAME = 'Wroc³aw G³ówny';

commit;
update "Z13"."STATIONS" set connection_station = 0;

INSERT INTO stations VALUES (1, 'Wroc³aw G³ówny');
INSERT INTO stations VALUES (2, 'Warszawa Centralna');
INSERT INTO stations VALUES (3, 'Wroc³aw Miko³ajów');
INSERT INTO stations VALUES (4, 'Wroc³aw Nadodrze');
INSERT INTO stations VALUES (5, 'Oleœnica Rataje');
INSERT INTO stations VALUES (6, 'Milicz');
INSERT INTO stations VALUES (7, 'Krotoszyn');
INSERT INTO stations VALUES (8, 'Ostrów Wielkopolski');
INSERT INTO stations VALUES (9, 'Kalisz');
INSERT INTO stations VALUES (10, 'Sieradz');
INSERT INTO stations VALUES (11, 'Zduñska Wola');
INSERT INTO stations VALUES (12, '£ask');
INSERT INTO stations VALUES (13, 'Pabianice');
INSERT INTO stations VALUES (14, '£ódŸ Retkinia');
INSERT INTO stations VALUES (15, '£ódŸ Chojny');
INSERT INTO stations VALUES (16, '£ódŸ Widzew');
INSERT INTO stations VALUES (17, 'Koluszki');
INSERT INTO stations VALUES (18, 'Skierniewice');
INSERT INTO stations VALUES (19, '¯yrardów');
INSERT INTO stations VALUES (20, 'Warszawa Zachodnia');

INSERT INTO stations VALUES (27, 'Kraków G³ówny');
INSERT INTO stations VALUES (21, 'Warszawa Wschodnia');
INSERT INTO stations VALUES (22, 'Gdañsk G³ówny');
INSERT INTO stations VALUES (23, 'Gdañsk Wrzeszcz');
INSERT INTO stations VALUES (24, 'Gdañsk Oliwa');
INSERT INTO stations VALUES (25, 'Sopot');
INSERT INTO stations VALUES (26, 'Gdynia G³ówna');

INSERT INTO stations VALUES (28, 'Przemyœl G³ówny');
INSERT INTO stations VALUES (29, 'Jaros³aw');
INSERT INTO stations VALUES (30, 'Przeworsk');
INSERT INTO stations VALUES (31, '£añcut');
INSERT INTO stations VALUES (32, 'Rzeszów G³ówny');
INSERT INTO stations VALUES (33, 'Dêbnica');
INSERT INTO stations VALUES (34, 'Tarnów');
INSERT INTO stations VALUES (35, 'Brzesko Okocim');
INSERT INTO stations VALUES (36, 'Bochnia');
INSERT INTO stations VALUES (37, 'Kraków P³aszów');
INSERT INTO stations VALUES (39, 'Krzeszowice');
INSERT INTO stations VALUES (40, 'Trzebnia');
INSERT INTO stations VALUES (41, 'Jaworzno Szczakowa');
INSERT INTO stations VALUES (42, 'Mys³owice');
INSERT INTO stations VALUES (43, 'Katowice');
INSERT INTO stations VALUES (44, 'Zabrze');
INSERT INTO stations VALUES (45, 'Gliwice');
INSERT INTO stations VALUES (46, 'Legnica');
INSERT INTO stations VALUES (47, 'Lubin');
INSERT INTO stations VALUES (48, 'G³ogów');
INSERT INTO stations VALUES (49, 'Bytom Odrzañski');
INSERT INTO stations VALUES (50, 'Nowa Sól');
INSERT INTO stations VALUES (51, 'Zielona Góra G³ówna');
INSERT INTO stations VALUES (52, 'Rzepin');
INSERT INTO stations VALUES (53, 'Kostrzyn');
INSERT INTO stations VALUES (54, 'Gryfino');
INSERT INTO stations VALUES (55, 'Szczecin G³ówny');
INSERT INTO stations VALUES (56, 'Szczecin D¹bie');
INSERT INTO stations VALUES (57, 'Goleniów');
INSERT INTO stations VALUES (58, 'Wysoka Kamieñska');
INSERT INTO stations VALUES (59, 'Miêdzyzdroje');
INSERT INTO stations VALUES (60, 'Œwinoujœcie');
INSERT INTO stations VALUES (default, 'Œwidnica Miasto', 0);

-- u¿ytkownicy

INSERT INTO users VALUES (1, 'Adam123', '123456', 'Adam', 'Nowak');
INSERT INTO users VALUES (default, 'Adamop123', '123456', 'Adaopm', 'Nowaopk');
INSERT INTO users VALUES (default, 'Adamop1234', '1234564', 'Adaopm44', 'Nowao4pk', null);



-- po³¹czenia

INSERT INTO connections VALUES (1, 1, 2);
INSERT INTO connections VALUES (2, 1, 2);
INSERT INTO connections VALUES (3, 20, 26);
INSERT INTO connections VALUES (4, 28, 44);
INSERT INTO connections VALUES (5, 5, 61);

-- przystanki (stops)

INSERT INTO stops VALUES (1, 1, '2000-01-01 6:37:00', '2000-01-01 6:37:00');
INSERT INTO stops VALUES (1, 2, '2000-01-01 11:47:00', '2000-01-01 11:47:00');
INSERT INTO stops VALUES (1, 3, '2000-01-01 6:42:00', '2000-01-01 6:43:00');
INSERT INTO stops VALUES (1, 4, '2000-01-01 6:47:00', '2000-01-01 6:48:00');
INSERT INTO stops VALUES (1, 5, '2000-01-01 7:07:00', '2000-01-01 7:08:00');
INSERT INTO stops VALUES (1, 6, '2000-01-01 7:33:00', '2000-01-01 7:35:00');
INSERT INTO stops VALUES (1, 7, '2000-01-01 7:50:00', '2000-01-01 7:52:00');
INSERT INTO stops VALUES (1, 8, '2000-01-01 8:15:00', '2000-01-01 8:22:00');
INSERT INTO stops VALUES (1, 9, '2000-01-01 8:38:00', '2000-01-01 8:39:00');
INSERT INTO stops VALUES (1, 10, '2000-01-01 9:10:00', '2000-01-01 9:11:00');
INSERT INTO stops VALUES (1, 11, '2000-01-01 9:21:00', '2000-01-01 9:22:00');
INSERT INTO stops VALUES (1, 12, '2000-01-01 9:30:00', '2000-01-01 9:31:00');
INSERT INTO stops VALUES (1, 13, '2000-01-01 9:41:00', '2000-01-01 9:42:00');
INSERT INTO stops VALUES (1, 14, '2000-01-01 9:49:00', '2000-01-01 9:50:00');
INSERT INTO stops VALUES (1, 15, '2000-01-01 9:58:00', '2000-01-01 9:59:00');
INSERT INTO stops VALUES (1, 16, '2000-01-01 10:07:00', '2000-01-01 10:17:00');
INSERT INTO stops VALUES (1, 17, '2000-01-01 10:31:00', '2000-01-01 10:32:00');
INSERT INTO stops VALUES (1, 18, '2000-01-01 10:52:00', '2000-01-01 10:53:00');
INSERT INTO stops VALUES (1, 19, '2000-01-01 11:04:00', '2000-01-01 11:05:00');
INSERT INTO stops VALUES (1, 20, '2000-01-01 11:41:00', '2000-01-01 11:42:00');

INSERT INTO stops VALUES (2, 1, '2000-01-01 10:37:00', '2000-01-01 10:37:00');
INSERT INTO stops VALUES (2, 2, '2000-01-01 15:47:00', '2000-01-01 15:47:00');
INSERT INTO stops VALUES (2, 3, '2000-01-01 10:42:00', '2000-01-01 10:43:00');
INSERT INTO stops VALUES (2, 4, '2000-01-01 10:47:00', '2000-01-01 10:48:00');
INSERT INTO stops VALUES (2, 5, '2000-01-01 11:07:00', '2000-01-01 11:08:00');
INSERT INTO stops VALUES (2, 6, '2000-01-01 11:33:00', '2000-01-01 11:35:00');
INSERT INTO stops VALUES (2, 7, '2000-01-01 11:50:00', '2000-01-01 11:52:00');
INSERT INTO stops VALUES (2, 8, '2000-01-01 12:15:00', '2000-01-01 12:22:00');
INSERT INTO stops VALUES (2, 9, '2000-01-01 12:38:00', '2000-01-01 12:39:00');
INSERT INTO stops VALUES (2, 10, '2000-01-01 13:10:00', '2000-01-01 13:11:00');
INSERT INTO stops VALUES (2, 11, '2000-01-01 13:21:00', '2000-01-01 13:22:00');
INSERT INTO stops VALUES (2, 12, '2000-01-01 13:30:00', '2000-01-01 13:31:00');
INSERT INTO stops VALUES (2, 13, '2000-01-01 13:41:00', '2000-01-01 13:42:00');
INSERT INTO stops VALUES (2, 14, '2000-01-01 13:49:00', '2000-01-01 13:50:00');
INSERT INTO stops VALUES (2, 15, '2000-01-01 13:58:00', '2000-01-01 13:59:00');
INSERT INTO stops VALUES (2, 16, '2000-01-01 14:07:00', '2000-01-01 14:17:00');
INSERT INTO stops VALUES (2, 17, '2000-01-01 14:31:00', '2000-01-01 14:32:00');
INSERT INTO stops VALUES (2, 18, '2000-01-01 14:52:00', '2000-01-01 14:53:00');
INSERT INTO stops VALUES (2, 19, '2000-01-01 15:04:00', '2000-01-01 15:05:00');
INSERT INTO stops VALUES (2, 20, '2000-01-01 15:41:00', '2000-01-01 15:42:00');

INSERT INTO stops VALUES (3, 27, '2000-01-01 15:50:00', '2000-01-01 15:50:00');
INSERT INTO stops VALUES (3, 20, '2000-01-01 18:15:00', '2000-01-01 18:16:00');
INSERT INTO stops VALUES (3, 2, '2000-01-01 18:21:00', '2000-01-01 18:26:00');
INSERT INTO stops VALUES (3, 21, '2000-01-01 18:32:00', '2000-01-01 18:34:00');
INSERT INTO stops VALUES (3, 22, '2000-01-01 20:59:00', '2000-01-01 21:01:00');
INSERT INTO stops VALUES (3, 23, '2000-01-01 21:06:00', '2000-01-01 21:07:00');
INSERT INTO stops VALUES (3, 24, '2000-01-01 21:11:00', '2000-01-01 21:12:00');
INSERT INTO stops VALUES (3, 25, '2000-01-01 21:16:00', '2000-01-01 21:17:00');
INSERT INTO stops VALUES (3, 26, '2000-01-01 21:25:00', '2000-01-01 21:25:00');

INSERT INTO stops VALUES (4, 27, '2000-01-01 21:59:00', '2000-01-01 21:59:00');
INSERT INTO stops VALUES (4, 28, '2000-01-01 18:51:00', '2000-01-01 18:51:00');
INSERT INTO stops VALUES (4, 29, '2000-01-01 19:15:00', '2000-01-01 19:17:00');
INSERT INTO stops VALUES (4, 30, '2000-01-01 19:27:00', '2000-01-01 19:29:00');
INSERT INTO stops VALUES (4, 31, '2000-01-01 19:55:00', '2000-01-01 19:56:00');
INSERT INTO stops VALUES (4, 32, '2000-01-01 20:09:00', '2000-01-01 20:11:00');
INSERT INTO stops VALUES (4, 33, '2000-01-01 20:39:00', '2000-01-01 20:40:00');
INSERT INTO stops VALUES (4, 34, '2000-01-01 20:57:00', '2000-01-01 20:59:00');
INSERT INTO stops VALUES (4, 35, '2000-01-01 21:14:00', '2000-01-01 21:15:00');
INSERT INTO stops VALUES (4, 36, '2000-01-01 21:23:00', '2000-01-01 21:25:00');
INSERT INTO stops VALUES (4, 37, '2000-01-01 21:50:00', '2000-01-01 21:51:00');
INSERT INTO stops VALUES (4, 39, '2000-01-01 22:38:00', '2000-01-01 22:40:00');
INSERT INTO stops VALUES (4, 40, '2000-01-01 22:51:00', '2000-01-01 22:52:00');
INSERT INTO stops VALUES (4, 41, '2000-01-01 23:03:00', '2000-01-01 23:05:00');
INSERT INTO stops VALUES (4, 42, '2000-01-01 23:15:00', '2000-01-01 23:17:00');
INSERT INTO stops VALUES (4, 43, '2000-01-01 23:29:00', '2000-01-01 23:39:00');
INSERT INTO stops VALUES (4, 44, '2000-01-01 23:54:00', '2000-01-01 23:56:00');

INSERT INTO stops VALUES (5, 5, '2000-01-01 12:12:00', '2000-01-01 12:12:00');
INSERT INTO stops VALUES (5, 61, '2000-01-01 12:54:00', '2000-01-01 12:56:00');


-- przejazdy rides
INSERT INTO rides VALUES (1, 1, '2022-12-01');
INSERT INTO rides VALUES (2, 2, '2022-12-01');
INSERT INTO rides VALUES (3, 3, '2022-12-01');
INSERT INTO rides VALUES (default, 4, '2022-12-01');
INSERT INTO rides VALUES (default, 2, '2022-12-01');

--tickets

INSERT INTO tickets VALUES (1, 1, '2022-12-01', 5, 9, 1, Null, 1, 12, 12.2);
INSERT INTO tickets VALUES (default, 1, '2022-12-01', 5, 9, 1, Null, 1, 12, 12.2);
commit;


--sits

INSERT INTO sits VALUES (default, 1, 12, 21, null);
commit;
INSERT INTO rides VALUES (default, 2, '2022-12-01'); 
select max(ride_id) from rides

















