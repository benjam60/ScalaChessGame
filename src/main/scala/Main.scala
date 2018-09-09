package ChessGame
import ChessGame.Color.{Black, White}
import ChessGame.gameControlFunctions.nextTurn
import ChessBoardPieceMovement.movePiece

object Main extends App {
  val colorsTurn = White
  val initialBoard = new ChessBoard(InitialChessBoardState.get, colorsTurn)
  nextTurn(initialBoard)
  println("Game Over")
}

object gameControlFunctions {
  import ChessGame.Color.{Black, White}
  def nextTurn(chessboard: ChessBoard): Unit = {
    println(chessboard.toString)
    whichPieceToMove(chessboard.turn)
    val srcCoordinates = scala.io.StdIn.readLine()
    whereToPlacePiece(chessboard.turn)
    val destCoordinates = scala.io.StdIn.readLine()
    val updatedBoard = movePiece(chessboard, readPieces(srcCoordinates)._1, readPieces(srcCoordinates)._2,
                                             readPieces(destCoordinates)._1, readPieces(destCoordinates)._2)
    nextTurn(updatedBoard)
  }

  def readPieces(input: String): (Int, Char) = {
    val indexOfRankCoordinateInString = 0
    val indexOfFileCoordinateInString = 2
    (input(indexOfRankCoordinateInString).asDigit, input(indexOfFileCoordinateInString))
  }

  def whichPieceToMove(pieceColor: Color.Value) = {
    println(pieceColor.toString + ", what piece would you like to move?")
    println("Type in this format: x,y")
  }

  def whereToPlacePiece(pieceColor: Color.Value): Unit = {
    println(pieceColor.toString + ", where do you want to move the piece?")
    println("Type in this format: x,y")
  }
}
