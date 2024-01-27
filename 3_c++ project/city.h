#ifndef CITY_H
#define CITY_H

#include <string>

class City
{
    private:
        std::string name;
    public:
        City(std::string name);
        std::string getName() const;
};

#endif 