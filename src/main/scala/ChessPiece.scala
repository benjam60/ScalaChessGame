package ChessGame

import ChessGame.InputMoveValidation.BoardPosition


object AllPieces {

  sealed trait ChessPiece {
    val color : Color
    val displayName: String

    def isValidMove(source: BoardPosition, destination: BoardPosition): Boolean
  }
  private val hasMovedTwice = true
  private val hasNotMovedTwice = false
  val BlackPawnCanMoveTwice = Pawn(hasNotMovedTwice, Black) //change wording
  val BlackPawnCanMoveOnce = Pawn(hasMovedTwice, Black)
  val WhitePawnCanMoveTwice = Pawn(hasNotMovedTwice, White)
  val WhitePawnCanMoveOnce = Pawn(hasMovedTwice, White)


  case class Pawn(alreadyMovedTwice : Boolean, override val color : Color) extends ChessPiece {
    override val displayName: String = if (color == White) WhitePawnName else BlackPawnName

    override def isValidMove(source: BoardPosition, destination: BoardPosition): Boolean = {
      val numSpacesCanMove = if (alreadyMovedTwice) 1 else 2
      source.fileBoardIndex == destination.fileBoardIndex &&
        Math.abs(source.rankBoardIndex - destination.rankBoardIndex) <= numSpacesCanMove
    }

    private def WhitePawnName = "Paw"
    private def BlackPawnName = WhitePawnName.toLowerCase
  }

  object Knight extends ChessPiece {
    override val displayName = "Kni"
    override val color: Color = White

    def isValidMove(source: BoardPosition, destination: BoardPosition): Boolean = true
  }

  object Rook extends ChessPiece {
    override val displayName = "Roo"
    override val color: Color = White

    def isValidMove(source: BoardPosition, destination: BoardPosition): Boolean = true
  }

  object Queen extends ChessPiece {
    override val displayName = "Que"
    override val color: Color = White

    def isValidMove(source: BoardPosition, destination: BoardPosition): Boolean = true
  }

  object King extends ChessPiece {
    override val displayName = "Kin"
    override val color: Color = White

    def isValidMove(source: BoardPosition, destination: BoardPosition): Boolean = true
  }

  object Bishop extends ChessPiece {
    override val displayName = "Bis"
    override val color: Color = White

    def isValidMove(source: BoardPosition, destination: BoardPosition): Boolean = true
  }

}
