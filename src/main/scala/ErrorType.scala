package ChessGame

sealed trait ErrorType
object NotYourTurn extends ErrorType
object GameOver extends ErrorType
object InvalidMove extends ErrorType
object InvalidInput extends ErrorType