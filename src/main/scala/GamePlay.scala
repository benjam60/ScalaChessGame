package ChessGame

import ChessGame.BoardPieceMovement.movePiece
import org.scalactic.{Bad, Or}
//use compiletime safety to ensure white and black must alternate moves

case class GamePlay(currentBoard: Board, currentTurn: Color) {
  def takeTurn(userInput : String) : Or[GamePlay, ErrorType] =
		PlayerInputParser.takeTurn(userInput, currentBoard, currentTurn).map { board =>
			this.copy(board, switchTurns(currentTurn))
		}

	private def switchTurns(color: Color) : Color = if (color == White) Black else White
}

case class ChessMove(source : (Int, Int), destination : (Int, Int))

object PlayerInputParser {
  def takeTurn(input : String, chessboard: Board, turn : Color) : Or[Board, ErrorType] =
    if (shouldContinueGame(input)) {
      InputMoveValidation.readPieces(input) match {
        case Some((sourcePosition, destinationPosition)) =>
          val pieceToMove = chessboard.get(sourcePosition)
          if (pieceToMove.map(_.color).contains(turn)) {
            movePiece(chessboard, sourcePosition, destinationPosition)
          } else Bad(NotYourTurn)
      	case None => Bad(InvalidInput)
      }
    }
    else Bad(GameOver)

  private def shouldContinueGame(input : String) : Boolean = input.toLowerCase() != "quit"
}
