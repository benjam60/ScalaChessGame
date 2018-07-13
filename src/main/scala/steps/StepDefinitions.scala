package steps
import ChessGame.boardPositionShortHands.{destinationFile, destinationRank, sourceFile, sourceRank}
import cucumber.api.DataTable
import cucumber.api.scala.{EN, ScalaDsl}
import steps.CucumberHelperFunctions.{convert, convertMoves}
import ChessGame.{ChessBoard, InitialChessBoardState}

class StepDefinitions extends ScalaDsl with EN {
  //private val log = LoggerFactory.getLogger(classOf[StepDefinitions])
  private val runner = new Runner

  Given("""^a new chess game$"""){ () =>
    runner.chessBoard = new ChessBoard(InitialChessBoardState.get)
  }

  Then("^the board should look like$"){ dataTable : DataTable =>
    val board : ChessBoard = convert(dataTable)
    assert(board.toString == runner.chessBoard.toString)
  }

  When("""^the following moves are made$"""){ (moves: DataTable) =>
    val playerMoves : List[Map[String, Int]] = convertMoves(moves)
    for {
      move <- playerMoves
      srcRank = move(sourceRank)
      srcFile = move(sourceFile)
      destRank = move(destinationRank)
      destFile = move(destinationFile)
    } runner.chessBoard = runner.chessBoard.movePiece(srcRank, srcFile, destRank, destFile) //TODO: Make monadic and put in for comprehension
  }

}
