package ChessGame

import ChessGame.AllPieces._
import Constants.RowSize
import shapeless.{HList, HNil, Sized, nat}
import shapeless.nat._

//put getter and setter here?
case class Board(state: IndexedSeq[IndexedSeq[Option[ChessPiece]]] = InitialBoard.state) {
	def get(position: BoardPosition): Option[ChessPiece] = state(position.rankBoardIndex)(position.fileBoardIndex)
}

object InitialBoard {
	val state: Sized[IndexedSeq[IndexedSeq[Option[ChessPiece]]], nat._8] =
		Sized.wrap[IndexedSeq[IndexedSeq[Option[ChessPiece]]], _8](IndexedSeq(
			Sized.wrap[IndexedSeq[Option[ChessPiece]], _8](IndexedSeq(WhiteRook, WhiteKnight, WhiteBishop, WhiteQueen, WhiteKing, WhiteBishop, WhiteKnight, WhiteRook).map(Option(_))),
			Sized.wrap[IndexedSeq[Option[ChessPiece]], _8](IndexedSeq.fill(RowSize)(WhitePawnCanMoveTwoSpaces).map(Option(_))),
			createEmptyRow,
			createEmptyRow,
			createEmptyRow,
			createEmptyRow,
			Sized.wrap[IndexedSeq[Option[ChessPiece]], _8](IndexedSeq.fill(RowSize)(BlackPawnCanMoveTwoSpaces).map(Option(_))),
			Sized.wrap[IndexedSeq[Option[ChessPiece]], _8](IndexedSeq(BlackRook, BlackKnight, BlackBishop, BlackKing, BlackQueen, BlackBishop, BlackKnight, BlackRook).map(Option(_))))
		)

	private def createEmptyRow: Sized[IndexedSeq[Option[ChessPiece]], nat._8] = Sized.wrap[IndexedSeq[Option[ChessPiece]], _8](IndexedSeq.fill(RowSize)(None))


}

import shapeless._

object Test extends App {
	val l = List(Sized("Ben", "Ten"))
	val r: Sized[List[Int], _root_.shapeless.Nat._3] = Sized.wrap[List[Int], Nat._3](List(1, 2, 3))

	def func(l: Sized[List[Int], nat._3]): Unit = ()

	func(r)
}