#include "passenger_classes.h"

using namespace std;
Passenger::Passenger(std::string p_name, std::string p_surname, std::string p_pesel)
    : Person(p_name, p_surname, p_pesel) {}

FirstClass::FirstClass(std::string p_name, std::string p_surname, std::string p_pesel, const FirstClassTicket &p_ticket)
    : Passenger(p_name, p_surname, p_pesel), ticket(p_ticket) {}

City FirstClass::getSourceCity() const
{
    return ticket.getSource();
}
City FirstClass::getDestinationCity() const
{
    return ticket.getDestination();
}

unsigned int FirstClass::getFlightId() const
{
    return ticket.getNumber();
}
Date FirstClass::getDate() const
{
    return ticket.getDate();
}

FirstClassTicket FirstClass::getTicket() const
{
    return ticket;
}

void FirstClass::setTicket(const FirstClassTicket &p_ticket)
{
    ticket = p_ticket;
}

bool FirstClass::isExtraFood() const
{
    return true;
}

bool FirstClass::isExtraArmachair() const
{
    return true;
}

bool FirstClass::isExtraPillows() const
{
    return true;
}
bool FirstClass::isWindowSeat() const
{
    return true;
}

std::ostream &operator<<(std::ostream &out, const FirstClass &passenger)
{
    out << "First class passenger: " << passenger.getName() << " " << passenger.getSurname() << endl;
    out << "PESEL: " << passenger.getPesel() << endl;
    out << "FROM: " << passenger.getTicket().getSource().getName() << endl;
    out << "TO: " << passenger.getTicket().getDestination().getName() << endl;
    return out;
}

SecondClass::SecondClass(std::string name, std::string surname, std::string pesel, const SecondClassTicket &p_ticket)
    : Passenger(name, surname, pesel), ticket(p_ticket) {}

City SecondClass::getSourceCity() const
{
    return ticket.getSource();
}
City SecondClass::getDestinationCity() const
{
    return ticket.getDestination();
}

unsigned int SecondClass::getFlightId() const
{
    return ticket.getNumber();
}
Date SecondClass::getDate() const
{
    return ticket.getDate();
}

SecondClassTicket SecondClass::getTicket() const
{
    return ticket;
}

void SecondClass::setTicket(const SecondClassTicket &p_ticket)
{
    ticket = p_ticket;
}
bool SecondClass::isExtraFood() const
{
    return false;
}

bool SecondClass::isExtraArmachair() const
{
    return false;
}

bool SecondClass::isExtraPillows() const
{
    return false;
}
bool SecondClass::isWindowSeat() const
{
    return false;
}

std::ostream &operator<<(std::ostream &out, const SecondClass &passenger)
{
    out << "Second class passenger: " << passenger.getName() << " " << passenger.getSurname() << endl;
    out << "PESEL: " << passenger.getPesel() << endl;
    out << "FROM: " << passenger.getTicket().getSource().getName() << endl;
    out << "TO: " << passenger.getTicket().getDestination().getName() << endl;
    return out;
}