package ChessGame

import ChessGame.AllPieces.ChessPiece

object InputMoveValidation {

  def readPieces(input : String) : Option[(BoardPosition, BoardPosition)] =
    if (input.length == ValidInputSize) {
      val sourceRank = input(0).asDigit //TODO BE: throws exception handle
      val destRank = input(4).asDigit
      val sourceFile = input(1)
      val destFile = input(5)
      if (isOnBoard(sourceRank, sourceFile) && isOnBoard(destRank, destFile) &&
        !sourceIsSameAsDestination(sourceRank, sourceFile, destRank, destFile)) {
        val sourcePosition = BoardPosition(sourceRank, sourceFile)
        val destinationPosition = BoardPosition(destRank, destFile)
        Option((sourcePosition, destinationPosition))
      } else None
    }
    else None

  def get(board: Board, boardPosition: BoardPosition) : Option[ChessPiece] = {
    val row = board.state(boardPosition.rankBoardIndex)
    row(boardPosition.fileBoardIndex)
  }

  private def isOnBoard(rank : Int, file : Char) : Boolean = RankRange.contains(rank) && FileRange.contains(file)
  private def sourceIsSameAsDestination(srcRank : Int, srcFile : Char, destRank : Int, destFile : Char): Boolean =
    srcRank == destRank && srcFile == destFile
  private def makeZeroBasedIndex(input : Int) : Int = input - 1
  case class BoardPosition(rankBoardIndex : Int, fileBoardIndex : Int)
    object BoardPosition {
        def apply(userInputtedRank : Int, userInputtedFile : Char): BoardPosition =
        BoardPosition(makeZeroBasedIndex(userInputtedRank), toBoardIndex(userInputtedFile))

    }
  private val RankRange = (1 to 8).toList
  private val FileRange = ('A' to 'H').toList
  private def toBoardIndex(file : Char) : Int = file.toInt - AsciiValueOfA
  private val AsciiValueOfA = 65
  private val ValidInputSize = 6
}