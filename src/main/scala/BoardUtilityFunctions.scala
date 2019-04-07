package ChessGame
import ChessGame.AllPieces.ChessPiece

object BoardUtilityFunctions {

  def formatRow(row: IndexedSeq[Option[ChessPiece]]): String =
    row.map(maybePiece => maybePiece.map(createChessSquare).getOrElse(createEmptySpace)).mkString + "|\n"
  def next(color: Color) : Color = if (color == White) Black else White
  def createChessSquare(chessPiece: ChessPiece) : String = s"|${chessPiece.displayName}"
  private def createEmptySpace : String = "|" + " "*3
  def createChessSquare(rank: Int) : String = s"| ${rank.toString} "
  def createChessSquare(file: Char) : String = s"| $file "
  def formatFiles(row: List[Char]): String = row.map(createChessSquare).mkString + "|\n"
}