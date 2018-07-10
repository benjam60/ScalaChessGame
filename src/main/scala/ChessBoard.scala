package ChessGame
import ChessPieceDisplayNames._
import ChessBoardUtilityFunctions.replaceOnBoard

  class ChessBoard(boardState: List[List[String]]) {
    val board: List[List[String]] = boardState

    override def toString: String = {
      val rowsWithSeparator = for {
        row <- board
      } yield "| " + row.mkString("|") + " |"
      rowsWithSeparator.mkString("\n")
    }

    def movePiece(srcX : Int, srcY : Int, destX : Int, destY : Int) : ChessBoard = {
      val x = board.take(srcX - 1)
      val y = board(srcX).patch[String, List[String]](srcY, Seq(Space), 1)
      val z = board.drop(srcX)
      val pieceName = board(srcX)(srcY)
     // val boardPickedUpPiece = board.take(srcX) ++ List(board(srcX).patch[String, List[String]](srcY, Seq(Space), 1)) ++ board.drop(srcX + 1) //not correct must fix, what srcx - 1 is -1
      //val placedPiece = boardPickedUpPiece.take(destX) ++ List(boardPickedUpPiece(destX).patch[String, List[String]](destY, Seq(pieceName), 1)) ++ boardPickedUpPiece.drop(destX + 1)
      new ChessBoard(replaceOnBoard(replaceOnBoard(board, srcX, srcY, srcX + "," + srcY), destX, destY, pieceName))
    }

  }

object ChessBoardUtilityFunctions {
  def replaceOnBoard(board : List[List[String]], xCoord : Int, yCoord : Int, piece : String) : List[List[String]] = {
    board.take(xCoord) ++ List(board(xCoord).patch[String, List[String]](yCoord, Seq(piece), 1)) ++ board.drop(xCoord + 1)
  }
}

  object InitialChessBoardState {
    val board: List[List[String]] = List(
      List(Rook, Knight, Bishop, Queen, King, Bishop, Knight, Rook),
      List.fill(8)(Pawn),
      List.fill(8)(Space).zipWithIndex.map{case(_,i) => 2 + "," + i.toString},
      List.fill(8)(Space).zipWithIndex.map{case(_,i) => 3 + "," + i.toString},
      List.fill(8)(Space).zipWithIndex.map{case(_,i) => 4 + "," + i.toString},
      List.fill(8)(Space).zipWithIndex.map{case(_,i) => 5 + "," + i.toString},
      List.fill(8)(Pawn),
      List(Rook, Knight, Bishop, King, Queen, Bishop, Knight, Rook)
    )
  }


