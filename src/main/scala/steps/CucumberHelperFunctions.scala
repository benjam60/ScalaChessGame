package steps

import ChessGame.ChessBoard
import cucumber.api.DataTable
import scala.collection.JavaConverters._


object CucumberHelperFunctions {

  def convert(datatable: DataTable): ChessBoard = {
    val boardCucumberStructure: List[String] = datatable.asList(classOf[String]).asScala.toList

    def restructureBoardImplementation(list: List[String]): List[List[String]] = {
      val rowSize = 8
      list match {
        case Nil => Nil
        case _ => list.take(rowSize) :: restructureBoardImplementation(list.drop(rowSize))
      }
    }

    new ChessBoard(restructureBoardImplementation(boardCucumberStructure))
  }

  def convertMoves(dataTable: DataTable): List[Map[String, Int]] = {
    dataTable.asMaps(classOf[String], classOf[Int]).asScala.map(_.asScala.toMap).toList
  }

}
