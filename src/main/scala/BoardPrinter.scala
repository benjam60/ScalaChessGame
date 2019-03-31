package ChessGame

import ChessGame.AllPieces.ChessPiece
import ChessGame.BoardUtilityFunctions.{createChessSquare, formatFiles, formatRow}

object ChessBoardPrinter {

  def print(chessBoard: Board) : String = {
    val topLeftCorner: Char = ' '
    val rowWithFileLettersAndSpace = topLeftCorner :: ('A' to 'H').toList
    val boardWithRanks = chessBoard.state.zip(1 to 8)
    val formattedFileRow = formatFiles(rowWithFileLettersAndSpace)
    val displayableBoardWithRanks =  boardWithRanks.map {
      case (row: IndexedSeq[ChessPiece], rank: Int) => createChessSquare(rank) + formatRow(row)
    }.mkString
    formattedFileRow + displayableBoardWithRanks
  }

}