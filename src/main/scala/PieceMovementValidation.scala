package ChessGame

import ChessGame.AllPieces.ChessPiece

//refactor this so it is easy to add and remove rules
object PieceMovementValidation {

	def arePiecesInBetween(board: Board, source: BoardPosition, destination: BoardPosition) : Boolean = {
		if (isMovingVertically(source, destination) && verticalDistance(source, destination) >= minimumLengthThatCouldHavePieces) {
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
	private val minimumLengthThatCouldHavePieces = 2
}
