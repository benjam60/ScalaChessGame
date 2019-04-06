package ChessGame

case class BoardPosition(rankBoardIndex : Int, fileBoardIndex : Int)
object BoardPosition {
	def apply(userInputtedRank : Int, userInputtedFile : Char): BoardPosition =
		BoardPosition(makeZeroBasedIndex(userInputtedRank), toBoardIndex(userInputtedFile))

	private def makeZeroBasedIndex(input : Int) : Int = input - 1
	private def toBoardIndex(file : Char) : Int = file.toInt - AsciiValueOfA
	private val AsciiValueOfA = 65
}
