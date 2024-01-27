
--------------------------------------------------------
--  DDL for Procedure PRINT_STATION_CONNECTIONS
--------------------------------------------------------
set define off;

  CREATE OR REPLACE EDITIONABLE PROCEDURE "Z13"."PRINT_STATION_CONNECTIONS" (p_station_id NUMBER)
AS
    v_station_name          VARCHAR2(40 BYTE);
    v_destination_id        NUMBER;
    v_destination_name      VARCHAR2(40 BYTE);
    v_departure_time        TIMESTAMP(6);
    v_time_char             VARCHAR2(40 BYTE);
    v_station_id_check      NUMBER;

    CURSOR cur_connections IS
        SELECT stations.name, c.arrival_station, stops.departure_hour
        FROM stops
        JOIN connections c ON stops.connection_id = c.connection_id
        JOIN stations ON stops.station_id = stations.station_id
        WHERE stops.station_id = p_station_id
        ORDER BY stops.departure_hour;

BEGIN   
    SELECT COUNT(*) INTO v_station_id_check FROM stations WHERE station_id = p_station_id;
    IF v_station_id_check = 0 THEN
        raise_application_error(-20001, 'Station with given ID does not exist');
    END IF;

    OPEN cur_connections;
    LOOP
        FETCH cur_connections INTO v_station_name, v_destination_id, v_departure_time;
        SELECT name INTO v_destination_name FROM stations WHERE station_id = v_destination_id;
        EXIT WHEN cur_connections%NOTFOUND;
        IF v_station_name != v_destination_name THEN
            SELECT TO_CHAR(v_departure_time, 'HH24:MI') INTO v_time_char FROM dual;
            dbms_output.put_line('Station: ' || v_station_name || 
            ', Destination: ' || v_destination_name ||
            ', Departure time: ' || v_time_char);
        END IF;
    END LOOP;
    CLOSE cur_connections;
END;

/
