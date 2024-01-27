-- Test deletion of a connection
DECLARE
    v_connections_cnt NUMBER;
    v_stops_cnt NUMBER;
    v_tickets_cnt NUMBER;
BEGIN
INSERT INTO connections (CONNECTION_ID, DEPARTURE_STATION, ARRIVAL_STATION)
    VALUES (992923, 1, 2);
INSERT INTO stops (CONNECTION_ID, STATION_ID, ARRIVAL_HOUR, DEPARTURE_HOUR)
    VALUES (992923, 1, '1970-01-01 10:00:00', '1970-01-01 12:00:00');
INSERT INTO stops (CONNECTION_ID, STATION_ID, ARRIVAL_HOUR, DEPARTURE_HOUR)
    VALUES (992923, 2, '1970-01-01 12:00:00', '1970-01-01 13:00:00');
INSERT INTO tickets (TICKET_ID, CONNECTION_ID, TICKET_DATE, ID_DEPARTURE_STATION, ID_ARRIVAL_STATION,
                     USER_ID, DISCOUNT_ID, CAR_ID, SEAT_ID, PRICE)
    VALUES (default, 992923, '2023-01-10', 1, 2, 183, 1, 1, 10, 2403);
SELECT COUNT(*) INTO v_connections_cnt FROM connections WHERE CONNECTION_ID = 992923;
SELECT COUNT(*) INTO v_stops_cnt FROM stops WHERE connection_id = 992923;
SELECT COUNT(*) INTO v_tickets_cnt FROM tickets WHERE connection_id = 992923;
IF v_connections_cnt = 1 AND v_stops_cnt = 2 AND v_tickets_cnt = 1 THEN
        DELETE FROM connections WHERE CONNECTION_ID = 992923;
        SELECT COUNT(*) INTO v_connections_cnt FROM connections WHERE CONNECTION_ID = 992923;
        SELECT COUNT(*) INTO v_stops_cnt FROM stops WHERE connection_id = 992923;
        SELECT COUNT(*) INTO v_tickets_cnt FROM tickets WHERE connection_id = 992923;
        IF v_connections_cnt = 0 AND v_stops_cnt = 0 AND v_tickets_cnt = 0 THEN
            DBMS_OUTPUT.PUT_LINE('TEST PASSED');
        ELSE
            DBMS_OUTPUT.PUT_LINE('TEST FAILED');
        end if;
ELSE
        dbms_output.put_line('TEST FAILED');
END IF;
END;
/

-- test deletion of connection with no tickets and stops
DECLARE
    v_connections_cnt NUMBER;
BEGIN
    INSERT INTO connections (CONNECTION_ID, DEPARTURE_STATION, ARRIVAL_STATION)
    VALUES (992923, 1, 2);
    SELECT COUNT(*) INTO v_connections_cnt FROM connections WHERE CONNECTION_ID = 992923;
    IF v_connections_cnt = 1 THEN
        DELETE FROM connections WHERE CONNECTION_ID = 992923;
        SELECT COUNT(*) INTO v_connections_cnt FROM connections WHERE CONNECTION_ID = 992923;
        IF v_connections_cnt = 0 THEN
            DBMS_OUTPUT.PUT_LINE('TEST PASSED');
        ELSE
            DBMS_OUTPUT.PUT_LINE('TEST FAILED');
        end if;
    ELSE
        dbms_output.put_line('TEST FAILED');
    END IF;
END;
/



