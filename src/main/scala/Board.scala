package ChessGame

import ChessGame.AllPieces._
import Constants.RowSize

//put getter and setter here?
case class Board(state: IndexedSeq[IndexedSeq[Option[ChessPiece]]] = InitialBoard.state) {
  def get(position : BoardPosition) : Option[ChessPiece] = state(position.rankBoardIndex)(position.fileBoardIndex)
}

object InitialBoard {
  val state: IndexedSeq[IndexedSeq[Option[ChessPiece]]] = IndexedSeq(
    IndexedSeq(Rook(White), Knight(White), Bishop(White), Queen(White), King(White), Bishop(White), Knight(White), Rook(White)).map(Option(_)),
    IndexedSeq.fill(RowSize)(Option(Pawn(canMoveTwoSpaces = true, White))),
    IndexedSeq.fill(RowSize)(None),
    IndexedSeq.fill(RowSize)(None),
    IndexedSeq.fill(RowSize)(None),
    IndexedSeq.fill(RowSize)(None),
    IndexedSeq.fill(RowSize)(Option(Pawn(canMoveTwoSpaces = true, Black))),
    IndexedSeq(Rook(Black), Knight(Black), Bishop(Black), Queen(Black), King(Black), Bishop(Black), Knight(Black), Rook(Black)).map(Option(_))
  )

}