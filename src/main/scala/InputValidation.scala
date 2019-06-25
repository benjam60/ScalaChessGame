package ChessGame

import ChessGame.Constants.{Files, Ranks}

object InputValidation {

  def readPieces(input : String) : Option[(BoardPosition, BoardPosition)] =
    if (input.length == ValidInputSize) {
      val(sourceRank, sourceFile, destRank, destFile) = parseInput(input)
      if (isOnBoard(sourceRank, sourceFile) && isOnBoard(destRank, destFile) &&
        !sourceIsSameAsDestination(sourceRank, sourceFile, destRank, destFile)) {
        val sourcePosition = BoardPosition(sourceRank, sourceFile)
        val destinationPosition = BoardPosition(destRank, destFile)
        Option((sourcePosition, destinationPosition))
      } else None
    }
    else None

  private def parseInput(input : String) : (Int, Char, Int, Char) =
    (input(0).asDigit, input(1), input(4).asDigit, input(5))

  private def isOnBoard(rank : Int, file : Char) : Boolean = Ranks.contains(rank) && Files.contains(file)
  private def sourceIsSameAsDestination(srcRank : Int, srcFile : Char, destRank : Int, destFile : Char): Boolean =
    srcRank == destRank && srcFile == destFile
  private val ValidInputSize = 6
}