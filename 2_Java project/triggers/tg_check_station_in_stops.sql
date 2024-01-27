
--------------------------------------------------------
--  DDL for Trigger TG_CHECK_STATION_IN_STOPS
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE TRIGGER "Z13"."TG_CHECK_STATION_IN_STOPS" 
BEFORE DELETE ON stations
FOR EACH ROW
DECLARE
    stops_cnt   NUMBER;
BEGIN
    SELECT COUNT(*) INTO stops_cnt FROM stops WHERE station_id = :old.station_id;
    IF stops_cnt > 0 THEN
        raise_application_error(-20001, 'Station cannot be deleted, there are stops on this station');
    END IF;
END;
/
ALTER TRIGGER "Z13"."TG_CHECK_STATION_IN_STOPS" ENABLE;
