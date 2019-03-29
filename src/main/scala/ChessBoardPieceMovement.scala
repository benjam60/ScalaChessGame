package ChessGame

import ChessGame.Color.{Black, Color, White}

object ChessBoardPieceMovement {

  def movePiece(board: ChessBoard, sourceRank: Int, sourceFile: Char, destRank: Int, destFile: Char): ChessBoard = {
    val pieceToMove = board.state(sourceRank)(sourceFile)
    if (pieceToMove.isValidMove(sourceRank, sourceFile, destRank, destFile)) {
      val updatedState = board.state.updated(sourceRank, board.state(sourceRank).updated(sourceFile, pieceToMove))
      board.copy(updatedState, switchTurns(board.turn))
    } else board
  }

  private def switchTurns(color: Color): Color = if (color == Black) White else Black
}