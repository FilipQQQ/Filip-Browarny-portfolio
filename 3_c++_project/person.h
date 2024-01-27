#ifndef PERSON_H
#define PERSON_H
#include <string>

class Person
{
protected:
    std::string name;
    std::string surname;
    std::string pesel;

public:
    Person(std::string name, std::string surname, std::string pesel);
    virtual ~Person();

    void setName(std::string p_name);
    std::string getName() const;

    void setSurname(std::string p_surname);
    std::string getSurname() const;

    void setPesel(const std::string p_pesel);
    std::string getPesel() const;
};

#endif