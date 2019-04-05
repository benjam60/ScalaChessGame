package ChessGame

import ChessGame.AllPieces._
import ChessGame.InputMoveValidation.BoardPosition
import org.scalactic.{Bad, Good, Or}

object BoardPieceMovement {

  def movePiece(board: Board, source : BoardPosition, destination : BoardPosition): Or[Board, ErrorType] = {
    val pieceToMove = board.state(source.rank.boardStateIndex)(source.file.boardStateIndex)
		pieceToMove.collect { case piece if piece.isValidMove(source, destination) =>
			Good(updateBoard(board, source, destination, piece)) }.getOrElse(Bad(InvalidMove))
  }

  private def updateBoard(board: Board, source : BoardPosition, destination : BoardPosition,
                          pieceToMove : ChessPiece): Board = {
    val destRowToUpdate = board.state(destination.rank.boardStateIndex)
    val updatedDestRow = destRowToUpdate.updated(destination.file.boardStateIndex,
      pieceToSet(pieceToMove, destination.rank.boardStateIndex - source.rank.boardStateIndex)) //add absolute value to test
    val movedPieceBoard = board.state.updated(destination.rank.boardStateIndex, updatedDestRow)
    val srcRow = board.state(source.rank.boardStateIndex)
    val clearedSrc = movedPieceBoard.updated(source.rank.boardStateIndex,
			srcRow.updated(source.file.boardStateIndex, None))
    Board(clearedSrc)
  }

  private def pieceToSet(pieceToMove : ChessPiece, verticalDistance : Int) : Option[ChessPiece] =
    if (verticalDistance > 1) {
      if (pieceToMove == BlackPawnCanMoveTwice) Option(BlackPawnCanMoveOnce)
      else if (pieceToMove == WhitePawnCanMoveTwice) Option(WhitePawnCanMoveOnce)
      else None
    } else None
}