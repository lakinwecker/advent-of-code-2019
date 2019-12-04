import scala.io.Source


object Day1P1 extends App {
  val fuel = (mass: Int) => mass / 3 - 2

  val lines = Source.fromFile(args(0)).getLines 
  val total = lines.flatMap(_.toIntOption).map(fuel).sum
  println(total)
}
