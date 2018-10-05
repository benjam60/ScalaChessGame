package ChessGame
import ChessGame.ChessBoardPieceMovement.movePiece
import ChessGame.Color.White
import ChessGame.gameControlFunctions.nextTurn

object Main extends App {
  val colorsTurn = White
  val initialBoard = new ChessBoard(InitialInternalChessBoardState.get, colorsTurn)
  nextTurn(initialBoard)
  println("Game Over")
}

object gameControlFunctions {
  def nextTurn(chessboard: ChessBoard): Unit = {
    println(chessboard.displayWithRankAndFile)
    whichPieceToMove(chessboard.turn)
    val srcCoordinates = scala.io.StdIn.readLine()
    whereToPlacePiece(chessboard.turn)
    val destCoordinates = scala.io.StdIn.readLine()
    val updatedBoard = movePiece(chessboard, readPieces(srcCoordinates)._1, readPieces(srcCoordinates)._2,
                                             readPieces(destCoordinates)._1, readPieces(destCoordinates)._2)
    nextTurn(updatedBoard)
  }

  def readPieces(input: String): (Int, Char) = { //Todo: Custom pattern match on this input for num,num
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
