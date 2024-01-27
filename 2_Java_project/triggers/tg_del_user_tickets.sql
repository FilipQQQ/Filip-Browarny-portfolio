
--------------------------------------------------------
--  DDL for Trigger TG_DEL_USER_TICKETS
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE TRIGGER "Z13"."TG_DEL_USER_TICKETS" 
BEFORE DELETE ON users
FOR EACH ROW
BEGIN
    DELETE FROM tickets WHERE user_id = :old.user_id;
END;

/
ALTER TRIGGER "Z13"."TG_DEL_USER_TICKETS" ENABLE;
