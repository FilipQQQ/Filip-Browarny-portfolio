
--------------------------------------------------------
--  DDL for Function TRAVEL_TIME
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE FUNCTION "Z13"."TRAVEL_TIME" (ride_id NUMBER, stop1 NUMBER, stop2 NUMBER)
RETURN NUMBER
AS
    time_min                NUMBER;
    stop1_dep               TIMESTAMP(6);
    stop2_arr               TIMESTAMP(6);
    ride_id_check           NUMBER;
BEGIN    
    SELECT COUNT(*) INTO ride_id_check FROM connections WHERE connection_id = ride_id;
    IF ride_id_check != 1 THEN
        raise_application_error(-20001, 'Connection with given ID does not exist');
    END IF;

    SELECT departure_hour INTO stop1_dep FROM stops WHERE connection_id = ride_id AND station_id = stop1;
    SELECT arrival_hour INTO stop2_arr FROM stops WHERE connection_id = ride_id AND station_id = stop2;

    time_min := timestamp_diff(stop1_dep, stop2_arr);

    IF stop1_dep IS NULL OR stop2_arr IS NULL THEN
        raise_application_error(-20001,'Invalid station id, one (or both) is not on route');
    ELSIF time_min <= 0 OR time_min IS NULL THEN
        raise_application_error(-20001,'Invalid station order, the first station must be on route before the second');    
    END IF;

    RETURN time_min;
END;

/
