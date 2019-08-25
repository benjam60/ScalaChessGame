package ChessGame

import ChessGame.AllPieces._

object PieceMovement {

  def movePiece(board: Board, legalMove: LegalMove): Board =
			updateBoard(removeSourcePiece(board, legalMove.sourcePosition),
				changePawnType(board.get(legalMove.sourcePosition).get, legalMove.sourcePosition, legalMove.destinationPosition), legalMove.destinationPosition)

	private def updateBoard(board : Board, piece: Option[ChessPiece], position : BoardPosition): Board = {
		val updatedState = board.state.updated(position.rankBoardIndex, placePiece(board, piece, position))
		board.copy(updatedState)
	}

	private def placePiece(board : Board, piece: Option[ChessPiece], position : BoardPosition) =
		board.state(position.rankBoardIndex).updated(position.fileBoardIndex, piece)

	private def removeSourcePiece(board: Board, source : BoardPosition) : Board =
		updateBoard(board, Option.empty[ChessPiece], source)

  private def changePawnType(pieceToMove : ChessPiece, source : BoardPosition, destination : BoardPosition) : Option[ChessPiece] =
    if (Math.abs(destination.rankBoardIndex - source.rankBoardIndex) == 2) { //can abstract as Pawn!!
      if (pieceToMove == Pawn(true, Black)) Option(Pawn(false, Black))
      else if (pieceToMove == Pawn(true, White)) Option(Pawn(false, White))
      else Option(pieceToMove)
    } else Option(pieceToMove)
}