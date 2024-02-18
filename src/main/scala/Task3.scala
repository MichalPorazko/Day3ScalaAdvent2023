import caseClases.Point

object Task3 {

  /*val singOnLeft: PartialFunction[String, Boolean] =
    case   */

  def isPartNumber(schematic: String, firstDigitPointer: Int, digitsLength: Int): Boolean =
    ???

  def getCoordinates(pointer: Int, rowLength: Int): (Int, Int) =
    (pointer/rowLength, pointer%rowLength)

  def getPointer(rowLength: Int, coordinates: (Int, Int)): Int =
    rowLength * coordinates._1 + coordinates._2





}
