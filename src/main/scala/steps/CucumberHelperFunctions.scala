package steps

import ChessGame.ChessBoard
import cucumber.api.DataTable
import scala.collection.JavaConverters._


object CucumberHelperFunctions {

  def convert(datatable: DataTable): ChessBoard = {
    val boardAsSingleList: List[String] = datatable.asList(classOf[String]).asScala.toList
    val boardWithoutFileLetters = boardAsSingleList.drop(rowSize)
    def removeRankNumbersAndChangeType(list: List[String]): List[List[String]] = {
      list match {
        case Nil => Nil
        case _ => list.slice(1, rowSize) :: removeRankNumbersAndChangeType(list.drop(rowSize))
      }
    }
    val chessBoardState = removeRankNumbersAndChangeType(boardWithoutFileLetters)
    new ChessBoard(chessBoardState)
  }

  def convertMoves(dataTable: DataTable): List[Map[String, Int]] = {
    dataTable.asMaps(classOf[String], classOf[Int]).asScala.map(_.asScala.toMap).toList
  }

  private val rowSize = 9

}
