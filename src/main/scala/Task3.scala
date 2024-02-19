import scala.io.Source

object Task3 {


  //this functions reads the engine schematic from the file and flattens it as an 1D grid
  def readingFromFile(filePath: String, state: Task3): Unit = 
    val stringBuilder = new StringBuilder
    var rowCount = 0

    val fileSource = Source.fromFile(filePath)
    try {
      for (line <- fileSource.getLines()) {
        rowCount += 1
        if (state.rowLength == 0) {
          state.rowLength = line.length
        }
        stringBuilder.append(line.trim)
      }
    } finally {
      fileSource.close()
    }

    state.input = stringBuilder.toString()
    state.rowCount = rowCount

 

  def fixedEngine(input: String, index: Int = 0, state: Task3, rowLength: Int): Int =
    if (index >= input.length)
      state.sum
    else
      val character = input(index)
      if (input(index).isDigit && !state.isNumberDetected)
        state.number += input(index)
        state.firstDigitPointer = index
        fixedEngine(input, index + 1, state, rowLength)
        ??? 
      else if (input(index).isDigit && state.isNumberDetected)
        state.number += input(index)
        fixedEngine(input, index + 1, state, rowLength)
      else
        state.number = ""


  def haha(schematic: String, firstDigitPointer: Int, rowLength: Int): Boolean = {
    val startCoordinates = getCoordinates(firstDigitPointer, rowLength)
    val numberLength = state.number.length
    val endCoordinates = getCoordinates(firstDigitPointer + numberLength - 1, rowLength)

    // List of potential adjacent positions relative to the number
    val adjacentOffsets = List((-1, -1), (-1, 0), (-1, 1), (0, -1), (0, 1), (1, -1), (1, 0), (1, 1))

    adjacentOffsets.exists { case (rowOffset, colOffset) =>
      val adjacentRow = startCoordinates._1 + rowOffset
      val adjacentCol = startCoordinates._2 + colOffset

      // Check boundaries
      if (adjacentRow >= 0 && adjacentRow < schematic.length / rowLength &&
        adjacentCol >= 0 && adjacentCol < rowLength) {
        val pointer = getPointer(rowLength, (adjacentRow, adjacentCol))
        val character = schematic(pointer)

        // Check if the character is a symbol
        character match {
          case '*' | '+' | '#' | '$' => true
          case _ => false
        }
      } else {
        false
      }
    }
  }


  def isPartNumber(schematic: String, firstDigitPointer: Int, rowLength: Int): Boolean =
    val firstDigitCoordinates = getCoordinates(firstDigitPointer, rowLength)
    if (schematic.length > 1)
      val lastDigitCoordinates = getCoordinates(firstDigitPointer + (schematic.length - 1), rowLength)




  def getCoordinates(pointer: Int, rowLength: Int): (Int, Int) =
    (pointer/rowLength, pointer%rowLength)

  def getPointer(rowLength: Int, coordinates: (Int, Int)): Int =
    rowLength * coordinates._1 + coordinates._2


}

class Task3(
             var sum: Int = 0,
             var number: String = "",
             var firstDigitPointer: Int = 0,
             var isNumberDetected: Boolean = false,
             var input: String = "",
             var rowLength: Int = 0,
             var rowCount: Int = 0
           )


