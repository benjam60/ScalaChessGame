package ChessGame


object AllPieces {

  sealed trait ChessPiece {
    val displayName: String

    def isValidMove(srcRow: Int, srcCol: Int, destRow: Int, destCol: Int): Boolean
  }

  object PawnCanMoveTwice extends ChessPiece {
    override val displayName = "Paw"

    def isValidMove(srcRow: Int, srcCol: Int, destRow: Int, destCol: Int): Boolean = {
      if (srcCol == destCol && Math.abs(srcRow - destRow) <= 2) true
      else false
    }
  }

  object PawnCanMoveOnce extends ChessPiece {
    override val displayName = "Paw"

    def isValidMove(srcRow: Int, srcCol: Int, destRow: Int, destCol: Int): Boolean = {
      srcCol == destCol && Math.abs(srcRow - destRow) == 1
    }
  }

  object Knight extends ChessPiece {
    override val displayName = "Kni"

    def isValidMove(srcRow: Int, srcCol: Int, destRow: Int, destCol: Int): Boolean = true
  }

  object Rook extends ChessPiece {
    override val displayName = "Roo"

    def isValidMove(srcRow: Int, srcCol: Int, destRow: Int, destCol: Int): Boolean = true
  }

  object Queen extends ChessPiece {
    override val displayName = "Que"

    def isValidMove(srcRow: Int, srcCol: Int, destRow: Int, destCol: Int): Boolean = true
  }

  object King extends ChessPiece {
    override val displayName = "Kin"

    def isValidMove(srcRow: Int, srcCol: Int, destRow: Int, destCol: Int): Boolean = true
  }

  object Bishop extends ChessPiece {
    override val displayName = "Bis"

    def isValidMove(srcRow: Int, srcCol: Int, destRow: Int, destCol: Int): Boolean = true
  }

  object Space extends ChessPiece {
    override val displayName = " " * 3

    def isValidMove(srcRow: Int, srcCol: Int, destRow: Int, destCol: Int): Boolean = false
  }

}