package ChessGame

import ChessGame.AllPieces.{ChessPiece, PawnCanMoveOnce, PawnCanMoveTwice, Space}
import ChessGame.ChessBoardUtilityFunctions.boardStateIndexes
import ChessGame.Color.{Black, White}

object ChessBoardPieceMovement {
  def movePiece(board: ChessBoard, sourceRank: Int, sourceFile: Char, destRank: Int, destFile: Char): ChessBoard = {
    val pieceToMove = getPiece(board, sourceRank, sourceFile)
    if (pieceToMove.isValidMove(sourceRank, sourceFile, destRank, destFile)) {
      val boardWithDeletedPiece: ChessBoard = deletePiece(board, sourceRank, sourceFile)
      if (pieceToMove.getClass == PawnCanMoveTwice.getClass) {
        setPiece(boardWithDeletedPiece, PawnCanMoveOnce, destRank, destFile)
      }
      else setPiece(boardWithDeletedPiece, pieceToMove, destRank, destFile)
    }
    else board
  }

  private def getPiece(board: ChessBoard, rankIn: Int, fileIn: Char): ChessPiece = {
    val (row, col) = boardStateIndexes(rankIn, fileIn)
    board.state(row)(col)
  }

  private def deletePiece(board: ChessBoard, rank: Int, file: Char): ChessBoard =
    setPiece(board, Space, rank, file)

  private def setPiece(board: ChessBoard, piece: ChessPiece, rankIn: Int, fileIn: Char): ChessBoard = {
    val (row, col) = boardStateIndexes(rankIn, fileIn)
    if (piece.getClass == Space.getClass) {
      new ChessBoard(overwriteCell(board.state, piece, row, col), board.turn)
    }
    else {
      new ChessBoard(overwriteCell(board.state, piece, row, col), switchTurns(board.turn))
    }
  }

  private def overwriteCell(boardState: List[List[ChessPiece]], piece: ChessPiece, row: Int, col: Int): List[List[ChessPiece]] = {
    val numberOfRowsBeforeEditedRow = row
    val indexOfRowAfterEditedRow = row + 1
    boardState.take(numberOfRowsBeforeEditedRow) ++
      List(boardState(row).patch[ChessPiece, List[ChessPiece]](col, Seq(piece), replaced = 1)) ++
      boardState.drop(indexOfRowAfterEditedRow)
  }

  private def switchTurns(color: Color.Value): Color.Value = {
    if (color == Black) White
    else Black
  }
}