package ChessGame

import Constants.{Files, Ranks}

object InputValidation {

  def readPieces(input : String) : Option[(BoardPosition, BoardPosition)] =
    if (input.length == ValidInputSize) {
      val sourceRank = input(0).asDigit //TODO BE: throws exception handle
      val sourceFile = input(1)
      val destRank = input(4).asDigit
      val destFile = input(5)
      if (isOnBoard(sourceRank, sourceFile) && isOnBoard(destRank, destFile) &&
        !sourceIsSameAsDestination(sourceRank, sourceFile, destRank, destFile)) {
        val sourcePosition = BoardPosition(sourceRank, sourceFile)
        val destinationPosition = BoardPosition(destRank, destFile)
        Option((sourcePosition, destinationPosition))
      } else None
    }
    else None

  private def isOnBoard(rank : Int, file : Char) : Boolean = Ranks.contains(rank) && Files.contains(file)
  private def sourceIsSameAsDestination(srcRank : Int, srcFile : Char, destRank : Int, destFile : Char): Boolean =
    srcRank == destRank && srcFile == destFile
  private val ValidInputSize = 6
}