#include "city.h"

City::City(std::string p_name) : name(p_name) {}

std::string City::getName() const
{
    return name;
}