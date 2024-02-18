class Test extends munit.FunSuite{

  test("testing getCoordinates method"){
    assertEquals(Task3.getCoordinates(0, 1), (0,0))

    assertEquals(Task3.getCoordinates(0, 2), (0,0))
    assertEquals(Task3.getCoordinates(1, 2), (0,1))
    assertEquals(Task3.getCoordinates(2, 2), (1,0))
    assertEquals(Task3.getCoordinates(3, 2), (1,1))

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

}
