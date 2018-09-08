package steps

import ChessGame.AllPieces._
import ChessGame.ChessBoard
import ChessGame.ChessBoardUtilityFunctions.addSpacing
import cucumber.api.DataTable
import Ben.cucumberConvert
import scala.collection.JavaConverters._

object CucumberHelperFunctions {

  def convert(datatable: DataTable): ChessBoard = {
    val boardAsSingleList: List[String] = datatable.asList(classOf[String]).asScala.toList
    val boardWithoutFileLetters = boardAsSingleList.drop(rowSize)

    def removeRankNumbersAndChangeType(list: List[String]): List[List[ChessPiece]] = {
      list match {
        case Nil => Nil
        case _ => {
          val x = list.slice(1, rowSize)
          x.map( piece => cucumberConvert(piece)):: removeRankNumbersAndChangeType(list.drop(rowSize))
        }
      }
    }

    val chessBoardState = removeRankNumbersAndChangeType(boardWithoutFileLetters)
    new ChessBoard(chessBoardState)
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