package ChessGame
import gameControlFunctions.getPlayerInputAndUpdateBoard

object Main extends App {

  val initialBoard = new ChessBoard(InitialChessBoardState.get)
  val firstPlayerToMove = 1
  getPlayerInputAndUpdateBoard(initialBoard, firstPlayerToMove)
  println("Game Over")
  }

object gameControlFunctions {

  def getPlayerInputAndUpdateBoard(chessboard : ChessBoard, playerNumber : Int) : Unit = {
    println(chessboard.toString)
    println("Player " + playerNumber + ", what piece would you like to move?")
    println("Type in this format: x,y")
    val srcCoordinates = scala.io.StdIn.readLine()
    val srcRank = srcCoordinates(0).asDigit
    val srcFile = srcCoordinates(2).asDigit
    println("Player " + playerNumber + ", where do you want to move the piece?")
    println("Type in this format: x,y")
    val destCoordinates = scala.io.StdIn.readLine()
    val destRank = destCoordinates(0).asDigit
    val destFile = destCoordinates(2).asDigit
    val updatedBoard = chessboard.movePiece(srcRank, srcFile, destRank, destFile)
    if (playerNumber == 1) {
      getPlayerInputAndUpdateBoard(updatedBoard, playerNumber = 2)
    }
    else {
      getPlayerInputAndUpdateBoard(updatedBoard, playerNumber = 1)
    }
  }

}
