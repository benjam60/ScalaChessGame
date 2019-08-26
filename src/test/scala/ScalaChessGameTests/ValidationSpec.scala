package ScalaChessGameTests

import ChessGame.{BoardPosition, InputValidation, UnvalidatedMove}
import org.scalatest.FreeSpec

class ValidationSpec extends FreeSpec {
	"Input Validation should handle empty case" in {
		assert(InputValidation.readPieces("").isEmpty)
	}

	"Input Validation should handle invalid inputs" - {
		Seq("A9->8B", ",,,,,,").foreach(in =>
			s"$in" in {
			assert(InputValidation.readPieces(in).isEmpty)
			}
		)
	}

	"Valid input" - {
		Seq("1A->2B").foreach(in =>
			s"$in" in {
				assert(InputValidation.readPieces(in) == Option(UnvalidatedMove(BoardPosition(0, 0), BoardPosition(1,1))))
			}
		)
	}
}
