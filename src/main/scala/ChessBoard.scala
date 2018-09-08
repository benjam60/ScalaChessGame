package ChessGame

import AllPieces._
import ChessBoardUtilityFunctions._

class ChessBoard(boardState: List[List[ChessPiece]]) {
  val state: List[List[ChessPiece]] = boardState

  def movePiece(sourceRank: Int, sourceFile: Char, destRank: Int, destFile: Char): ChessBoard = {
    val pieceToMove = getPiece(sourceRank, sourceFile)
    if (pieceToMove.isValidMove(sourceRank, sourceFile, destRank, destFile)) {
      val boardWithDeletedPiece: ChessBoard = deletePiece(this, sourceRank, sourceFile)
      if (pieceToMove.getClass == PawnCanMoveTwice.getClass) {
        setPiece(boardWithDeletedPiece, PawnCanMoveOnce, destRank, destFile)
      }
      else {
        setPiece(boardWithDeletedPiece, pieceToMove, destRank, destFile)
      }
    }
    else this
  }

  override def toString: String = {
    val topLeftCorner = "   "
    val fileLettersRow = List(topLeftCorner, "A", "B", "C", "D", "E", "F", "G", "H").map(addSpacing)
    val getRowsWithRank = boardState.zip(Stream from 1)
    formatFiles(fileLettersRow) + getRowsWithRank.map {
      case (row: List[ChessPiece], rank: Int) => "|" + addSpacing(rank.toString) + formatRow(row)
    }.mkString("")
  }

  private def getPiece(rankIn : Int, fileIn : Char) : ChessPiece = {
    val(row, col) = boardStateIndexes(rankIn, fileIn)
    state(row)(col)
  }

  private def deletePiece(board: ChessBoard, rank : Int, file : Char) : ChessBoard =
    setPiece(board, Space, rank, file)

  private def setPiece(board: ChessBoard, piece : ChessPiece, rankIn : Int, fileIn : Char) : ChessBoard = {
    val(row, col) = boardStateIndexes(rankIn, fileIn)
    new ChessBoard(overwriteCell(board.state, piece, row, col))
  }

  private def overwriteCell(boardState: List[List[ChessPiece]], piece : ChessPiece, row : Int, col : Int) : List[List[ChessPiece]] = {
    val numberOfRowsBeforeEditedRow = row
    val indexOfRowAfterEditedRow = row + 1
      boardState.take(numberOfRowsBeforeEditedRow) ++
      List(boardState(row).patch[ChessPiece, List[ChessPiece]](col, Seq(piece), replaced = 1)) ++
      boardState.drop(indexOfRowAfterEditedRow)
  }
}

object ChessBoardUtilityFunctions {
  def addSpacing(boardCell: String) = if (boardCell.size != StandardPieceSize) {
    " " + boardCell + " "
  } else boardCell

  def boardStateIndexes(rank : Int, file : Char) : (Int, Int) = (rank - 1, convertFileToIndex(file))

  def formatRow(row: List[ChessPiece]) = "|" + row.map(piece => addSpacing(piece.displayName)).mkString("|") + "|\n"
  def formatFiles(row: List[String]) = "|" + row.map(file => addSpacing(file)).mkString("|") + "|\n"


  def convertFileToIndex(file: Char): Int = file.toInt - 65
  private val StandardPieceSize = 3
}

object InitialChessBoardState {
  val rowSize = 8
  val get: List[List[ChessPiece]] = List(
    List(Rook, Knight, Bishop, Queen, King, Bishop, Knight, Rook),
    List.fill(rowSize)(PawnCanMoveTwice),
    List.fill(rowSize)(Space),
    List.fill(rowSize)(Space),
    List.fill(rowSize)(Space),
    List.fill(rowSize)(Space),
    List.fill(rowSize)(PawnCanMoveTwice),
    List(Rook, Knight, Bishop, King, Queen, Bishop, Knight, Rook)
  )
}


