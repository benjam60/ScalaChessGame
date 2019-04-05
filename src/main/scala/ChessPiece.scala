package ChessGame

import ChessGame.InputMoveValidation.BoardPosition


object AllPieces {

  sealed trait ChessPiece {
    val color : Color
    val displayName: String

    def isValidMove(source: BoardPosition, destination: BoardPosition): Boolean
  }

  val BlackPawnCanMoveTwice = Pawn(false, Black)
  val BlackPawnCanMoveOnce = Pawn(true, Black)
  val WhitePawnCanMoveTwice = Pawn(false, White)
  val WhitePawnCanMoveOnce = Pawn(true, White)


  case class Pawn(alreadyMovedTwice : Boolean, override val color : Color) extends ChessPiece {
    override val displayName: String = if (color == White) WhitePawnName else BlackPawnName

    override def isValidMove(source: BoardPosition, destination: BoardPosition): Boolean = {
      val numSpacesCanMove = if (alreadyMovedTwice) 1 else 2
      source.file == destination.file &&
        Math.abs(source.rank.boardStateIndex - destination.rank.boardStateIndex) <= numSpacesCanMove
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
