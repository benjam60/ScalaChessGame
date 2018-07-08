package steps
import cucumber.api.DataTable
import cucumber.api.scala.{EN, ScalaDsl}
import org.slf4j.LoggerFactory


class StepDefinitions extends ScalaDsl with EN {
  private val log = LoggerFactory.getLogger(classOf[StepDefinitions])
  private val runner = new Runner

  //ChessBoard.feature
  Given("""^a new chess game$"""){ () =>
  }
  Then("^the board should look like$"){ dataTable : DataTable =>
    val board = CucumberHelperFunctions.convert(dataTable)
    assert(board.toString == runner.chessBoard.toString)
  }

  //PieceMovement.feature
  When("""^a piece is moved and the board looks like$"""){ (arg0:DataTable) =>
    runner.chessBoard = runner.chessBoard.movePiece(1,1,2,1)
   // println(runner.chessBoard.movePiece(1, 1, 2, 1).toString)
  }

}
