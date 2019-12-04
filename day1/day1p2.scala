import scala.io.Source


object Day1P2 extends App {
  def fuel(mass: Int): Int = {
    val f = mass / 3 - 2
    f match {
      case f if f <= 0 => 0
      case f => f + fuel(f)
    }
  }

  val lines = Source.fromFile(args(0)).getLines 
  val total = lines.flatMap(_.toIntOption).map(fuel).sum
  println(total)
}
