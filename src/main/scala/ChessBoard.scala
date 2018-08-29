package ChessGame
import ChessPieceDisplayNames._
import ChessBoardUtilityFunctions.{writeToBoardCell, addSpacing, formatRow, convertFileToIndex}

  class ChessBoard(boardState: List[List[String]]) {
    val board: List[List[String]] = boardState

    override def toString: String = {
      val topLeftCorner = Space
      val fileLettersRow = List(topLeftCorner, "A", "B", "C", "D", "E", "F", "G", "H").map(addSpacing)
      val getRowsWithRank = boardState.zipWithIndex
      formatRow(fileLettersRow) + getRowsWithRank.map {
        case (row: List[String], rank: Int) => "|" + addSpacing(rank.toString) + formatRow(row)
      }.mkString("")
    }

    def movePiece(sourceRank : Int, sourceFile : Char, destRank : Int, destFile : Char) : ChessBoard = {
      val pieceToMove = board(sourceRank)(convertFileToIndex(sourceFile))
      val boardWithSpaceAtSource : List[List[String]] =
        writeToBoardCell(board, sourceRank, convertFileToIndex(sourceFile), Space)
      new ChessBoard(writeToBoardCell(boardWithSpaceAtSource, destRank, convertFileToIndex(destFile), pieceToMove))
    }
  }

object ChessBoardUtilityFunctions {
  def writeToBoardCell(board : List[List[String]], rank : Int, file : Int, piece : String) : List[List[String]] = {
    val numberOfRowsBeforeEditedRow = rank
    val indexOfRowAfterEditedRow = rank + 1
    board.take(numberOfRowsBeforeEditedRow) ++
      List(board(rank).patch[String, List[String]](file, Seq(piece), replaced = 1)) ++
      board.drop(indexOfRowAfterEditedRow)
  }
  def addSpacing(boardCell : String) = if (boardCell.size != 3) { " " + boardCell + " "} else boardCell
  def formatRow(row : List[String]) =  "|" + row.map(addSpacing).mkString("|") + "|\n"
  def convertFileToIndex(file : Char) : Int = file.toInt - 65
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


