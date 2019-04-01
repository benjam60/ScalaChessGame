package ChessGame

import ChessGame.InputMoveValidation.BoardPosition


object AllPieces {

  sealed trait ChessPiece {
    val displayName: String
    val color : Color

    def isValidMove(sourcePosition: BoardPosition, destinationPosition: BoardPosition): Boolean
  }

  object BlackPawnCanMoveTwice extends ChessPiece {
    override val displayName = BlackPawnName
    override val color: Color = Black

    def isValidMove(sourcePosition: BoardPosition, destinationPosition: BoardPosition): Boolean =
      isValidPawnCanMoveTwice(sourcePosition, destinationPosition)

  }

  object BlackPawnCanMoveOnce extends ChessPiece {
    override val displayName = BlackPawnName
    override val color: Color = Black

    def isValidMove(sourcePosition: BoardPosition, destinationPosition: BoardPosition): Boolean =
      isValidPawnCanMoveOnce(sourcePosition, destinationPosition)

  }

  object WhitePawnCanMoveTwice extends ChessPiece {
    override val displayName = WhitePawnName
    override val color: Color = White

    def isValidMove(sourcePosition: BoardPosition, destinationPosition: BoardPosition): Boolean =
      isValidPawnCanMoveTwice(sourcePosition, destinationPosition)

  }

  object WhitePawnCanMoveOnce extends ChessPiece {
    override val displayName = WhitePawnName
    override val color: Color = White

    def isValidMove(sourcePosition: BoardPosition, destinationPosition: BoardPosition): Boolean =
      isValidPawnCanMoveOnce(sourcePosition, destinationPosition)
  }

  private def isValidPawnCanMoveOnce(sourcePosition: BoardPosition,
                                     destinationPosition: BoardPosition): Boolean =
    sourcePosition.file == destinationPosition.file &&
      Math.abs(sourcePosition.rank.boardStateIndex - destinationPosition.rank.boardStateIndex) <= 1

  private def isValidPawnCanMoveTwice(sourcePosition: BoardPosition,
                                      destinationPosition: BoardPosition): Boolean =
    sourcePosition.file == destinationPosition.file &&
      Math.abs(sourcePosition.rank.boardStateIndex - destinationPosition.rank.boardStateIndex) <= 2

  val WhitePawnName = "Paw"
  val BlackPawnName = WhitePawnName.toLowerCase


  object Knight extends ChessPiece {
    override val displayName = "Kni"
    override val color: Color = White

    def isValidMove(sourcePosition: BoardPosition, destinationPosition: BoardPosition): Boolean = true
  }

  object Rook extends ChessPiece {
    override val displayName = "Roo"
    override val color: Color = White

    def isValidMove(sourcePosition: BoardPosition, destinationPosition: BoardPosition): Boolean = true
  }

  object Queen extends ChessPiece {
    override val displayName = "Que"
    override val color: Color = White

    def isValidMove(sourcePosition: BoardPosition, destinationPosition: BoardPosition): Boolean = true
  }

  object King extends ChessPiece {
    override val displayName = "Kin"
    override val color: Color = White

    def isValidMove(sourcePosition: BoardPosition, destinationPosition: BoardPosition): Boolean = true
  }

  object Bishop extends ChessPiece {
    override val displayName = "Bis"
    override val color: Color = White

    def isValidMove(sourcePosition: BoardPosition, destinationPosition: BoardPosition): Boolean = true
  }

  object Space extends ChessPiece {
    override val displayName = " " * 3
    override val color: Color = White //shouldn't exist

    def isValidMove(sourcePosition: BoardPosition, destinationPosition: BoardPosition): Boolean = false
  }

}