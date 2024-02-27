class TestPart1 extends munit.FunSuite{

  test("testing getCoordinates method with simple 1D and 2D grids"){

    //1 x 1
    assertEquals(Part1.getCoordinates(0, 1), (0,0))

    //2 x 2
    assertEquals(Part1.getCoordinates(0, 2), (0,0))
    assertEquals(Part1.getCoordinates(1, 2), (0,1))
    assertEquals(Part1.getCoordinates(2, 2), (1,0))
    assertEquals(Part1.getCoordinates(3, 2), (1,1))

    //3 x 3
    assertEquals(Part1.getCoordinates(0, 3), (0, 0))
    assertEquals(Part1.getCoordinates(1, 3), (0, 1))
    assertEquals(Part1.getCoordinates(2, 3), (0, 2))
    assertEquals(Part1.getCoordinates(3, 3), (1, 0))
    assertEquals(Part1.getCoordinates(4, 3), (1, 1))
    assertEquals(Part1.getCoordinates(5, 3), (1, 2))
    assertEquals(Part1.getCoordinates(6, 3), (2, 0))
    assertEquals(Part1.getCoordinates(7, 3), (2, 1))
    assertEquals(Part1.getCoordinates(8, 3), (2, 2))
  }
  
  test("testing getPointer method"){
    assertEquals(Part1.getPointer(2, (0,0)), 0)
    assertEquals(Part1.getPointer(2, (0,1)), 1)
    assertEquals(Part1.getPointer(2, (1,0)), 2)
    assertEquals(Part1.getPointer(2, (1,1)), 3)
  }

  test("testing getCoordinates method with edge cases - left top"){
    //egde situation the number is on the left top corner and it's length is 3, the row length is 5
    //example
    // 1 2 3 x
    // x x x x
    assertEquals(Part1.getCoordinates(3, 5), (0, 3))
    assertEquals(Part1.getCoordinates(5, 5), (1, 0))
    assertEquals(Part1.getCoordinates(6, 5), (1, 1))
    assertEquals(Part1.getCoordinates(7, 5), (1, 2))
    assertEquals(Part1.getCoordinates(8, 5), (1, 3))
  }

  test("testing getCoordinates method with edge cases - left top") {
    //egde situation the number is on the left top corner and it's length is 3, the row length is 5
    //example
    // 1 2 3 x
    // x x x x
    assertEquals(Part1.getCoordinates(3, 5), (0, 3))
    assertEquals(Part1.getCoordinates(5, 5), (1, 0))
    assertEquals(Part1.getCoordinates(6, 5), (1, 1))
    assertEquals(Part1.getCoordinates(7, 5), (1, 2))
    assertEquals(Part1.getCoordinates(8, 5), (1, 3))
  }

  test("testing getCoordinates method with edge cases - right top") {
    //egde situation the number is on the right top corner and it's length is 2, the row length is 6
    //example
    // x 3 3
    // x x x
    assertEquals(Part1.getCoordinates(3, 6), (0, 3))
    assertEquals(Part1.getCoordinates(9, 6), (1, 3))
    assertEquals(Part1.getCoordinates(10, 6), (1, 4))
    assertEquals(Part1.getCoordinates(11, 6), (1, 5))
  }

  test("testing getCoordinates method with edge cases - right top") {
    //egde situation the number is on the right top corner and it's length is 2, the row length is 6
    //example
    // x 3 3
    // x x x
    assertEquals(Part1.getCoordinates(3, 6), (0, 3))
    assertEquals(Part1.getCoordinates(9, 6), (1, 3))
    assertEquals(Part1.getCoordinates(10, 6), (1, 4))
    assertEquals(Part1.getCoordinates(11, 6), (1, 5))
  }

  test("testing getCoordinatesAroundNumber method"){
    assertEquals(Part1.getCoordinatesAroundNumber(0, 1,1,1), Seq.empty)
    assertEquals(Part1.getCoordinatesAroundNumber(0, 2, 2,1), Seq.empty)
    assertEquals(Part1.getCoordinatesAroundNumber(1, 1, 2,1), List((0,0)))

    assertEquals(Part1.getCoordinatesAroundNumber(4, 1, 3,3), List((0,0), (0,1), (0,2), (1,0), (1,2), (2,0), (2,1), (2,2)))

    assertEquals(Part1.getCoordinatesAroundNumber(5, 2, 4,3), List((0,0), (0,1), (0,2), (0,3), (1,0), (1,3), (2,0), (2,1), (2,2), (2,3)))
  }

  test("understanding how to get character of a pointer having the rowLenght and the coordinates "){
    val character = "48............491.842....*...*...363.....961".charAt(Part1.getPointer(11, (1,3)))
    assertEquals(character, '4')

    val character2 = "48............491.842....*...*...363.....961".charAt(Part1.getPointer(11, (2, 3)))
    assertEquals(character2, '*')

  }

  test("testing the method isSymbol"){
    assertEquals(Part1.isSymbol("...", 3)((0,0)), false)

    assertEquals(Part1.isSymbol("#..", 3)((0,0)), true)
    assertEquals(Part1.isSymbol("#...3&", 3)((1,2)), true)
    assertEquals(Part1.isSymbol("#...3&", 3)((1,1)), false)
    assertEquals(Part1.isSymbol("#...ó&", 3)((1,1)), false)

    assertEquals(Part1.isSymbol("48............491.842....*...*...363.....961", 11)((2,3)), true)

    //assertEquals(Task3.isSymbol("", 0)((0,0)), false)
  }

  test("testing the method getCoordinatesAroundNumber with whole input"){

    /*I will be testing this method on the input:"
    "48........."
    "...491.842."
    "...*...*..."
    "363.....961"*/

    //Analysing the 48
    val fdc = Part1.getCoordinates(0, 11)
    assertEquals(fdc, (0, 0))

    //the equivalent to Seq((fdc._1, fdc._2 - 1)
    assertEquals((fdc._1, fdc._2 - 1), (0, -1))

    //(fdc._1, fdc._2 + digitLength)
    assertEquals((fdc._1, fdc._2 + 2), (0, 2))

    assertEquals("22".length, 2)

    val secondPartOfCoordinates =
      (-1 to 2).flatMap(x => Seq((fdc._1 - 1, fdc._2 + x), (fdc._1 + 1, fdc._2 + x)))

    assertEquals(secondPartOfCoordinates, Seq((-1,-1), (1,-1), (-1,0), (1,0), (-1,1), (1,1), (-1,2), (1,2)))

  }

  test("testing the method isPartNumber"){
    /*I will be testing this method on the input:"
    "48........."
    "...491.842."
    "...*...*..."
    "363.....961"*/

    //at first trying to understand the function exists
    val exists = Seq("2342", "sfdsdfs", "#$^", "..").exists(x => x.contains('.'))
    assertEquals(exists, true)

    val is48partNumber = Part1.isPartNumber("48............491.842....*...*...363.....961", 0, 2, 11, 4)
    assertEquals(is48partNumber, false)

    val is491partNumber = Part1.isPartNumber("48............491.842....*...*...363.....961", 14, 3, 11, 4)
    assertEquals(is491partNumber, true)

  }

  test("and finnaly testing the method fixedEngine"){

    /*
        "48........."
        "...491.842."
        "...*...*..."
        "363.....961"*/

    val task3 = new State("48............491.842....*...*...363.....961", sum = 0, number = "", firstDigitPointer = 0, isNumberDetected = false, rowLength = 11, rowCount = 4)
    val fixedEngine = Part1.fixedEngine(index = 0, task3)
    assertEquals(fixedEngine, 2657)
  }

  test("testing the whole program including the readingFromFile function"){
    //for testing the program I made I example inputTest file, the sum from this test file is more or less
    // 10024 (I did the counting manually)
    val task3 = new State("", 0, "", 0, false, 0, 0)
    Part1.readingFromFile("inputTest", task3)

    assertEquals(task3.rowLength, 140)

    val state = new State("", 0, "", 0, false, 0, 0)
    Part1.readingFromFile("input", state)
    assertEquals(state.rowLength, 140)
  }



}
