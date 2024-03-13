import scala.annotation.tailrec

object NewPart2 {

  //trait has two methods, no values
  // so that only a specific implementation has it's own values

  trait Board {
    //why get returns Option???
    def get(c: Coordinate): Option[Char]

    def foreach(f: (Char, Coordinate) => Unit): Unit
  }

  type Coordinate =  (Int, Int)

  //this "extension" is used to add functionalities to any type
  extension (c: Coordinate)
    //
    def left: Coordinate = (c._1, c._2 -1)
    def right: Coordinate = (c._1, c._2+ 1)
    def up: Coordinate = (c._1 -1, c._2 )
    def down: Coordinate = (c._1 + 1, c._2)
    


  extension (s1: Seq[Int])

    // I am using the word infix so taht I can later use this function as a +
    // seq1 addMax seq2

    //this argument is a by-name parameter, it will be used only when needed,
    // not always when  addMax2 is invoked
    infix def addMax2(s2: => Seq[Int]): Seq[Int] =
      if(s1.size <= 2) s1 else s1 ++ s2


  def solveEngine(board: Board): Int =
      var sum: Int = 0
      board.foreach ((char, coordinate) => if board.get(coordinate).equals("*") then sum = sum + getGearRatio(board, coordinate))
      sum

  def getGearRatio(b: Board, c: Coordinate): Int =
    getSurroundingNumbers(b, c) match
      case x: Seq[Int] if x.size == 2 => x.head * x(1)
      case _ => 0

  def getSurroundingNumbers(b: Board, c: Coordinate): Seq[Int] =
    Seq(findDigitsLeftOf("", b, c).toInt) ++ Seq(findDigitsRightOf("", b, c).toInt) addMax2 {
      findNumber(b, c.up).toSeq addMax2{
        findNumber(b,c.down).toSeq
      }
    }

  def findNumber(b: Board, c: Coordinate): Option[Int] =
    val digit = b.get(c).filter(_.isDigit)
    //is a trick what empty String is used because
    // we don't have to worry what one of those functions will add more "digit" than it is needed
    digit.map( digit =>
      (findDigitsLeftOf("", b, c) + digit + findDigitsRightOf("", b, c)).toInt)


  //this function is used to get digits on the left, is we fund a digit we make a recursive call and so on
  @tailrec
  def findDigitsLeftOf(existing: String, b: Board, c: Coordinate): String =
    val digit = b.get(c.left).filter(_.isDigit)
    digit match
      case None => existing
      case Some(d) => findDigitsLeftOf(d + existing, b, c.left)

  //this function is used to get digits on the right, is we fund a digit we make a recursive call and so on
  @tailrec
  def findDigitsRightOf(existing: String, b: Board, c: Coordinate): String =
    val digit = b.get(c.right).filter(_.isDigit)
    digit match
      case None => existing
      case Some(d) => findDigitsRightOf(existing + d, b, c.right)


  case class RectangleBoard(input: String, rowLength: Int, columnHeight: Int) extends Board {

    def get(c: Coordinate): Option[Char] =
      if (c._1 < 0 || c._1 >= columnHeight || c._2 < 0 || c._2 >= rowLength)
        None
      else 
        val n = c._1 * rowLength + c._2
        if n < 0 || n >= input.length()
        then None
        else Some(input.charAt(n))

    def foreach(f: (Char, Coordinate) => Unit): Unit =
      input.zipWithIndex.foreach((char, index) => f(char, (index / rowLength, index % rowLength)))

  }


}
