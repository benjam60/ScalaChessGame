package steps

import ChessGame.AllPieces._
import ChessGame.Board
import cucumber.api.DataTable
import steps.Ben.cucumberConvert

import scala.collection.JavaConverters._


object CucumberHelperFunctions {

  def convert(datatable: DataTable, color : ChessGame.Color.Value): Board = {
    val boardAsSingleList = datatable.asList(classOf[String]).asScala.toList.toIndexedSeq
    val boardWithoutFileLetters = boardAsSingleList.drop(rowSize)

    def removeRankNumbersAndChangeType(state: IndexedSeq[String]): IndexedSeq[IndexedSeq[ChessPiece]] = {
      def getNextRow(rest : IndexedSeq[String]): IndexedSeq[IndexedSeq[String]] = {
        val nextRow: IndexedSeq[String] = rest.slice(1, rowSize)
        val newRest: IndexedSeq[String] = rest.drop(rowSize)
        if (newRest.isEmpty) IndexedSeq(nextRow) else IndexedSeq(nextRow) ++ getNextRow(newRest)
      }
      val rows: IndexedSeq[IndexedSeq[String]] = getNextRow(state)
      rows.map(row => row.map(cucumberConvert))
    }

    val state = removeRankNumbersAndChangeType(boardWithoutFileLetters)
    new Board(state, color)
  }

  def convertMovesToList(dataTable: DataTable): List[String] = {
    dataTable.asList(classOf[String]).asScala.map(x => x).toList
  }

  private val rowSize = 9

}


object Ben {
  def cucumberConvert(piece: String): ChessPiece = {
    piece match { //how to differentiate between move once or twice for a pawn
      case WhitePawnName => WhitePawnCanMoveTwice
      case BlackPawnName => BlackPawnCanMoveTwice
      case "Kni" => Knight
      case "Bis" => Bishop
      case "Que" => Queen
      case "Kin" => King
      case "Roo" => Rook
      case _ => Space
    }
  }
}