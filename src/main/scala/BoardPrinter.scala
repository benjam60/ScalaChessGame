package ChessGame

import ChessGame.AllPieces.ChessPiece
import ChessGame.BoardUtilityFunctions.{createChessSquare, formatFiles, formatRow}
import BoardUtilityFunctions.{Ranks, Files}

object BoardPrinter {

  def print(board: Board) : String = {
    val topLeftCorner: Char = ' '
    val rowWithFileLettersAndSpace = topLeftCorner :: Files
    val boardWithRanks = board.state.zip(Ranks)
    val formattedFileRow = formatFiles(rowWithFileLettersAndSpace)
    val displayableBoardWithRanks =  boardWithRanks.map {
      case (row: IndexedSeq[Option[ChessPiece]], rank: Int) => createChessSquare(rank) + formatRow(row)
    }.mkString
    formattedFileRow + displayableBoardWithRanks
  }
}
