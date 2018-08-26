package ChessGame
import ChessPieceDisplayNames._
import ChessBoardUtilityFunctions.{writeToBoardCell, addSpacing, formatRow}

  class ChessBoard(boardState: List[List[String]]) {
    val board: List[List[String]] = boardState

    override def toString: String = {
      val topLeftCorner = Space
      val fileLettersRow = List(topLeftCorner, "A", "B", "C", "D", "E", "F", "G", "H").map(addSpacing)
      val getRowsWithRank = boardState.zipWithIndex
      formatRow(fileLettersRow) + getRowsWithRank.map {
        case (row: List[String], rank: Int) => "|" + addSpacing(rank.toString) + formatRow(row)                        //"| " + rank + " | " + row.mkString("|") + " |"
      }.mkString("")
    }

    def movePiece(sourceRank : Int, sourceFile : Char, destRank : Int, destFile : Char) : ChessBoard = {
      val pieceValue = board(sourceFile)(sourceRank)
      val erasedLocationName = s"$sourceFile,$sourceRank"
      val boardWithErasedSource = writeToBoardCell(board, sourceFile, sourceRank, erasedLocationName)
      new ChessBoard(writeToBoardCell(boardWithErasedSource, destFile, destRank, pieceValue))
    }
  }

object ChessBoardUtilityFunctions {
  def writeToBoardCell(board : List[List[String]], file : Char, rank : Int, piece : String) : List[List[String]] = {
    val fileInt = convertFileToIndex(file)
    val numberOfRowsBeforeEditedRow = fileInt
    val indexOfRowAfterEditedRow = fileInt + 1
    board.take(numberOfRowsBeforeEditedRow) ++
      List(board(file).patch[String, List[String]](rank, Seq(piece), replaced = 1)) ++
      board.drop(indexOfRowAfterEditedRow)
  }
  def addSpacing(boardCell : String) = if (boardCell.size != 3) { " " + boardCell + " "} else boardCell
  def formatRow(row : List[String]) =  "|" + row.map(addSpacing).mkString("|") + "|\n"
  private def convertFileToIndex(file : Char) : Int = file.toInt - 65
}

  object InitialChessBoardState {
    val rowSize = 8
    val firstRowIndex = 1
    val get: List[List[String]] = List(
      List(Rook, Knight, Bishop, Queen, King, Bishop, Knight, Rook),
      List.fill(rowSize)(Pawn),
      List.fill(rowSize)(Space),
      List.fill(rowSize)(Space),
      List.fill(rowSize)(Space),
      List.fill(rowSize)(Space),
      List.fill(rowSize)(Pawn),
      List(Rook, Knight, Bishop, King, Queen, Bishop, Knight, Rook)
    )
  }


