import scala.annotation.tailrec
import caseClases.{Number => CaseNumber}

object Part2 {

  @tailrec
  def fixedEngine(index: Int = 0, state: State2): Int =
    if (index >= state.input.length)
      state.sum
    else
      if (state.input(index).equals("*"))
        state.symbolPointer = index

      fixedEngine(index + 1, state)


  def analyseStarSymbol(pointer: Int, state: State2): Unit =
    adjacentDigits(state) match
      case Seq() => ()
      case Seq(x) => ???

    def symbolSurroundedByDigit(seq: Seq[(Int, Int)]): Unit =
      seq.foreach( x => if isDigitInNumber(x, state)  )

  //this function will return the final number containing the analysed digit
  //we have to get this number because if the gear is right we need it for the sum
  def digitInNumber(digit: (Int, Int), state: State2): CaseNumber =
    val digitPointer = getPointer(state.rowLength, digit)
    checkTwoAdjacentDigits(digitPointer, state) match
      case Seq() => CaseNumber(digitPointer , digitPointer)
      case Seq(oneElement) =>
        val seq = checkOneAdjacentDigits(digitPointer, oneElement, state)
        CaseNumber(seq.min, seq.max)
      case Seq(firstElement, secondElement) => CaseNumber(firstElement, secondElement)


  def checkOneAdjacentDigits(digitPointer: Int, adjacentDigitPointer: Int, state: State2): Seq[Int] =
    val adjustment = if (digitPointer > adjacentDigitPointer) -1 else 1
    val seq = Seq(digitPointer, adjacentDigitPointer).sorted

    @tailrec
    def adjustAndCollect(currentPointer: Int, seq: Seq[Int]): Seq[Int] =
      if (currentPointer >= 0 && currentPointer < state.rowLength && state.input.charAt(currentPointer).isDigit)
        adjustAndCollect(currentPointer + adjustment, seq :+ currentPointer)
      else
        seq.sorted


    adjustAndCollect(adjacentDigitPointer + adjustment, seq)

  def checkTwoAdjacentDigits(digitPointer: Int, state: State2): Seq[Int] =
    Seq(digitPointer - 1, digitPointer + 1).
      filter(x => x < state.input.length && x >= 0).filter(x => state.input.charAt(x).isDigit).sorted

  //this function will check if a coordinates of a digit are already in a number
  def isDigitInNumber(digit: (Int, Int), state2: State2): Boolean =
    val digitPointer = getPointer(state2.rowLength, digit)
    state2.numbers.exists { number =>
      (number.firstDigitPointer to number.lastDigitPointer).contains(digitPointer)
    }


  //checking if there is a digit is adjacent to a * symbol
  def adjacentDigits(state2: State2): Seq[(Int, Int)] =
    getCoordinatesAroundNumber(state2).filter((x, y) => state2.input.charAt(getPointer(state2.rowLength, (x, y))).isDigit)

  def getCoordinatesAroundNumber(state2: State2): Seq[(Int, Int)] =
    val sc = getCoordinates(state2.symbolPointer, state2.rowLength)
    val check = isValidCoordinate(state2.rowLength, state2.rowCount)
    (Seq((sc._1, sc._2 - 1), (sc._1, sc._2 + 1)) ++ (-1 to 2).flatMap(x =>
      Seq((sc._1 - 1, sc._2 + x), (sc._1 + 1, sc._2 + x)))).filter(check).sorted

  def isValidCoordinate(rowLength: Int, rowCount: Int)(c: (Int, Int)): Boolean =
    c._1 >= 0 && c._2 < rowLength && c._2 >= 0 && c._1 < rowCount

  def getCoordinates(pointer: Int, rowLength: Int): (Int, Int) =
    (pointer / rowLength, pointer % rowLength)

  def getPointer(rowLength: Int, coordinates: (Int, Int)): Int =
    rowLength * coordinates._1 + coordinates._2


  //older version of isDigitInNumber, made to practice for comprehension
  def isDigitInNumberDifferentVersion(digit: (Int, Int), state2: State2): Boolean =
    val digitPointer = getPointer(state2.rowLength, digit)
    val checks = for {
      number <- state2.numbers
      if (number.firstDigitPointer to number.lastDigitPointer).contains(digitPointer)
    } yield true
    checks.nonEmpty

}

class State2(
             var input: String = "",
             var sum: Int = 0,
             var symbolPointer: Int = 0,
             var rowLength: Int = 0,
             var rowCount: Int = 0,
             var numbers: List[CaseNumber]
           )
