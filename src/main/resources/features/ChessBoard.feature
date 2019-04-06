Feature: Ensures chessboard initializes correctly

  Scenario: When a game begins, there will be a chessboard with the correct pieces
    Given a new chess game
    Then the board should look like
      |   | A   | B   | C   | D   | E   | F   | G   | H   |
      | 1 | Roo | Kni | Bis | Que | Kin | Bis | Kni | Roo |
      | 2 | Paw | Paw | Paw | Paw | Paw | Paw | Paw | Paw |
      | 3 |     |     |     |     |     |     |     |     |
      | 4 |     |     |     |     |     |     |     |     |
      | 5 |     |     |     |     |     |     |     |     |
      | 6 |     |     |     |     |     |     |     |     |
      | 7 | paw | paw | paw | paw | paw | paw | paw | paw |
      | 8 | Roo | Kni | Bis | Kin | Que | Bis | Kni | Roo |

  Scenario: Custom starting board (for testing)
    Given It is black's turn and the board looks like
      |   | A | B   | C   | D | E | F | G | H |
      | 1 |   |     |     |   |   |   |   |   |
      | 2 |   |     |     |   |   |   |   |   |
      | 3 |   |     |     |   |   |   |   |   |
      | 4 |   | Paw |     |   |   |   |   |   |
      | 5 |   |     | Paw |   |   |   |   |   |
      | 6 |   |     |     |   |   |   |   |   |
      | 7 |   |     |     |   |   |   |   |   |
      | 8 |   |     |     |   |   |   |   |   |
    Then the board should look like
      |   | A | B   | C   | D | E | F | G | H |
      | 1 |   |     |     |   |   |   |   |   |
      | 2 |   |     |     |   |   |   |   |   |
      | 3 |   |     |     |   |   |   |   |   |
      | 4 |   | Paw |     |   |   |   |   |   |
      | 5 |   |     | Paw |   |   |   |   |   |
      | 6 |   |     |     |   |   |   |   |   |
      | 7 |   |     |     |   |   |   |   |   |
      | 8 |   |     |     |   |   |   |   |   |

