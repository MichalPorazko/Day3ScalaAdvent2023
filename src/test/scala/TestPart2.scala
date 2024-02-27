
import caseClases.{Number => CaseNumber}
class TestPart2 extends munit.FunSuite {

  test("testing the method isDigitAdjacent"){
    //checking if a digit is correctly detected
    /*  . * 3
        . * .
        . 5 7
    */

    val state1 = new State2(".*3.*..57", 0, 1, 3, 3, List.empty)
    assertEquals(Part2.adjacentDigits(state1), Seq((0,2)))

    /*  . * 3
        . * .
        . 5 7
        */

    val state2 = new State2(".*3.*..57", 0, 4, 3, 3, List.empty)
    assertEquals(Part2.adjacentDigits(state2), Seq((0,2), (2,1), (2,2)))

    /*  . * 3
                */

    val state3 = new State2(".*3", 0, 1, 3, 1, List.empty)
    assertEquals(Part2.adjacentDigits(state3), Seq((0,2)))

    val state4 = new State2("*", 0, 0, 1, 1, List.empty)
    val seq = Part2.adjacentDigits(state4)
    assertEquals(seq, Seq.empty)


    /*  . * 3
        . * .
        . . *
            */
    val state5 = new State2(".*3.*...*", 0, 8, 3, 3, List.empty)
    assertEquals(Part2.adjacentDigits(state5), Seq.empty)


  }

  test("testing the method isDidigtInNumber"){

    /*  . 6 7
        . * .
        . 6 7
            */


    val number1 = CaseNumber("67", 1, 2)
    val number2 = CaseNumber("67", 7, 8)
    val state = new State2(".67.*..67", 0, 4, 3, 3, List(number1, number2))
    val check = Part2.isDigitInNumber((0,1), state)
    assertEquals(check, true)

    /*     . 8 4 2 . .
           . . . * . .
           6 3 3 * . .    */
    val number3 = CaseNumber("842", 1, 3)
    val number4 = CaseNumber("633", 12, 14)
    val state2 = new State2(".842.....*..633*..", 0, 9, 6, 3, List(number3, number4))
    assertEquals(Part2.isDigitInNumber((2,2), state2), true)

    /*     . 8 4 2 . .
               . . . * . .
               6 3 3 * . .    */
    val someNUmber = CaseNumber("842", 1, 3)
    val state3 = new State2(".842.....*..633*..", 0, 9, 6, 3, List(someNUmber))
    //chechikng if 633 is part of the list (isn't)
    assertEquals(Part2.isDigitInNumber((2, 2), state3), false)
  }


}
