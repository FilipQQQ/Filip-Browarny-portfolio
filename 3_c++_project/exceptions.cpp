#include "exceptions.h"

InvalidInput::InvalidInput() : std::invalid_argument("Invalid input!") {}

InvalidClass::InvalidClass() : std::invalid_argument("invalid class") {}

InvalidWorker::InvalidWorker() : std::invalid_argument("invalid worker") {}

InvalidPlaneType::InvalidPlaneType() : std::invalid_argument("invalid plane type") {}

PassagerNotFound::PassagerNotFound() : std::invalid_argument("Passager not found") {}

PeselException::PeselException(std::string p_pesel) : std::invalid_argument("Invalid pesel input: " + p_pesel) {}

NegativePriceException::NegativePriceException() : std::invalid_argument("Negative value of the base price") {}

InvalidPassenger::InvalidPassenger() : std::invalid_argument("This passenger is already on this flight") {}

NoSpaceException::NoSpaceException() : std::invalid_argument("This flight is full.") {}

DateException::DateException() : std::invalid_argument("Invalid Date format") {}

FileException::FileException() : std::invalid_argument("Invalid files") {}