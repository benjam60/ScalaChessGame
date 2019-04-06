package ChessGame

import ChessGame.AllPieces._

case class Board(state: IndexedSeq[IndexedSeq[Option[ChessPiece]]] = InitialBoard.state)

sealed trait Color
object Black extends Color
object White extends Color

object InitialBoard {
  private val rowSize = 8
  val state: IndexedSeq[IndexedSeq[Option[ChessPiece]]] = IndexedSeq(
    IndexedSeq(Rook, Knight, Bishop, Queen, King, Bishop, Knight, Rook).map(Option(_)),
    IndexedSeq.fill(rowSize)(WhitePawnCanMoveTwice).map(Option(_)),
    IndexedSeq.fill(rowSize)(None), //TODO BE: Try making 0 to 4 loop
    IndexedSeq.fill(rowSize)(None),
    IndexedSeq.fill(rowSize)(None),
    IndexedSeq.fill(rowSize)(None),
    IndexedSeq.fill(rowSize)(BlackPawnCanMoveTwice).map(Option(_)),
    IndexedSeq(Rook, Knight, Bishop, King, Queen, Bishop, Knight, Rook).map(Option(_))
  )
}