#ifndef TICKET_H
#define TICKET_H

#include "city.h"
#include "price.h"
#include "date.h"

class Ticket
{
protected:
    unsigned int flight_id;
    City source;
    City destination;
    Date date;
    Price base_price;
    void calculateBasePrice();

public:
    Ticket(City source, City Destination, Date date, unsigned int number);

    virtual ~Ticket();

    unsigned int getNumber() const;
    void setNumber(unsigned int);

    City getSource() const;
    void setSource(const City &city);

    City getDestination() const;
    void setDestination(const City &city);

    Date getDate() const;
    void setDate(const Date &city);

    Price getBasePrice() const;

    virtual Price getRealPrice() const = 0;
};

class FirstClassTicket : public Ticket
{
public:
    FirstClassTicket(City source, City Destination, Date date, unsigned int num);

    Price getRealPrice() const override;
};

class SecondClassTicket : public Ticket
{
public:
    SecondClassTicket(City source, City Destination, Date date, unsigned int num);

    Price getRealPrice() const override;
};

#endif