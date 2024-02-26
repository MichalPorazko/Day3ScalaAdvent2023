import scala.annotation.tailrec
import scala.io.Source

object Task3 {


  def main(args: Array[String]): Unit = {

    val task3 = new Task3("", 0, "", 0, false, 0, 0)
    readingFromFile("input" ,task3 )
    fixedEngine(0, task3 )
    println(task3.sum)
  }



  //this functions reads the engine schematic from the file and flattens it as an 1D grid
  def readingFromFile(filePath: String, state: Task3): Unit =
    val stringBuilder = new StringBuilder
    var rowCount = 0

    val fileSource = Source.fromFile(filePath)
    try {
      for (line <- fileSource.getLines())
        rowCount += 1
        if (state.rowLength == 0)
          state.rowLength = line.length
        stringBuilder.append(line.trim)

    } finally {
      fileSource.close()
    }

    state.input = stringBuilder.toString()
    state.rowCount = rowCount

  @tailrec
  def fixedEngine(index: Int = 0, state: Task3): Int =
    if (index >= state.input.length)
      if state.isNumberDetected then
        state.checkPartNumber()
      state.sum
    else
      if (state.input(index).isDigit && state.isNumberDetected)
        state.number += state.input(index)
      else if (!state.input(index).isDigit && state.isNumberDetected)
        state.checkPartNumber()
      else if (state.input(index).isDigit && !state.isNumberDetected)
        state.numberDetected(index)
      fixedEngine(index + 1, state)




  /*def fixedEngine(index: Int = 0, state: Task3): Int =
    if (index >= state.input.length)
      state.sum
    else
      val character = state.input(index)
      if (!state.input(index).isDigit && !state.isNumberDetected)
        fixedEngine(index + 1, state)
      else if (state.input(index).isDigit && !state.isNumberDetected)
        state.numberDetected(index)
        fixedEngine(index + 1, state)
      else if (state.input(index).isDigit && state.isNumberDetected)
        state.number += state.input(index)
        fixedEngine(index + 1, state)
      else !state.input(index).isDigit && state.isNumberDetected
        state.checkPartNumber()
        fixedEngine(index + 1, state)*/

  def isPartNumber(schematic: String, firstDigitPointer: Int, digitLength: Int, rowLength: Int, rowCount: Int): Boolean =
    getCoordinatesAroundNumber(firstDigitPointer, digitLength, rowLength, rowCount).exists(isSymbol(schematic, rowLength))
  
  def getCoordinatesAroundNumber(firstDigitPointer: Int, digitLength: Int, rowLength: Int, rowCount: Int): Seq[(Int, Int)] =
    val fdc = getCoordinates(firstDigitPointer, rowLength)
    val check = isValidCoordinate(rowLength, rowCount)
    (Seq((fdc._1, fdc._2 - 1), (fdc._1, fdc._2 + digitLength)) ++ (-1 to digitLength).flatMap( x =>
      Seq((fdc._1 -1, fdc._2 + x), (fdc._1 + 1 , fdc._2 + x)))).filter(check).sorted

  def isValidCoordinate(rowLength:Int, rowCount: Int)(c: (Int, Int)): Boolean =
    c._1 >= 0 && c._2 < rowLength && c._2 >= 0 && c._1 < rowCount

  def isSymbol(schematic: String, rowLength: Int)(c: (Int, Int)): Boolean =
    val character = schematic.charAt(getPointer(rowLength, c))
    character != '.' && !character.isLetterOrDigit && schematic.nonEmpty
  
  def getCoordinates(pointer: Int, rowLength: Int): (Int, Int) =
    (pointer/rowLength, pointer%rowLength)

  def getPointer(rowLength: Int, coordinates: (Int, Int)): Int =
    rowLength * coordinates._1 + coordinates._2


}

class Task3(
             var input: String = "",
             var sum: Int = 0,
             var number: String = "",
             var firstDigitPointer: Int = 0,
             var isNumberDetected: Boolean = false,
             var rowLength: Int = 0,
             var rowCount: Int = 0
           ) {
  def checkPartNumber(): Unit =
    if Task3.isPartNumber(input, firstDigitPointer, number.length, rowLength, rowCount) then
      sum = sum + number.toInt
    number = ""
    firstDigitPointer = 0
    isNumberDetected = false

  def numberDetected(index: Int): Unit =
    number += input(index)
    firstDigitPointer = index
    isNumberDetected = true
}


