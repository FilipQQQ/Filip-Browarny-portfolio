#include "data_base.h"

using namespace std;

DataBase::DataBase(
    string people_path,
    string workers_path,
    string flights_path) : people_path(people_path),
                           workers_path(workers_path),
                           flights_path(flights_path)
{
    import_passengers();
    import_workers();
    import_flights();
    assignPassengers();
}

void DataBase::import_passengers()
{
    fstream file;
    file.open(people_path, ios::in);
    if (!file.good())
        throw FileException();

    string name, surname, pesel;

    while (file >> name)
    {
        file >> surname;
        file >> pesel;
        Person peop(name, surname, pesel);
        people.push_back(peop);
    }
    file.close();
}

void DataBase::import_workers()
{
    fstream file;
    file.open(workers_path, ios::in);
    if (!file.good())
        throw FileException();
    string name, surname, pesel, type;
    unsigned int flight_num;
    while (file >> name)
    {
        file >> surname;
        file >> pesel;
        file >> flight_num;
        file >> type;
        if (type == "1")
        {
            Stewardess stew(name, surname, pesel, flight_num);
            stewardess.push_back(stew);
        }
        else if (type == "2")
        {
            Pilot pil(name, surname, pesel, flight_num);
            pilot.push_back(pil);
        }
        else if (type == "3")
        {
            LuggageMan luggage(name, surname, pesel, flight_num);
            luggage_man.push_back(luggage);
        }
        else if (type == "4")
        {
            Other oth(name, surname, pesel, flight_num);
            other.push_back(oth);
        }
        else
        {
            throw InvalidClass();
        }
    }
    file.close();
}

shared_ptr<Planes> DataBase::create_plane(std::string p_type, std::string p_name)
{
    if (p_type == "0")
    {
        std::shared_ptr<Light_plane> plane = std::make_shared<Light_plane>(p_name);
        return plane;
    }
    else if (p_type == "1")
    {
        std::shared_ptr<Small_plane> plane = std::make_shared<Small_plane>(p_name);
        return plane;
    }
    else if (p_type == "2")
    {
        std::shared_ptr<Medium_plane> plane = std::make_shared<Medium_plane>(p_name);
        return plane;
    }
    else if (p_type == "3")
    {
        std::shared_ptr<Big_plane> plane = std::make_shared<Big_plane>(p_name);
        return plane;
    }
    else
    {
        throw InvalidPlaneType();
    }
}

void DataBase::import_flights()
{
    fstream file;
    file.open(flights_path, ios::in);
    if (!file.good())
        throw FileException();
    string id, day, month, year, departure, arrival, p_name, p_type;
    while (file >> id)
    {
        file >> day;
        file >> month;
        file >> year;
        file >> departure;
        file >> arrival;
        file >> p_name;
        file >> p_type;

        auto ptr = create_plane(p_type, p_name);
        Flight flight(stoul(id), Date(stoi(day), stoi(month), stoi(year)), City(departure), City(arrival), move(ptr));

        for (auto stew : stewardess)
        {
            if (flight.get_id() == stew.get_flight_num())
            {
                flight.add_stewardess(stew);
            }
        }

        for (auto pil : pilot)
        {
            if (flight.get_id() == pil.get_flight_num())
            {
                flight.add_pilot(pil);
            }
        }

        for (auto lugg : luggage_man)
        {
            if (flight.get_id() == lugg.get_flight_num())
            {
                flight.add_luggage_man(lugg);
            }
        }

        for (auto oth : other)
        {
            if (flight.get_id() == oth.get_flight_num())
            {
                flight.add_other(oth);
            }
        }

        for (auto firs : first)
        {
            try
            {
                if (flight.get_id() == firs.getTicket().getNumber())
                {
                    flight.add_first_class(firs);
                }
            } catch(const std::exception& e)
            {
                std::cerr << e.what() << '\n';
            }
        }

        for (auto sec : second)
        {
            try
            {
                if (flight.get_id() == sec.getTicket().getNumber())
                {
                    flight.add_second_class(sec);
                }            }
            catch(const std::exception& e)
            {
                std::cerr << e.what() << '\n';
            }
            
            if (flight.get_id() == sec.getTicket().getNumber())
            {
                flight.add_second_class(sec);
            }
        }

        flights.push_back(flight);
    }
    file.close();
}

void DataBase::assignPassengers()
{
    random_device rd;
    mt19937 mt(rd());
    std::uniform_int_distribution<int> distribution2(1, 2);
    for (auto &flight : flights)
    {
        std::uniform_int_distribution<int> distribution1(10, flight.get_plane().get_sitting_places());  //losowanie liczby miejsc w samolocie
        int random_num_of_passnegers = distribution1(mt);

        for (int i = 0; i < random_num_of_passnegers; i++)
        {
            std::uniform_int_distribution<int> distribution3(0, people.size());

            int random_class = distribution2(mt);
            int random_person_num = distribution3(mt);

            Person random_person = people[random_person_num];

            for (std::vector<Person>::iterator iter = people.begin(); iter != people.end(); ++iter) // usuwanie czlowieka o danym peselu, moze mozna latwiej
            {
                if ((*iter).getPesel() == random_person.getPesel())
                {
                    people.erase(iter);
                    break;
                }
            }
            try
            { 
                if (random_class == 1)
                {
                    FirstClassTicket ticket(flight.get_departure(), flight.get_arrival(), flight.get_date(), flight.get_id());
                    FirstClass passenger(random_person.getName(), random_person.getSurname(), random_person.getPesel(), ticket);

                    flight.add_first_class(passenger);

                    std::shared_ptr<FirstClass> passager = std::make_shared<FirstClass>(random_person.getName(), random_person.getSurname(), random_person.getPesel(), ticket);
                    passengers.push_back(std::move(passager));
                }
                else if (random_class == 2)
                {
                    SecondClassTicket ticket(flight.get_departure(), flight.get_arrival(), flight.get_date(), flight.get_id());
                    SecondClass passenger(random_person.getName(), random_person.getSurname(), random_person.getPesel(), ticket);

                    flight.add_second_class(passenger);

                    std::shared_ptr<SecondClass> passager = std::make_shared<SecondClass>(random_person.getName(), random_person.getSurname(), random_person.getPesel(), ticket);
                    passengers.push_back(std::move(passager));
                }
            }
            catch(...)
            {
                cerr << "Cannot assign passangers to flights!";
            }
        }
    }
}

std::vector<Flight> DataBase::get_flights()
{
    return flights;
}

std::vector<std::shared_ptr<Passenger>> DataBase::get_passengers()
{
    return passengers;
}