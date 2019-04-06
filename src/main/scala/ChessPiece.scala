package ChessGame

import ChessGame.InputMoveValidation.BoardPosition


object AllPieces {

  sealed trait ChessPiece {
    val color : Color
    val displayName: String

    //how can i write the code so all the pieces by default must use this function + their code
    def isValidMove(board: Board, pieceToMove: ChessPiece, source: BoardPosition, destination: BoardPosition): Boolean = {
      board.get(destination).exists(_.color != pieceToMove.color)
    }
  }
  private val hasMovedTwice = true //canMoveTwice and only need one boolean, use operator
  private val hasNotMovedTwice = false
  val BlackPawnCanMoveTwice = Pawn(hasNotMovedTwice, Black) //change wording
  val BlackPawnCanMoveOnce = Pawn(hasMovedTwice, Black)
  val WhitePawnCanMoveTwice = Pawn(hasNotMovedTwice, White)
  val WhitePawnCanMoveOnce = Pawn(hasMovedTwice, White)


  case class Pawn(alreadyMovedTwice : Boolean, override val color : Color) extends ChessPiece {
    override val displayName: String = if (color == White) WhitePawnName else BlackPawnName

    override def isValidMove(board: Board, pieceToMove: ChessPiece, source: BoardPosition, destination: BoardPosition): Boolean =
      super.isValidMove(board, pieceToMove, source, destination) && {
      if (source.fileBoardIndex == destination.fileBoardIndex) isLegalVerticalMove(board, source, destination)
      else isLegalDiagonalMove(board, source, destination) }

    private def isLegalDiagonalMove(board: Board, source: BoardPosition, destination: BoardPosition) : Boolean =
      source.rankBoardIndex - destination.rankBoardIndex == 1*color.direction &&
        List(1, -1).contains(source.fileBoardIndex - destination.fileBoardIndex) &&
        board.state(destination.rankBoardIndex)(destination.fileBoardIndex).exists(_.color == oppositeColor)

    private def isLegalVerticalMove(board: Board, source: BoardPosition,
                                    destination: BoardPosition) : Boolean = {
      val numSpacesCanMove = if (alreadyMovedTwice) 1 else 2
      source.fileBoardIndex == destination.fileBoardIndex &&
        Math.abs(source.rankBoardIndex - destination.rankBoardIndex) <= numSpacesCanMove
    }

    private def WhitePawnName = "Paw"
    private def BlackPawnName = WhitePawnName.toLowerCase
    private def oppositeColor: Color = if (color == White) Black else White
  }



  object Knight extends ChessPiece {
    override val displayName = "Kni"
    override val color: Color = White

    override def isValidMove(board: Board, pieceToMove: ChessPiece, source: BoardPosition, destination: BoardPosition): Boolean = true
  }

  object Rook extends ChessPiece {
    override val displayName = "Roo"
    override val color: Color = White

    override def isValidMove(board: Board, pieceToMove: ChessPiece, source: BoardPosition, destination: BoardPosition): Boolean = true
  }

  object Queen extends ChessPiece {
    override val displayName = "Que"
    override val color: Color = White

    override def isValidMove(board: Board, pieceToMove: ChessPiece, source: BoardPosition, destination: BoardPosition): Boolean = true
  }

  object King extends ChessPiece {
    override val displayName = "Kin"
    override val color: Color = White

    override def isValidMove(board: Board, pieceToMove: ChessPiece, source: BoardPosition, destination: BoardPosition): Boolean = true
  }

  object Bishop extends ChessPiece {
    override val displayName = "Bis"
    override val color: Color = White

    override def isValidMove(board: Board, pieceToMove: ChessPiece, source: BoardPosition, destination: BoardPosition): Boolean = true
  }

}
