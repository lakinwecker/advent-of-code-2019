import scala.io.Source
import scala.collection.immutable.Vector
import scala.util.{Try, Success, Failure}

class IntcodeException(s:String) extends RuntimeException(s){}
class EmptyProgramException(s:String) extends IntcodeException(s){}
class UnknownOpCodeException(s:String) extends IntcodeException(s){}
class InvalidStructureException(s:String) extends IntcodeException(s){}

object Day2P1 extends App {
  def binaryOp(opcode:Int): Option[(Int, Int) => Int] = opcode match {
    case 1 => Some(_ + _)
    case 2 => Some(_ * _)
    case _ => None
  }
  def process(idx: Int, opcodes: Vector[Int]): Try[Int] = {
    def getVals(idx1:Int, idx2:Int) = opcodes.lift(idx1).zip(opcodes.lift(idx2))
    opcodes.slice(idx, idx+4) match {
      case 99 +: rest => opcodes.lift(0) match {
        case Some(v) => Success(v)
        case None => Failure(new EmptyProgramException("Program is empty"))
      }
      case opcode +: lhsI +: rhsI +: target +: rest => 
        (binaryOp(opcode), getVals(lhsI, rhsI)) match {
          case (Some(op), Some((lhs,rhs))) => process(idx+4, opcodes.updated(target, op(lhs, rhs)))
          case _ => Failure(new UnknownOpCodeException("$opcode%s is not a valid opcode"))
        }
      case opcode => Failure(new InvalidStructureException(f"$opcode%s is not a valid opcode"))
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
    case Success(result) => println(result)
    case Failure(e) => println("Bad Input: " + e.getMessage)
  }
}
