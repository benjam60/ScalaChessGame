package ChessGame

import ChessGame.AllPieces._
import ChessGame.InputMoveValidation.BoardPosition

case class Board(state: IndexedSeq[IndexedSeq[Option[ChessPiece]]] = InitialBoard.state) {
  def get(position : BoardPosition) : Option[ChessPiece] = state(position.rankBoardIndex)(position.fileBoardIndex)
}

sealed trait Color {
  val direction : Int
  override def toString: String
}
object Black extends Color {
  private val UpTheBoard = 1
  override val direction: Int = UpTheBoard
  override def toString: String = "Black"
}
object White extends Color {
  private val DownTheBoard = -1
  override val direction: Int = DownTheBoard
  override def toString: String = "White"
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