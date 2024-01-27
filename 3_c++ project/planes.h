#ifndef PLANES_H
#define PLANES_H
#include <string>
#include <iostream>

class Planes {
    protected:
        std::string plane_name;
        unsigned int sitting_places;

    public:
        Planes(std::string plane_name, int sitting_places);
        void print() const;
        virtual ~Planes();

        std::string get_plane_name() const;
        unsigned int get_sitting_places() const;
        
        void set_plane_name(std::string);
        void set_sitting_places(unsigned int);

        friend std::ostream &operator<<(std::ostream& out, const Planes& plane);
};

class Light_plane : public Planes {
    protected:
        float price_multiplier;
    public:
        Light_plane(std::string plane_name, int sitting_places=20, float price_multiplier=1.02);
        void print() const;

        float get_price_multiplier();
        void set_price_multiplier(float);
};

class Small_plane : public Planes {
    protected:
        float price_multiplier;
    public:
        Small_plane(std::string plane_name, int sitting_places=60, float price_multiplier=1.018);
        void print() const;

        float get_price_multiplier();
        void set_price_multiplier(float);
};

class Medium_plane : public Planes {
    protected:
        float price_multiplier;
    public:
        Medium_plane(std::string plane_name, int sitting_places=90, float price_multiplier=1.013);
        void print() const;

        float get_price_multiplier();
        void set_price_multiplier(float);
};

class Big_plane : public Planes {
    protected:
        float price_multiplier;
    public:
        Big_plane(std::string plane_name, int sitting_places=200, float price_multiplier=1.01);
        void print() const;

        float get_price_multiplier();
        void set_price_multiplier(float);
};


#endif