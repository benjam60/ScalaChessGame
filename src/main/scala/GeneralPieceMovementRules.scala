import ChessGame.AllPieces.Knight
import ChessGame._

object GeneralPieceMovementRules {

	def isLegalVerticalMove(board: Board, source: BoardPosition,
	                        destination: BoardPosition)(maxAllowableSpaces : Int) : Boolean =
		(math.abs(source.rankBoardIndex - destination.rankBoardIndex) < maxAllowableSpaces) &&
			(source.fileBoardIndex == destination.fileBoardIndex)
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