package ChessGame

import ChessGame.AllPieces._
import ChessGame.ChessBoardUtilityFunctions.{addSpacing, formatFiles, formatRow}
import Color.Color

class ChessBoard(val state: List[List[ChessPiece]], val turn: Color) {

  override def toString: String = {
    val topLeftCorner = "   "
    val fileLettersRow = List(topLeftCorner, "A", "B", "C", "D", "E", "F", "G", "H").map(addSpacing)
    val getRowsWithRank = state.zip(Stream from 1)
    formatFiles(fileLettersRow) + getRowsWithRank.map {
      case (row: List[ChessPiece], rank: Int) => "|" + addSpacing(rank.toString) + formatRow(row)
    }.mkString("")
  }
}

object Color extends Enumeration {
  type Color = Value
  val White, Black = Value
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