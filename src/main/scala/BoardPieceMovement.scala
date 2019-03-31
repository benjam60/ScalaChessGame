package ChessGame

import ChessGame.AllPieces.ChessPiece
import ChessGame.Color.{Black, Color, White}

object BoardPieceMovement {

  def movePiece(board: Board, sourceRank: Int, sourceFile: Char, destRank: Int, destFile: Char): Board = {
    val pieceToMove = board.state(sourceRank)(sourceFile)
    if (pieceToMove.isValidMove(sourceRank, sourceFile, destRank, destFile)) {
      movePieceAndSwitchTurns(board, sourceRank, sourceFile, destRank, destFile, pieceToMove)
    } else board
  }
  private def movePieceAndSwitchTurns(board: Board, sourceRank: Int, sourceFile: Char,
                                      destRank: Int, destFile: Char, pieceToMove : ChessPiece): Board = {
    val updatedState = board.state.updated(sourceRank, board.state(sourceRank).updated(sourceFile, pieceToMove))
    board.copy(updatedState, switchTurns(board.turn))
  }

  private def switchTurns(color: Color): Color = if (color == Black) White else Black
}