package ChessGame

object AllPieceApplicableRules {

	def sourcePieceExists(board : Board, source : BoardPosition) : Boolean = board.get(source).isDefined

	def isMoversPiece(board : Board, source : BoardPosition, colorsTurn : Color) : Boolean =
		board.get(source).exists(_.getColor == colorsTurn)

	def isCapturingNoOne(board: Board, destination : BoardPosition) : Boolean = board.get(destination).isEmpty

	def isCapturingOpponent(board: Board, destination : BoardPosition, moverColor : Color) : Option[Boolean] =
		board.get(destination).map(piece => piece.getColor != moverColor)


}
