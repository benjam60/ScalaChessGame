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
    askWhichPieceShouldMove(playerNumber)
    val srcCoordinates = scala.io.StdIn.readLine()
    askWhereToMovePieceTo(playerNumber)
    val destCoordinates = scala.io.StdIn.readLine()
    val updatedBoard = chessboard.movePiece(readPieces(srcCoordinates)._1, readPieces(srcCoordinates)._2,
      readPieces(destCoordinates)._1, readPieces(destCoordinates)._2)
    if (playerNumber == 1) {
      getPlayerInputAndUpdateBoard(updatedBoard, playerNumber = 2)
    }
    else {
      getPlayerInputAndUpdateBoard(updatedBoard, playerNumber = 1)
    }
  }

  def readPieces(input : String) : (Int, Char) = {
    val indexOfRankCoordinateInString = 0
    val indexOfFileCoordinateInString = 2
    (input(indexOfRankCoordinateInString).asDigit, input(indexOfFileCoordinateInString))
  }
  def askWhichPieceShouldMove(playerNumber : Int) = {
    println("Player " + playerNumber + ", what piece would you like to move?")
    println("Type in this format: x,y")
  }
  def askWhereToMovePieceTo(playerNumber : Int) : Unit = {
    println("Player " + playerNumber + ", where do you want to move the piece?")
    println("Type in this format: x,y")
  }

}
