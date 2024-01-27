#include "price.h"
#include "exceptions.h"
#include <iomanip>

using namespace std;

Price::Price(unsigned int p_zl, unsigned int p_gr)
{
    set(p_zl, p_gr);
}

Price::Price()
    : gr(0){};

void Price::set(unsigned int p_zl, unsigned int p_gr)
{
    if (p_gr > 99)
    {
        throw NegativePriceException();
    }
    gr = p_zl * 100 + p_gr;
}

unsigned int Price::getZl() const
{
    return gr / 100;
}

unsigned int Price::getGr() const
{
    return gr % 100;
}

bool Price::operator>(const Price &new_price) const
{
    return (this->gr > new_price.gr);
}

bool Price::operator>=(const Price &new_price) const
{
    return (this->gr >= new_price.gr);
}

bool Price::operator<(const Price &new_price) const
{
    return (this->gr < new_price.gr);
}

bool Price::operator<=(const Price &new_price) const
{
    return (this->gr <= new_price.gr);
}

bool Price::operator==(const Price &new_price) const
{
    return (this->gr == new_price.gr);
}

bool Price::operator!=(const Price &new_price) const
{
    return !(*this == new_price);
}

Price Price::operator+(const Price &p_price) const
{
    Price price;
    price.gr = this->gr + p_price.gr;
    return price;
}

Price &Price::operator+=(const Price &price)
{
    this->gr += price.gr;
    return *this;
}

Price Price::operator*(float x) const
{
    if (x < 0)
    {
        throw NegativePriceException();
    }
    Price price;
    price.gr = this->gr * x;
    return price;
}

Price Price::operator*(unsigned int x) const
{
    Price price;
    price.gr = this->gr * x;
    return price;
}

std::ostream &operator<<(std::ostream &out, const Price &price)
{
    // 95,05 zl  set_precision(2) ??
    out << price.getZl() << "," << setw(2) << setfill('0') << price.getGr() << " zl" << endl;
    return out;
}
