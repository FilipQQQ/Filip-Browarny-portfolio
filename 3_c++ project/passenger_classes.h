#ifndef PASSENGER_CLASSES_H
#define PASSENGER_CLASSES_H
#include "person.h"
#include "price.h"
#include "date.h"
#include "ticket.h"
#include "city.h"

class Passenger : public Person
{
private:
public:
    Passenger(std::string name, std::string surname, std::string pesel);
    virtual ~Passenger() = default;

    // virtual Ticket getTicket() const = 0;

    virtual City getSourceCity() const = 0;
    virtual City getDestinationCity() const = 0;
    virtual unsigned int getFlightId() const = 0;
    virtual Date getDate() const = 0;

    virtual bool isExtraFood() const = 0;
    virtual bool isExtraArmachair() const = 0;
    virtual bool isExtraPillows() const = 0;
    virtual bool isWindowSeat() const = 0;

    friend std::ostream &operator<<(std::ostream &out, const Passenger &Passenger);
};

class FirstClass : public Passenger
{
private:
    FirstClassTicket ticket;

public:
    FirstClass(std::string name, std::string surname, std::string pasel, const FirstClassTicket &ticket);

    City getSourceCity() const override;
    City getDestinationCity() const override;
    unsigned int getFlightId() const override;
    Date getDate() const override;

    FirstClassTicket getTicket() const;
    void setTicket(const FirstClassTicket &ticket);

    bool isExtraFood() const;
    bool isExtraArmachair() const;
    bool isExtraPillows() const;
    bool isWindowSeat() const;

    friend std::ostream &operator<<(std::ostream &out, const FirstClass &Passenger);

    friend std::ostream &operator<<(std::ostream &out, const FirstClass &passager);
};

class SecondClass : public Passenger
{
private:
    SecondClassTicket ticket;

public:
    SecondClass(std::string name, std::string surname, std::string pasel, const SecondClassTicket &ticket);

    City getSourceCity() const override;
    City getDestinationCity() const override;
    unsigned int getFlightId() const override;
    Date getDate() const override;

    SecondClassTicket getTicket() const;
    void setTicket(const SecondClassTicket &ticket);

    bool isExtraFood() const;
    bool isExtraArmachair() const;
    bool isExtraPillows() const;
    bool isWindowSeat() const;

    friend std::ostream &operator<<(std::ostream &out, const SecondClass &passager);
};

#endif