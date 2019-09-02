package ChessGame

import ChessGame.BoardUtilityFunctions.{createChessSquare, formatFiles, formatRow}
import Constants.{Ranks, Files}

object BoardPrinter {

  def toString(board: Board) : String = {
    val topLeftCorner: Char = ' '
    val rowWithFileLettersAndSpace = topLeftCorner :: Files
    val boardWithRanks = board.state.zip(Ranks)
    val displayableBoardWithRanks =  boardWithRanks.map {
      case (row: IndexedSeq[Option[Color#ChessPiece]], rank: Int) => createChessSquare(rank) + formatRow(row)
    }.mkString
    formatFiles(rowWithFileLettersAndSpace) + displayableBoardWithRanks
  }
}
