package ChessGame

import ChessPieceDisplayNames._
import ChessBoardUtilityFunctions._

class ChessBoard(boardState: List[List[String]]) {
  val state: List[List[String]] = boardState

  def movePiece(sourceRank: Int, sourceFile: Char, destRank: Int, destFile: Char): ChessBoard = {
    val pieceToMove = getPiece(sourceRank, sourceFile)
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

  private def setPiece(board: ChessBoard, piece : String, rankIn : Int, fileIn : Char) : ChessBoard = {
    val(rank, file) = boardStateIndexes(rankIn, fileIn)
    val numberOfRowsBeforeEditedRow = rank
    val indexOfRowAfterEditedRow = rank + 1
    new ChessBoard(board.state.take(numberOfRowsBeforeEditedRow) ++
      List(board.state(rank).patch[String, List[String]](file, Seq(piece), replaced = 1)) ++
      board.state.drop(indexOfRowAfterEditedRow))
  }

  private def getPiece(rankIn : Int, fileIn : Char) : String = {
    val(rank, file) = boardStateIndexes(rankIn, fileIn)
    state(rank)(file)
  }

  private def deletePiece(board: ChessBoard, rank : Int, file : Char) : ChessBoard =
    setPiece(board, Space, rank, file)
}

object ChessBoardUtilityFunctions {
  private val StandardPieceSize = 3
  def addSpacing(boardCell: String) = if (boardCell.size != StandardPieceSize) {
    " " + boardCell + " "
  } else boardCell

  def boardStateIndexes(rank : Int, file : Char) : (Int, Int) = (rank - 1, convertFileToIndex(file))

  def formatRow(row: List[String]) = "|" + row.map(addSpacing).mkString("|") + "|\n"

  def convertFileToIndex(file: Char): Int = file.toInt - 65
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


