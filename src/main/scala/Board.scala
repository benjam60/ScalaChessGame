package ChessGame

import ChessGame.AllPieces._

case class Board(state: IndexedSeq[IndexedSeq[ChessPiece]])

sealed trait Color {
  def nextTurn : Color
}
object Black extends Color {
  override def nextTurn: Color = White
}
object White extends Color {
  override def nextTurn: Color = Black
}

object InitialBoard {
  private val rowSize = 8
  //need to differentiate
  val state: IndexedSeq[IndexedSeq[ChessPiece]] = IndexedSeq(
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