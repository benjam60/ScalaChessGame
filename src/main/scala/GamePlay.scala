package ChessGame

import ChessGame.BoardPieceMovement.movePiece
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
  def takeTurn(chessboard: Board, turn : Color) : (Board, Color) = {
    println(ChessBoardPrinter.print(chessboard))
    val input = inputSource.readLine
    if (input.toLowerCase() != "quit") {
      val (srcRank, srcFile, destRank, destFile) = readPieces(input)
      val updatedBoard = movePiece(chessboard, srcRank, srcFile, destRank, destFile)
      takeTurn(updatedBoard, turn.nextTurn)
    }
    else (chessboard, turn)
  }

  private def readPieces(input: String): (Int, Char, Int, Char) = { //Todo: Custom pattern match on this input for num,num
    val indexOfSrcRankCoordinateInString = 0
    val indexOfSrcFileCoordinateInString = 1
    val indexOfDestRankCoordinateInString = 4
    val indexOfDestFileCoordinateInString = 5
    (input(indexOfSrcRankCoordinateInString).asDigit, input(indexOfSrcFileCoordinateInString),
      input(indexOfDestRankCoordinateInString).asDigit, input(indexOfDestFileCoordinateInString))
  }

  private def whichPieceToMove(pieceColor: Color) : Unit = {
    println(pieceColor.toString + ", what piece would you like to move?")
    println("Type in this format: x,y")
  }

  private def whereToPlacePiece(pieceColor: Color): Unit = {
    println(pieceColor.toString + ", where do you want to move the piece?")
    println("Type in this format: x,y")
  }
}

trait InputSource {
  def readLine : String
}