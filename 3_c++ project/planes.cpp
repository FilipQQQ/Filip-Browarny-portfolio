#include "planes.h"

using namespace std;

Planes::Planes(string plane_name, int sitting_places) : plane_name(plane_name), sitting_places(sitting_places) {} // dodac sprawdzenie

string Planes::get_plane_name() const
{
    return plane_name;
}

Planes::~Planes() {}

unsigned int Planes::get_sitting_places() const
{
    return sitting_places;
}

void Planes::set_plane_name(string new_name)
{
    plane_name = new_name;
}

void Planes::set_sitting_places(unsigned int new_numb)
{
    sitting_places = new_numb;
}

void Planes::print() const
{
    cout << plane_name << "\nSitting places: " << sitting_places;
}

std::ostream &operator<<(std::ostream &out, const Planes &plane)
{
    out << plane.get_plane_name() << "\nSitting places: " << plane.get_sitting_places();
    return out;
}

Light_plane::Light_plane(string plane_name, int sitting_places, float price_multiplier) : Planes(plane_name, sitting_places), price_multiplier(price_multiplier) {}

float Light_plane::get_price_multiplier()
{
    return price_multiplier;
}

void Light_plane::set_price_multiplier(float new_multi)
{
    price_multiplier = new_multi;
}

void Light_plane::print() const
{
    Planes::print();
    cout << "\nprice multiplier: " << price_multiplier;
}

Small_plane::Small_plane(string plane_name, int sitting_places, float price_multiplier) : Planes(plane_name, sitting_places), price_multiplier(price_multiplier) {}

float Small_plane::get_price_multiplier()
{
    return price_multiplier;
}

void Small_plane::set_price_multiplier(float new_multi)
{
    price_multiplier = new_multi;
}

void Small_plane::print() const
{
    Planes::print();
    cout << "\nprice multiplier: " << price_multiplier;
}

Medium_plane::Medium_plane(string plane_name, int sitting_places, float price_multiplier) : Planes(plane_name, sitting_places), price_multiplier(price_multiplier) {}

float Medium_plane::get_price_multiplier()
{
    return price_multiplier;
}

void Medium_plane::set_price_multiplier(float new_multi)
{
    price_multiplier = new_multi;
}

void Medium_plane::print() const
{
    Planes::print();
    cout << "\nprice multiplier: " << price_multiplier;
}

Big_plane::Big_plane(string plane_name, int sitting_places, float price_multiplier) : Planes(plane_name, sitting_places), price_multiplier(price_multiplier) {}

float Big_plane::get_price_multiplier()
{
    return price_multiplier;
}

void Big_plane::set_price_multiplier(float new_multi)
{
    price_multiplier = new_multi;
}

void Big_plane::print() const
{
    Planes::print();
    cout << "\nprice multiplier: " << price_multiplier;
}