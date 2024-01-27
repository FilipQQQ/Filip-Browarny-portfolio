#include <iostream>
#include <iomanip>
#include "date.h"

using namespace std;

Date::Date(unsigned int p_day, unsigned int p_month, unsigned int p_year)
    : day(p_day), month(p_month), year(p_year)
{
    if (!isValid())
        throw DateException();
}

void Date::setYear(unsigned int p_year)
{
    year = p_year;
    if (!isValid())
        throw DateException();
}

void Date::setMonth(unsigned int p_month)
{
    month = p_month;
    if (!isValid())
        throw DateException();
}

void Date::setDay(unsigned int p_day)
{
    day = p_day;
    if (!isValid())
        throw DateException();
}

unsigned int Date::getDay() const
{
    return day;
}

unsigned int Date::getMonth() const
{
    return month;
}

unsigned int Date::getYear() const
{
    return year;
}

string Date::toString() const
{
    ostringstream ostream;
    ostream << *this;
    return ostream.str();
}

std::ostream &operator<<(std::ostream &out, const Date &date)
{
    out << setfill('0') << setw(2) << date.day << '.'
        << setfill('0') << setw(2) << date.month << '.' << date.year;
    return out;
}

bool Date::isValid() const
{
    if (year < 1 || year > 3000)
        return false;
    if (month < 1 || month > 12)
        return false;
    if (day < 1)
    {
        return false;
    }

    unsigned int numberOfdays = 31;

    switch (month)
    {
    case 4:
    case 6:
    case 9:
    case 11:
        numberOfdays = 30;
        break;
    case 2:
        if ((year % 100 != 0 && year % 4 == 0) || (year % 400 == 0))
        {
            numberOfdays = 29;
        }
        else
        {
            numberOfdays = 28;
        }
    }

    if (day > numberOfdays)
    {
        return false;
    }

    return true;
}

bool Date::operator>(const Date &p_date)
{

    if (this->year > p_date.getYear())
        return true;
    else if (this->year == p_date.getYear())
        if (this->month > p_date.getMonth())
            return true;
    if (this->month == p_date.getMonth())
        if (this->day > p_date.getDay())
            return true;
    return false;
}

bool Date::operator>=(const Date &p_date)
{

    if (this->year > p_date.getYear())
        return true;
    else if (this->year == p_date.getYear())
        if (this->month > p_date.getMonth())
            return true;
    if (this->month == p_date.getMonth())
        if (this->day >= p_date.getDay())
            return true;
    return false;
}

bool Date::operator<(const Date &p_date)
{

    if (this->year < p_date.getYear())
        return true;
    else if (this->year == p_date.getYear())
        if (this->month < p_date.getMonth())
            return true;
    if (this->month == p_date.getMonth())
        if (this->day < p_date.getDay())
            return true;
    return false;
}

bool Date::operator<=(const Date &p_date)
{

    if (this->year < p_date.getYear())
        return true;
    else if (this->year == p_date.getYear())
        if (this->month < p_date.getMonth())
            return true;
    if (this->month == p_date.getMonth())
        if (this->day <= p_date.getDay())
            return true;
    return false;
}

bool Date::operator==(const Date &date) const
{
    return (this->day == date.getDay() && this->month == date.getMonth() && this->year == date.getYear());
}

bool Date::operator!=(const Date &date) const
{
    return !(*this == date);
}

// wersja z prefixem, preinkrementacja
Date &Date::operator++()
{
    switch (month)
    {
    case 1:
    case 3:
    case 5:
    case 7:
    case 8:
    case 10:
    {
        if (day == 31)
        {
            month += 1;
            day = 1;
        }
        else
        {
            day += 1;
        }
        break;
    }
    case 12:
    {
        if (day == 31)
        {
            year += 1;
            month = 1;
            day = 1;
        }
        else
        {
            day += 1;
        }
        break;
    }
    case 2:
        if ((year % 100 != 0 && year % 4 == 0) || (year % 400 == 0))
        {
            int numberOfdays = 29;
            if (day == numberOfdays)
            {
                day = 1;
                month += 1;
            }
            else
            {
                day++;
            }
        }
        else
        {
            int numberOfdays = 28;
            if (day == numberOfdays)
            {
                day = 1;
                month += 1;
            }
            else
            {
                day++;
            }
        }
        break;
    case 4:
    case 6:
    case 9:
    case 11:
    {
        if (day == 30)
        {
            month += 1;
            day = 1;
        }
        else
        {
            day += 1;
        }
        break;
    }
    }
    return *this;
}

// wersja z postfiksem, postinkrementacja
Date Date::operator++(int)
{
    Date temp = *this;
    ++*this;
    return temp;
}
