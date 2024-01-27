-- Test deletion of Test station without stops
DECLARE
v_stations_cnt NUMBER;
BEGIN
INSERT INTO stations (name) VALUES ('Test Station');
DELETE FROM stations WHERE name = 'Test Station';
SELECT COUNT(*) INTO v_stations_cnt FROM stations WHERE name = 'Test Station';
IF v_stations_cnt = 0 THEN
        dbms_output.put_line('TEST PASSED');
ELSE
        dbms_output.put_line('TEST FAILED');
END IF;
END;


-- Test deletion of Test station with stops
DECLARE
v_error_code NUMBER;
BEGIN
INSERT INTO stations (STATION_ID, name) VALUES (default, 'Test Station');
INSERT INTO stops (CONNECTION_ID, STATION_ID, ARRIVAL_HOUR, DEPARTURE_HOUR) VALUES (1,
                    (SELECT station_id FROM stations WHERE name = 'Test Station'),
                    '1970-01-01 08:00:00',
                    '1970-01-01 09:00:00');
DELETE FROM stations WHERE name = 'Test Station';
EXCEPTION
    WHEN OTHERS THEN
        v_error_code := SQLCODE;
IF v_error_code = -20001 THEN
  dbms_output.put_line('TEST PASSED');
ELSE
  dbms_output.put_line('TEST FAILED');
END IF;
    DELETE FROM stops WHERE STATION_ID = (SELECT station_id FROM stations WHERE name = 'Test Station');
    DELETE FROM stations WHERE name = 'Test Station';
END;


-- Test deletion of real station with stops
DECLARE
    v_error_code NUMBER;
BEGIN
    DELETE FROM stations WHERE name = 'Białystok';
EXCEPTION
    WHEN OTHERS THEN
        v_error_code := SQLCODE;
        IF v_error_code = -20001 THEN
            dbms_output.put_line('TEST PASSED');
        ELSE
            dbms_output.put_line('TEST FAILED');
        END IF;
END;