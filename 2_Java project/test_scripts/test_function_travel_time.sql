-- 16104: Białystok -> Warszawa Centralna (14:10 -> 16:45)
DECLARE
travel_time NUMBER;
time_diff NUMBER;
BEGIN
SELECT "Z13"."TRAVEL_TIME"(16104, (SELECT station_id FROM STATIONS WHERE name = 'Białystok'),
    (SELECT station_id FROM STATIONS WHERE name = 'Warszawa Centralna')
    ) INTO travel_time FROM dual;
SELECT TIMESTAMP_DIFF((SELECT DEPARTURE_HOUR FROM CONNECTION_16104 WHERE NAME='Białystok'),
                      (SELECT ARRIVAL_HOUR FROM CONNECTION_16104 WHERE NAME='Warszawa Centralna'))
    INTO time_diff FROM dual;
    IF time_diff = travel_time
        THEN DBMS_OUTPUT.PUT_LINE('TEST PASSED');
    ELSE DBMS_OUTPUT.PUT_LINE('TEST FAILED');
    END IF;
END;

-- 16104: Warszawa Centralna -> Wrocław Główny (16:53 -> 21:38)
DECLARE
    travel_time NUMBER;
    time_diff NUMBER;
BEGIN
    SELECT "Z13"."TRAVEL_TIME"(16104, (SELECT station_id FROM STATIONS WHERE name = 'Warszawa Centralna'),
                               (SELECT station_id FROM STATIONS WHERE name = 'Wrocław Główny')
               ) INTO travel_time FROM dual;
    SELECT TIMESTAMP_DIFF((SELECT DEPARTURE_HOUR FROM CONNECTION_16104 WHERE NAME='Warszawa Centralna'),
                          (SELECT ARRIVAL_HOUR FROM CONNECTION_16104 WHERE NAME='Wrocław Główny'))
    INTO time_diff FROM dual;
    IF time_diff = travel_time
    THEN DBMS_OUTPUT.PUT_LINE('TEST PASSED');
    ELSE DBMS_OUTPUT.PUT_LINE('TEST FAILED');
    END IF;
END;

SELECT * FROM CONNECTION_5;
-- 5: Izbica -> Dęblin (08:32 -> 10:53)
DECLARE
    travel_time NUMBER;
    time_diff NUMBER;
BEGIN
    SELECT "Z13"."TRAVEL_TIME"(5, (SELECT station_id FROM STATIONS WHERE name = 'Izbica'),
                               (SELECT station_id FROM STATIONS WHERE name = 'Dęblin')
               ) INTO travel_time FROM dual;
    SELECT TIMESTAMP_DIFF((SELECT DEPARTURE_HOUR FROM CONNECTION_5 WHERE NAME='Izbica'),
                          (SELECT ARRIVAL_HOUR FROM CONNECTION_5 WHERE NAME='Dęblin'))
    INTO time_diff FROM dual;
    IF time_diff = travel_time
    THEN DBMS_OUTPUT.PUT_LINE('TEST PASSED');
    ELSE DBMS_OUTPUT.PUT_LINE('TEST FAILED');
    END IF;
END;

-- ERROR: Invalid Station Order
DECLARE
travel_time NUMBER;
BEGIN
SELECT Z13.TRAVEL_TIME(16104,
    (SELECT station_id FROM STATIONS WHERE name = 'Warszawa Centralna'),
    (SELECT station_id FROM STATIONS WHERE name = 'Białystok'))
    INTO travel_time FROM dual;
EXCEPTION
        WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE('TEST PASSED');
END;

-- ERROR: No connection with that ID found
DECLARE
    travel_time NUMBER;
BEGIN
    BEGIN
        SELECT Z13.TRAVEL_TIME(16105, 1, 2) INTO travel_time FROM dual;
    EXCEPTION
        WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE('TEST PASSED');
    END;
END;