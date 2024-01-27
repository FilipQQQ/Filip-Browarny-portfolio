#ifndef INTERFACE_H
#define INTERFACE_H
#include "data_base.h"
#include <chrono>
#include <thread>

class Interface
{
private:
    DataBase db;
    Date stop_date;
    Date current_date;

public:
    Interface(
        const DataBase &db,
        Date current_date = Date(1, 5, 2022),
        Date stop_date = Date(9, 5, 2020));

    void ask();
    void change_seats();

    Date getStopDate() const;
    Date getCurrentDate() const;

    void run();
};

#endif