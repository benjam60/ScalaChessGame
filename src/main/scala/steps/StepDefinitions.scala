package steps
import ChessGame._
import cucumber.api.DataTable
import cucumber.api.scala.{EN, ScalaDsl}
import steps.CucumberHelperFunctions.{convert, convertMovesToList}

class StepDefinitions extends ScalaDsl with EN {

  private var runner = new Runner //Todo: Before and After create new Runner; import before and after
  private var actualBoard : Board = _
  private var actualTurn : Color = _

  object TestInputWriter extends InputSource {
    def addMove(userInputtedMove : String): Unit = storedMoves = storedMoves :+ userInputtedMove
    private var storedMoves : List[String] = List.empty[String]
    override def readLine: String = storedMoves.headOption.map { move =>
          storedMoves = storedMoves.drop(1)
          move
        }.getOrElse("quit")
    }

  private val gamePlay = new GamePlay(TestInputWriter)(_, _)

  Given("""^a new chess game$"""){ () =>
    runner = new Runner //move to before hook until we figure this out
    runner.gamePlay = gamePlay(Board(InitialBoard.state), White)
    actualBoard = Board(InitialBoard.state)
    actualTurn = White
  }

  Given("""^the board looks like$""") { dataTable: DataTable =>
    val arbitraryColor = Black
    val board = convert(dataTable)
    runner.gamePlay = gamePlay(board, arbitraryColor)
    actualBoard = board
    actualTurn = arbitraryColor
  }

  Given("""^In a new game, it is the turn of (White|Black)""") { color : String =>
    if (color == "White") runner.gamePlay = gamePlay(Board(InitialBoard.state), White)
    else runner.gamePlay = gamePlay(Board(InitialBoard.state), Black)
  }

  When("""^the following moves are made$"""){ moves: DataTable =>
    convertMovesToList(moves).foreach(TestInputWriter.addMove)
    val ret = runner.gamePlay.start()
    actualBoard = ret._1
    actualTurn = ret._2
  }

  Then("^the board should look like$"){ dataTable : DataTable =>
    val board : Board = convert(dataTable)
    val actBoard = ChessBoardPrinter.print(actualBoard)
    val expectedBoard = ChessBoardPrinter.print(board)
    println("My class' chessboard is\n" + actBoard)
    println("and the expected board is \n" + expectedBoard)
    assert(expectedBoard == actBoard)
  }

  Then("""^it is the turn of (White|Black)$""") { color : String =>
    if (color == "White") assert(actualTurn == White) else assert(actualTurn == Black)
  }

}
