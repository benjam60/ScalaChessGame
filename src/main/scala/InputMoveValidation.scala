package ChessGame

import ChessGame.AllPieces.{ChessPiece, Space}

object InputMoveValidation {

  def readPieces(input : String) : Option[(BoardPosition, BoardPosition)] = {
    if (input.length == 6) {
      val sourceRank = input(0).asDigit
      val destRank = input(4).asDigit
      val sourceFile = input(1)
      val destFile = input(5)
      if (RankRange.contains(sourceRank) && RankRange.contains(destRank) &&
        FileRange.contains(sourceFile) && FileRange.contains(destFile)) {
        val sourcePosition = BoardPosition(Rank(sourceRank, makeZeroIndex(sourceRank)),
          File(sourceFile, toBoardIndex(sourceFile)))
        val destinationPosition = BoardPosition(Rank(destRank, makeZeroIndex(destRank)),
          File(destFile, toBoardIndex(destFile)))
        Option((sourcePosition, destinationPosition))
      } else None
    }
    else None
  }

  def get(board: Board, boardPosition: BoardPosition) : Option[ChessPiece] = {
    val row = board.state(boardPosition.rank.boardStateIndex)
    val piece = row(boardPosition.file.boardStateIndex)
    if (piece != Space) Option(piece) else None
  }

  private def makeZeroIndex(input : Int) : Int = input - 1
  case class BoardPosition(rank : Rank, file : File)
  case class Rank(value : Int, boardStateIndex : Int)
  case class File(value : Char, boardStateIndex : Int)
  private val RankRange = (1 to 8).toList
  private val FileRange = ('A' to 'H').toList
  private def toBoardIndex(file : Char) : Int = file.toInt - AsciiValueOfA
  private val AsciiValueOfA = 65
}