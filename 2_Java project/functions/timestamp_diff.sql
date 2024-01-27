
--------------------------------------------------------
--  DDL for Function TIMESTAMP_DIFF
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE FUNCTION "Z13"."TIMESTAMP_DIFF" (a TIMESTAMP, b TIMESTAMP)
RETURN NUMBER
AS
BEGIN
  RETURN EXTRACT (DAY    FROM (b-a))*24*60+
         EXTRACT (HOUR   FROM (b-a))*60+
         EXTRACT (MINUTE FROM (b-a));
END;

/
