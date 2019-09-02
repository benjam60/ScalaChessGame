package ChessGame //TODO BE: Fix package name
import ChessGame.GeneralPieceMovementRules._
import ChessGame.PieceMovementValidation.arePiecesInBetween

object AllPieces {

	def isValidMove(chessPiece: Color#ChessPiece, board: Board, source: BoardPosition, destination: BoardPosition): Boolean = {
		val legalMove = LegalMove(source, destination)
		chessPiece match {
			case White.Pawn(canMoveTwoSpaces: Boolean) =>
				val verticalMovementRule = if (canMoveTwoSpaces) isLegalVerticalMoveTwoSpaces else isLegalVerticalMoveOneSpace
				(!arePiecesInBetween(board, source, destination) &&
					List(verticalMovementRule, correctColorDirection, isNotCapturingPiece).forall(f => f(board, legalMove))) ||
					List(correctColorDirection, isValidPawnDiagnolCapture, destinationHasPiece).forall(f => f(board, legalMove))
			case Black.Pawn(canMoveTwoSpaces: Boolean) =>
				val verticalMovementRule = if (canMoveTwoSpaces) isLegalVerticalMoveTwoSpaces else isLegalVerticalMoveOneSpace
				(!arePiecesInBetween(board, source, destination) &&
					List(verticalMovementRule, correctColorDirection, isNotCapturingPiece).forall(f => f(board, legalMove))) ||
					List(correctColorDirection, isValidPawnDiagnolCapture, destinationHasPiece).forall(f => f(board, legalMove))
			case Black.Knight || White.Knight => (calculateVerticalDistance(source, destination), calculateHorizontalDistance(source, destination)) match {
				case (1, 2) => true
				case (2, 1) => true
				case _ => false
			}
			case Black.Rook || White.Rook => !arePiecesInBetween(board, source, destination) &&
				(calculateHorizontalDistance(source, destination) != 0 ^ calculateVerticalDistance(source, destination) != 0)
			case Black.Queen || White.Queen => isValidMove(Black.Rook, board, source, destination) || isValidMove(Black.Bishop, board, source, destination)
			case Black.King || White.King => List(isLegalVerticalMoveOneSpace, isLegalHorizontalMoveOneSpace, isLegalDiagonalMoveOneSpace).exists(f => f(board, legalMove))
			case Black.Bishop || White.Bishop => calculateHorizontalDistance(source, destination) == calculateVerticalDistance(source, destination) &&
				!arePiecesInBetweenDiagonally(board, source, destination)
		}
	}

	private def calculateVerticalDistance(source: BoardPosition, destination: BoardPosition): Int =
		math.abs(source.rankBoardIndex - destination.rankBoardIndex)

	private def calculateHorizontalDistance(source: BoardPosition, destination: BoardPosition): Int =
		math.abs(source.fileBoardIndex - destination.fileBoardIndex)

	private def arePiecesInBetweenDiagonally (board: Board, source: BoardPosition,
	                                          destination: BoardPosition): Boolean =
		(source.rankBoardIndex, source.fileBoardIndex, destination.rankBoardIndex, destination.fileBoardIndex) match {
			case (srcRank, srcFile, destRank, destFile) if srcRank > destRank && srcFile < destFile => {
				val filesToTraverse = (srcFile + 1 until destFile).toList
				(srcRank - 1 to destRank + 1 by - 1).toList.zip (filesToTraverse).exists {
					case (r: Int, f: Int) => board.state (r) (f).isDefined
				}
			}
			case (srcRank, srcFile, destRank, destFile) if srcRank < destRank && srcFile < destFile => {
				(1 until destRank - srcRank).toList.exists {
					index =>
						board.state (srcRank + index) (srcFile + index).isDefined
				}
			}
			case _ => false
		}

}
