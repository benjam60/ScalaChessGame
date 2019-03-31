package ChessGame


object AllPieces {

  sealed trait ChessPiece {
    val displayName: String

    def isValidMove(srcRow: Int, srcCol: Int, destRow: Int, destCol: Int): Boolean
  }

  object BlackPawnCanMoveTwice extends ChessPiece {
    override val displayName = BlackPawnName

    def isValidMove(srcRow: Int, srcCol: Int, destRow: Int, destCol: Int): Boolean =
      isValidPawnCanMoveTwice(srcRow, srcCol, destRow, destCol)

  }

  object BlackPawnCanMoveOnce extends ChessPiece {
    override val displayName = BlackPawnName

    def isValidMove(srcRow: Int, srcCol: Int, destRow: Int, destCol: Int): Boolean =
      isValidPawnCanMoveOnce(srcRow, srcCol, destRow, destCol)

  }

  object WhitePawnCanMoveTwice extends ChessPiece {
    override val displayName = WhitePawnName

    def isValidMove(srcRow: Int, srcCol: Int, destRow: Int, destCol: Int): Boolean =
      isValidPawnCanMoveTwice(srcRow, srcCol, destRow, destCol)

  }

  object WhitePawnCanMoveOnce extends ChessPiece {
    override val displayName = WhitePawnName

    def isValidMove(srcRow: Int, srcCol: Int, destRow: Int, destCol: Int): Boolean =
      isValidPawnCanMoveOnce(srcRow, srcCol, destRow, destCol)
  }

  private def isValidPawnCanMoveOnce(srcRow: Int, srcCol: Int, destRow: Int, destCol: Int): Boolean =
    srcCol == destCol && Math.abs(srcRow - destRow) == 1

  private def isValidPawnCanMoveTwice(srcRow: Int, srcCol: Int, destRow: Int, destCol: Int): Boolean =
    srcCol == destCol && Math.abs(srcRow - destRow) <= 2

  val WhitePawnName = "Paw"
  val BlackPawnName = WhitePawnName.toLowerCase


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