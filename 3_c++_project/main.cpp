#include <iostream>
#include <math.h>
#include <map>

#include "date.h"
#include "price.h"
#include "person.h"
#include "passenger_classes.h"
#include "worker_classes.h"
#include "planes.h"
#include "flight.h"
#include "interface.h"

using namespace std;

using namespace std::this_thread;
using namespace std::chrono; 

int main(int argc, char *argv[])
{
    string people_path = "source/people.txt";
    string workers_path = "source/workers.txt";
    string flights_path = "source/flights.txt";

    try 
    {
        if (argc == 4)
        {
            people_path = argv[1];
            workers_path = argv[2];
            flights_path = argv[3];
        }

        if (argc != 1 && argc != 4)
        {
            throw FileException();
        }
    }
    catch(const std::exception &e)
    {
        cerr << e.what() << endl;
        return 0; 
    }

    try
    {
        DataBase db(people_path, workers_path, flights_path);
        Interface interface(db);
        cout << endl << "Simulation starting" << endl;
        sleep_for(100ms);
        cout << "-------------------------------------------------" << endl << endl;
        interface.ask();
        interface.run();
    }
    catch (const std::exception &e)
    {
        std::cerr << e.what() << '\n';
        return 0;
    }

    return 0;
}