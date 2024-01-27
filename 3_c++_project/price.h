#ifndef PRICE_H
#define PRICE_H
#include <iostream>

class Price
{
private:
    unsigned int gr;

public:
    Price(unsigned int p_zl, unsigned int p_gr);
    Price();

    void set(unsigned int p_zl, unsigned int p_gr);

    unsigned int getZl() const;

    unsigned int getGr() const;

    bool operator>(const Price &price) const;
    bool operator<(const Price &price) const;
    bool operator==(const Price &price) const;
    bool operator!=(const Price &price) const;
    bool operator>=(const Price &price) const;
    bool operator<=(const Price &price) const;

    Price operator+(const Price &price) const;

    Price &operator+=(const Price &price);

    Price operator*(float x) const;

    Price operator*(unsigned int x) const;

    friend std::ostream &operator<<(std::ostream &out, const Price &price);

    bool isValid() const;
};

#endif