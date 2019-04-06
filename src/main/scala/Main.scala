package ChessGame

import org.scalactic.{Bad, Good}

object Main extends App {
	private val colorsTurn = White
  private val initialBoard = Board(InitialBoard.state)
  private val newGame  = GamePlay(initialBoard, colorsTurn)
	playGame(newGame)
  println("Game Over.")

	private def playGame(gamePlay: GamePlay) : Unit = {
		println(BoardPrinter.toString(gamePlay.currentBoard))
		println(s"${gamePlay.currentTurn.toString}, type in your move <Rank><File>-><Rank><File> e.g. 2C->3C")
		val input = scala.io.StdIn.readLine()
		gamePlay.takeTurn(input) match {
			case Good(updatedGamePlay) => playGame(updatedGamePlay)
			case Bad(GameOver) => ()
			case Bad(_) => {
				println("Invalid Move.")
				playGame(gamePlay)
			}
		}

	}
}

