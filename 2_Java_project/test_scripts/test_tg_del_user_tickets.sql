-- Test deletion of a user with no tickets
DECLARE
    v_users_cnt NUMBER;
    v_tickets_cnt NUMBER;
BEGIN
    DELETE FROM USERS WHERE login = 'kakkva';
    INSERT INTO users (login, PASSWORD, name, surname, email)
    VALUES ('kakkva', 'asd2', 'Test', 'user', 'testuser@email.com');
    SELECT COUNT(*) INTO v_users_cnt FROM users WHERE login = 'kakkva';
    SELECT COUNT(*) INTO v_tickets_cnt FROM tickets
        WHERE user_id = (SELECT user_id FROM users WHERE login = 'kakkva');
    IF v_users_cnt = 1 AND v_tickets_cnt = 0 THEN
        DELETE FROM USERS WHERE login = 'kakkva';
        SELECT COUNT(*) INTO v_users_cnt FROM users WHERE login = 'kakkva';
        SELECT COUNT(*) INTO v_tickets_cnt FROM tickets WHERE user_id = (SELECT user_id FROM users WHERE login = 'kakkva');
        IF v_users_cnt = 0 AND v_tickets_cnt = 0 THEN
            dbms_output.put_line('TEST PASSED');
        ELSE
            dbms_output.put_line('TEST FAILED');
        END IF;
    ELSE
            dbms_output.put_line('TEST FAILED');
    END IF;
END;
/

-- Test deletion of a user with few tickets
DECLARE
    v_users_cnt NUMBER;
    v_tickets_cnt NUMBER;
BEGIN
    DELETE FROM USERS WHERE login = 'kakkva';
    INSERT INTO users (login, PASSWORD, name, surname, email)
        VALUES ('kakkva', 'asd2', 'Test', 'user', 'testuser@email.com');
    INSERT INTO tickets (TICKET_ID, CONNECTION_ID, TICKET_DATE, ID_DEPARTURE_STATION, ID_ARRIVAL_STATION,
                         USER_ID, DISCOUNT_ID, CAR_ID, SEAT_ID, PRICE)
    VALUES (default, 1, '2023-01-12', 1, 2, (SELECT USER_ID FROM USERS WHERE login = 'kakkva'), 1, 1, 10, 2403);
    INSERT INTO tickets (TICKET_ID, CONNECTION_ID, TICKET_DATE, ID_DEPARTURE_STATION, ID_ARRIVAL_STATION,
                         USER_ID, DISCOUNT_ID, CAR_ID, SEAT_ID, PRICE)
    VALUES (default, 1, '2023-01-10', 1, 2, (SELECT USER_ID FROM USERS WHERE login = 'kakkva'), 1, 1, 10, 2403);
    SELECT COUNT(*) INTO v_users_cnt FROM users WHERE login = 'kakkva';
    SELECT COUNT(*) INTO v_tickets_cnt FROM tickets
            WHERE user_id = (SELECT user_id FROM users WHERE login = 'kakkva');
    IF v_users_cnt = 1 AND v_tickets_cnt = 2 THEN
        DELETE FROM USERS WHERE login = 'kakkva';
        SELECT COUNT(*) INTO v_users_cnt FROM users WHERE login = 'kakkva';
        SELECT COUNT(*) INTO v_tickets_cnt FROM tickets
                WHERE user_id = (SELECT user_id FROM users WHERE login = 'kakkva');
        IF v_users_cnt = 0 AND v_tickets_cnt = 0 THEN
            dbms_output.put_line('TEST PASSED');
        ELSE
            dbms_output.put_line('TEST FAILED');
        END IF;
    ELSE
        dbms_output.put_line('TEST FAILED');
    END IF;
END;
/