package ChessGame

import ChessGame.Constants.{Files, Ranks}

object InputValidation {

  def readPieces(input : String) : Option[UnvalidatedMove] =
    if (input.length == ValidInputSize) {
      if (isOnBoard(sourceRank(input), sourceFile(input)) && isOnBoard(destinationRank(input), destinationFile(input))
        && !sourceIsSameAsDestination(sourceRank(input), sourceFile(input),destinationRank(input),
        destinationFile(input))) {
        Option(UnvalidatedMove(BoardPosition(sourceRank(input), sourceFile(input)), BoardPosition(destinationRank(input),
          destinationFile(input))))
      } else None
    }
    else None

  private def isOnBoard(rank : Int, file : Char) : Boolean = Ranks.contains(rank) && Files.contains(file)
  private def sourceIsSameAsDestination(srcRank : Int, srcFile : Char, destRank : Int, destFile : Char): Boolean =
  srcRank == destRank && srcFile == destFile
  private val ValidInputSize = 6
  private def sourceRank(input : String) : Int = input(0).asDigit
  private def sourceFile(input : String) : Char = input(1)
  private def destinationRank(input : String) : Int = input(4).asDigit
  private def destinationFile(input : String) : Char = input(5)
}
