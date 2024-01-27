CREATE VIEW connection_5 AS
    SELECT station_id, name, ARRIVAL_HOUR, DEPARTURE_HOUR
    FROM STATIONS station JOIN STOPS stop USING(station_id)
    WHERE CONNECTION_ID = 5 order by connection_id, DEPARTURE_HOUR;

SELECT * FROM connection_5;

-- Connection 5, Zamosc -> Warszawa Centralna (7:37->12:15)
DECLARE
    time_diff NUMBER;
BEGIN
    SELECT "Z13"."TIMESTAMP_DIFF"(
        (SELECT departure_hour FROM connection_5 WHERE name = 'Zamość'),
        (SELECT arrival_hour FROM connection_5 WHERE name = 'Warszawa Centralna')
        ) INTO time_diff FROM dual;
    IF time_diff = 278
    THEN dbms_output.put_line('TEST PASSED, time: ' || time_diff);
    ELSE dbms_output.put_line('TEST FAILED');
    END IF;
END;
/

-- Connection 5, Kutno -> Kolobrzeg (14:22->20:22)
DECLARE
    time_diff NUMBER;
BEGIN
    SELECT "Z13"."TIMESTAMP_DIFF"(
                   (SELECT departure_hour FROM connection_5 WHERE name = 'Kutno'),
                   (SELECT arrival_hour FROM connection_5 WHERE name = 'Kołobrzeg')
               ) INTO time_diff FROM dual;
    IF time_diff = 360
    THEN dbms_output.put_line('TEST PASSED, time: ' || time_diff);
    ELSE dbms_output.put_line('TEST FAILED');
    END IF;
END;
/

CREATE VIEW connection_16104 AS
SELECT station_id, name, ARRIVAL_HOUR, DEPARTURE_HOUR
FROM STATIONS station JOIN STOPS stop USING(station_id)
WHERE CONNECTION_ID = 16104 order by connection_id, DEPARTURE_HOUR;

SELECT * FROM connection_16104;
-- Connection 16104, Białystok -> Małkinia (14:10->15:34)
DECLARE
    time_diff NUMBER;
BEGIN
    SELECT "Z13"."TIMESTAMP_DIFF"(
                   (SELECT departure_hour FROM connection_16104 WHERE name = 'Białystok'),
                   (SELECT arrival_hour FROM connection_16104 WHERE name = 'Małkinia')
               ) INTO time_diff FROM dual;
    IF time_diff = 84
    THEN dbms_output.put_line('TEST PASSED, time: ' || time_diff);
    ELSE dbms_output.put_line('TEST FAILED');
    END IF;
END;
/

-- Connection 16104, Warszawa Centralna -> Wrocław Główny (16:53->21:38)
DECLARE
    time_diff NUMBER;
BEGIN
    SELECT "Z13"."TIMESTAMP_DIFF"(
                   (SELECT departure_hour FROM connection_16104 WHERE name = 'Warszawa Centralna'),
                   (SELECT arrival_hour FROM connection_16104 WHERE name = 'Wrocław Główny')
               ) INTO time_diff FROM dual;
    IF time_diff = 285
    THEN dbms_output.put_line('TEST PASSED, time: ' || time_diff);
    ELSE dbms_output.put_line('TEST FAILED');
    END IF;
END;
/

-- 3000
DECLARE
    time_diff NUMBER;
BEGIN
    SELECT "Z13"."TIMESTAMP_DIFF"('2023-01-30 10:00:00', '2023-02-01 12:00:00') INTO time_diff FROM dual;
    IF time_diff = 3000
    THEN dbms_output.put_line('TEST PASSED');
    ELSE dbms_output.put_line('TEST FAILED');
    END IF;
END;
/


-- -120
DECLARE
    time_diff NUMBER;
BEGIN
    SELECT "Z13"."TIMESTAMP_DIFF"('2023-01-30 10:00:00', '2023-01-30 08:00:00') INTO time_diff FROM dual;
    IF time_diff = -120
    THEN dbms_output.put_line('TEST PASSED');
    ELSE dbms_output.put_line('TEST FAILED');
    END IF;
END;
/


-- 120
DECLARE
    time_diff NUMBER;
BEGIN
    SELECT "Z13"."TIMESTAMP_DIFF"('2023-01-30 10:00:00', '2023-01-30 12:00:00') INTO time_diff FROM dual;
    IF time_diff = 120
    THEN dbms_output.put_line('TEST PASSED');
    ELSE dbms_output.put_line('TEST FAILED');
    END IF;
END;
/