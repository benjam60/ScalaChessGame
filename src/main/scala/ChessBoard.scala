package ChessGame

import ChessPieceDisplayNames._
import ChessBoardUtilityFunctions._

class ChessBoard(boardState: List[List[String]]) {
  val state: List[List[String]] = boardState

  def movePiece(sourceRank: Int, sourceFile: Char, destRank: Int, destFile: Char): ChessBoard = {
    val zeroIndexSourceRank = sourceRank - 1
    val zeroIndexDestRank = destRank - 1
    val pieceToMove = state(zeroIndexSourceRank)(convertFileToIndex(sourceFile))
    val boardWithDeletedPiece: ChessBoard = deletePiece(this, sourceRank, sourceFile)
    setPiece(boardWithDeletedPiece, pieceToMove, destRank, destFile)
  }

  override def toString: String = {
    val topLeftCorner = Space
    val fileLettersRow = List(topLeftCorner, "A", "B", "C", "D", "E", "F", "G", "H").map(addSpacing)
    val getRowsWithRank = boardState.zip(Stream from 1)
    formatRow(fileLettersRow) + getRowsWithRank.map {
      case (row: List[String], rank: Int) => "|" + addSpacing(rank.toString) + formatRow(row)
    }.mkString("")
  }

  private def setPiece(board: ChessBoard, piece : String, rank : Int, file : Char) : ChessBoard = {
    val zeroIndexedRank = rank - 1
    val fileAsInt = convertFileToIndex(file)
    val numberOfRowsBeforeEditedRow = zeroIndexedRank
    val indexOfRowAfterEditedRow = zeroIndexedRank + 1
    new ChessBoard(board.state.take(numberOfRowsBeforeEditedRow) ++
      List(board.state(zeroIndexedRank).patch[String, List[String]](fileAsInt, Seq(piece), replaced = 1)) ++
      board.state.drop(indexOfRowAfterEditedRow))
  }

  private def getPiece(rank : Int, file : Char) : String = {
    val zeroIndexedRank = rank - 1
    val fileAsInt = convertFileToIndex(file)
    state(zeroIndexedRank)(fileAsInt)
  }

  private def deletePiece(board: ChessBoard, rank : Int, file : Char) : ChessBoard =
    setPiece(board, Space, rank, file)
}

object ChessBoardUtilityFunctions {
  private val StandardPieceSize = 3
  def addSpacing(boardCell: String) = if (boardCell.size != StandardPieceSize) {
    " " + boardCell + " "
  } else boardCell

  def formatRow(row: List[String]) = "|" + row.map(addSpacing).mkString("|") + "|\n"

  def convertFileToIndex(file: Char): Int = file.toInt - 65
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


