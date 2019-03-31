package ChessGame

import ChessGame.AllPieces._
import ChessGame.BoardUtilityFunctions.{createChessSquare, formatFiles, formatRow}
import ChessGame.Color.Color

case class Board(state: IndexedSeq[IndexedSeq[ChessPiece]], turn: Color)

object Color extends Enumeration {
  type Color = Value
  val White, Black = Value
}

object InitialInternalChessBoardState {
  private val rowSize = 8
  //need to differentiate
  val get: IndexedSeq[IndexedSeq[ChessPiece]] = IndexedSeq(
    IndexedSeq(Rook, Knight, Bishop, Queen, King, Bishop, Knight, Rook),
    IndexedSeq.fill(rowSize)(WhitePawnCanMoveTwice),
    IndexedSeq.fill(rowSize)(Space),
    IndexedSeq.fill(rowSize)(Space),
    IndexedSeq.fill(rowSize)(Space),
    IndexedSeq.fill(rowSize)(Space),
    IndexedSeq.fill(rowSize)(BlackPawnCanMoveTwice),
    IndexedSeq(Rook, Knight, Bishop, King, Queen, Bishop, Knight, Rook)
  )
}