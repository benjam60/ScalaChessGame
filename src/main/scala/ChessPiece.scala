package ChessGame

import ChessGame.AllPieces._

object boardPositionShortHands {
  val sourceRank = "srcRank"
  val sourceFile = "srcFile"
  val destinationRank = "destRank"
  val destinationFile = "destFile"
}

object AllPieces {

  sealed trait ChessPiece {
    val displayName: String
  }

  object PawnMovedOnce extends ChessPiece {
    override val displayName = "Paw"
  }

  object PawnMovedTwice extends ChessPiece {
    override val displayName = "Paw"
  }

  object Knight extends ChessPiece {
    override val displayName = "Kni"
  }

  object Rook extends ChessPiece {
    override val displayName = "Roo"
  }

  object Queen extends ChessPiece {
    override val displayName = "Que"
  }

  object King extends ChessPiece {
    override val displayName = "Kin"
  }

  object Bishop extends ChessPiece {
    override val displayName = "Bis"
  }

  object Space extends ChessPiece {
    override val displayName = "   "
  }

}