package ChessGame


object Main extends App {

  def f(chessboard : ChessBoard, playerNumber : Int) : Unit = {
    println(chessboard.toString)
    println("Player " + playerNumber + ", what piece would you like to move?")
    println("Type in this format: x,y")
    val srcCoordinates = scala.io.StdIn.readLine()
    val srcX = srcCoordinates(0).asDigit
    val srcY = srcCoordinates(2).asDigit
    println("Player " + playerNumber + ", where do you want to move the piece?")
    println("Type in this format: x,y")
    val destCoordinates = scala.io.StdIn.readLine()
    val destX = destCoordinates(0).asDigit
    val destY = destCoordinates(2).asDigit
    val updatedBoard = chessboard.movePiece(srcX, srcY, destX, destY)
    if (playerNumber == 1) f(updatedBoard, playerNumber = 2)
    else f(updatedBoard, playerNumber = 1)

  }

    val x = new ChessBoard(InitialChessBoardState.board)
    f(x, playerNumber = 1)
    println("Game Over")

  }


