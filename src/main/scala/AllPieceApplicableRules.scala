package ChessGame

object AllPieceApplicableRules {

	def sourcePieceExists(board: Board, source: BoardPosition): Boolean = board.get(source).isDefined

	def isMoversPiece(board: Board, source: BoardPosition, colorsTurn: Color): Boolean =
		board.get(source).exists(x => x.colorInstance == colorsTurn)

	def isNotCapturingSelf(board: Board, destination: BoardPosition, moverColor: Color): Boolean =
		board.get(destination).forall(x => x.colorInstance != moverColor)

}
