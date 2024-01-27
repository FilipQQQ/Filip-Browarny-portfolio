#include "person.h"
#include "exceptions.h"
#include <string>

using namespace std;

Person::Person(string p_name, string p_surname, string p_pesel)
    : name(p_name), surname(p_surname)
{
    int check = 0;
    string ch;
    if (p_pesel.length() != 11)
        throw PeselException(p_pesel);

    for (int i = 0; i < 11; i++)
    {
        try
        {
            ch = p_pesel[i];
            check = stoi(ch);
        }
        catch (...)
        {
            throw PeselException(p_pesel);
        }
    }

    pesel = p_pesel;
}

Person::~Person() {}

void Person::setName(string p_name)
{
    name = p_name;
}

string Person::getName() const
{
    return name;
}

void Person::setSurname(string p_surname)
{
    surname = p_surname;
}

string Person::getSurname() const
{
    return surname;
}

void Person::setPesel(string p_pesel)
{
    int check = 0;
    string ch;
    if (p_pesel.length() != 11)
        throw PeselException(p_pesel);

    for (int i = 0; i < 11; i++)
    {
        try
        {
            ch = p_pesel[i];
            check = stoi(ch);
        }
        catch (...)
        {
            throw PeselException(p_pesel);
        }
    }

    pesel = p_pesel;
}

string Person::getPesel() const
{
    return pesel;
}
