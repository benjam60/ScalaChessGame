package ChessGame

import ChessGame.AllPieces._
import ChessGame.InputMoveValidation.BoardPosition
object BoardPieceMovement {

  def movePiece(board: Board, sourcePosition : BoardPosition, destinationPosition : BoardPosition): Board = {
    val pieceToMove = board.state(sourcePosition.rank.boardStateIndex)(sourcePosition.file.boardStateIndex)
    if (pieceToMove.isValidMove(sourcePosition, destinationPosition))
      updateBoard(board, sourcePosition, destinationPosition, pieceToMove)
    else board
  }
  private def updateBoard(board: Board, sourcePosition : BoardPosition, destinationPosition : BoardPosition,
                          pieceToMove : ChessPiece): Board = {
    val destRowToUpdate = board.state(destinationPosition.rank.boardStateIndex)
    val updatedDestRow = destRowToUpdate.updated(destinationPosition.file.boardStateIndex,
      pieceToSet(pieceToMove, destinationPosition.rank.boardStateIndex - sourcePosition.rank.boardStateIndex)) //add absolute value to test
    val movedPieceBoard = board.state.updated( destinationPosition.rank.boardStateIndex, updatedDestRow)
    val srcRow = board.state(sourcePosition.rank.boardStateIndex)
    val clearedSrc = movedPieceBoard.updated(sourcePosition.rank.boardStateIndex,
      srcRow.updated(sourcePosition.file.boardStateIndex, Space))
    Board(clearedSrc)
  }

  private def pieceToSet(pieceToMove : ChessPiece, verticalDistance : Int) : ChessPiece =
    if (verticalDistance > 1) {
      if (pieceToMove == BlackPawnCanMoveTwice) BlackPawnCanMoveOnce
      else if (pieceToMove == WhitePawnCanMoveTwice) WhitePawnCanMoveOnce
      else pieceToMove
    } else pieceToMove
}