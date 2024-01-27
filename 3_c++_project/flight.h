#ifndef FLIGHT_H
#define FLIGHT_H

#include <vector>
#include <string>
#include <memory>
#include <list>

#include "planes.h"
#include "passenger_classes.h"
#include "worker_classes.h"
#include "person.h"
#include "exceptions.h"


class Flight {
    private:
        unsigned int id;
        Date date;
        City departure;
        City arrival;
        std::shared_ptr<Planes> plane;
        std::vector<Stewardess> stewardess;
        std::vector<Pilot> pilot;
        std::vector<LuggageMan> luggage_man;
        std::vector<Other> other;
        std::vector<FirstClass> first_class;
        std::vector<SecondClass> second_class;
    public:
        Flight(
        unsigned int id,
        Date date,
        City departure,
        City arrival,  

        std::shared_ptr<Planes> plane,

        std::vector<Stewardess> stewardess = {},
        std::vector<Pilot> pilot = {},
        std::vector<LuggageMan> luggage_man = {},
        std::vector<Other> other = {},

        std::vector<FirstClass> first_class = {},
        std::vector<SecondClass> second_class = {});

        void set_plane(std::shared_ptr<Planes> plane);

        unsigned int get_id();
        Date get_date();
        City get_departure();
        City get_arrival();

        void set_id(unsigned int);
        void set_date(Date);
        void set_arrival(City);
        void set_departure(City);

        Planes& get_plane();
        std::vector<Pilot> get_pilot();
        std::vector<Stewardess> get_stewardess();
        std::vector<LuggageMan> get_luggage_man();
        std::vector<Other> get_other();

        std::vector<FirstClass> get_first_class();
        std::vector<SecondClass> get_second_class();
        
        void add_stewardess(Stewardess);
        void add_pilot(Pilot);
        void add_luggage_man(LuggageMan);
        void add_other(Other);

        void add_first_class(FirstClass);
        void add_second_class(SecondClass);

        int first_class_num();
        int second_class_num();
        int passengers_number();
        
        int stewardess_num();
        int pilot_num();
        int luggage_num();
        int other_num();
        int workers_number();

        void remove_passenger(std::string pesel);
        bool check_passenger(std::string pesel); 

        bool empty_seat();

};

#endif 