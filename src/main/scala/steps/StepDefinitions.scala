package steps
import ChessGame.boardPositionShortHands
import ChessGame.boardPositionShortHands.{destinationCol, destinationRow, sourceCol, sourceRow}
import cucumber.api.DataTable
import cucumber.api.scala.{EN, ScalaDsl}
import org.slf4j.LoggerFactory
import scala.collection.JavaConverters._
import CucumberHelperFunctions.convertMoves

class StepDefinitions extends ScalaDsl with EN {
  private val log = LoggerFactory.getLogger(classOf[StepDefinitions])
  private val runner = new Runner

  //ChessBoard.feature
  Given("""^a new chess game$"""){ () =>
  }
  Then("^the board should look like$"){ dataTable : DataTable =>
    val board = CucumberHelperFunctions.convert(dataTable)
    println(runner.chessBoard.toString)
    assert(board.toString == runner.chessBoard.toString)
  }

  //PieceMovement.feature
  When("""^the following moves are made$"""){ (moves: DataTable) =>
    val playerMoves : List[Map[String, Int]] = convertMoves(moves)
    for {
      move <- playerMoves
      srcX = move(sourceRow)
      srcY = move(sourceCol)
      destX = move(destinationRow)
      destY = move(destinationCol)
    } runner.chessBoard = runner.chessBoard.movePiece(srcX, srcY, destX, destY)

    println("New Board-----")
    println(runner.chessBoard.toString)
    println("End of new Board ---")
  }

}
