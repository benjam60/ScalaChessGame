package steps

import ChessGame.SpecificPieceMovement._
import ChessGame._
import cucumber.api.DataTable
import cucumber.api.scala.{EN, ScalaDsl}
import steps.CucumberHelperFunctions.{convert, convertMovesToList}

class StepDefinitions extends ScalaDsl with EN {

	//ToDo: delete the runner because it is not doing anything
	private val runner = new Runner //Todo: Before and After create new Runner; import before and after
	private def actualBoard: Board = runner.gamePlay.currentBoard

	private def actualTurn: Color = runner.gamePlay.currentTurn

	private val newPlayer = Player(false, false)

	Given("""^a new chess game$""") { () =>
		runner.gamePlay = GamePlay(Board(), White, Map(White -> newPlayer, Black -> newPlayer))
	}

	Given("""^It is (Black|White)'s turn and the board looks like$""") { (color: String, dataTable: DataTable) =>
		val chosenColor = if (color == "Black") Black else White
		val board = convert(dataTable)
		runner.gamePlay = GamePlay(board, chosenColor, Map(White -> newPlayer, Black -> newPlayer))
	}

	Given("""^It is (Black|White)'s turn and they are in check$""") { (color: String, dataTable: DataTable) =>
		val board = convert(dataTable)
		runner.gamePlay = GamePlay(board, fromString(color),
			Map(fromString(color) -> Player.createPlayerInCheck, BoardUtilityFunctions.getOther(fromString(color)) -> Player.createPlayerNotInCheck))
	}

	//it is an invalid move
	Given("""^In a new game, it is the turn of (White|Black)""") { color: String =>
		runner.gamePlay = GamePlay(Board(), fromString(color), Map(White -> newPlayer, Black -> newPlayer))
	}

	When("""^the following moves are made$""") { moves: DataTable =>
		convertMovesToList(moves).foreach { move: String =>
			runner.gamePlay.takeTurn(move).foreach(newGamePlay =>
				runner.gamePlay = newGamePlay)
		}
	}

	Then("^the board should look like$") { dataTable: DataTable =>
		val board: Board = convert(dataTable)
		val printedActualBoard = BoardPrinter.toString(actualBoard)
		val printedExpectedBoard = BoardPrinter.toString(board)
		println("My class' chessboard is\n" + printedActualBoard)
		println("and the expected board is \n" + printedExpectedBoard)
		assert(printedExpectedBoard == printedActualBoard)
	}

	Then("""^it is the turn of (White|Black)$""") { color: String =>
		if (color == "White") assert(actualTurn == White) else assert(actualTurn == Black)
	}

	Then("""^position (.{2}) contains a (White|Black) pawn that can move once""") { (pos: String, color: String) =>
		val rankIndex = pos(0).asDigit - 1
		val fileIndex = pos(1) - 65
		val expectedPiece = if (color == "White") White.Pawn(canMoveTwoSpaces = false) else Black.Pawn(canMoveTwoSpaces = false)
		assert(actualBoard.state(rankIndex)(fileIndex) == Option(expectedPiece))
	}

	Then("""(Black|White) is in check""") { color: String =>
		assert(runner.gamePlay.players(fromString(color)).isInCheck)
	}

	Then("""(Black|White) wins""") { color: String =>
		assert(runner.gamePlay.players(fromString(color)).isInCheckMate)
	}

	Then("""Black is out of check""") { () =>
		assert(!runner.gamePlay.players(Black).isInCheck)
	}

	private def fromString(input: String): Color =
		if (input == "White") White else if (input == "Black") Black else throw new RuntimeException("Color undefined.")
}
