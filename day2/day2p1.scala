import scala.io.Source
import scala.collection.immutable.Vector


object Day2P1 extends App {
  def process(idx: Int, opcodes: Vector[Int]): Vector[Int] =
    idx match {
      case idx if idx > opcodes.length-4 => opcodes
      case idx => opcodes(idx) match {
        case 99 => opcodes
        case 1 => process(idx+4, opcodes.updated(opcodes(idx+3), opcodes(opcodes(idx+1)) + opcodes(opcodes(idx+2))))
        case 2 => process(idx+4, opcodes.updated(opcodes(idx+3), opcodes(opcodes(idx+1)) * opcodes(opcodes(idx+2))))
      }
    }
  val opcodes = Vector.empty ++ Source.fromFile(args(0)).getLines
      .filter(s => s.length() > 0)
      .mkString(",")
      .split(",")
      .flatMap(_.toIntOption)
  val finalOpcodes = process(0, opcodes)
  println(finalOpcodes.headOption)
}
