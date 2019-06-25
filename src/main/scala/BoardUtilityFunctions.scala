package ChessGame
import ChessGame.AllPieces.ChessPiece

object BoardUtilityFunctions {

  def formatRow(row: IndexedSeq[Option[ChessPiece]]): String =
    row.map(maybePiece => maybePiece.map(createChessSquare).getOrElse(createEmptySpace)).mkString + s"$cellDivider\n"
  def next(color: Color) : Color = if (color == White) Black else White //TODO: WHERE TO MOVE?
  def createChessSquare(chessPiece: ChessPiece) : String = s"$cellDivider${chessPiece.getDisplayName}"
  private def createEmptySpace : String = cellDivider + " "*cellSize
  def createChessSquare(rank: Int) : String = s"$cellDivider ${rank.toString} "
  def createChessSquare(file: Char) : String = s"$cellDivider $file "
  def formatFiles(row: List[Char]): String = row.map(createChessSquare).mkString + s"$cellDivider\n"
  private val cellSize = 3
  private val cellDivider = "|"
}