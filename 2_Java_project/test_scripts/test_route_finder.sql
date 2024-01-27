-- bez przesiadek
select stops.connection_id connection_id, stops.station_id departure_station_id, stop2.station_id arrival_station_id
from stops cross join stops stop2 where stops.connection_id = stop2.connection_id and
stops.station_id = (select station_id from stations where name = :departure)
and stop2.station_id = (select station_id from stations where name = :destination)
    and extract(hour from stop2.arrival_hour)*60 +
        extract(minute from stop2.arrival_hour) - extract(hour from stops.departure_hour)*60 -+
            extract(minute from stops.departure_hour) > 0;

-- 1 przesiadka
    select stops.connection_id connection_id,
           stop2.station_id departure_station_id,
           stops.station_id change_station_id,
           stop3.connection_id connection2_id,
           stop4.station_id arrival_station_id
    from stops join stations st on stops.station_id = st.station_id cross join stops stop2 cross join stops stop3 cross join stops stop4
    where stops.connection_id = stop2.connection_id and stop2.station_id = (select station_id from stations where name = :departure) and
            stops.connection_id in (select stops.connection_id from stops join stations st on stops.station_id = st.station_id where st.name = :departure) and
            st.connection_station = 1 and
                        extract(hour from stop2.departure_hour)*60 + extract(minute from stop2.departure_hour) - extract(hour from stops.arrival_hour)*60 - extract(minute from stops.arrival_hour) < 0 and
                        extract(hour from stop3.departure_hour)*60 + extract(minute from stop3.departure_hour) - extract(hour from stop4.arrival_hour)*60 - extract(minute from stop4.arrival_hour) < 0 and
                        extract(hour from stops.arrival_hour)*60 + extract(minute from stops.arrival_hour) - extract(hour from stop3.departure_hour)*60 - extract(minute from stop3.departure_hour) < 0 and
            stops.station_id = stop3.station_id and
            stop3.connection_id = stop4.connection_id and
            stop4.station_id = (select station_id from stations where name = :destination) and
                        extract(hour from stop4.arrival_hour)*60 + extract(minute from stop4.arrival_hour) - extract(hour from stop2.departure_hour)*60 - extract(minute from stop2.departure_hour) < 90 +
              (select min(extract(hour from stop4.arrival_hour)*60 + extract(minute from stop4.arrival_hour) - extract(hour from stop2.departure_hour)*60 - extract(minute from stop2.departure_hour))
               from stops join stations st on stops.station_id = st.station_id cross join stops stop2 cross join stops stop3 cross join stops stop4
               where stops.connection_id = stop2.connection_id and
                       stop2.station_id = (select station_id from stations where name = :departure) and
                                   extract(hour from stop2.departure_hour)*60 + extract(minute from stop2.departure_hour) - extract(hour from stops.arrival_hour)*60 - extract(minute from stops.arrival_hour) < 0 and
                                   extract(hour from stop3.departure_hour)*60 + extract(minute from stop3.departure_hour) - extract(hour from stop4.arrival_hour)*60 - extract(minute from stop4.arrival_hour) < 0 and
                                   extract(hour from stops.arrival_hour)*60 + extract(minute from stops.arrival_hour) - extract(hour from stop3.departure_hour)*60 - extract(minute from stop3.departure_hour) < 0 and
                       stops.connection_id in
                       (select stops.connection_id from stops join stations st on stops.station_id = st.station_id where st.name = :departure)
                 and st.connection_station = 1 and TO_CHAR(stops.departure_hour,'HH24:MI') >= TO_CHAR(stop2.arrival_hour,'HH24:MI') and stops.station_id = stop3.station_id
                 and stop3.connection_id = stop4.connection_id and stop4.station_id = (select station_id from stations where name = :destination));