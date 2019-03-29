package steps

import ChessGame.AllPieces._
import ChessGame.ChessBoard
import cucumber.api.DataTable
import steps.Ben.cucumberConvert

import scala.collection.JavaConverters._


object CucumberHelperFunctions {

  def convert(datatable: DataTable, color : ChessGame.Color.Value): ChessBoard = {
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

    val chessBoardState = removeRankNumbersAndChangeType(boardWithoutFileLetters)
    new ChessBoard(chessBoardState, color)
  }

//  private def emptyStringToEmptyCell(str: String) : ChessPiece = if (str == "") Space

  def convertMoves(dataTable: DataTable): List[Map[String, Any]] = {
    dataTable.asMaps(classOf[String], classOf[Any]).asScala.map(_.asScala.toMap).toList
  }

  def convertMovesToList(dataTable: DataTable): List[String] = {
    dataTable.asList(classOf[String]).asScala.map(x => x).toList
  }

  private val rowSize = 9

}


object Ben { //this function is for cucumber
  def cucumberConvert(piece: String): ChessPiece = {
    piece match {
      case "Paw" => PawnCanMoveTwice //problem here
      case "Kni" => Knight
      case "Bis" => Bishop
      case "Que" => Queen
      case "Kin" => King
      case "Roo" => Rook
      case _ => Space
    }
  }
}