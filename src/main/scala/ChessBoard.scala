package ChessGame

import ChessGame.AllPieces._
import ChessGame.ChessBoardUtilityFunctions.{boardStateIndexes, formatFiles, formatRow, addSpacing}

class ChessBoard(boardState: List[List[ChessPiece]]) {
  val state: List[List[ChessPiece]] = boardState

  override def toString: String = {
    val topLeftCorner = "   "
    val fileLettersRow = List(topLeftCorner, "A", "B", "C", "D", "E", "F", "G", "H").map(addSpacing)
    val getRowsWithRank = boardState.zip(Stream from 1)
    formatFiles(fileLettersRow) + getRowsWithRank.map {
      case (row: List[ChessPiece], rank: Int) => "|" + addSpacing(rank.toString) + formatRow(row)
    }.mkString("")
  }
}

object InitialChessBoardState {
  val rowSize = 8
  val get: List[List[ChessPiece]] = List(
    List(Rook, Knight, Bishop, Queen, King, Bishop, Knight, Rook),
    List.fill(rowSize)(PawnCanMoveTwice),
    List.fill(rowSize)(Space),
    List.fill(rowSize)(Space),
    List.fill(rowSize)(Space),
    List.fill(rowSize)(Space),
    List.fill(rowSize)(PawnCanMoveTwice),
    List(Rook, Knight, Bishop, King, Queen, Bishop, Knight, Rook)
  )
}