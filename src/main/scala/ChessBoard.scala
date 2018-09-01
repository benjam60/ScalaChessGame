package ChessGame

import ChessPieceDisplayNames._
import ChessBoardUtilityFunctions._

class ChessBoard(boardState: List[List[String]]) {
  val state: List[List[String]] = boardState

  def movePiece(sourceRank: Int, sourceFile: Char, destRank: Int, destFile: Char): ChessBoard = {
    val pieceToMove = getPiece(sourceRank, sourceFile)
    val boardWithDeletedPiece: ChessBoard = deletePiece(this, sourceRank, sourceFile)
    val x = setPiece(boardWithDeletedPiece, pieceToMove, destRank, destFile)
    x
  }

  override def toString: String = {
    val topLeftCorner = Space
    val fileLettersRow = List(topLeftCorner, "A", "B", "C", "D", "E", "F", "G", "H").map(addSpacing)
    val getRowsWithRank = boardState.zip(Stream from 1)
    formatRow(fileLettersRow) + getRowsWithRank.map {
      case (row: List[String], rank: Int) => "|" + addSpacing(rank.toString) + formatRow(row)
    }.mkString("")
  }

  private def getPiece(rankIn : Int, fileIn : Char) : String = {
    val(row, col) = boardStateIndexes(rankIn, fileIn)
    state(row)(col)
  }

  private def deletePiece(board: ChessBoard, rank : Int, file : Char) : ChessBoard =
    setPiece(board, Space, rank, file)

  private def setPiece(board: ChessBoard, piece : String, rankIn : Int, fileIn : Char) : ChessBoard = {
    val(row, col) = boardStateIndexes(rankIn, fileIn)
    new ChessBoard(overwriteCell(board.state, piece, row, col))
  }

  private def overwriteCell(boardState: List[List[String]], piece : String, row : Int, col : Int) : List[List[String]] = {
    val numberOfRowsBeforeEditedRow = row
    val indexOfRowAfterEditedRow = row + 1
      boardState.take(numberOfRowsBeforeEditedRow) ++
      List(boardState(row).patch[String, List[String]](col, Seq(piece), replaced = 1)) ++
      boardState.drop(indexOfRowAfterEditedRow)
  }
}

object ChessBoardUtilityFunctions {
  def addSpacing(boardCell: String) = if (boardCell.size != StandardPieceSize) {
    " " + boardCell + " "
  } else boardCell

  def boardStateIndexes(rank : Int, file : Char) : (Int, Int) = (rank - 1, convertFileToIndex(file))

  def formatRow(row: List[String]) = "|" + row.map(addSpacing).mkString("|") + "|\n"

  def convertFileToIndex(file: Char): Int = file.toInt - 65
  private val StandardPieceSize = 3
}

object InitialChessBoardState {
  val rowSize = 8
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


