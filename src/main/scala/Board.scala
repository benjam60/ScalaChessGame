package ChessGame

import ChessGame.Constants.NumRows

//put getter and setter here?
case class Board(state: IndexedSeq[IndexedSeq[Option[Color#ChessPiece]]] = InitialBoard.state) {
  def get(position : BoardPosition) : Option[Color#ChessPiece] = state(position.rankBoardIndex)(position.fileBoardIndex)
}

object InitialBoard {

  val state: IndexedSeq[IndexedSeq[Option[Color#ChessPiece]]] = IndexedSeq(
    IndexedSeq(White.Rook, White.Knight, White.Bishop, White.Queen, White.King, White.Bishop, White.Knight, White.Rook).map(Option(_)),
    IndexedSeq.fill(NumRows)(Option(White. Pawn(canMoveTwoSpaces = true))),
    IndexedSeq.fill(NumRows)(None),
    IndexedSeq.fill(NumRows)(None),
    IndexedSeq.fill(NumRows)(None),
    IndexedSeq.fill(NumRows)(None),
    IndexedSeq.fill(NumRows)(Option(Black.Pawn(canMoveTwoSpaces = true))),
    IndexedSeq(Black.Rook, Black.Knight, Black.Bishop, Black.Queen, Black.King, Black.Bishop, Black.Knight, Black.Rook).map(Option(_))
  )

}