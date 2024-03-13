import scala.annotation.tailrec

object NewPart2 {

  trait Board {
    def get(c: Coordinate): Option[Char]

    def foreach(f: (Char, Coordinate) => Unit): Unit
  }

  type Coordinate = (Int, Int)

  extension (c: Coordinate)
    def left: Coordinate = (c._1, c._2 - 1)

    def right: Coordinate = (c._1, c._2 + 1)

    def up: Coordinate = (c._1 - 1, c._2)

    def down: Coordinate = (c._1 + 1, c._2)

  extension (s1: Seq[Int])
    infix def addMax2(s2: => Seq[Int]): Seq[Int] =
      if (s1.size >= 2) s1 else s1 ++ s2

  def solveEngine(board: Board): Int =
    var sum: Int = 0
    board.foreach((char, coord) => if (char == '*') then sum = sum + getGearRatio(board, coord))
    sum

  def getGearRatio(b: Board, c: Coordinate): Int =
    val numbers = getSurroundingNumbers(b, c)
    if (numbers.size == 2)
    then numbers.head * numbers(1)
    else 0

  def debug[T](context: String, t: T): T = {
    println(s"$context: $t")
    t
  }

  def getSurroundingNumbers(b: Board, c: Coordinate): Seq[Int] =
    debug(
      s"getSurroundingNumbers: $c",
      findNumber(b, c.left).toSeq addMax2 {
        findNumber(b, c.right).toSeq addMax2 {
          findNumber(b, c.up)
            .map(d => Seq(d))
            .getOrElse(findNumber(b, c.up.left).toSeq ++ findNumber(b, c.up.right).toSeq) addMax2 {
            findNumber(b, c.down)
              .map(d => Seq(d))
              .getOrElse(findNumber(b, c.down.left).toSeq ++ findNumber(b, c.down.right).toSeq)
          }
        }
      }
    )

  def findNumber(b: Board, c: Coordinate): Option[Int] =
   val maybeDigit = b.get(c).filter(_.isDigit)
   maybeDigit.map { digit =>
    (findDigitsLeftOf("", b, c) + digit + findDigitsRightOf("", b, c)).toInt
   }

  @tailrec
  def findDigitsLeftOf(existing: String, b: Board, c: Coordinate): String =
   val maybeDigit = b.get(c.left).filter(_.isDigit)
   maybeDigit match {
    case Some(digit) => findDigitsLeftOf(digit +: existing, b, c.left)
    case None => existing
   }

  @tailrec
  def findDigitsRightOf(existing: String, b: Board, c: Coordinate): String =
   val maybeDigit = b.get(c.right).filter(_.isDigit)
   maybeDigit match {
    case Some(digit) => findDigitsRightOf(existing :+ digit, b, c.right)
    case None => existing
  }


  case class RectangleBoard(input: String, rowLength: Int, columnHeight: Int) extends Board {

    def get(c: Coordinate): Option[Char] =
      val n = c._1 * rowLength + c._2
      if (n < 0 || n >= input.length())
      then None
      else Some(input.charAt(n))

    def foreach(f: (Char, Coordinate) => Unit): Unit =
      input.zipWithIndex.foreach((char, index) => f(char, (index / rowLength, index % rowLength)))

  }


}
