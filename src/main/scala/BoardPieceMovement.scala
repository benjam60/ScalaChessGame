package ChessGame

import ChessGame.AllPieces._
import ChessGame.InputMoveValidation.BoardPosition
import org.scalactic.{Bad, Good, Or}

object BoardPieceMovement {

  def movePiece(board: Board, source : BoardPosition, destination : BoardPosition): Or[Board, ErrorType] = {
    val pieceToMove = board.state(source.rankBoardIndex)(source.fileBoardIndex)
		pieceToMove.collect { case piece if piece.isValidMove(source, destination) =>
			Good(updateBoard(board, source, destination, piece)) }.getOrElse(Bad(InvalidMove))
  }

  private def updateBoard(board: Board, source : BoardPosition, destination : BoardPosition,
                          pieceToMove : ChessPiece): Board = {
    val destRowToUpdate = board.state(destination.rankBoardIndex)
    val updatedDestRow = destRowToUpdate.updated(destination.fileBoardIndex,
      changePawnType(pieceToMove, destination.rankBoardIndex - source.rankBoardIndex)) //add absolute value to test
    val movedPieceBoard = board.state.updated(destination.rankBoardIndex, updatedDestRow)
    val srcRow = board.state(source.rankBoardIndex)
    val clearedSrc = movedPieceBoard.updated(source.rankBoardIndex, srcRow.updated(source.fileBoardIndex, None))
    Board(clearedSrc)
  }

  private def changePawnType(pieceToMove : ChessPiece, verticalDistance : Int) : Option[ChessPiece] =
    if (verticalDistance > 1) { //can abstract as Pawn!!
      if (pieceToMove == BlackPawnCanMoveTwice) Option(BlackPawnCanMoveOnce)
      else if (pieceToMove == WhitePawnCanMoveTwice) Option(WhitePawnCanMoveOnce)
      else Option(pieceToMove)
    } else Option(pieceToMove)
}