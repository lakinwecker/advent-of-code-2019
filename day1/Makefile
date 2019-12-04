all: cpp-day1p1 hs-day1p1 scala-day1p1 test

cpp-day1p1: day1p1.cpp
	g++ -Wall -Werror -o cpp-day1p1 day1p1.cpp

hs-day1p1: day1p1.hs
	ghc --make day1p1.hs -o hs-day1p1

scala-day1p1:
	sbt --error compile

test: cpp-day1p1 hs-day1p1 scala-day1p1
	echo
	./cpp-day1p1 < input1
	echo
	./hs-day1p1 input1
	echo
	sbt --error "run input1"

