package ScalaChessGameTests

import ChessGame.InputValidation
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
}
