package ChessGame

import ChessGame.AllPieces.King
import ChessGame.BoardUtilityFunctions.getOther
import ChessGame.PieceMovement.movePiece
import org.scalactic.{Bad, Good, Or}

import scala.collection.immutable
//use compiletime safety to ensure white and black must alternate moves

case class Player(isInCheck : Boolean, isInCheckMate : Boolean)

case class GamePlay(currentBoard: Board, currentTurn: Color, white : Player, black : Player) {
//  def takeTurn(userInput : String) : Or[GamePlay, ErrorType] =
//		validateAndThenTakeTurn(userInput, this).map { board =>
//			setToCheck(this.copy(currentBoard = board)).copy(currentTurn = getOther(currentTurn))
//		}

	def takeTurn(userInput : String) : Or[GamePlay, ErrorType] =
		checkIfLegalMove(userInput, currentBoard, currentTurn).map { legalMove =>
			val updatedBoard = PieceMovement.movePiece(currentBoard,currentBoard.get(legalMove.sourcePosition).get,
				legalMove.sourcePosition, legalMove.destinationPosition)
			setToCheck(this.copy(currentBoard = updatedBoard)).copy(currentTurn = getOther(currentTurn))
		}

	def checkIfLegalMove(input : String, board : Board, colorsTurn : Color) : Or[Move, ErrorType] =
		validateInput(input).flatMap { case m@Move(sourcePosition: BoardPosition, destinationPosition: BoardPosition) =>
			if (followsAllApplicableRules(board, sourcePosition, destinationPosition, colorsTurn)
				&& board.get(sourcePosition).exists(_.isValidMove(board, sourcePosition, destinationPosition))
			)
				Good(m) else Bad(InvalidMove)
		}

	private def followsAllApplicableRules(board : Board, sourcePosition : BoardPosition, destinationPosition : BoardPosition, colorsTurn : Color) : Boolean =
		AllPieceApplicableRules.isNotCapturingSelf(board, destinationPosition, colorsTurn) &&
	  AllPieceApplicableRules.isMoversPiece(board, sourcePosition, colorsTurn) &&
		AllPieceApplicableRules.sourcePieceExists(board, sourcePosition)


	private def validateInput(input : String) : Or[Move, ErrorType] =
		if (shouldContinueGame(input)) {
			InputValidation.readPieces(input).map(move => Good(move)).getOrElse(Bad(InvalidInput))
		} else Bad(GameOver)

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
	private def isKingInCheck(color : Color, board: Board) : Boolean = {
		val kingPosition = findKing(color, board)
		getAllPieces(board, getOther(color)).exists(boardPos =>
			board.get(boardPos).get.isValidMove(board, boardPos, kingPosition) )
	}

	private def findKing(color : Color, board: Board) : BoardPosition = {
		def traverseBoard(rowIndex : Int): BoardPosition = {
			val columnIndexOfBlackKing = board.state(rowIndex).indexOf(Option(King(color)))
			if (columnIndexOfBlackKing > -1) {
				BoardPosition(rowIndex, columnIndexOfBlackKing)
			}
			else traverseBoard(rowIndex + 1)
		}
		traverseBoard(0)
	}

	private def setToCheck(gamePlay: GamePlay) : GamePlay =
		gamePlay.currentTurn match {
			case White => if (isKingInCheck(Black, gamePlay.currentBoard)) gamePlay.copy(black = Player(true, false)) else gamePlay
			case Black => if (isKingInCheck(White, gamePlay.currentBoard)) gamePlay.copy(white = Player(true, false)) else gamePlay
		}

	private def getAllPieces(board : Board, color : Color): immutable.Seq[BoardPosition] =
	(0 until 8).flatMap(rowIndex =>
		(0 until 8).collect { case colIndex
			if board.state(rowIndex)(colIndex).exists(_.getColor == color) =>
			BoardPosition(rowIndex, colIndex) }.toList
	)

  private def shouldContinueGame(input : String) : Boolean = input.toLowerCase() != "quit"
}
