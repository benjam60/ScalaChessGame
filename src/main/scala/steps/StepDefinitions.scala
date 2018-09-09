package steps
import ChessGame.ChessBoardPieceMovement.movePiece
import cucumber.api.DataTable
import cucumber.api.scala.{EN, ScalaDsl}
import steps.CucumberHelperFunctions.{convert, convertMovesToList}
import ChessGame.{ChessBoard, InitialChessBoardState}
import ChessGame.Color.{Black, White}

class StepDefinitions extends ScalaDsl with EN {
  //private val log = LoggerFactory.getLogger(classOf[StepDefinitions])

  private var runner = new Runner

  Given("""^a new chess game$"""){ () =>
    runner = new Runner //move to before hook until we figure this out
  }

  Given("""^In a new game, it is the turn of (White|Black)""") { color : String =>
    runner = new Runner
    if (color == "White") runner.chessBoard = new ChessBoard(InitialChessBoardState.get, White)
    else runner.chessBoard = new ChessBoard(InitialChessBoardState.get, Black)
  }

  When("""^the following moves are made$"""){ (moves: DataTable) =>
    val playerMoves : List[String] = convertMovesToList(moves)
        runner.chessBoard =
          playerMoves.foldLeft(runner.chessBoard)((acc, move) => { movePiece(acc,
                                                                   move(0).asDigit,
                                                                   move(1),
                                                                   move(4).asDigit,
                                                                   move(5)) })
  }

  Then("^the board should look like$"){ dataTable : DataTable =>
    val arbitraryColor = Black
    val board : ChessBoard = convert(dataTable, arbitraryColor)
    println("My class' chessboard is\n" + runner.chessBoard.toString)
    println("and the expected board is \n" + board.toString)
    assert(board.toString == runner.chessBoard.toString)
  }

  Then("""^it is the turn of (White|Black)$""") { color : String =>
    if (color == "White") assert(runner.chessBoard.turn == White)
    else assert(runner.chessBoard.turn == Black)
  }

}
