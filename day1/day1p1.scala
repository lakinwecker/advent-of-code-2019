import scala.io.Source


object Day1P1 extends App {
  val fuel = (mass: Int) => mass / 3 - 2

  val total =
    ((Source.fromFile(args(0)).getLines)
      .foldLeft
        (0)
        ((total, line) => total + fuel(line.toInt)))
  println(total)
}
