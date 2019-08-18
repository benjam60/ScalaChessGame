package ChessGame //TODO BE: Fix package name
import BoardUtilityFunctions.getOther
import ChessGame.PieceMovementValidation.arePiecesInBetween
import GeneralPieceMovementRules._

object AllPieces {

  sealed trait ChessPiece {
    def getColor : Color = color
    def getDisplayName: String =
    if (color == White) colorAgnosticDisplayName.toUpperCase else colorAgnosticDisplayName.toLowerCase

    def isValidMove(board : Board, source: BoardPosition, destination: BoardPosition): Boolean

    protected val colorAgnosticDisplayName : String
    protected val color : Color
  }

  case class Pawn(canMoveTwoSpaces : Boolean, override val color : Color) extends ChessPiece {
    override val colorAgnosticDisplayName: String = "Paw"

    override def isValidMove(board: Board, source: BoardPosition, destination: BoardPosition): Boolean = {
      val verticalMovementRule = if (canMoveTwoSpaces) isLegalVerticalMoveTwoSpaces else isLegalVerticalMoveOneSpace
      val legalMove = LegalMove(source, destination)
      (!arePiecesInBetween(board, source, destination) &&
        List(verticalMovementRule, correctColorDirection, isNotCapturingPiece).forall(f => f(board, legalMove))) ||
        List(correctColorDirection, isValidPawnDiagnolCapture, destinationHasPiece).forall(f => f(board, legalMove))
    }
  }

  case class Knight(override val color: Color) extends ChessPiece {
    override val colorAgnosticDisplayName: String = "Kni"

    override def isValidMove(board: Board, source: BoardPosition, destination: BoardPosition): Boolean =
      (calculateVerticalDistance(source, destination), calculateHorizontalDistance(source, destination)) match {
        case (1, 2) => true
        case (2, 1) => true
        case _ => false
      }
  }

  case class Rook(override val color : Color) extends ChessPiece {
    override val colorAgnosticDisplayName: String = "Roo"

    override def isValidMove(board: Board, source: BoardPosition, destination: BoardPosition): Boolean =
      calculateHorizontalDistance(source, destination) != 0 ^ calculateVerticalDistance(source, destination) != 0
  }

  case class Queen(override val color : Color) extends ChessPiece {
    override val colorAgnosticDisplayName: String = "Que"

    override def isValidMove(board: Board, source: BoardPosition, destination: BoardPosition): Boolean = {
      val anyColor = White
      Rook(anyColor).isValidMove(board, source, destination) || Bishop(anyColor).isValidMove(board, source, destination)
    }
  }

  case class King(override val color : Color) extends ChessPiece {
    override val colorAgnosticDisplayName: String = "Kin"

    override def isValidMove(board: Board, source: BoardPosition, destination: BoardPosition): Boolean =
      Math.abs(source.fileBoardIndex - destination.fileBoardIndex) < 2 &&
        Math.abs(source.rankBoardIndex - destination.rankBoardIndex) < 2

  }

  case class Bishop(override val color : Color) extends ChessPiece {
    override val colorAgnosticDisplayName: String = "Bis"

    override def isValidMove(board: Board, source: BoardPosition, destination: BoardPosition): Boolean =
      calculateHorizontalDistance(source, destination) == calculateVerticalDistance(source, destination) &&
        !arePiecesInBetweenDiagonally(board, source, destination)
  }

  private def arePiecesInBetweenDiagonally(board: Board, source: BoardPosition,
                                           destination: BoardPosition) : Boolean =
    (source.rankBoardIndex, source.fileBoardIndex, destination.rankBoardIndex, destination.fileBoardIndex) match {
      case (srcRank, srcFile, destRank, destFile) if srcRank > destRank && srcFile < destFile  => {
        val filesToTraverse = (srcFile + 1 until destFile).toList
        (srcRank - 1 to destRank + 1 by -1).toList.zip(filesToTraverse).exists {
          case (r : Int, f : Int) => board.state(r)(f).isDefined
        }
      }
      case (srcRank, srcFile, destRank, destFile) if srcRank < destRank && srcFile < destFile  => {
        (1 until destRank - srcRank).toList.exists { index =>
          board.state(srcRank + index)(srcFile + index).isDefined
        }
      }
      case _ => false
  }

  private def calculateVerticalDistance(source: BoardPosition, destination: BoardPosition) : Int =
    math.abs(source.rankBoardIndex - destination.rankBoardIndex)
  private def calculateHorizontalDistance(source: BoardPosition, destination: BoardPosition) : Int =
    math.abs(source.fileBoardIndex - destination.fileBoardIndex)

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
