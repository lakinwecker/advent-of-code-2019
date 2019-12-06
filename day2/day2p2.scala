import scala.io.Source
import scala.collection.immutable.Vector


object Day2P2 extends App {

  def process(idx: Int, opcodes: Vector[Int]): Option[Int] = {
    val getVals = (idx1:Int, idx2:Int) => opcodes.lift(idx1).zip(opcodes.lift(idx2))
    opcodes.slice(idx, idx+4) match {
      case 99 +: rest => opcodes.lift(0)
      case op +: rhsI +: lhsI +: target +: rest => 
        (op, getVals(rhsI, lhsI)) match {
          case (1, Some((rhs,lhs))) => process(idx+4, opcodes.updated(target, rhs + lhs))
          case (2, Some((rhs,lhs))) => process(idx+4, opcodes.updated(target, rhs * lhs))
          case _ => None
        }
      case _ => None
    }
  }

  // https://stackoverflow.com/questions/8217764/cartesian-product-of-two-lists
  def cartesianProduct[T](xss: List[List[T]]): List[List[T]] = xss match {
    case Nil => List(Nil)
    case h :: t => for(xh <- h; xt <- cartesianProduct(t)) yield xh :: xt
  }
  // Recurse instead of double for loop. Ick.
  def tryAll(noun: Int, verb: Int, opcodes: Vector[Int]): Option[List[Int]] =
    cartesianProduct(List((0 to 100).toList, (0 to 100).toList)).find(
      _ match {
        case (n +: v +: rest) => 
          process(0, opcodes.updated(1, n).updated(2, v)) == Some(19690720)
        case _ => false
      }
    )

  val opcodes = Source
      .fromFile(args(0))
      .getLines
      .filter(_.nonEmpty)
      .flatMap(_.split(","))
      .flatMap(_.toIntOption)
      .toVector

  tryAll(0, 0, opcodes) match {
    case Some(n +: v +: List()) => println(100 * n +v)
    case _ => println("Unable to find solution")
  }
}
