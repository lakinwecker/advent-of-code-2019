import scala.io.Source
import scala.collection.immutable.Vector


object Day2P2 extends App {

  def process(idx: Int, opcodes: Vector[Int]): Int =
    idx match {
      case idx if idx > opcodes.length-4 => opcodes(0)
      case idx => opcodes(idx) match {
        case 99 => opcodes(0)
        case 1 => process(idx+4, opcodes.updated(opcodes(idx+3), opcodes(opcodes(idx+1)) + opcodes(opcodes(idx+2))))
        case 2 => process(idx+4, opcodes.updated(opcodes(idx+3), opcodes(opcodes(idx+1)) * opcodes(opcodes(idx+2))))
      }
    }
  // Recurse instead of double for loop. Ick.
  def tryAll(noun: Int, verb: Int, opcodes: Vector[Int]): (Int, Int) =
    (noun, verb) match {
      case (n, v) if n > 99 => (n, v)
      case (n, v) if v > 99 => tryAll(n+1, 0, opcodes)
      case (n, v) => 
        process(0, opcodes.updated(1, n).updated(2, v)) match {
          case 19690720 => (n, v)
          case _ => tryAll(n, v+1, opcodes)
        }
    }
  val opcodes = Vector.empty ++ Source.fromFile(args(0)).getLines
      .filter(s => s.length() > 0)
      .mkString(",")
      .split(",")
      .flatMap(_.toIntOption)

  val answer = tryAll(0, 0, opcodes) match {
    case (n, v) => 100 * n +v
  }

  println(answer)
}
