import scala.io.Source

object Day1P1 extends App {
  var total = 0;
  val filename = args(0)
  for (line <- Source.fromFile(filename).getLines){
    total += (line.toInt) / 3 - 2
  }
  println(total)
}
