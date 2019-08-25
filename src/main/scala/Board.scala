package ChessGame

import ChessGame.AllPieces._
import Constants.RowSize

//put getter and setter here?
case class Board(state: IndexedSeq[IndexedSeq[Option[ChessPiece]]] = InitialBoard.state) {
  def get(position : BoardPosition) : Option[ChessPiece] = state(position.rankBoardIndex)(position.fileBoardIndex)
}

object InitialBoard {
  val state: IndexedSeq[IndexedSeq[Option[ChessPiece]]] = IndexedSeq(
    IndexedSeq(WhiteRook, WhiteKnight, WhiteBishop, WhiteQueen, WhiteKing, WhiteBishop, WhiteKnight, WhiteRook).map(Option(_)),
    IndexedSeq.fill(RowSize)(WhitePawnCanMoveTwoSpaces).map(Option(_)),
    IndexedSeq.fill(RowSize)(None),
    IndexedSeq.fill(RowSize)(None),
    IndexedSeq.fill(RowSize)(None),
    IndexedSeq.fill(RowSize)(None),
    IndexedSeq.fill(RowSize)(BlackPawnCanMoveTwoSpaces).map(Option(_)),
    IndexedSeq(BlackRook, BlackKnight, BlackBishop, BlackQueen, BlackKing, BlackBishop, BlackKnight, BlackRook).map(Option(_))
  )
}