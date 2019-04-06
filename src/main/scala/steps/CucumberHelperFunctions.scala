package steps

import ChessGame.AllPieces._
import ChessGame.Board
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
      val rows: IndexedSeq[IndexedSeq[String]] = twoDimensionalize(state)
      val x = rows.map(row => row.map(convert))
      x
    }

    val state = removeRankNumbersAndChangeType(boardWithoutFileLetters)
    Board(state)
  }

  //ToDo: implement from string to get rid of these case statements
  def convert(piece: String): Option[ChessPiece] = {
    piece match { //how to differentiate between move once or twice for a pawn
      case "Paw" => Option(WhitePawnCanMoveTwice)
      case "paw" => Option(BlackPawnCanMoveTwice)
      case "Kni" => Option(Knight)
      case "Bis" => Option(Bishop)
      case "Que" => Option(Queen)
      case "Kin" => Option(King)
      case "Roo" => Option(Rook)
      case _ => Option.empty[ChessPiece]
    }
  }

  def convertMovesToList(dataTable: DataTable): List[String] = dataTable.asList(classOf[String]).asScala.toList
  private val rowSize = 9
}