package ChessGame
import ChessGame.AllPieces.ChessPiece

object ChessBoardUtilityFunctions {
  def addSpacing(boardCell: String) =
    if (boardCell.size != StandardPieceSize) " " + boardCell + " "
    else boardCell

  def boardStateIndexes(rank: Int, file: Char): (Int, Int) = (rank - 1, convertFileToIndex(file))

  def formatRow(row: List[ChessPiece]) = "|" + row.map(piece => addSpacing(piece.displayName)).mkString("|") + "|\n"

  def formatFiles(row: List[String]) = "|" + row.map(file => addSpacing(file)).mkString("|") + "|\n"

  def convertFileToIndex(file: Char): Int = file.toInt - 65

  private val StandardPieceSize = 3
}