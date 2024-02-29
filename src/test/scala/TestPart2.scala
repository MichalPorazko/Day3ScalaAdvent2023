
import caseClases.Number as CaseNumber

import scala.annotation.tailrec
class TestPart2 extends munit.FunSuite {

  test("testing the method adjacentDigit"){
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


    val number1 = CaseNumber( 1, 2)
    val number2 = CaseNumber( 7, 8)
    val state = new State2(".67.*..67", 0, 4, 3, 3, List(number1, number2))
    val check = Part2.isDigitInNumber((0,1), state)
    assertEquals(check, true)

    /*     . 8 4 2 . .
           . . . * . .
           6 3 3 * . .    */
    val number3 = CaseNumber( 1, 3)
    val number4 = CaseNumber( 12, 14)
    val state2 = new State2(".842.....*..633*..", 0, 9, 6, 3, List(number3, number4))
    assertEquals(Part2.isDigitInNumber((2,2), state2), true)

    /*     . 8 4 2 . .
               . . . * . .
               6 3 3 * . .    */
    val someNUmber = CaseNumber( 1, 3)
    val state3 = new State2(".842.....*..633*..", 0, 9, 6, 3, List(someNUmber))
    //chechikng if 633 is part of the list (isn't)
    assertEquals(Part2.isDigitInNumber((2, 2), state3), false)
  }

  test("testing the method checkTwoAdjacentDigits "){
    /*     . . 4 2 5 .
           . . . * . .
               */
    val number = CaseNumber(2, 4)
    val state = new State2("..425....*..", 0, 9, 6, 2, List(number))
    val check = Part2.checkTwoAdjacentDigits(3, state)
    assertEquals(check, Seq(2,4))

    //checking an two-digit number case
    /*     . . . 2 5 .
           . . . * . .
               */
    val number2digit = CaseNumber(3,4)
    val state2 = new State2("...25....*..", 0, 9, 6, 2, List(number2digit))
    val check2 = Part2.checkTwoAdjacentDigits(3, state2)
    assertEquals(check2, Seq(4))

    // * .
    // . 3
    val edgeNumber = CaseNumber(1,1)
    val edgeState = new State2("*..3", 0, 0, 2, 2, List(number))
    val check3 = Part2.checkTwoAdjacentDigits(3, edgeState)
    assertEquals(check3,  Seq())

  }

  test("testing the method checkTwoAdjacentDigits with an example that's not working"){

    //this case was not working in the test "testing the method digitInNumber" that's why Im testing it'
    //. . . 2 5 4 . .
    //. . . * . . * .
    //. 7 8 . . . . 3
    //we also have to take into account this case

    val number = CaseNumber(23, 23)
    val edgeState = new State2("...254.....*.....78.....", 0, 14, 8, 3, List(number))
    assertEquals(Part2.checkTwoAdjacentDigits(18, edgeState), Seq(17))
  }

  test("testing the inner function adjustAndCollect of the method checkOneAdjacentDigit ") {
    /*     . . . 2 5 4
           . . . * . .
               */
    val number = CaseNumber(2, 5)
    val state = new State2("...254...*..", 0, 9, 6, 2, List(number))

    //the adjustment is 1 because the pointer of 2 is smaller the the pointer of 5
    val adjustment = 1

    //the seq is 3 (pointer of 2) and 4 (pointer of 5) because 2 was detected by the function adjacentDigits and 5 was detected by checkTwoAdjacentDigits
    val seq = Seq(3, 4)

    //this is a fuction written just to simulate the inner function AdjustAndCollect to just test it
    @tailrec
    def adjustAndCollect(currentPointer: Int, seq: Seq[Int]): Seq[Int] =
      if (state.input.charAt(currentPointer).isDigit)
        adjustAndCollect(currentPointer + adjustment, seq :+ currentPointer)
      else
        seq

    //the current pointer is one bigger than 5 which is the pointer of the digit 4 which is 5
    assertEquals(adjustAndCollect(5, seq), Seq(3, 4, 5))

  }

  test("testing the inner function adjustAndCollect of the method checkOneAdjacentDigit ") {

    //or another case where the number is just two digit so we expect the function
    // adjustAndCollect to return the same sequence without changing it

    /*     . 2 9 . . .
           . . . * . .
                   */
    val number2 = CaseNumber(1, 2)
    val state2 = new State2(".29......*..", 0, 9, 6, 2, List(number2))

    val adjustment2 = -1

    val seq2 = Seq(1, 2)

    //this is a fuction written just to simulate the inner function AdjustAndCollect to just test it
    @tailrec
    def adjustAndCollect(currentPointer: Int, seq: Seq[Int]): Seq[Int] =
      if (state2.input.charAt(currentPointer).isDigit)
        adjustAndCollect(currentPointer + adjustment2, seq :+ currentPointer)
      else
        seq

    assertEquals(adjustAndCollect(0, seq2), Seq(1, 2))

  }

  test("testing the inner function adjustAndCollect of the method checkOneAdjacentDigit ") {


    //we also have to check the "edge" cases so when the pointer is close to one of the edges


    /*   3 4 3 * . . .
                   */
    val number0 = CaseNumber(0, 2)
    val state0 = new State2("343*...", 0, 3, 7, 1, List(number0))

    val adjustment0 = -1

    val seq0 = Seq(1, 2)

    //this is a fuction written just to simulate the inner function AdjustAndCollect to just test it
    @tailrec
    def adjustAndCollect(currentPointer: Int, seq: Seq[Int]): Seq[Int] = {
      if (currentPointer >= 0 && currentPointer < state0.rowLength && state0.input.charAt(currentPointer).isDigit)
        adjustAndCollect(currentPointer + adjustment0, seq :+ currentPointer)
      else
        seq.sorted
    }


    val check = adjustAndCollect(0, seq0)
    assertEquals(check,  Seq(0, 1, 2))

  }

  test("testing the method checkOneAdjacentDigit ") {
       //  . . . 2 5 4
       //  . . . * . .

    val number = CaseNumber(2, 5)
    val state = new State2("...254...*..", 0, 9, 6, 2, List(number))


    //the current pointer is one bigger than 5 which is the pointer of the digit 4 which is 5
    assertEquals(Part2.checkOneAdjacentDigits(3, 4, state), Seq(3, 4, 5))



       //  2 3 9 8 . .
       //  . . . * . .

    val number2 = CaseNumber(2, 5)
    val state2 = new State2("2398.....*..", 0, 9, 6, 2, List(number2))


    //the current pointer is one bigger than 5 which is the pointer of the digit 4 which is 5
    assertEquals(Part2.checkOneAdjacentDigits(3, 2, state2), Seq(0, 1, 2, 3))




  }

  test("testing the method digitInNumber"){

    //  . . . 2 5 4 . .
    //  . . . * . . . .
    //  . 7 8 . . . . .

    val number1 = CaseNumber(3, 5)
    val number2 = CaseNumber(17,18)
    val state = new State2("...254.....*.....78.....", 0, 11, 8, 3, List(number1, number2))

    assertEquals(Part2.digitInNumber((0,3), state), number1)
    val check = Part2.digitInNumber((2,2), state)
    assertEquals(check, number2)


    //. . . 2 5 4 . .
    //. . . * . . * .
    //. 7 8 . . . . 3
    //we also have to take into account this case

    val number = CaseNumber(23, 23)
    val edgeState = new State2("...254.....*.....78.....", 0, 14, 8, 3, List(number))
    assertEquals(Part2.digitInNumber((2,7), edgeState), number)
  }




}
