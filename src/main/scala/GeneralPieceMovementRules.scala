package ChessGame


object GeneralPieceMovementRules {

	val isNotCapturingPiece = (board: Board, legalMove: LegalMove) => board.get(legalMove.destinationPosition).isEmpty
	val isLegalVerticalMoveOneSpace = (board: Board, legalMove: LegalMove) => isLegalVerticalMove(board, legalMove)(1)
	val isLegalVerticalMoveTwoSpaces = (board: Board, legalMove: LegalMove) => isLegalVerticalMove(board, legalMove)(2)
	val correctColorDirection = (pawn : Color#ChessPiece, legalMove: LegalMove) => {
		val diff = legalMove.sourcePosition.rankBoardIndex - legalMove.destinationPosition.rankBoardIndex
		pawn match {
			case White.Pawn(_) => diff < 0
			case Black.Pawn(_) => diff > 0
		}
	}

	val destinationHasPiece = (board: Board, legalMove: LegalMove) => board.get(legalMove.destinationPosition).isDefined

	val isValidPawnDiagnolCapture = (board: Board, legalMove: LegalMove) => {
		val distance = calculateDistance(legalMove)
		distance.horizontal == 1 && distance.vertical == 1 &&
			sourceAndDestAreDifferentColors(board, legalMove) //different rule
	}

	val isLegalHorizontalMoveOneSpace = (board: Board, legalMove: LegalMove) => calculateDistance(legalMove) == Distance(1, 0)

	val isLegalDiagonalMoveOneSpace = (board: Board, legalMove: LegalMove) => calculateDistance(legalMove) == Distance(1, 1)


	private def isLegalVerticalMove(board: Board, legalMove: LegalMove)(maxAllowableSpaces : Int) : Boolean =
		(math.abs(legalMove.sourcePosition.rankBoardIndex - legalMove.destinationPosition.rankBoardIndex) <= maxAllowableSpaces) &&
			(legalMove.sourcePosition.fileBoardIndex == legalMove.destinationPosition.fileBoardIndex)

	def noPiecesInBetween(board : Board, source : BoardPosition, destination: BoardPosition) : Boolean =
		board.get(source).exists { piece: Color#ChessPiece =>
			piece match {
				case White.Knight | Black.Knight => PieceMovementValidation.arePiecesInBetween(board, source, destination)
				case _ => true
			}
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
		} yield s.colorInstance != d.colorInstance
		areDifferent.getOrElse(true)
	}

}