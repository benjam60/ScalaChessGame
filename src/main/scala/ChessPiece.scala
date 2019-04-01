package ChessGame


object AllPieces {

  sealed trait ChessPiece {
    val displayName: String
    val color : Color

    def isValidMove(srcRow: Int, srcCol: Int, destRow: Int, destCol: Int): Boolean
  }

  object BlackPawnCanMoveTwice extends ChessPiece {
    override val displayName = BlackPawnName
    override val color: Color = Black

    def isValidMove(srcRow: Int, srcCol: Int, destRow: Int, destCol: Int): Boolean =
      isValidPawnCanMoveTwice(srcRow, srcCol, destRow, destCol)

  }

  object BlackPawnCanMoveOnce extends ChessPiece {
    override val displayName = BlackPawnName
    override val color: Color = Black

    def isValidMove(srcRow: Int, srcCol: Int, destRow: Int, destCol: Int): Boolean =
      isValidPawnCanMoveOnce(srcRow, srcCol, destRow, destCol)

  }

  object WhitePawnCanMoveTwice extends ChessPiece {
    override val displayName = WhitePawnName
    override val color: Color = White

    def isValidMove(srcRow: Int, srcCol: Int, destRow: Int, destCol: Int): Boolean =
      isValidPawnCanMoveTwice(srcRow, srcCol, destRow, destCol)

  }

  object WhitePawnCanMoveOnce extends ChessPiece {
    override val displayName = WhitePawnName
    override val color: Color = White

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
    override val color: Color = White

    def isValidMove(srcRow: Int, srcCol: Int, destRow: Int, destCol: Int): Boolean = true
  }

  object Rook extends ChessPiece {
    override val displayName = "Roo"
    override val color: Color = White

    def isValidMove(srcRow: Int, srcCol: Int, destRow: Int, destCol: Int): Boolean = true
  }

  object Queen extends ChessPiece {
    override val displayName = "Que"
    override val color: Color = White

    def isValidMove(srcRow: Int, srcCol: Int, destRow: Int, destCol: Int): Boolean = true
  }

  object King extends ChessPiece {
    override val displayName = "Kin"
    override val color: Color = White

    def isValidMove(srcRow: Int, srcCol: Int, destRow: Int, destCol: Int): Boolean = true
  }

  object Bishop extends ChessPiece {
    override val displayName = "Bis"
    override val color: Color = White

    def isValidMove(srcRow: Int, srcCol: Int, destRow: Int, destCol: Int): Boolean = true
  }

  object Space extends ChessPiece {
    override val displayName = " " * 3
    override val color: Color = White //shouldn't exist

    def isValidMove(srcRow: Int, srcCol: Int, destRow: Int, destCol: Int): Boolean = false
  }

}