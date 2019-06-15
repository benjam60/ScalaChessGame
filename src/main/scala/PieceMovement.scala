package ChessGame

import ChessGame.AllPieces._

object PieceMovement {

  def movePiece(board: Board, piece: ChessPiece, source : BoardPosition, destination : BoardPosition): Board = {
			val removedSourcePiece = updateBoard(board, Option.empty[ChessPiece], source)
			updateBoard(removedSourcePiece, changePawnType(piece, source, destination), destination)
		}

	 private def updateBoard(board : Board, piece: Option[ChessPiece], position : BoardPosition): Board = {
		 val updatedRow = board.state(position.rankBoardIndex).updated(position.fileBoardIndex, piece)
		 val updatedState = board.state.updated(position.rankBoardIndex, updatedRow)
		 board.copy(updatedState)
	 }

  private def changePawnType(pieceToMove : ChessPiece, source : BoardPosition, destination : BoardPosition) : Option[ChessPiece] =
    if (Math.abs(destination.rankBoardIndex - source.rankBoardIndex) == 2) { //can abstract as Pawn!!
      if (pieceToMove == BlackPawnCanMoveTwoSpaces) Option(BlackPawnCanMoveOneSpace)
      else if (pieceToMove == WhitePawnCanMoveTwoSpaces) Option(WhitePawnCanMoveOneSpace)
      else Option(pieceToMove)
    } else Option(pieceToMove)
}