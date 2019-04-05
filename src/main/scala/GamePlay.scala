package ChessGame

import ChessGame.BoardPieceMovement.movePiece
import org.scalactic.{Bad, Or}
//use compiletime safety to ensure white and black must alternate moves

class GamePlay(inputSource: InputSource)(initialBoard: Board, firstMove: Color) {
  def start() : (Board, Color) = {
    println("Starting Game")
    playerInputParser.takeTurn(initialBoard, firstMove)
  }
  private val playerInputParser = new PlayerInputParser(inputSource)
}

case class ChessMove(source : (Int, Int), destination : (Int, Int))

class PlayerInputParser(inputSource : InputSource) {
  def takeTurn(chessboard: Board, turn : Color) : Or[Board, ErrorType] = {
    println(BoardPrinter.print(chessboard))
    whichPieceToMove(turn)
    val input = inputSource.readLine
    if (shouldContinueGame(input)) {
      InputMoveValidation.readPieces(input) match {
        case Some((sourcePosition, destinationPosition)) =>
          val pieceToMove = InputMoveValidation.get(chessboard, sourcePosition)
          if (pieceToMove.map(_.color).contains(turn)) {
            movePiece(chessboard, sourcePosition, destinationPosition)
          } else Bad(NotYourTurn)
      	case None => Bad(InvalidInput)
      }
    }
    else Bad(GameOver)
  }

  private def whichPieceToMove(pieceColor: Color) : Unit = {
    println(s"${pieceColor.toString}, please type exactly in this format e.g. 2B->3B to move piece from 2B to 3B")
    println("Type in this format: x,y")
  }

  private def shouldContinueGame(input : String) : Boolean = input.toLowerCase() != "quit"
}

trait InputSource {
  def readLine : String
}

sealed trait ErrorType
object NotYourTurn extends ErrorType
object GameOver extends ErrorType
object InvalidMove extends ErrorType
object InvalidInput extends ErrorType