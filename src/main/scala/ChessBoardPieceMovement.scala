package ChessGame

import ChessGame.AllPieces.{ChessPiece, PawnCanMoveOnce, PawnCanMoveTwice, Space}
import ChessGame.ChessBoardUtilityFunctions.boardStateIndexes
import ChessGame.Color.{Black, Color, White}

object ChessBoardPieceMovement {
  def movePiece(board: ChessBoard, sourceRank: Int, sourceFile: Char, destRank: Int, destFile: Char): ChessBoard = {
    val pieceToMove = getPiece(board, sourceRank, sourceFile)
    if (pieceToMove.isValidMove(sourceRank, sourceFile, destRank, destFile)) {
      val boardWithErasedSourcePiece = deletePiece(board, sourceRank, sourceFile)
      val boardWithPlacedPiece = setPiece(boardWithErasedSourcePiece, pieceToSet(pieceToMove), destRank, destFile)
      new ChessBoard(boardWithPlacedPiece.state, switchTurns(boardWithPlacedPiece.turn))
    }
    else board
  }

  private def pieceToSet(pieceToMove: ChessPiece): ChessPiece = {
    if (pieceToMove == PawnCanMoveTwice) PawnCanMoveOnce else pieceToMove
  }

  private def getPiece(board: ChessBoard, rankIn: Int, fileIn: Char): ChessPiece = {
    val (row, col) = boardStateIndexes(rankIn, fileIn)
    board.state(row)(col)
  }

  private def deletePiece(board: ChessBoard, rank: Int, file: Char): ChessBoard = setPiece(board, Space, rank, file)

  private def setPiece(board: ChessBoard, piece: ChessPiece, rankIn: Int, fileIn: Char): ChessBoard = {
    val (row, col) = boardStateIndexes(rankIn, fileIn)
    val boardWithDeletedSource = overwriteCell(board.state, piece, row, col)
    if (piece == Space) new ChessBoard(boardWithDeletedSource, board.turn)
    else new ChessBoard(boardWithDeletedSource, board.turn)
  }

  private def overwriteCell(boardState: List[List[ChessPiece]], piece: ChessPiece, row: Int, col: Int): List[List[ChessPiece]] = {
    val numberOfRowsBeforeEditedRow = row
    val indexOfRowAfterEditedRow = row + 1
    boardState.take(numberOfRowsBeforeEditedRow) ++
      List(boardState(row).patch[ChessPiece, List[ChessPiece]](col, Seq(piece), replaced = 1)) ++
      boardState.drop(indexOfRowAfterEditedRow)
  }

  private def switchTurns(color: Color): Color = if (color == Black) White else Black
}