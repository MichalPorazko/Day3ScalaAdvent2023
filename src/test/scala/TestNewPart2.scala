import NewPart2.{Board, Coordinate, findNumber, getSurroundingNumbers}

import scala.io.Source

class TestNewPart2 extends munit.FunSuite{

  //fot those all test I m using the inputTest file

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

  val rowLength = 140
  val rowCount = 3
  val input: String = readingFromFile("inputTest")
  val board = RectangleBoard(input, rowLength, rowCount)

  //this method, copied from the Part 1 just to get the input
  def readingFromFile(filePath: String): String =
    val stringBuilder = new StringBuilder

    val fileSource = Source.fromFile(filePath)
    try {
      for (line <- fileSource.getLines())
        stringBuilder.append(line.trim)

    } finally {
      fileSource.close()
    }
    stringBuilder.toString()

  test("testing the get method of a class extending type Board"){

    //testing if the receiving a pointer from a coordinate is correct
    def getPointer(c: Coordinate): Int =
      c._1 * rowLength + c._2
    assertEquals(getPointer((0,20)), 20)
    assertEquals(getPointer((2,4)), 284)

    assertEquals(board.get((3,5)), None)
    assertEquals(board.get((3,-1)), None)
    assertEquals(board.get((3,7)), None)
  }

  test("testing findDigitsRightOf method"){
    //testing the 491 number coordinate of the 1 is (1,5)
    val number = NewPart2.findDigitsLeftOf("", board, (1,5))
    assertEquals(number, "49")

    assertEquals(NewPart2.findDigitsRightOf("", board, (1,5)), "")
    assertEquals(NewPart2.findDigitsLeftOf("", board, (1,6)), "491")

    assertEquals(NewPart2.findDigitsLeftOf("1", board, (1,5)), "491")

  }

  test("testing the method findNUmber"){

    //testing the edge number in the test input which is the first number 48
    assertEquals(findNumber(board, (0,0)), Some(48))
    assertEquals(findNumber(board, (0,1)), Some(48))
    assertEquals(findNumber(board, (0,2)), None)
    //val haha = findNumber(board, (0,2)), None)

    assertEquals(findNumber(board, (1,3)), Some(491))
    assertEquals(findNumber(board, (1,4)), Some(491))
    assertEquals(findNumber(board, (1,5)), Some(491))
  }

  test("testing the method getSurroundingNumbers"){
    assertEquals(getSurroundingNumbers(board, (0,0)), Seq(48))
  }

}
