package ChessGame //TODO BE: Fix package name
import BoardUtilityFunctions.next

object AllPieces {

  sealed trait ChessPiece {
    protected val colorAgnosticDisplayName : String
    val color : Color
    def displayName: String =
      if (color == White) colorAgnosticDisplayName.toUpperCase else colorAgnosticDisplayName.toLowerCase

    def isValidMove(board : Board, source: BoardPosition, destination: BoardPosition): Boolean = {
      val isNotEatingOwnPiece = board.get(destination).forall(_.color != color)
      isNotEatingOwnPiece && isValidMoveForPiece(board, source, destination)
    }

    protected def isValidMoveForPiece(board: Board, source: BoardPosition, destination: BoardPosition): Boolean
  }

  case class Pawn(canMoveTwoSpaces : Boolean, override val color : Color) extends ChessPiece {
    override val colorAgnosticDisplayName: String = "Paw"

    override def isValidMoveForPiece(board: Board, source: BoardPosition, destination: BoardPosition): Boolean =
      if (source.fileBoardIndex == destination.fileBoardIndex)
        isLegalVerticalMove(board, source, destination) && board.get(destination).isEmpty
      else isLegalDiagonalMove(board, source, destination)

    private def isLegalDiagonalMove(board: Board, source: BoardPosition, destination: BoardPosition) : Boolean =
      source.rankBoardIndex - destination.rankBoardIndex == color.direction &&
        List(1, -1).contains(source.fileBoardIndex - destination.fileBoardIndex) &&
        board.state(destination.rankBoardIndex)(destination.fileBoardIndex).exists(_.color == next(color))

    private def isLegalVerticalMove(board: Board, source: BoardPosition,
                                    destination: BoardPosition) : Boolean = {
      val numSpacesCanMove = if (canMoveTwoSpaces) List(1, 2).map(_*color.direction) else List(1).map(_*color.direction)
      source.fileBoardIndex == destination.fileBoardIndex &&
        numSpacesCanMove.contains(source.rankBoardIndex - destination.rankBoardIndex)
    }
  }

  case class Knight(override val color: Color) extends ChessPiece {
    override val colorAgnosticDisplayName: String = "Kni"

    override def isValidMoveForPiece(board: Board, source: BoardPosition, destination: BoardPosition): Boolean = {
      val rankDifference = Math.abs(source.rankBoardIndex - destination.rankBoardIndex)
      val fileDifference = Math.abs(source.fileBoardIndex - destination.fileBoardIndex)
      (rankDifference == 2 && fileDifference == 1) || (rankDifference == 1 && fileDifference == 2)
    }
  }

  case class Rook(override val color : Color) extends ChessPiece {
    override val colorAgnosticDisplayName: String = "Roo"

    override def isValidMoveForPiece(board: Board, source: BoardPosition, destination: BoardPosition): Boolean = true
  }

  case class Queen(override val color : Color) extends ChessPiece {
    override val colorAgnosticDisplayName: String = "Que"

    override def isValidMoveForPiece(board: Board, source: BoardPosition, destination: BoardPosition): Boolean = true
  }

  case class King(override val color : Color) extends ChessPiece {
    override val colorAgnosticDisplayName: String = "Kin"

    override def isValidMoveForPiece(board: Board, source: BoardPosition, destination: BoardPosition): Boolean = true
  }

  case class Bishop(override val color : Color) extends ChessPiece {
    override val colorAgnosticDisplayName: String = "Bis"

    override def isValidMoveForPiece(board: Board, source: BoardPosition, destination: BoardPosition): Boolean = true
  }


  private val canMoveTwoSpaces = true
  val BlackPawnCanMoveTwoSpaces = Pawn(canMoveTwoSpaces, Black)
  val BlackPawnCanMoveOneSpace = Pawn(!canMoveTwoSpaces, Black)
  val WhitePawnCanMoveTwoSpaces = Pawn(canMoveTwoSpaces, White)
  val WhitePawnCanMoveOneSpace = Pawn(!canMoveTwoSpaces, White)
	val BlackRook = Rook(Black)
	val WhiteRook = Rook(White)
  val BlackKnight = Knight(Black)
  val WhiteKnight = Knight(White)
  val BlackKing = King(Black)
  val WhiteKing = King(White)
  val BlackQueen = Queen(Black)
  val WhiteQueen = Queen(White)
  val BlackBishop = Bishop(Black)
  val WhiteBishop = Bishop(White)
}
