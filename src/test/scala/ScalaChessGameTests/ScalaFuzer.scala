package ScalaChessGameTests

import ChessGame.Game

object ScalaFuzzer extends App {
	private val exclusiveTop = 9

	def getRandomNumber: Int = scala.util.Random.nextInt(exclusiveTop)

	def getRandomLetter: Char = (scala.util.Random.nextInt(exclusiveTop) + asciiValueOfA).toChar
	private val asciiValueOfA = 65
	def readInput : String = {
		val n1 = getRandomNumber.toString
		val c1 = getRandomLetter.toString
		val n2 = getRandomNumber.toString
		val c2 = getRandomLetter.toString
		s"$n1$c1->$n2$c2"
	}

	new Game(readInput, (output: String) => println(output))
}
