import scala.io.Source
import scala.collection.immutable.Vector


object Day2P1 extends App {
  def process(idx: Int, opcodes: Vector[Int]): Option[Int] = {
    def getVals(idx1:Int, idx2:Int) = opcodes.lift(idx1).zip(opcodes.lift(idx2))
    def binaryOp(opcode:Int): Option[(Int, Int) => Int] = opcode match {
      case 1 => Some(_ + _)
      case 2 => Some(_ * _)
      case _ => None
    }
    opcodes.slice(idx, idx+4) match {
      case 99 +: rest => opcodes.lift(0)
      case opcode +: lhsI +: rhsI +: target +: rest => 
        (binaryOp(opcode), getVals(lhsI, rhsI)) match {
          case (Some(op), Some((lhs,rhs))) => process(idx+4, opcodes.updated(target, op(lhs, rhs)))
          case _ => None
        }
      case _ => None
    }
  }

  val opcodes = Source
      .fromFile(args(0))
      .getLines
      .filter(_.nonEmpty)
      .flatMap(_.split(","))
      .flatMap(_.toIntOption)
      .toVector
      .updated(1, 12)
      .updated(2, 2)

  process(0, opcodes) match {
    case Some(result) => println(result)
    case None => println("Bad Input")
  }
}
