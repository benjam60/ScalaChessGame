package ChessGame
import ChessGame.AllPieces.ChessPiece

object BoardUtilityFunctions {

  def boardStateIndexes(rank: Int, file: Char): (Int, Int) = (rank - 1, toBoardIndex(file))

  def formatRow(row: IndexedSeq[ChessPiece]): String = row.map(createChessSquare).mkString + "|\n"

  def createChessSquare(chessPiece: ChessPiece) : String = s"|${chessPiece.displayName}"
  def createChessSquare(rank: Int) : String = s"| ${rank.toString} "
  def createChessSquare(file: Char) : String = s"| $file "
  def formatFiles(row: List[Char]): String = row.map(createChessSquare).mkString + "|\n"
  def toBoardIndex(file : Char): Int = file - AsciiValueOfA
  private val AsciiValueOfA = 65
  val Ranks: List[Int] = (1 to 8).toList
  val Files: List[Char] = ('A' to 'H').toList
}