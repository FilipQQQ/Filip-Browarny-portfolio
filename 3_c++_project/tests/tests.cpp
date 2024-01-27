#include "catch.hpp"
#include "../date.h"
#include "../price.h"
#include "../person.h"
#include "../passenger_classes.h"
#include "../worker_classes.h"
#include "../planes.h"
#include "../flight.h"

#include "../exceptions.h"
#include "../ticket.h"
#include "../city.h"

using namespace std;
#include <sstream>

TEST_CASE("Simple classes tests", "[tests]") 
{

    SECTION("test for date class")
    {
        Date date1(21, 2, 2020);
        Date date2(23, 2, 2020);
        Date date3(23, 2, 2020);
        Date date4(31, 3, 2016);
        CHECK(date1.getDay() == 21);
        CHECK(date1.getMonth() == 2);
        CHECK(date1.getYear() == 2020);

        //przeciazenia operatorow
        CHECK((date1 < date2) == true);
        CHECK((date1 > date2) == false);
        CHECK((date2 >= date3) == true);
        CHECK((date3 >= date2) == true);
        CHECK((date3 <= date1) == false);

        date3.setDay(25);
        date3.setMonth(4);
        date3.setYear(2021);
        CHECK(date3.getDay() == 25);
        CHECK(date3.getMonth() == 4);
        CHECK(date3.getYear() == 2021);

        CHECK_THROWS(date2.setDay(30));
        CHECK_THROWS(date4.setMonth(4));
        CHECK_THROWS(date4.setYear(2015));

        Date date5(31, 12, 2020);
        Date date6(31, 3, 2000);
        Date date7(30, 9, 2019);
        date5++;
        date6++;
        date7++;

        CHECK(date5.getDay() == 1);
        CHECK(date5.getMonth() == 1);
        CHECK(date5.getYear() == 2021);

        CHECK(date6.getDay() == 1);
        CHECK(date6.getMonth() == 4);
        CHECK(date6.getYear() == 2000);

        CHECK(date7.getDay() == 1);
        CHECK(date7.getMonth() == 10);
        CHECK(date7.getYear() == 2019);


    }

    SECTION("person tests")
    {
        Person person("kuba", "kowalski", "12345678910");
        CHECK(person.getName() == "kuba");
        CHECK(person.getSurname() == "kowalski");
        CHECK(person.getPesel() == "12345678910");

        person.setName("anna");
        person.setSurname("kowalska");
        person.setPesel("09876543210");
        CHECK(person.getName() == "anna");
        CHECK(person.getSurname() == "kowalska");
        CHECK(person.getPesel() == "09876543210");

        CHECK_THROWS(person.setPesel("abcdef"));
        CHECK_THROWS(person.setPesel("1234556"));
        CHECK_THROWS(person.setPesel("1234556ad09"));
    }
    SECTION("price tests")
    {
        Price p1(10, 25);
        Price p2(20, 50);
        Price p3(20, 5);
        Price p4(20, 5);

        CHECK(p1.getZl() == 10);
        CHECK(p2.getZl() == 20);
        CHECK(p3.getZl() == 20);

        CHECK(p1.getGr() == 25);
        CHECK(p2.getGr() == 50);
        CHECK(p3.getGr() == 5);

        CHECK((p1 < p2) == true);
        CHECK((p1 <= p2) == true);

        CHECK((p2 > p3) == true);
        CHECK((p2 >= p3) == true);

        CHECK((p2 != p3) == true);
        CHECK((p2 == p3) == false);
        CHECK((p4 == p3) == true);

        Price p5 = p3 + p4;
        CHECK(p5.getZl() == 40);
        CHECK(p5.getGr() == 10);

        p3 += p4;
        CHECK(p3.getZl() == 40);
        CHECK(p3.getGr() == 10);

        Price p6 = p2 * 0.5f;
        CHECK(p6.getZl() == 10);
        CHECK(p6.getGr() == 25);

        unsigned int x = 2;
        Price p7 = p2 * x;
        CHECK(p7.getZl() == 41);
        CHECK(p7.getGr() == 0);
    }
    SECTION("ticket tests")
    {
        FirstClassTicket first1(City("Dubai"), City("Warsaw"), Date(12, 12, 2020), 1);
        CHECK(first1.getDate().getDay() == 12);
        CHECK(first1.getDate().getMonth() == 12);
        CHECK(first1.getDate().getYear() == 2020);
        CHECK(first1.getNumber() == 1);
        CHECK(first1.getSource().getName() == "Dubai");
        CHECK(first1.getDestination().getName() == "Warsaw");


        SecondClassTicket second1(City("Berlin"), City("Warsaw"), Date(30, 12, 2021), 2);
        CHECK(second1.getDate().getDay() == 30);
        CHECK(second1.getDate().getMonth() == 12);
        CHECK(second1.getDate().getYear() == 2021);
        CHECK(second1.getNumber() == 2);
        CHECK(second1.getSource().getName() == "Berlin");
        CHECK(second1.getDestination().getName() == "Warsaw");
    }
    SECTION("passengers tests")
    {
        FirstClassTicket first1(City("Dubai"), City("Warsaw"), Date(12, 12, 2020), 1);
        FirstClass firstp("kuba", "kowal", "12345678910", first1);
        CHECK(firstp.getName() == "kuba");
        CHECK(firstp.getSurname() == "kowal");
        CHECK(firstp.getPesel() == "12345678910");
        CHECK(firstp.getSourceCity().getName() == "Dubai");
        CHECK(firstp.getTicket().getDate().getDay() == 12);


        SecondClassTicket ticket2(City("Berlin"), City("Warsaw"), Date(12, 12, 2021), 2);
        SecondClass secondp("kuba", "kowalski", "12345678901", ticket2);
        CHECK(secondp.getName() == "kuba");
        CHECK(secondp.getSurname() == "kowalski");
        CHECK(secondp.getPesel() == "12345678901");
        CHECK(secondp.getSourceCity().getName() == "Berlin");
        CHECK(secondp.getTicket().getDate().getDay() == 12);
        CHECK(secondp.getTicket().getNumber() == 2);

    }

    SECTION("workers tests")
    {
        Stewardess s1("anna", "nowak", "12345678901", 1);
        Stewardess s2("kuba", "kowal", "12345678901", 1, Price(420, 0));

        CHECK(s1.getName() == "anna");
        CHECK(s1.getSurname() == "nowak");
        CHECK(s1.getPesel() == "12345678901");
        CHECK(s1.getSpeciality() == "stewardess");
        CHECK(s1.getPrice().getZl() == 4000);
        CHECK(s2.passengersPerWorker() == 20);

        CHECK(s2.getName() == "kuba");
        CHECK(s2.getSurname() == "kowal");
        CHECK(s2.getPesel() == "12345678901");
        CHECK(s2.getSpeciality() == "stewardess");
        CHECK(s2.getPrice().getZl() == 420);
        CHECK(s1.passengersPerWorker() == 20);

        s2.setPrice(Price(2, 90));
        CHECK(s2.getPrice().getZl() == 2);
        CHECK(s2.getPrice().getGr() == 90);
    } 
}

TEST_CASE("planes", "[planes]") {

    SECTION("light plane getters") {
        Light_plane lp("AAAA");
        CHECK(lp.get_plane_name() == "AAAA");
        CHECK(lp.get_sitting_places() == 20);
        CHECK(lp.get_price_multiplier()==1.02f);
    }

    SECTION("light plane getters") {
        Small_plane lp("AAAA");
        CHECK(lp.get_plane_name() == "AAAA");
        CHECK(lp.get_sitting_places() == 60);
        CHECK(lp.get_price_multiplier()==1.018f);
    }

    SECTION("medium plane getters") {
        Medium_plane lp("AAAA");
        CHECK(lp.get_plane_name() == "AAAA");
        CHECK(lp.get_sitting_places() == 90);
        CHECK(lp.get_price_multiplier()==1.013f);
    }

        SECTION("big plane getters") {
        Big_plane lp("AAAA");
        CHECK(lp.get_plane_name() == "AAAA");
        CHECK(lp.get_sitting_places() == 200);
        CHECK(lp.get_price_multiplier()==1.01f);
    }
}

TEST_CASE("flight class") {
    shared_ptr<Small_plane> plane = std::make_shared<Small_plane>("AAAA");
    Date date5(12, 12, 2020);
    Flight flight(1, date5, City("Wroclaw"), City("Warszawa"), move(plane));

    SECTION("Setters/num of workers") {
        Stewardess stew("Anna", "Kowalska", "12345678902", 1);
        Pilot pilot("Piotr", "Nowak", "09876542146", 1);
        LuggageMan luggage("Ola", "Kowalczyk", "22222222222", 1);
        Other other("Tom", "Json", "33333333333", 1);
        
        CHECK(flight.stewardess_num() == 0);
        CHECK(flight.pilot_num() == 0);
        CHECK(flight.luggage_num() == 0);
        CHECK(flight.other_num() == 0);
        CHECK(flight.workers_number() == 0);

        flight.add_stewardess(stew);
        flight.add_pilot(pilot);
        flight.add_luggage_man(luggage);
        flight.add_other(other);

        CHECK(flight.stewardess_num() == 1);
        CHECK(flight.pilot_num() == 1);
        CHECK(flight.luggage_num() == 1);
        CHECK(flight.other_num() == 1);
        CHECK(flight.workers_number() == 4);
    }
}