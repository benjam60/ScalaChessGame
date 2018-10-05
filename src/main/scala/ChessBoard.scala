package ChessGame

import ChessGame.AllPieces._
import ChessGame.ChessBoardUtilityFunctions.{createChessSquare, formatFiles, formatRow}
import ChessGame.Color.Color

class ChessBoard(val state: List[List[ChessPiece]], val turn: Color) {

  def displayWithRankAndFile: String = {
    val topLeftCorner: Char = ' '
    val rowWithFileLettersAndSpace = topLeftCorner :: ('A' to 'H').toList
    val boardWithRanks = state.zip(1 to 8)
    val formattedFileRow = formatFiles(rowWithFileLettersAndSpace)
    val displayableBoardWithRanks =  boardWithRanks.map {
      case (row: List[ChessPiece], rank: Int) => createChessSquare(rank) + formatRow(row)
    }.mkString
    formattedFileRow + displayableBoardWithRanks
  }
}

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