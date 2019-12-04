import scala.io.Source


object Day1P2 extends App {
  def fuel(mass: Int) = mass / 3 - 2
  def extraFuel(mass: Int): Int = fuel(mass) match {
      case f if f <= 0 => 0
      case f => f + extraFuel(f)
    }

  val lines = Source.fromFile(args(0)).getLines 
  val subtotal = lines.flatMap(_.toIntOption).map(fuel).sum
  val total = subtotal + extraFuel(subtotal)
  println(total)
}
