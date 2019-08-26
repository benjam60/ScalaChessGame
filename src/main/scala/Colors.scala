package ChessGame

sealed trait Color {
	val direction : Int
	override def toString: String
}
object Black extends Color {
	private val UpTheBoard = 1
	override val direction: Int = UpTheBoard
	override def toString: String = "Black"
}
object White extends Color {
	private val DownTheBoard = -1
	override val direction: Int = DownTheBoard
	override def toString: String = "White"
}
