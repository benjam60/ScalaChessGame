package ChessGame
import ChessPieceDisplayNames._
import ChessBoardUtilityFunctions.writeToBoardCell

  class ChessBoard(boardState: List[List[String]]) {
    val board: List[List[String]] = boardState

    override def toString: String = boardState.map("| " + _.mkString("|") + " |").mkString("\n")

    def movePiece(sourceXCoordinate : Int, sourceYCoordinate : Int, destXCoord : Int, destYCoord : Int) : ChessBoard = {
      val pieceValue = board(sourceXCoordinate)(sourceYCoordinate)
      val erasedLocationName = s"$sourceXCoordinate,$sourceYCoordinate"
      val boardWithErasedSource = writeToBoardCell(board, sourceXCoordinate, sourceYCoordinate, erasedLocationName)
      new ChessBoard(writeToBoardCell(boardWithErasedSource, destXCoord, destYCoord, pieceValue))
    }
  }

object ChessBoardUtilityFunctions {
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
      List.fill(8)(Space).zipWithIndex.map{case(_,i) => s"2,${i.toString}"},
      List.fill(8)(Space).zipWithIndex.map{case(_,i) => s"3,${i.toString}"},
      List.fill(8)(Space).zipWithIndex.map{case(_,i) => s"4,${i.toString}"},
      List.fill(8)(Space).zipWithIndex.map{case(_,i) => s"5,${i.toString}"},
      List.fill(8)(Pawn),
      List(Rook, Knight, Bishop, King, Queen, Bishop, Knight, Rook)
    )
  }


