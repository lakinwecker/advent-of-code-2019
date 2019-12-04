all: cpp-day1p1 hs-day1p1 scala-day1p1 cpp-day1p2 hs-day1p2 scala-day1p2 test

cpp-day1p1: day1p1.cpp
	g++ -Wall -Werror -o cpp-day1p1 day1p1.cpp

hs-day1p1: day1p1.hs
	ghc --make day1p1.hs -o hs-day1p1

scala-day1p1: day1p1.scala
	sbt --error compile

cpp-day1p2: day1p2.cpp
	g++ -Wall -Werror -o cpp-day1p2 day1p2.cpp

hs-day1p2: day1p2.hs
	ghc --make day1p2.hs -o hs-day1p2

scala-day1p2: day1p2.scala
	sbt --error compile

test: cpp-day1p1 hs-day1p1 scala-day1p1
	echo
	./cpp-day1p1 < input1
	echo
	./cpp-day1p2 < input1
	echo
	./hs-day1p1 input1
	echo
	./hs-day1p2 input1
	echo
	sbt --error "runMain  Day1P1 input1"
	echo
	sbt --error "runMain  Day1P2 input1"

