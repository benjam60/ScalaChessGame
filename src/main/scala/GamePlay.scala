package ChessGame

import ChessGame.BoardUtilityFunctions.getOther
import org.scalactic.{Bad, Good, Or}

import scala.collection.immutable
//use compiletime safety to ensure white and black must alternate moves

case class Player(isInCheck: Boolean, isInCheckMate: Boolean)

object Player {
	def createPlayerInCheck = Player(isInCheck = true, isInCheckMate = false)

	def createPlayerNotInCheck = Player(isInCheck = false, isInCheckMate = false)
}

case class GamePlay(currentBoard: Board, currentTurn: Color, players: Map[Color, Player]) {

	def takeTurn(userInput: String): Or[GamePlay, ErrorType] =
		checkIfLegalMove(userInput, currentBoard).map { legalMove =>
			val updatedBoard = PieceMovement.movePiece(currentBoard, legalMove)
			val opponent = getOther(currentTurn)
			if (isKingInCheck(opponent, updatedBoard))
				setToCheck(this.copy(currentBoard = updatedBoard), opponent)
			else
				this.copy(currentBoard = updatedBoard)
		}.map(removeCheck).map(_.copy(currentTurn = getOther(currentTurn)))

	private def checkIfLegalMove(input: String, board: Board): Or[LegalMove, ErrorType] =
		validateInput(input).flatMap { case UnvalidatedMove(sourcePosition: BoardPosition, destinationPosition: BoardPosition) =>
			if (followsAllApplicableRules(board, sourcePosition, destinationPosition)
				&& board.get(sourcePosition).exists(piece => SpecificPieceMovement.isValidMove(piece, board, sourcePosition, destinationPosition))
			)
				Good(LegalMove(sourcePosition, destinationPosition)) else Bad(InvalidMove)
		}

	private def followsAllApplicableRules(board: Board, sourcePosition: BoardPosition, destinationPosition: BoardPosition): Boolean = {
		val isMovingPiece = AllPieceApplicableRules.sourcePieceExists(board, sourcePosition)
		if (!isMovingPiece) {
			println("Player isn't moving any existing piece")
		}
		val isNotCapturingSelf = AllPieceApplicableRules.isNotCapturingSelf(board, destinationPosition, currentTurn)
		if (!isNotCapturingSelf) {
			println("Invalid move because is capturing self")
		}
		val isTheMoversPiece = AllPieceApplicableRules.isMoversPiece(board, sourcePosition, currentTurn)
		if (!isTheMoversPiece) {
			println("Player is moving opponents piece")
		}

		if (isMovingPiece && isTheMoversPiece) {
			val kingIsInCheck = isKingInCheck(currentTurn, PieceMovement.movePiece(board, LegalMove(sourcePosition, destinationPosition)))
			if (kingIsInCheck) {
				println("Move leaves mover's king in check")
			}
			isNotCapturingSelf && isTheMoversPiece && isMovingPiece && !kingIsInCheck
		}
		else false
	}

	private def validateInput(input: String): Or[UnvalidatedMove, ErrorType] =
		if (shouldContinueGame(input)) {
			InputValidation.readPieces(input).map(move => Good(move)).getOrElse(Bad(InvalidInput))
		} else Bad(GameOver)

	//make sure you check if the move puts the mover's king in check as well as the opponent's
	private def isKingInCheck(color: Color, board: Board): Boolean = {
		val kingPosition = findKing(color, board)
		getAllPieces(board, getOther(color)).exists { boardPos =>
			val piece = board.get(boardPos).get
			SpecificPieceMovement.isValidMove(piece, board, boardPos, kingPosition)
		}
	}

	private def findKing(color: Color, board: Board): BoardPosition = {
		def traverseBoard(rowIndex: Int): BoardPosition = {
			val columnIndexOfBlackKing = board.state(rowIndex).indexOf(Option(color.King))
			if (columnIndexOfBlackKing > -1) {
				BoardPosition(rowIndex, columnIndexOfBlackKing)
			}
			else traverseBoard(rowIndex + 1)
		}

		traverseBoard(0)
	}

	private def setToCheck(gamePlay: GamePlay, color: Color): GamePlay =
		gamePlay.copy(players = players.updated(color, Player.createPlayerInCheck))

	private def getAllPieces(board: Board, color: Color): immutable.Seq[BoardPosition] =
		(0 until Constants.RowSize).flatMap(rowIndex =>
			(0 until Constants.NumColumns).collect { case colIndex
				if board.state(rowIndex)(colIndex).exists(_.colorInstance == color) =>
				BoardPosition(rowIndex, colIndex)
			}.toList
		)

	private def shouldContinueGame(input: String): Boolean = input.toLowerCase() != "quit"

	private def removeCheck(gamePlay: GamePlay): GamePlay =
		gamePlay.copy(players = gamePlay.players.updated(gamePlay.currentTurn, Player.createPlayerNotInCheck))

}
