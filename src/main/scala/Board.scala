package ChessGame

import ChessGame.AllPieces._

case class Board(state: IndexedSeq[IndexedSeq[Option[ChessPiece]]] = InitialBoard.state) {
  def get(position : BoardPosition) : Option[ChessPiece] = state(position.rankBoardIndex)(position.fileBoardIndex)
}

object InitialBoard {
  private val rowSize = 8
  val state: IndexedSeq[IndexedSeq[Option[ChessPiece]]] = IndexedSeq(
    IndexedSeq(Rook, Knight, Bishop, Queen, King, Bishop, Knight, Rook).map(Option(_)),
    IndexedSeq.fill(rowSize)(WhitePawnCanMoveTwoSpaces).map(Option(_)),
    IndexedSeq.fill(rowSize)(None), //TODO BE: Try making 0 to 4 loop
    IndexedSeq.fill(rowSize)(None),
    IndexedSeq.fill(rowSize)(None),
    IndexedSeq.fill(rowSize)(None),
    IndexedSeq.fill(rowSize)(BlackPawnCanMoveTwoSpaces).map(Option(_)),
    IndexedSeq(Rook, Knight, Bishop, King, Queen, Bishop, Knight, Rook).map(Option(_))
  )
}