Feature: Ensures chessboard initializes correctly

  Scenario: When a game begins, there will be a chessboard with the correct pieces
    Given a new chess game
    Then the board should look like
      |   | A   | B   | C   | D   | E   | F   | G   | H   |
      | 1 | ROO | KNI | BIS | QUE | KIN | BIS | KNI | ROO |
      | 2 | PAW | PAW | PAW | PAW | PAW | PAW | PAW | PAW |
      | 3 |     |     |     |     |     |     |     |     |
      | 4 |     |     |     |     |     |     |     |     |
      | 5 |     |     |     |     |     |     |     |     |
      | 6 |     |     |     |     |     |     |     |     |
      | 7 | paw | paw | paw | paw | paw | paw | paw | paw |
      | 8 | roo | kni | bis | kin | que | bis | kni | roo |

  Scenario: Custom starting board (for testing)
    Given It is Black's turn and the board looks like
      |   | A | B   | C   | D | E | F | G | H |
      | 1 |   |     |     |   |   |   |   |   |
      | 2 |   |     |     |   |   |   |   |   |
      | 3 |   |     |     |   |   |   |   |   |
      | 4 |   | PAW |     |   |   |   |   |   |
      | 5 |   |     | PAW |   |   |   |   |   |
      | 6 |   |     |     |   |   |   |   |   |
      | 7 |   |     |     |   |   |   |   |   |
      | 8 |   |     |     |   |   |   |   |   |
    Then the board should look like
      |   | A | B   | C   | D | E | F | G | H |
      | 1 |   |     |     |   |   |   |   |   |
      | 2 |   |     |     |   |   |   |   |   |
      | 3 |   |     |     |   |   |   |   |   |
      | 4 |   | PAW |     |   |   |   |   |   |
      | 5 |   |     | PAW |   |   |   |   |   |
      | 6 |   |     |     |   |   |   |   |   |
      | 7 |   |     |     |   |   |   |   |   |
      | 8 |   |     |     |   |   |   |   |   |

