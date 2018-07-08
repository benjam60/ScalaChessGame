package ChessGame
import ChessPieceDisplayNames._

  class ChessBoard(boardState: List[List[String]]) {
    val board: List[List[String]] = boardState

    override def toString: String = {
      val rowsWithSeparator = for {
        row <- board
      } yield "| " + row.mkString("|") + " |"
      rowsWithSeparator.mkString("\n")
    }
  }


  object InitialChessBoardState {
    val board: List[List[String]] = List(
      List(Rook, Knight, Bishop, Queen, King, Bishop, Knight, Rook),
      List.fill(8)(Pawn),
      List.fill(8)(Space),
      List.fill(8)(Space),
      List.fill(8)(Space),
      List.fill(8)(Space),
      List.fill(8)(Pawn),
      List(Rook, Knight, Bishop, King, Queen, Bishop, Knight, Rook)
    )
  }


