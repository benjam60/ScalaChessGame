package ChessGame

import ChessGame.AllPieces._
import BoardUtilityFunctions.toBoardIndex
object BoardPieceMovement {

  //ToDo: need to test -1 and absolute value(write code) for vertical distance

  def movePiece(board: Board, sourceRank: Int, sourceFile: Char, destRank: Int, destFile: Char): Board = {
    val pieceToMove = board.state(sourceRank - 1)(toBoardIndex(sourceFile))
    if (pieceToMove.isValidMove(sourceRank - 1, sourceFile, destRank - 1, destFile)) {
      movePieceAndSwitchTurns(board, sourceRank - 1, sourceFile, destRank - 1, destFile, pieceToMove)
    } else board
  }
  private def movePieceAndSwitchTurns(board: Board, sourceRank: Int, sourceFile: Char,
                                      destRank: Int, destFile: Char, pieceToMove : ChessPiece): Board = {
    val destRowToUpdate = board.state(destRank)
    val updatedDestRow = destRowToUpdate.updated(toBoardIndex(destFile),
      pieceToSet(pieceToMove, destRank - sourceRank)) //add absolute value to test
    val movedPieceBoard = board.state.updated(destRank, updatedDestRow)
    val srcRow = board.state(sourceRank)
    val clearedSrc = movedPieceBoard.updated(sourceRank, srcRow.updated(toBoardIndex(sourceFile), Space))
    Board(clearedSrc)
  }

  private def pieceToSet(pieceToMove : ChessPiece, verticalDistance : Int) : ChessPiece =
    if (verticalDistance > 1) {
      if (pieceToMove == BlackPawnCanMoveTwice) BlackPawnCanMoveOnce
      else if (pieceToMove == WhitePawnCanMoveTwice) WhitePawnCanMoveOnce
      else pieceToMove
    } else pieceToMove

  //write test
  private def switchTurns(color: Color): Color = if (color == Black) White else Black
}