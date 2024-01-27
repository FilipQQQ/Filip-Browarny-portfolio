
--------------------------------------------------------
--  DDL for Trigger TG_CHECK_NAME_STATION
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE TRIGGER "Z13"."TG_CHECK_NAME_STATION" 
BEFORE INSERT ON stations
FOR EACH ROW
DECLARE
    stations_cnt    NUMBER;
BEGIN
    SELECT COUNT(*) INTO stations_cnt FROM stations WHERE name = :new.name;
    IF stations_cnt > 0 THEN
        raise_application_error(-20001, 'Station with this name exists already');
    END IF;
END;

/
ALTER TRIGGER "Z13"."TG_CHECK_NAME_STATION" ENABLE;
