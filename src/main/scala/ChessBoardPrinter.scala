package ChessGame

import ChessGame.AllPieces.ChessPiece
import ChessGame.ChessBoard
import ChessGame.ChessBoardUtilityFunctions.{createChessSquare, formatFiles, formatRow}

object ChessBoardPrinter {

  def printBoard(chessBoard: ChessBoard) : String = {
    val topLeftCorner: Char = ' '
    val rowWithFileLettersAndSpace = topLeftCorner :: ('A' to 'H').toList
    val boardWithRanks = chessBoard.state.zip(1 to 8)
    val formattedFileRow = formatFiles(rowWithFileLettersAndSpace)
    val displayableBoardWithRanks =  boardWithRanks.map {
      case (row: List[ChessPiece], rank: Int) => createChessSquare(rank) + formatRow(row)
    }.mkString
    formattedFileRow + displayableBoardWithRanks
  }

}
