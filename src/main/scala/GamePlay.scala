package ChessGame

import ChessGame.BoardPieceMovement.movePiece
import ChessGame.BoardUtilityFunctions.toBoardIndex
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
      val pieceToMove: AllPieces.ChessPiece = chessboard.state(srcRank - 1)(toBoardIndex(srcFile))
      if (pieceToMove.color != turn) { takeTurn(chessboard, turn) }
      else {
        val updatedBoard = movePiece(chessboard, srcRank, srcFile, destRank, destFile)
        takeTurn(updatedBoard, turn.nextTurn)
      }
    }
    else (chessboard, turn)
  }

  private def readPieces(input: String): (Int, Char, Int, Char) = {
    val SrcRankIndex = 0
    val SrcFileIndex = 1
    val DestRankIndex = 4
    val DestFileIndex = 5
    (input(SrcRankIndex).asDigit, input(SrcFileIndex), input(DestRankIndex).asDigit, input(DestFileIndex))
  }

  private def whichPieceToMove(pieceColor: Color) : Unit = {
    println(pieceColor.toString +
      ", please type exactly in this format e.g. 2B->3B to move piece from 2B to 3B")
    println("Type in this format: x,y")
  }
}

trait InputSource {
  def readLine : String
}