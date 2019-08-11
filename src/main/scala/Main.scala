package ChessGame

object Main extends App {
	private val startingColor = White
	private val isInCheck = false
	private val whitePlayer = Player(isInCheck)
	private val blackPlayer = Player(isInCheck)
	playGame(GamePlay(Board(InitialBoard.state), startingColor, whitePlayer, blackPlayer))

	private def playGame(gamePlay: GamePlay) : Unit = {
		printBoard(gamePlay)
		printPrompt(gamePlay)
		gamePlay.takeTurn(getPlayerInput()).map(playGame).badMap {
			case GameOver => printMsg("Game Over.")
			case _ => printMsg("Invalid Move.")
				playGame(gamePlay)
		}
	}

	private def printBoard(gamePlay: GamePlay) : Unit = println(BoardPrinter.toString(gamePlay.currentBoard))
	private def printPrompt(gamePlay: GamePlay) : Unit = println(s"${gamePlay.currentTurn.toString}, type in your move <Rank><File>-><Rank><File> e.g. 2C->3C")
	private def getPlayerInput() : String = scala.io.StdIn.readLine()
	private def printMsg(msg : String) : Unit = println(msg)
}

