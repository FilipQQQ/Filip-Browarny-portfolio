DECLARE
v_station_id NUMBER := 1; -- change to whatever station you need info about
BEGIN
    "Z13"."PRINT_STATION_CONNECTIONS"(v_station_id);
END;
/

DECLARE
    v_station_id NUMBER;
    v_station_name VARCHAR2(50) := 'Białystok'; -- change to whatever station you need info about
BEGIN
    SELECT station_id INTO v_station_id FROM STATIONS WHERE name = v_station_name;
    "Z13"."PRINT_STATION_CONNECTIONS"(v_station_id);
END;
/

-- no connection found
DECLARE
    v_station_id NUMBER := 10000;
BEGIN
    "Z13"."PRINT_STATION_CONNECTIONS"(v_station_id);
    EXCEPTION
        WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE('TEST PASSED');
END;
/

-- no connection found
DECLARE
    v_station_id NUMBER := -10000;
BEGIN
    "Z13"."PRINT_STATION_CONNECTIONS"(v_station_id);
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('TEST PASSED');
END;
/