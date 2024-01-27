#ifndef EXCEPTIONS_H
#define EXCEPTIONS_H

#include <stdexcept>

class PeselException : public std::invalid_argument
{
public:
    PeselException(std::string p_pesel);
};

class PassagerNotFound : public std::invalid_argument
{
public:
    PassagerNotFound();
};

class InvalidInput : public std::invalid_argument
{
public:
    InvalidInput();
};

class InvalidClass : public std::invalid_argument
{
public:
    InvalidClass();
};

class InvalidPlaneType : public std::invalid_argument
{
public:
    InvalidPlaneType();
};

class InvalidWorker : public std::invalid_argument
{
public:
    InvalidWorker();
};

class NegativePriceException : public std::invalid_argument
{
public:
    NegativePriceException();
};

class InvalidPassenger : public std::invalid_argument
{
public:
    InvalidPassenger();
};

class NoSpaceException : public std::invalid_argument
{
public:
    NoSpaceException();
};

class  DateException: public std::invalid_argument
{
public:
    DateException();
};

class  FileException: public std::invalid_argument
{
public:
    FileException();
};
#endif