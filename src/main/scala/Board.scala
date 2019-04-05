package ChessGame

import ChessGame.AllPieces._

case class Board(state: IndexedSeq[IndexedSeq[Option[ChessPiece]]])

sealed trait Color
object Black extends Color
object White extends Color

object InitialBoard {
  private val rowSize = 8
  val state: IndexedSeq[IndexedSeq[Option[ChessPiece]]] = IndexedSeq(
    IndexedSeq(Rook, Knight, Bishop, Queen, King, Bishop, Knight, Rook).map(c => Option(c)),
    IndexedSeq.fill(rowSize)(WhitePawnCanMoveTwice).map(c => Option(c)),
    IndexedSeq.fill(rowSize)(None),
    IndexedSeq.fill(rowSize)(None),
    IndexedSeq.fill(rowSize)(None),
    IndexedSeq.fill(rowSize)(None),
    IndexedSeq.fill(rowSize)(BlackPawnCanMoveTwice).map(c => Option(c)),
    IndexedSeq(Rook, Knight, Bishop, King, Queen, Bishop, Knight, Rook).map(c => Option(c))
  )
}