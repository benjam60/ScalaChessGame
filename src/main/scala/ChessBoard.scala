package ChessGame

import ChessGame.AllPieces._
import ChessGame.ChessBoardUtilityFunctions.{createChessSquare, formatFiles, formatRow}
import ChessGame.Color.Color

case class ChessBoard(state: List[List[ChessPiece]], turn: Color)

object Color extends Enumeration {
  type Color = Value
  val White, Black = Value
}

object InitialInternalChessBoardState {
  private val rowSize = 8
  //need to differentiate
  val get: List[List[ChessPiece]] = List(
    List(Rook, Knight, Bishop, Queen, King, Bishop, Knight, Rook),
    List.fill(rowSize)(PawnCanMoveTwice),
    List.fill(rowSize)(Space),
    List.fill(rowSize)(Space),
    List.fill(rowSize)(Space),
    List.fill(rowSize)(Space),
    List.fill(rowSize)(PawnCanMoveTwice),
    List(Rook, Knight, Bishop, King, Queen, Bishop, Knight, Rook)
  )
}