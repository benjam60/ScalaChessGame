package steps
import cucumber.api.DataTable
import cucumber.api.scala.{EN, ScalaDsl}
import org.slf4j.LoggerFactory


class StepDefinitions extends ScalaDsl with EN {
  private val log = LoggerFactory.getLogger(classOf[StepDefinitions])
  private val runner = new Runner


  Given("""^a new chess game$"""){ () =>
  }
  Then("^the board should look like$"){ (datatable : DataTable) =>
    val board = CucumberHelperFunctions.convert(datatable)
    assert(board.toString == runner.chessBoard.toString)
  }

}
