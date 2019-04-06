package ChessGame

import ChessGame.AllPieces._
import org.scalactic.{Bad, Good, Or}

object PieceMovement {

  def movePiece(board: Board, source : BoardPosition, destination : BoardPosition): Or[Board, ErrorType] =
		board.get(source).collect { case piece if piece.isValidMove(board, source, destination) =>
			val placedPiece = changePawnType(piece, source, destination)
			val removedSourcePiece = updateBoard(board, Option.empty[ChessPiece], source)
			val placedAtDestination = updateBoard(removedSourcePiece, placedPiece, destination)
			Good(placedAtDestination) }.getOrElse(Bad(InvalidMove))

	 private def updateBoard(board : Board, piece: Option[ChessPiece], position : BoardPosition): Board = {
		 val updatedRow = board.state(position.rankBoardIndex).updated(position.fileBoardIndex, piece)
		 val updatedState = board.state.updated(position.rankBoardIndex, updatedRow)
		 board.copy(updatedState)
	 }

  private def changePawnType(pieceToMove : ChessPiece, source : BoardPosition, destination : BoardPosition) : Option[ChessPiece] =
    if (Math.abs(destination.rankBoardIndex - source.rankBoardIndex) == 2) { //can abstract as Pawn!!
      if (pieceToMove == BlackPawnCanMoveTwoSpaces) Option(BlackPawnCanMoveOneSpace)
      else if (pieceToMove == WhitePawnCanMoveTwoSpaces) Option(WhitePawnCanMoveOneSpace)
      else Option(pieceToMove)
    } else Option(pieceToMove)
}