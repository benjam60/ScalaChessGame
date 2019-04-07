package ChessGame

import ChessGame.AllPieces._
import Constants.RowSize

case class Board(state: IndexedSeq[IndexedSeq[Option[ChessPiece]]] = InitialBoard.state) {
  def get(position : BoardPosition) : Option[ChessPiece] = state(position.rankBoardIndex)(position.fileBoardIndex)
}

object InitialBoard {
  val state: IndexedSeq[IndexedSeq[Option[ChessPiece]]] = IndexedSeq(
    IndexedSeq(WhiteRook, WhiteKnight, WhiteBishop, WhiteQueen, WhiteKing, WhiteBishop, WhiteKnight, WhiteRook).map(Option(_)),
    IndexedSeq.fill(RowSize)(WhitePawnCanMoveTwoSpaces).map(Option(_)),
    IndexedSeq.fill(RowSize)(None), //TODO BE: Try making 0 to 4 loop
    IndexedSeq.fill(RowSize)(None),
    IndexedSeq.fill(RowSize)(None),
    IndexedSeq.fill(RowSize)(None),
    IndexedSeq.fill(RowSize)(BlackPawnCanMoveTwoSpaces).map(Option(_)),
    IndexedSeq(BlackRook, BlackKnight, BlackBishop, BlackKing, BlackQueen, BlackBishop, BlackKnight, BlackRook).map(Option(_))
  )
}