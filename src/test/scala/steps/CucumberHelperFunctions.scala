package steps

import ChessGame.AllPieces._
import ChessGame.{Black, Board, Color, White}
import cucumber.api.DataTable

import scala.collection.JavaConverters._


object CucumberHelperFunctions {

  def convert(boardState: DataTable): Board = {
    val boardAsSingleList = boardState.asList(classOf[String]).asScala.toList.toIndexedSeq
    val boardWithoutFileLetters = boardAsSingleList.drop(rowSize)

    def removeRankNumbersAndChangeType(state: IndexedSeq[String]): IndexedSeq[IndexedSeq[Option[Color#ChessPiece]]] = {
      def twoDimensionalize(rest : IndexedSeq[String]): IndexedSeq[IndexedSeq[String]] = {
        val nextRow: IndexedSeq[String] = rest.slice(1, rowSize)
        val newRest: IndexedSeq[String] = rest.drop(rowSize)
        if (newRest.isEmpty) IndexedSeq(nextRow) else IndexedSeq(nextRow) ++ twoDimensionalize(newRest)
      }
      val rows = twoDimensionalize(state)
      rows.map(row => row.map(convert))
    }

    val state = removeRankNumbersAndChangeType(boardWithoutFileLetters)
    Board(state)
  }

  //ToDo: implement from string to get rid of these case statements
  def convert(piece: String): Option[Color#ChessPiece] = {
    piece match { //how to differentiate between move once or twice for a pawn
      case "PAW" => Option(White.Pawn(canMoveTwoSpaces = true))
      case "paw" => Option(Black.Pawn(canMoveTwoSpaces = true))
      case "KNI" => Option(White.Knight)
      case "kni" => Option(Black.Knight)
      case "BIS" => Option(White.Bishop)
      case "bis" => Option(Black.Bishop)
      case "QUE" => Option(White.Queen)
      case "que" => Option(Black.Queen)
      case "KIN" => Option(White.King)
      case "kin" => Option(Black.King)
      case "ROO" => Option(White.Rook)
      case "roo" => Option(Black.Rook)
      case _ => Option.empty[Color#ChessPiece]
    }
  }

  def convertMovesToList(dataTable: DataTable): List[String] = dataTable.asList(classOf[String]).asScala.toList
  private val rowSize = 9
}