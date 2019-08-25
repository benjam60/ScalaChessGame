package steps

import ChessGame.AllPieces._
import ChessGame.{Black, Board, White}
import cucumber.api.DataTable

import scala.collection.JavaConverters._


object CucumberHelperFunctions {

  def convert(boardState: DataTable): Board = {
    val boardAsSingleList = boardState.asList(classOf[String]).asScala.toList.toIndexedSeq
    val boardWithoutFileLetters = boardAsSingleList.drop(rowSize)

    def removeRankNumbersAndChangeType(state: IndexedSeq[String]): IndexedSeq[IndexedSeq[Option[ChessPiece]]] = {
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
  def convert(piece: String): Option[ChessPiece] = {
    piece match { //how to differentiate between move once or twice for a pawn
      case "PAW" => Option(Pawn(canMoveTwoSpaces = true, White))
      case "paw" => Option(Pawn(canMoveTwoSpaces = true, Black))
      case "KNI" => Option(Knight(White))
      case "kni" => Option(Knight(Black))
      case "BIS" => Option(Bishop(White))
      case "bis" => Option(Bishop(Black))
      case "QUE" => Option(Queen(White))
      case "que" => Option(Queen(Black))
      case "KIN" => Option(King(White))
      case "kin" => Option(King(Black))
      case "ROO" => Option(Rook(White))
      case "roo" => Option(Rook(Black))
      case _ => Option.empty[ChessPiece]
    }
  }

  def convertMovesToList(dataTable: DataTable): List[String] = dataTable.asList(classOf[String]).asScala.toList
  private val rowSize = 9
}