package ChessGame
//TODO: also add a legal move so you know it is valid
case class UnvalidatedMove(sourcePosition : BoardPosition, destinationPosition : BoardPosition)
case class LegalMove(sourcePosition : BoardPosition, destinationPosition : BoardPosition)

