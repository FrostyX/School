CMAKE_MINIMUM_REQUIRED(VERSION 2.8)
PROJECT(1-hash)
ADD_EXECUTABLE(../bin/1-hash ../src/main.cpp ../src/Hash.cpp ../src/Zlomek.cpp)
ADD_DEFINITIONS("-std=c++11")
