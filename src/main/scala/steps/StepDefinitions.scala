package steps
import ChessGame.BoardPieceMovement.movePiece
import ChessGame.Color.{Black, White}
import ChessGame.{Board, ChessBoardPrinter, InitialInternalChessBoardState}
import cucumber.api.DataTable
import cucumber.api.scala.{EN, ScalaDsl}
import steps.CucumberHelperFunctions.{convert, convertMovesToList}

class StepDefinitions extends ScalaDsl with EN {

  private var runner = new Runner //Todo: Before and After create new Runner; import before and after

  Given("""^a new chess game$"""){ () =>
    runner = new Runner //move to before hook until we figure this out
  }

  Given("""^In a new game, it is the turn of (White|Black)""") { color : String =>
    runner = new Runner
    if (color == "White") runner.chessBoard = new Board(InitialInternalChessBoardState.get, White)
    else runner.chessBoard = new Board(InitialInternalChessBoardState.get, Black)
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
    val board : Board = convert(dataTable, arbitraryColor)
    val actualBoard = ChessBoardPrinter.print(runner.chessBoard)
    val expectedBoard = ChessBoardPrinter.print(board)
    println("My class' chessboard is\n" + actualBoard)
    println("and the expected board is \n" + expectedBoard)
    assert(expectedBoard == actualBoard)
  }

  Then("""^it is the turn of (White|Black)$""") { color : String =>
    if (color == "White") assert(runner.chessBoard.turn == White)
    else assert(runner.chessBoard.turn == Black)
  }

}
