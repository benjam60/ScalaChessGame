package ChessGame

import ChessGame.AllPieces.Knight

object GeneralPieceMovementRules {

	val isNotCapturingPiece = (board: Board, legalMove: LegalMove) => board.get(legalMove.destinationPosition).isEmpty
	val isLegalVerticalMoveOneSpace = (board: Board, legalMove: LegalMove) => isLegalVerticalMove(board, legalMove)(1)
	val isLegalVerticalMoveTwoSpaces = (board: Board, legalMove: LegalMove) => isLegalVerticalMove(board, legalMove)(2)
	val correctColorDirection = (board: Board, legalMove: LegalMove) => {
		val color = board.get(legalMove.sourcePosition).get.getColor
		val diff = legalMove.sourcePosition.rankBoardIndex - legalMove.destinationPosition.rankBoardIndex
		(color.direction < 0 && diff < 0) || (color.direction > 0 && diff > 0)
	}

	private def isLegalVerticalMove(board: Board, legalMove: LegalMove)(maxAllowableSpaces : Int) : Boolean =
		(math.abs(legalMove.sourcePosition.rankBoardIndex - legalMove.destinationPosition.rankBoardIndex) <= maxAllowableSpaces) &&
			(legalMove.sourcePosition.fileBoardIndex == legalMove.destinationPosition.fileBoardIndex)
//
//	def isCorrectDirection(source : BoardPosition, destination: BoardPosition, mover : Color) : Boolean = {
//
//	}


	def noPiecesInBetween(board : Board, source : BoardPosition, destination: BoardPosition) : Boolean =
		board.get(source).exists { piece: AllPieces.ChessPiece =>
			if (!piece.isInstanceOf[Knight]) PieceMovementValidation.arePiecesInBetween(board, source, destination: BoardPosition)
			else true
		}

}