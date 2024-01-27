#ifndef WORKER_CLASSES_H
#define WORKER_CLASSES_H

#include "person.h"
#include "price.h"

class Worker : public Person
{
protected:
    Price salary;
    unsigned int flight_num;

public:
    Worker(std::string name, std::string surname, std::string pesel,
           unsigned int flight_num, const Price &salary = Price(3000, 0)); // domyslna wyplata
    virtual ~Worker();

    Price getPrice() const;
    void setPrice(const Price &price);

    unsigned int get_flight_num();

    virtual unsigned int passengersPerWorker() const = 0;
    virtual std::string getSpeciality() const = 0;

    friend std::ostream &operator<<(std::ostream &out, const Worker &worker);
};

class Stewardess : public Worker
{
private:
public:
    Stewardess(std::string name, std::string surname, std::string pesel, unsigned int flight_num, const Price &salary = Price(4000, 0));

    unsigned int passengersPerWorker() const override;
    std::string getSpeciality() const override;
};

class Pilot : public Worker
{
private:
public:
    Pilot(std::string name, std::string surname, std::string pesel, unsigned int flight_num, const Price &salary = Price(6000, 0));

    unsigned int passengersPerWorker() const override;
    std::string getSpeciality() const override;
};

class LuggageMan : public Worker
{
private:
public:
    LuggageMan(std::string name, std::string surname, std::string pesel, unsigned int flight_num, const Price &salary = Price(3500, 0));

    unsigned int passengersPerWorker() const override;
    std::string getSpeciality() const override;
};

class Other : public Worker
{
private:
public:
    Other(std::string name, std::string surname, std::string pesel, unsigned int flight_num, const Price &salary = Price(3000, 0));

    unsigned int passengersPerWorker() const override;
    std::string getSpeciality() const override;
};
#endif