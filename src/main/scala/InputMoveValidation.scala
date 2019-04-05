package ChessGame

import ChessGame.AllPieces.{ChessPiece, Space}

object InputMoveValidation {

  def readPieces(input : String) : Option[(BoardPosition, BoardPosition)] =
    if (input.length == ValidInputSize) {
      val sourceRank = input(0).asDigit //TODO BE: throws exception handle
      val destRank = input(4).asDigit
      val sourceFile = input(1)
      val destFile = input(5)
      if (isOnBoard(sourceRank, sourceFile) && isOnBoard(destRank, destFile)) {
        val sourcePosition = BoardPosition(sourceRank, sourceFile)
        val destinationPosition = BoardPosition(destRank, destFile)
        Option((sourcePosition, destinationPosition))
      } else None
    }
    else None

  def get(board: Board, boardPosition: BoardPosition) : Option[ChessPiece] = {
    val row = board.state(boardPosition.rank.boardStateIndex)
    val piece = row(boardPosition.file.boardStateIndex)
    Option(piece).filter(_ != Space)
  }

  private def isOnBoard(rank : Int, file : Char) : Boolean = RankRange.contains(rank) && FileRange.contains(file)

  private def makeZeroBasedIndex(input : Int) : Int = input - 1
  case class BoardPosition(rank : Rank, file : File)
    object BoardPosition {
        def apply(userInputtedRank : Int, userInputtedFile : Char): BoardPosition =
        BoardPosition(Rank(makeZeroBasedIndex(userInputtedRank)), File(toBoardIndex(userInputtedFile)))

    }
  case class Rank(boardStateIndex : Int)
  case class File(boardStateIndex : Int)
  private val RankRange = (1 to 8).toList
  private val FileRange = ('A' to 'H').toList
  private def toBoardIndex(file : Char) : Int = file.toInt - AsciiValueOfA
  private val AsciiValueOfA = 65
  private val ValidInputSize = 6
}