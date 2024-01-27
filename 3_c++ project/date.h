#ifndef DATE_H_
#define DATE_H_
#include <string>
#include <sstream>
#include "exceptions.h"
class Date
{
private:
    unsigned int year;
    unsigned int month;
    unsigned int day;

public:
    Date(unsigned int p_day, unsigned int p_month, unsigned int p_year);
    void setYear(unsigned int p_year);
    void setMonth(unsigned int p_month);
    void setDay(unsigned int p_day);
    unsigned int getYear() const;
    unsigned int getMonth() const;
    unsigned int getDay() const;

    bool operator>(const Date &date);
    bool operator<(const Date &date);
    bool operator==(const Date &date) const;
    bool operator!=(const Date &date) const;
    bool operator>=(const Date &date);
    bool operator<=(const Date &date);

    Date &operator++();

    Date operator++(int);

    friend std::ostream &operator<<(std::ostream &out, const Date &date);
    std::string toString() const;
    bool isValid() const;
};

#endif