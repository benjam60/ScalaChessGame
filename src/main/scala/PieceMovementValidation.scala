package ChessGame

import ChessGame.AllPieces.ChessPiece

object PieceMovementValidation {

	def isValidMove(board: Board, source: BoardPosition,
									destination: BoardPosition, turn: Color, pieceToMove: Option[ChessPiece]) : Boolean =
	//Boolean, Option[ErrorType]
		pieceToMove.exists(piece => piece.isValidMove(board, source, destination) &&
			playerIsMovingOwnPiece(turn, piece))

	private def playerIsMovingOwnPiece(turn : Color, piece : ChessPiece) : Boolean = piece.color == turn
}
