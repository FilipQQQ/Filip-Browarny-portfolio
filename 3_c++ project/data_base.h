#ifndef DATA_BASE_H
#define DATA_BASE_H
#include "exceptions.h"
#include <fstream>
#include <string>
#include <memory>
#include <list>
#include "flight.h"
#include "passenger_classes.h"
#include <random>

class DataBase
{
private:
    std::string people_path;
    std::string workers_path;
    std::string planes_path;
    std::string flights_path;
    std::vector<Other> other;
    std::vector<LuggageMan> luggage_man;
    std::vector<Stewardess> stewardess;
    std::vector<Pilot> pilot;
    std::vector<FirstClass> first;
    std::vector<SecondClass> second;
    std::vector<Person> people;
    std::vector<std::shared_ptr<Passenger>> passengers;
    std::vector<Flight> flights;

    void import_passengers();
    void import_workers();
    void import_flights();

    std::shared_ptr<Planes> create_plane(std::string p_type, std::string p_name);
    void assignPassengers();

public:
    DataBase(
        std::string people_path,
        std::string workers_path,
        std::string flights_path);

    std::vector<Flight> get_flights();
    std::vector<std::shared_ptr<Passenger>> get_passengers();

    friend class Interface;
};

#endif