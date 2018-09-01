package steps

import ChessGame.{ChessBoard, ChessPieceDisplayNames}
import cucumber.api.DataTable
import ChessGame.ChessPieceDisplayNames.Space
import scala.collection.JavaConverters._
import ChessGame.ChessBoardUtilityFunctions.addSpacing

object CucumberHelperFunctions {

  def convert(datatable: DataTable): ChessBoard = {
    val boardAsSingleList: List[String] = datatable.asList(classOf[String]).asScala.toList
    val boardWithoutFileLetters = boardAsSingleList.drop(rowSize)
    def removeRankNumbersAndChangeType(list: List[String]): List[List[String]] = {
      list match {
        case Nil => Nil
        case _ => list.slice(1, rowSize).map(emptyStringToEmptyCell) :: removeRankNumbersAndChangeType(list.drop(rowSize))
      }
    }
    val chessBoardState = removeRankNumbersAndChangeType(boardWithoutFileLetters)
    new ChessBoard(chessBoardState)
  }
  private def emptyStringToEmptyCell(str : String) = if (str == "") addSpacing(Space) else str

  def convertMoves(dataTable: DataTable): List[Map[String, Any]] = {
    dataTable.asMaps(classOf[String], classOf[Any]).asScala.map(_.asScala.toMap).toList
  }

  def convertMovesToList(dataTable: DataTable) : List[String] = {
    dataTable.asList(classOf[String]).asScala.map(x => x).toList
  }

  private val rowSize = 9

}
