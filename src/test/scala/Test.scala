class Test extends munit.FunSuite{

  test("testing getCoordinates method with simple 1D and 2D grids"){

    //1 x 1
    assertEquals(Task3.getCoordinates(0, 1), (0,0))

    //2 x 2
    assertEquals(Task3.getCoordinates(0, 2), (0,0))
    assertEquals(Task3.getCoordinates(1, 2), (0,1))
    assertEquals(Task3.getCoordinates(2, 2), (1,0))
    assertEquals(Task3.getCoordinates(3, 2), (1,1))

    //3 x 3
    assertEquals(Task3.getCoordinates(0, 3), (0, 0))
    assertEquals(Task3.getCoordinates(1, 3), (0, 1))
    assertEquals(Task3.getCoordinates(2, 3), (0, 2))
    assertEquals(Task3.getCoordinates(3, 3), (1, 0))
    assertEquals(Task3.getCoordinates(4, 3), (1, 1))
    assertEquals(Task3.getCoordinates(5, 3), (1, 2))
    assertEquals(Task3.getCoordinates(6, 3), (2, 0))
    assertEquals(Task3.getCoordinates(7, 3), (2, 1))
    assertEquals(Task3.getCoordinates(8, 3), (2, 2))
  }
  
  test("testing getPointer method"){
    assertEquals(Task3.getPointer(2, (0,0)), 0)
    assertEquals(Task3.getPointer(2, (0,1)), 1)
    assertEquals(Task3.getPointer(2, (1,0)), 2)
    assertEquals(Task3.getPointer(2, (1,1)), 3)
  }


  test("testing getCoordinates method with edge cases - left top"){
    //egde situation the number is on the left top corner and it's length is 3, the row length is 5
    //example
    // 1 2 3 x
    // x x x x
    assertEquals(Task3.getCoordinates(3, 5), (0, 3))
    assertEquals(Task3.getCoordinates(5, 5), (1, 0))
    assertEquals(Task3.getCoordinates(6, 5), (1, 1))
    assertEquals(Task3.getCoordinates(7, 5), (1, 2))
    assertEquals(Task3.getCoordinates(8, 5), (1, 3))
  }

  test("testing getCoordinates method with edge cases - left top") {
    //egde situation the number is on the left top corner and it's length is 3, the row length is 5
    //example
    // 1 2 3 x
    // x x x x
    assertEquals(Task3.getCoordinates(3, 5), (0, 3))
    assertEquals(Task3.getCoordinates(5, 5), (1, 0))
    assertEquals(Task3.getCoordinates(6, 5), (1, 1))
    assertEquals(Task3.getCoordinates(7, 5), (1, 2))
    assertEquals(Task3.getCoordinates(8, 5), (1, 3))
  }

  test("testing getCoordinates method with edge cases - right top") {
    //egde situation the number is on the right top corner and it's length is 2, the row length is 6
    //example
    // x 3 3
    // x x x
    assertEquals(Task3.getCoordinates(3, 6), (0, 3))
    assertEquals(Task3.getCoordinates(9, 6), (1, 3))
    assertEquals(Task3.getCoordinates(10, 6), (1, 4))
    assertEquals(Task3.getCoordinates(11, 6), (1, 5))
  }

  test("testing getCoordinates method with edge cases - right top") {
    //egde situation the number is on the right top corner and it's length is 2, the row length is 6
    //example
    // x 3 3
    // x x x
    assertEquals(Task3.getCoordinates(3, 6), (0, 3))
    assertEquals(Task3.getCoordinates(9, 6), (1, 3))
    assertEquals(Task3.getCoordinates(10, 6), (1, 4))
    assertEquals(Task3.getCoordinates(11, 6), (1, 5))
  }



}
