package steps
import ChessGame.boardPositionShortHands.{destinationFile, destinationRank, sourceFile, sourceRank}
import cucumber.api.DataTable
import cucumber.api.scala.{EN, ScalaDsl}
import steps.CucumberHelperFunctions.{convert, convertMovesToList}
import ChessGame.{ChessBoard, InitialChessBoardState}

class StepDefinitions extends ScalaDsl with EN {
  //private val log = LoggerFactory.getLogger(classOf[StepDefinitions])

  private var runner = new Runner

  Given("""^a new chess game$"""){ () =>
    runner = new Runner //move to before hook until we figure this out
  }

  When("""^the following moves are made$"""){ (moves: DataTable) =>
    val playerMoves : List[String] = convertMovesToList(moves)
      runner.chessBoard =
        playerMoves.foldLeft(runner.chessBoard)((acc, move) => { acc.movePiece(move(0).asDigit,
                                                                               move(1),
                                                                               move(4).asDigit,
                                                                               move(5)) })
  }

  Then("^the board should look like$"){ dataTable : DataTable =>
    val board : ChessBoard = convert(dataTable)
    println("My class' chessboard is\n" + runner.chessBoard.toString)
    println("and the expected board is \n" + board.toString)
    assert(board.toString == runner.chessBoard.toString)
  }

}
