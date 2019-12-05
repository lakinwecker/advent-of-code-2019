all: cpp-day2p1 hs-day2p1 scala-day2p1 cpp-day2p2 hs-day2p2 scala-day2p2 test

cpp-day2p1: day2p1.cpp
	g++ -Wall -Werror -o cpp-day2p1 day2p1.cpp

hs-day2p1: day2p1.hs
	ghc --make day2p1.hs -o hs-day2p1

scala-day2p1: day2p1.scala
	sbt --error compile

cpp-day2p2: day2p2.cpp
	g++ -Wall -Werror -o cpp-day2p2 day2p2.cpp

hs-day2p2: day2p2.hs
	ghc --make day2p2.hs -o hs-day2p2

scala-day2p2: day2p2.scala
	sbt --error compile

test: cpp-day2p1 hs-day2p1 scala-day2p1
	echo
	./cpp-day2p1 < input
	echo
	./cpp-day2p2 < input
	echo
	./hs-day2p1 input
	echo
	./hs-day2p2 input
	echo
	sbt --error "runMain  Day2P1 input"
	echo
	sbt --error "runMain  Day2P2 input"

