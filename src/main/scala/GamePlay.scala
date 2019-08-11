package ChessGame

import ChessGame.AllPieces.King
import ChessGame.BoardUtilityFunctions.next
import ChessGame.PieceMovement.movePiece
import org.scalactic.{Bad, Good, Or}

import scala.collection.immutable
//use compiletime safety to ensure white and black must alternate moves

case class Player(isInCheck : Boolean)

case class GamePlay(currentBoard: Board, currentTurn: Color, white : Player, black : Player) {
  def takeTurn(userInput : String) : Or[GamePlay, ErrorType] =
		validateAndThenTakeTurn(userInput, this).map { board =>
			this.copy(board, next(currentTurn))
		}.map(gamePlay => if (isKingInCheck(gamePlay.currentTurn)) gamePlay.copy(black = Player(true)) else gamePlay )

	//TODO BE: a function should do one thing, so split out all the validation into another function
  private def validateAndThenTakeTurn(input : String, gamePlay: GamePlay) : Or[Board, ErrorType] =
    if (shouldContinueGame(input)) {
      InputValidation.readPieces(input).map { move =>
	      takeTurn(gamePlay.currentBoard, move.sourcePosition, move.destinationPosition, gamePlay.currentTurn)
      }.getOrElse(Bad(InvalidInput))
    } else Bad(GameOver)

	private def takeTurn(board: Board, sourcePosition : BoardPosition, destinationPosition : BoardPosition, turn : Color) =
		if (PieceMovementValidation.isValidMove(board, sourcePosition, destinationPosition, turn, board.get(sourcePosition))) {
			Good(movePiece(board, board.get(sourcePosition).get, sourcePosition, destinationPosition))
		} else Bad(NotYourPiece)

	//make sure you check if the move puts the mover's king in check as well as the opponent's
	private def isKingInCheck(color : Color) : Boolean = {
		val blackKingPosition = findKing(color)
		getAllWhitePieces.exists(boardPos =>
			currentBoard.get(boardPos).get.isValidMove(currentBoard, boardPos, blackKingPosition) )
	}

	private def findKing(color : Color) : BoardPosition = {
		def traverseBoard(rowIndex : Int): BoardPosition = {
			val columnIndexOfBlackKing = currentBoard.state(rowIndex).indexOf(Option(King(Black)))
			if (columnIndexOfBlackKing > -1) {
				BoardPosition(rowIndex, columnIndexOfBlackKing)
			}
			else traverseBoard(rowIndex + 1)
		}
		traverseBoard(0)
	}

//	private def getAllWhitePieces : List[(ChessPiece] = {
//		currentBoard.state.flatMap(row =>
//			row.flatten.collect { case piece if piece.getColor == White => piece } )
//	}
private def getAllWhitePieces: immutable.Seq[BoardPosition] =
	(0 until 8).flatMap(rowIndex =>
		(0 until 8).collect { case colIndex
			if currentBoard.state(rowIndex)(colIndex).exists(_.getColor == White) =>
			BoardPosition(rowIndex, colIndex) }.toList
	)

  private def shouldContinueGame(input : String) : Boolean = input.toLowerCase() != "quit"
}
