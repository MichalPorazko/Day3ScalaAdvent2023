object Main {

  def main(args: Array[String]): Unit = {
    val part1 = new State("", 0, "", 0, false, 0, 0)
    Part1.readingFromFile("input", part1)
    Part1.fixedEngine(0, part1)
    println(part1.sum)

    val part2 =  new State2(part1.input, 0, 0, part1.rowLength, part1.rowCount, Seq.empty)
    Part2.fixedEngine(0, part2)
    println(part2.sum)
  }

}
