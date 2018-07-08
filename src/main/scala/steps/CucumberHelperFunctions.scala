package steps

import ChessGame.ChessBoard
import ChessGame.ChessPieceDisplayNames.Space
import cucumber.api.DataTable

import scala.collection.JavaConverters._

object CucumberHelperFunctions {


  def convert(datatable: DataTable): ChessBoard = {
    val pieces: List[String] = datatable.asList(classOf[String]).asScala.toList
    val rowSize = 8

    def formatForBoardConstructor(list: List[String]): List[List[String]] = list match {
      case Nil => Nil
      case _ => list.take(rowSize) :: formatForBoardConstructor(list.drop(rowSize))
    }

    val piecesWithCorrectSpacing: List[String] = pieces.map { piece =>
      if (piece == "" || piece == " ") Space else piece
    }
    new ChessBoard(formatForBoardConstructor(piecesWithCorrectSpacing))
  }


}
