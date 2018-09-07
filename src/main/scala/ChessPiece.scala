package ChessGame

object ChessPieceDisplayNames {
  val Pawn = "Paw"
  val Knight = "Kni"
  val King = "Kin"
  val Rook = "Roo"
  val Queen = "Que"
  val Bishop = "Bis"
  val Space = " "
}

object boardPositionShortHands {
  val sourceRank = "srcRank"
  val sourceFile = "srcFile"
  val destinationRank = "destRank"
  val destinationFile = "destFile"
}

sealed trait ChessPiece
object PawnMovedOnce extends ChessPiece {
  val displayName = "Paw"
}
object PawnMovedTwice extends ChessPiece {
  val displayName = "Paw"
}
object Knight extends ChessPiece {
  val displayName = "Kni"
}
object Rook extends ChessPiece {
  val displayName = "Roo"
}
object Queen extends ChessPiece {
  val displayName = "Que"
}
object King extends ChessPiece {
  val displayName = "Kin"
}
object Bishop extends ChessPiece {
  val displayName = "Bis"
}