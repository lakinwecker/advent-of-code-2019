import scala.io.Source

object Day3 extends App {

  case class Point(x: Int, y: Int) {
    def +(that: Point) = Point(this.x + that.x, this.y + that.y)
    def l1norm() = Math.abs(this.x)+Math.abs(this.y)
  }

  case class Range(min: Int, max: Int)
  def orderedRange(x1: Int, x2: Int) =
    Range(Math.min(x1, x2), Math.max(x1, x2))

  case class Line(xRange: Range, yRange: Range)

  def intersectRange(a: Range, b: Range): Option[Int] = {
    val x1 = Math.max(a.min, b.min)
    val x2 = Math.min(a.max, b.max)
    if (x2 < x1) {
      None
    } else {
      // Many points intersect, return closest to origin
      Some(Math.min(Math.abs(x1), Math.abs(x2)))
    }
  }

  def intersect(l1: Line, l2: Line): Option[Point] =
    intersectRange(l1.xRange, l2.xRange)
      .zip(intersectRange(l1.yRange, l2.yRange))
      .map({ case (x, y) => Point(x, y) })

  def parsePoint(s: String): Option[Point] =
    (s.slice(0, 1), s.slice(1, s.length).toIntOption) match {
      case ("U", Some(d)) => Some(Point(0, d))
      case ("D", Some(d)) => Some(Point(0, -d))
      case ("R", Some(d)) => Some(Point(d, 0))
      case ("L", Some(d)) => Some(Point(-d, 0))
      case _ =>  None
    }

  def parsePath(p: List[String]): List[Line] =
    p.flatMap(parsePoint)
      .scanLeft(Point(0, 0))(_+_)
      .sliding(2)
      .map({
        case p1 :: p2 :: List() =>
          Line(orderedRange(p1.x, p2.x), orderedRange(p1.y, p2.y))
      }).toList

  def problem1 = {
    val paths = Source
      .fromFile(args(0))
      .getLines
      .filter(_.nonEmpty)
      .map(_ split ",")
      .map(_.toList)
      .map(parsePath)
      .toList
    val pairs = for(x <- paths(0); y <- paths(1)) yield (x, y)
    val closest = pairs
      .flatMap({ case (l1, l2) => intersect(l1, l2) })
      .map(_.l1norm)
      .toList
      .filter(_ != 0)
      .min
    println(s"Problem1 - closest: $closest")

  }

  def problem2 = {
    println("Problem 2")
  }
  problem1
  problem2
}
