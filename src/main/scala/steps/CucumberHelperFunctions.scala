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
    //.map(q => if (q.size == 2) {q + " "})
    val chessBoardState = removeRankNumbersAndChangeType(boardWithoutFileLetters)
    new ChessBoard(chessBoardState)
  }
  private def emptyStringToEmptyCell(str : String) = if (str == "") addSpacing(Space) else str

  def convertMoves(dataTable: DataTable): List[Map[String, Int]] = {
    dataTable.asMaps(classOf[String], classOf[Int]).asScala.map(_.asScala.toMap).toList
  }

  private val rowSize = 9

}
