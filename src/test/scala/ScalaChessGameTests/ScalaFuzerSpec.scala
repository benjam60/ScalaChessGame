package ScalaChessGameTests

import ChessGame.Game

object ScalaFuzerSpec extends App {
	private val exlusiveTop = 9

	def getRandomNumber: Int = scala.util.Random.nextInt(exlusiveTop)

	def getRandomLetter: Char = (scala.util.Random.nextInt(exlusiveTop) + 65).toChar

	def readInput : String = {
		val n1 = getRandomNumber.toString
		val c1 = getRandomLetter.toString
		val n2 = getRandomNumber.toString
		val c2 = getRandomLetter.toString
		s"${n1}${c1}->${n2}${c2}"
	}

	new Game(readInput, (output: String) => println(output))
}
