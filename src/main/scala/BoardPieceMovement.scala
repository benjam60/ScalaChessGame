package ChessGame

import ChessGame.AllPieces._
import ChessGame.InputMoveValidation.BoardPosition
import org.scalactic.{Bad, Good, Or}

object BoardPieceMovement {

  def movePiece(board: Board, source : BoardPosition, destination : BoardPosition): Or[Board, ErrorType] = {
    val pieceToMove = board.state(source.rankBoardIndex)(source.fileBoardIndex)
		pieceToMove.collect { case piece if piece.isValidMove(board, piece, source, destination) =>
			Good(updateBoard(board, source, destination, piece)) }.getOrElse(Bad(InvalidMove))
  }

	private def updateBoard(board: Board, source : BoardPosition, destination : BoardPosition,
													pieceToMove : ChessPiece): Board = {
		val piece = changePawnType(pieceToMove, source, destination)
		board.updateBoard(Option.empty[ChessPiece], source).updateBoard(piece, destination)
	}

	private implicit class BoardExtensions(board : Board) {
	 def updateBoard(piece: Option[ChessPiece], position : BoardPosition): Board = {
		 val updatedRow = board.state(position.rankBoardIndex).updated(position.fileBoardIndex, piece)
		 val updatedState = board.state.updated(position.rankBoardIndex, updatedRow)
		 board.copy(updatedState)
	 }
	}

  private def changePawnType(pieceToMove : ChessPiece, source : BoardPosition, destination : BoardPosition) : Option[ChessPiece] =
    if (Math.abs(destination.rankBoardIndex - source.rankBoardIndex) > 1) { //can abstract as Pawn!!
      if (pieceToMove == BlackPawnCanMoveTwice) Option(BlackPawnCanMoveOnce)
      else if (pieceToMove == WhitePawnCanMoveTwice) Option(WhitePawnCanMoveOnce)
      else Option(pieceToMove)
    } else Option(pieceToMove)
}