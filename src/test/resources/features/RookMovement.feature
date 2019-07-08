Feature: Ensures correct knight movement

  Scenario Outline: Rook valid moves
    Given It is White's turn and the board looks like
      |   | A   | B   | C   | D   | E   | F   | G   | H   |
      | 1 | ROO | KNI | BIS | QUE | KIN | BIS | KNI | ROO |
      | 2 |     | PAW | PAW | PAW | PAW | PAW | PAW | PAW |
      | 3 |     |     |     |     |     |     |     |     |
      | 4 |     |     |     |     |     |     |     |     |
      | 5 |     |     |     |     |     |     |     |     |
      | 6 |     |     |     |     |     |     |     |     |
      | 7 | paw | paw | paw | paw | paw | paw | paw | paw |
      | 8 | roo | kni | bis | kin | que | bis | kni | roo |
    When the following moves are made
      | <Valid Moves> |
    Then it is the turn of Black
    Examples:
      | Valid Moves |
      | 1A->2A      |
      | 1A->3A      |

  Scenario Outline: More rook valid moves
    Given It is White's turn and the board looks like
      |   | A   | B   | C   | D   | E   | F   | G   | H   |
      | 1 |     | KNI | BIS | QUE | KIN | BIS | KNI | ROO |
      | 2 |     | PAW | PAW | PAW | PAW | PAW | PAW | PAW |
      | 3 |     |     |     |     |     |     |     |     |
      | 4 |     |     |     | ROO |     |     |     |     |
      | 5 |     |     |     |     |     |     |     |     |
      | 6 |     |     |     |     |     |     |     |     |
      | 7 | paw | paw | paw | paw | paw | paw | paw | paw |
      | 8 | roo | kni | bis | kin | que | bis | kni | roo |
    When the following moves are made
      | <Valid Moves> |
    Then it is the turn of Black
    Examples:
      | Valid Moves |
      | 4D->3D      |
      | 4D->4C      |
      | 4D->4E      |

  Scenario Outline: Invalid rook moves
    Given It is White's turn and the board looks like
      |   | A   | B   | C   | D   | E   | F   | G   | H   |
      | 1 |     | KNI | BIS | QUE | KIN | BIS | KNI | ROO |
      | 2 |     | PAW | PAW | PAW | PAW | PAW | PAW | PAW |
      | 3 |     |     |     |     |     |     |     |     |
      | 4 |     |     |     | ROO |     |     |     |     |
      | 5 |     |     |     |     |     |     |     |     |
      | 6 |     |     |     |     |     |     |     |     |
      | 7 | paw | paw | paw | paw | paw | paw | paw | paw |
      | 8 | roo | kni | bis | kin | que | bis | kni | roo |
    When the following moves are made
      | <Invalid Moves> |
    Then it is the turn of White
    Examples:
      | Invalid Moves |
      | 4D->3C        |
