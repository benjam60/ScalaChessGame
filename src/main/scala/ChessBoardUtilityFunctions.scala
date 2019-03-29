package ChessGame
import ChessGame.AllPieces.ChessPiece

object ChessBoardUtilityFunctions {

  def boardStateIndexes(rank: Int, file: Char): (Int, Int) = (rank - 1, convertFileToIndex(file))

  def formatRow(row: IndexedSeq[ChessPiece]) = row.map(createChessSquare).mkString + "|\n"

  def createChessSquare(chessPiece: ChessPiece) : String = s"|${chessPiece.displayName}"
  def createChessSquare(rank: Int) : String = s"| ${rank.toString} "
  def createChessSquare(file: Char) : String = s"| $file "


  def formatFiles(row: List[Char]) = row.map(createChessSquare).mkString + "|\n"

  private def convertFileToIndex(file: Char): Int = file.toInt - 65

}