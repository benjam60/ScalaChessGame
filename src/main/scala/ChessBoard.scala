package ChessGame
import ChessPieceDisplayNames._
import ChessBoardUtilityFunctions.writeToBoardCell

  class ChessBoard(boardState: List[List[String]]) {
    val board: List[List[String]] = boardState

    override def toString: String = boardState.map("| " + _.mkString("|") + " |").mkString("\n")

    def movePiece(srcX : Int, srcY : Int, destX : Int, destY : Int) : ChessBoard = {
      val pieceValue = board(srcX)(srcY)
      val erasedLocationName =  srcX + "," + srcY
      val boardWithErasedSource = writeToBoardCell(board, srcX, srcY, erasedLocationName)
      new ChessBoard(writeToBoardCell(boardWithErasedSource, destX, destY, pieceValue))
    }
  }

object ChessBoardUtilityFunctions {
  val BoardSize = 8
  def writeToBoardCell(board : List[List[String]], xCoord : Int, yCoord : Int, piece : String) : List[List[String]] = {
    val numberOfRowsBeforeEditedRow = xCoord
    val indexOfRowAfterEditedRow = xCoord + 1
    board.take(numberOfRowsBeforeEditedRow) ++ List(board(xCoord).patch[String, List[String]](yCoord, Seq(piece), replaced = 1)) ++
    board.drop(indexOfRowAfterEditedRow)
  }
}

  object InitialChessBoardState {
    val board: List[List[String]] = List(
      List(Rook, Knight, Bishop, Queen, King, Bishop, Knight, Rook),
      List.fill(8)(Pawn),
      List.fill(8)(Space).zipWithIndex.map{case(_,i) => "2," + i.toString},
      List.fill(8)(Space).zipWithIndex.map{case(_,i) => "3," + i.toString},
      List.fill(8)(Space).zipWithIndex.map{case(_,i) => "4," + i.toString},
      List.fill(8)(Space).zipWithIndex.map{case(_,i) => "5," + i.toString},
      List.fill(8)(Pawn),
      List(Rook, Knight, Bishop, King, Queen, Bishop, Knight, Rook)
    )
  }


