package steps
import ChessGame.ChessBoard
import cucumber.api.DataTable;
import scala.collection.JavaConverters._

object CucumberHelperFunctions {


  def convert(datatable :DataTable) : ChessBoard = {
    val pieces : List[String] = datatable.asList(classOf[String]).asScala.toList
    def formatForBoard(list : List[String]) : List[List[String]] = list match {
      case Nil => Nil
      case _ => {
        val next_eight = list.take(8)
        val rest = list.drop(8)
        next_eight::formatForBoard(rest)
      }
    }
    new ChessBoard(formatForBoard(pieces))
  }


}
