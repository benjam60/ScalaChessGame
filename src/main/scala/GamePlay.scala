package ChessGame

import ChessGame.BoardUtilityFunctions.next
import ChessGame.PieceMovement.movePiece
import org.scalactic.{Bad, Good, Or}
//use compiletime safety to ensure white and black must alternate moves

case class GamePlay(currentBoard: Board, currentTurn: Color) {
  def takeTurn(userInput : String) : Or[GamePlay, ErrorType] =
		validateAndThenTakeTurn(userInput, currentBoard, currentTurn).map { board =>
			this.copy(board, next(currentTurn))
		}

	//TODO BE: a function should do one thing, so split out all the validation into another function
  def validateAndThenTakeTurn(input : String, board: Board, turn : Color) : Or[Board, ErrorType] =
    if (shouldContinueGame(input)) {
      InputValidation.readPieces(input).map { case (sourcePosition, destinationPosition) =>
          if (PieceMovementValidation.isValidMove(board, sourcePosition, destinationPosition,
						turn, board.get(sourcePosition))) {
						Good(movePiece(board, board.get(sourcePosition).get, sourcePosition, destinationPosition))
					}
					else Bad(NotYourPiece)
      }.getOrElse(Bad(InvalidInput))
    } else Bad(GameOver)

  private def shouldContinueGame(input : String) : Boolean = input.toLowerCase() != "quit"
}
