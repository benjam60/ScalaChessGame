package ChessGame

import ChessGame.AllPieces.ChessPiece

object PieceMovementValidation {

	def isValidMove(board: Board, source: BoardPosition, destination: BoardPosition,
									turn: Color, pieceToMove: Option[ChessPiece]) : Boolean =
	//Boolean, Option[ErrorType]
		pieceToMove.exists(piece => piece.isValidMove(board, source, destination) &&
			playerIsMovingOwnPiece(turn, piece) && !arePiecesInBetween(board, source, destination))

	private def playerIsMovingOwnPiece(turn : Color, piece : ChessPiece) : Boolean = piece.color == turn

	private def arePiecesInBetween(board: Board, source: BoardPosition, destination: BoardPosition) : Boolean = {
		if (isMovingVertically(source, destination) && verticalDistance(source, destination) > 1) {
			val (minRank, maxRank) = if (source.rankBoardIndex > destination.rankBoardIndex)
				(destination.rankBoardIndex, source.rankBoardIndex) else (source.rankBoardIndex, destination.rankBoardIndex)
			val firstInBetweenSpace = minRank + 1
			(firstInBetweenSpace until maxRank).toList.exists { rank =>
				board.state(rank)(source.fileBoardIndex).isDefined
			}
		} else false
	}

	private def isMovingVertically(source: BoardPosition, destination: BoardPosition) : Boolean =
		source.fileBoardIndex == destination.fileBoardIndex
	private def verticalDistance(source: BoardPosition, destination: BoardPosition) : Int =
		Math.abs(source.rankBoardIndex - destination.rankBoardIndex)

}
