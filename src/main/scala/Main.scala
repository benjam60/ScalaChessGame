package ChessGame

import org.scalactic.{Bad, Or}

class Game(getInput : => String, writeToConsole : String => Unit) {
	private val startingColor = White
	private val isInCheck = false
	private val isInCheckMate = false
	private val whitePlayer = Player(isInCheck, isInCheckMate)
	private val blackPlayer = Player(isInCheck, isInCheckMate)
	playGame(GamePlay(Board(InitialBoard.state), startingColor, Map(White -> whitePlayer, Black -> blackPlayer)))

	private def playGame(gamePlay: GamePlay) : Unit = {
		printBoard(gamePlay)
		printPrompt(gamePlay)
		val resultOfTurn = gamePlay.takeTurn(getInput)
		if (resultOfTurn.isGood) playGame(resultOfTurn.get)
		else {
			resultOfTurn match {
				case Bad(GameOver) => writeToConsole("Game Over.")
				case _ => writeToConsole("Invalid Move.")
			}
			playGame(gamePlay)
		}
	}

	private def printBoard(gamePlay: GamePlay) : Unit = writeToConsole(BoardPrinter.toString(gamePlay.currentBoard))
	private def printPrompt(gamePlay: GamePlay) : Unit = writeToConsole(s"${gamePlay.currentTurn.toString}, type in your move <Rank><File>-><Rank><File> e.g. 2C->3C")

}

object Main extends App {
	private def getPlayerInput : String = scala.io.StdIn.readLine()
	private def writeOutput(output : String) : Unit = println(output)
	new Game(getPlayerInput, writeOutput)
}

