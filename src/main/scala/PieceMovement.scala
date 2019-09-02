package ChessGame

object PieceMovement {

  def movePiece(board: Board, legalMove: LegalMove): Board =
			updateBoard(removeSourcePiece(board, legalMove.sourcePosition),
				changePawnType(board.get(legalMove.sourcePosition).get, legalMove), legalMove.destinationPosition)

	private def updateBoard(board : Board, piece: Option[Color#ChessPiece], position : BoardPosition): Board = {
		val updatedState = board.state.updated(position.rankBoardIndex, placePiece(board, piece, position))
		board.copy(updatedState)
	}

	private def placePiece(board : Board, piece: Option[Color#ChessPiece], position : BoardPosition) =
		board.state(position.rankBoardIndex).updated(position.fileBoardIndex, piece)

	private def removeSourcePiece(board: Board, source : BoardPosition) : Board =
		updateBoard(board, Option.empty[Color#ChessPiece], source)

  private def changePawnType(pieceToMove : Color#ChessPiece, legalMove: LegalMove) : Option[Color#ChessPiece] =
    if (Math.abs(legalMove.destinationPosition.rankBoardIndex - legalMove.sourcePosition.rankBoardIndex) == 2) { //can abstract as Pawn!!
      if (pieceToMove == Black.Pawn(true)) Option(Black.Pawn(false))
      else if (pieceToMove == White.Pawn(true)) Option(White.Pawn(false))
      else Option(pieceToMove)
    } else Option(pieceToMove)
}