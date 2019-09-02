package ChessGame

sealed trait Color {
	val direction : Int
	override def toString: String
	val color : String

	sealed trait ChessPiece {
		def getDisplayName: String =
			if (color == "White") colorAgnosticDisplayName.toUpperCase else colorAgnosticDisplayName.toLowerCase
		protected val colorAgnosticDisplayName : String
		val colorInstance: Color = Color.this
	}
	case class Pawn(canMoveTwoSpaces : Boolean) extends ChessPiece {
		val pieceDirection : Int = direction
		protected override val colorAgnosticDisplayName: String = "Paw"
	}

	object Knight extends ChessPiece {
		protected override val colorAgnosticDisplayName: String = "Kni"
	}

	object Rook extends ChessPiece {
		protected override val colorAgnosticDisplayName: String = "Roo"
	}

	object Queen extends ChessPiece {
		protected override val colorAgnosticDisplayName: String = "Que"
	}

	object King extends ChessPiece {
		protected override val colorAgnosticDisplayName: String = "Kin"
	}

	object Bishop extends ChessPiece {
		protected override val colorAgnosticDisplayName: String = "Bis"

	}
}
object Black extends Color {
	override val color: String = "Black"
	private val UpTheBoard = 1
	override val direction: Int = UpTheBoard
	override def toString: String = "Black"
}
object White extends Color {
	override val color: String = "White"
	private val DownTheBoard = -1
	override val direction: Int = DownTheBoard
	override def toString: String = "White"
}
