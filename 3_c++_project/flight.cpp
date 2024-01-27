#include "flight.h"

using namespace std;

Flight::Flight(
        unsigned int id,
        Date date,
        City departure,
        City arrival,  
        shared_ptr<Planes> plane,
        vector<Stewardess> stewardess,
        vector<Pilot> pilot,
        vector<LuggageMan> luggage_man,
        vector<Other> other,
        vector<FirstClass> first_class,
        vector<SecondClass> second_class) :
        id(id), date(date), departure(departure),
        arrival(arrival), plane(move(plane)), 
        stewardess(stewardess), pilot(pilot), 
        luggage_man(luggage_man), other(other), 
        first_class(first_class), second_class(second_class) {}


unsigned int Flight::get_id() {
    return id;
}

Date Flight::get_date() {
    return date;
}

City Flight::get_arrival() {
    return arrival;
}

City Flight::get_departure() {
    return departure;
}

void Flight::set_id(unsigned int new_id) {
    id = new_id;
}

void Flight::set_date(Date new_date) {
    date = new_date;
}

void Flight::set_arrival(City new_arrival) {
    arrival = new_arrival;
}

void Flight::set_departure(City new_departure) {
    departure = new_departure;
}

Planes& Flight::get_plane() {
    return *plane;
}

vector<Stewardess> Flight::get_stewardess() {
    return stewardess;
}

vector<Pilot> Flight::get_pilot() {
    return pilot;
}

vector<LuggageMan> Flight::get_luggage_man() {
    return luggage_man;
}

vector<Other> Flight::get_other() {
    return other;
}

vector<FirstClass> Flight::get_first_class() {
    return first_class;
}

vector<SecondClass> Flight::get_second_class() {
    return second_class;
}

// sekcja dodawania pracownikow
void Flight::add_stewardess(Stewardess new_stewardess) {
    stewardess.push_back(new_stewardess);
}

void Flight::add_pilot(Pilot new_pilot) {
    pilot.push_back(new_pilot);
}

void Flight::add_luggage_man(LuggageMan new_luggage_man) {
    luggage_man.push_back(new_luggage_man);
}

void Flight::add_other(Other new_other) {
    other.push_back(new_other);
}


// sekcja dodawania pasazerow
void Flight::add_first_class(FirstClass new_first_class) {
    if (!empty_seat()) {
        throw NoSpaceException();
    }
    if(check_passenger(new_first_class.getPesel())) {
        throw InvalidPassenger();
    }
    first_class.push_back(new_first_class);
}

void Flight::add_second_class(SecondClass new_second_class) {
    if (!empty_seat()) {
        throw NoSpaceException();
    }
    if(check_passenger(new_second_class.getPesel())) {
        throw InvalidPassenger();
    }
    second_class.push_back(new_second_class);
}

void Flight::set_plane(shared_ptr<Planes> new_plane) {
    plane = move(new_plane);
}

int Flight::passengers_number() {
    return first_class.size()+second_class.size();
}

int Flight::first_class_num() {
    return first_class.size();
}

int Flight::second_class_num() {
    return second_class.size();
}

int Flight::stewardess_num() {
    return stewardess.size();
}

int Flight::pilot_num() {
    return pilot.size();
}

int Flight::luggage_num() {
    return luggage_man.size();
}

int Flight::other_num() {
    return other.size();
}

int Flight::workers_number() {
    return stewardess.size()+pilot.size()
    +luggage_man.size()+other.size();
}

bool Flight::check_passenger(string pesel) {
    for (auto passenger : first_class) {
        if (passenger.getPesel() == pesel) {
            return true;
        }
    }
    for (auto passenger : second_class) {
        if (passenger.getPesel() == pesel) {
            return true;
        }
    }
    return false;
}

void Flight::remove_passenger(string pesel) {
    for(int i=0 ; i<first_class.size() ; i++) {
        if(first_class[i].getPesel() == pesel) {
            first_class.erase(first_class.begin()+i);
            return;
        }
    }
    for(int i=0 ; i<second_class.size() ; i++) {
        if(second_class[i].getPesel() == pesel) {
            second_class.erase(second_class.begin()+i);
            return;
        }
    }
}

bool Flight::empty_seat() {
    if (passengers_number() >= plane->get_sitting_places()) {
        return false;
    }
    return true;
}