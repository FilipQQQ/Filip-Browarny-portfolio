-- Test insertion of a new station
DECLARE
v_stations_cnt NUMBER;
BEGIN
DELETE FROM stations WHERE name='Test Station';
INSERT INTO stations (station_id, name) VALUES (default, 'Test Station');
SELECT COUNT(*) INTO v_stations_cnt FROM stations WHERE name = 'Test Station';
IF v_stations_cnt = 1 THEN
        dbms_output.put_line('TEST PASSED');
ELSE
        dbms_output.put_line('TEST FAILED');
END IF;
DELETE FROM stations WHERE name='Test Station';
END;
/

-- Test insertion of a station with the same name
DECLARE
v_error_code NUMBER;
BEGIN
INSERT INTO stations (name) VALUES ('Białystok');
EXCEPTION
    WHEN OTHERS THEN
        v_error_code := SQLCODE;
IF v_error_code = -20001 THEN
  dbms_output.put_line('TEST PASSED');
ELSE
  dbms_output.put_line('TEST FAILED');
END IF;
END;
/

-- Test insertion of a station with the same name
DECLARE
    v_error_code NUMBER;
BEGIN
    INSERT INTO stations (name) VALUES ('Wrocław Główny');
EXCEPTION
    WHEN OTHERS THEN
        v_error_code := SQLCODE;
        IF v_error_code = -20001 THEN
            dbms_output.put_line('TEST PASSED');
        ELSE
            dbms_output.put_line('TEST FAILED');
        END IF;
END;
/