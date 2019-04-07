package ChessGame


object AllPieces {

  sealed trait ChessPiece {
    val color : Color
    val displayName: String
    def isValidMove(board : Board, source: BoardPosition, destination: BoardPosition): Boolean =
      isValidMoveForAnyPiece(board, source, destination) && isValidMoveForPiece(board, source, destination)

    private def isValidMoveForAnyPiece(board: Board, source: BoardPosition, destination: BoardPosition) : Boolean = {
      val isNotEatingOwnPiece = board.get(destination).forall(_.color != color)
      isNotEatingOwnPiece
    }
    protected def isValidMoveForPiece(board: Board, source: BoardPosition, destination: BoardPosition): Boolean
  }

  case class Pawn(canMoveTwoSpaces : Boolean, override val color : Color) extends ChessPiece {
    override val displayName: String = if (color == White) WhitePawnName else BlackPawnName

    override def isValidMoveForPiece(board: Board, source: BoardPosition, destination: BoardPosition): Boolean =
      if (source.fileBoardIndex == destination.fileBoardIndex) {
        isLegalVerticalMove(board, source, destination) && board.get(destination).isEmpty
      }
      else isLegalDiagonalMove(board, source, destination)

    private def isLegalDiagonalMove(board: Board, source: BoardPosition, destination: BoardPosition) : Boolean =
      source.rankBoardIndex - destination.rankBoardIndex == 1*color.direction &&
        List(1, -1).contains(source.fileBoardIndex - destination.fileBoardIndex) &&
        board.state(destination.rankBoardIndex)(destination.fileBoardIndex).exists(_.color == oppositeColor)

    private def isLegalVerticalMove(board: Board, source: BoardPosition,
                                    destination: BoardPosition) : Boolean = {
      val numSpacesCanMove = if (canMoveTwoSpaces) List(1, 2).map(_*color.direction) else List(1).map(_*color.direction)
      source.fileBoardIndex == destination.fileBoardIndex &&
        numSpacesCanMove.contains(source.rankBoardIndex - destination.rankBoardIndex)
    }

    private def WhitePawnName = "Paw"
    private def BlackPawnName = WhitePawnName.toLowerCase
    private def oppositeColor: Color = if (color == White) Black else White
  }

  object Knight extends ChessPiece {
    override val displayName = "Kni"
    override val color: Color = White

    override def isValidMoveForPiece(board: Board, source: BoardPosition, destination: BoardPosition): Boolean = true
  }

  case class Rook(override val color : Color) extends ChessPiece {
    override val displayName: String = if (color == White) "Roo" else "roo"

    override def isValidMoveForPiece(board: Board, source: BoardPosition, destination: BoardPosition): Boolean = true
  }

  object Queen extends ChessPiece {
    override val displayName = "Que"
    override val color: Color = White

    override def isValidMoveForPiece(board: Board, source: BoardPosition, destination: BoardPosition): Boolean = true
  }

  object King extends ChessPiece {
    override val displayName = "Kin"
    override val color: Color = White

    override def isValidMoveForPiece(board: Board, source: BoardPosition, destination: BoardPosition): Boolean = true
  }

  object Bishop extends ChessPiece {
    override val displayName = "Bis"
    override val color: Color = White

    override def isValidMoveForPiece(board: Board, source: BoardPosition, destination: BoardPosition): Boolean = true
  }


  private val canMoveTwoSpaces = true
  val BlackPawnCanMoveTwoSpaces = Pawn(canMoveTwoSpaces, Black)
  val BlackPawnCanMoveOneSpace = Pawn(!canMoveTwoSpaces, Black)
  val WhitePawnCanMoveTwoSpaces = Pawn(canMoveTwoSpaces, White)
  val WhitePawnCanMoveOneSpace = Pawn(!canMoveTwoSpaces, White)
	val BlackRook = Rook(Black)
	val WhiteRook = Rook(White)
}
