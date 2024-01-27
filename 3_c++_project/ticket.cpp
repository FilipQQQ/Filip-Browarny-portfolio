#include "ticket.h"

#include <iostream>
#include <math.h>
#include <map>

using namespace std;

double geo_distance(string city1, string city2) {
    // Wroclaw Warszawa Gdansk Kopenhaga Mediolan Barcelona Londyn Krakow <- miasta do bazy
    // do mapy podajemy liczby w nastepujacym formacie:
    // Warszawa ma wspolrzedne 52°15′N 21°00′E
    // podajemy 52, 15, 21, 0
    // jesli zamiast N jest S to podajemy -52 i analogicznie dla E i W

    // tu tez trzeba bedzie zrobic liste dostepnych miast i wyjatek jakby ktos podal zle
    
    typedef array<double, 4> Fourth;
    map<string, Fourth> city_map;
    city_map["Wroclaw"] = {51, 6, 17, 2};
    city_map["Warszawa"] = {52, 15, 21, 0};
    city_map["Gdansk"] = {54, 21, 18, 40};    
    city_map["Kopenhaga"] = {55, 40, 12, 34};
    city_map["Mediolan"] = {45, 27, 9, 11};
    city_map["Barcelona"] = {41, 23, 2, 9};
    city_map["Londyn"] = {51, 30, 0, 7};
    city_map["Krakow"] = {50, 3, 19, 56};

    Fourth city_1 = city_map[city1];
    Fourth city_2 = city_map[city2];
    double x1_degree = city_1[0];
    double x1_min = city_1[1]*5/3;
    double y1_degree = city_1[2];
    double y1_min = city_1[3]*5/3;
    double x2_degree = city_2[0];
    double x2_min = city_2[1]*5/3;
    double y2_degree = city_2[2];
    double y2_min = city_2[3]*5/3;

    double x1, y1, x2, y2;
    x1 = x1_degree+x1_min/100;
    x2 = x2_degree+x2_min/100;
    y1 = y1_degree+y1_min/100;       
    y2 = y2_degree+y2_min/100;
    double one = pow((x2-x1), 2);
    double two = pow(cos(x1*M_PI/180)*(y2-y1), 2);
    double ret = sqrt(one+two)*40075.704/360;
    return ret;
    }

using namespace std;

Ticket::Ticket(City p_source, City p_destination, Date p_date, unsigned int p_num)
: source(p_source), destination(p_destination), date(p_date), flight_id(p_num)
{
    double distance = geo_distance(source.getName(), destination.getName());

    int price_in_zl = distance;

    base_price = Price(price_in_zl, 0);
}

Ticket::~Ticket() {}


unsigned int Ticket::getNumber() const
{
    return flight_id;
}

void Ticket::setNumber(unsigned int p_num)
{
    flight_id = p_num;
}

City Ticket::getSource() const
{
    return source;
}
void Ticket::setSource(const City& p_city)
{
    source = p_city;
}

City Ticket::getDestination() const
{
    return destination;
}
void Ticket::setDestination(const City& p_city)
{
    destination = p_city;
}


Date Ticket::getDate() const
{
    return date;
}

void Ticket::setDate(const Date& p_date)
{
    date = p_date;
}

Price Ticket::getBasePrice() const
{
    return base_price;
}

FirstClassTicket::FirstClassTicket(City source, City destination, Date date, unsigned int num)
: Ticket(source, destination, date, num) {}

Price FirstClassTicket::getRealPrice() const
{
    int gr_price = 1.25f * (base_price.getZl()*100) + base_price.getGr();
    return Price(gr_price / 100, gr_price % 100);
}

SecondClassTicket::SecondClassTicket(City source, City destination, Date date, unsigned int num)
: Ticket(source, destination, date, num) {}


Price SecondClassTicket::getRealPrice() const
{
    return base_price;
}

