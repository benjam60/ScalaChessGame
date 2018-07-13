package ChessGame
import ChessPieceDisplayNames._
import ChessBoardUtilityFunctions.writeToBoardCell

  class ChessBoard(boardState: List[List[String]]) {
    val board: List[List[String]] = boardState

    override def toString: String = boardState.map("| " + _.mkString("|") + " |").mkString("\n")

    def movePiece(sourceFile : Int, sourceRank : Int, destFile : Int, destRank : Int) : ChessBoard = {
      val pieceValue = board(sourceFile)(sourceRank)
      val erasedLocationName = s"$sourceFile,$sourceRank"
      val boardWithErasedSource = writeToBoardCell(board, sourceFile, sourceRank, erasedLocationName)
      new ChessBoard(writeToBoardCell(boardWithErasedSource, destFile, destRank, pieceValue))
    }
  }

object ChessBoardUtilityFunctions {
  def writeToBoardCell(board : List[List[String]], file : Int, rank : Int, piece : String) : List[List[String]] = {
    val numberOfRowsBeforeEditedRow = file
    val indexOfRowAfterEditedRow = file + 1
    board.take(numberOfRowsBeforeEditedRow) ++
      List(board(file).patch[String, List[String]](rank, Seq(piece), replaced = 1)) ++
      board.drop(indexOfRowAfterEditedRow)
  }
}

  object InitialChessBoardState {
    val rowSize = 8
    val get: List[List[String]] = List(
      List(Rook, Knight, Bishop, Queen, King, Bishop, Knight, Rook),
      List.fill(rowSize)(Pawn),
      List.fill(rowSize)(Space).zipWithIndex.map{case(_,i) => s"2,$i"},
      List.fill(rowSize)(Space).zipWithIndex.map{case(_,i) => s"3,$i"},
      List.fill(rowSize)(Space).zipWithIndex.map{case(_,i) => s"4,$i"},
      List.fill(rowSize)(Space).zipWithIndex.map{case(_,i) => s"5,$i"},
      List.fill(rowSize)(Pawn),
      List(Rook, Knight, Bishop, King, Queen, Bishop, Knight, Rook)
    )
  }


