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
	val destinationHasPiece = (board: Board, legalMove: LegalMove) => board.get(legalMove.destinationPosition).isDefined

	val isValidPawnDiagnolCapture = (board: Board, legalMove: LegalMove) => {
		val distance = calculateDistance(legalMove)
		distance.horizontal == 1 && distance.vertical == 1 &&
			sourceAndDestAreDifferentColors(board, legalMove)
		//		source.rankBoardIndex - destination.rankBoardIndex == color.direction &&
//			calculateHorizontalDistance(source, destination) == 1 &&
			//board.state(destination.rankBoardIndex)(destination.fileBoardIndex).exists(_.getColor == getOther(color))
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

	private def calculateDistance(legalMove: LegalMove) : Distance =
		Distance(
			math.abs(legalMove.sourcePosition.fileBoardIndex - legalMove.destinationPosition.fileBoardIndex),
			math.abs(legalMove.sourcePosition.rankBoardIndex - legalMove.destinationPosition.rankBoardIndex)
		)
	case class Distance(horizontal : Int, vertical : Int)

	private def sourceAndDestAreDifferentColors(board: Board, legalMove: LegalMove) : Boolean = {
		val src = board.get(legalMove.sourcePosition)
		val dest = board.get(legalMove.destinationPosition)
		val areDifferent = for {
			s <- src
			d <- dest
		} yield s.getColor != d.getColor
		areDifferent.getOrElse(true)
	}


}