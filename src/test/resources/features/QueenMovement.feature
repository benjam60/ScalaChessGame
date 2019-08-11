Feature: Ensures correct Queen movement

  Scenario Outline: Queen valid moves
    Given It is White's turn and the board looks like
      |   | A   | B   | C   | D   | E   | F   | G   | H   |
      | 1 | ROO | KNI | BIS |     | KIN | BIS | KNI | ROO |
      | 2 |     |     |     |     |     |     |     |     |
      | 3 |     |     |     | QUE |     |     |     |     |
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
      | 3D->4D      |
      | 3D->2D      |
      | 3D->4E      |
      | 3D->2C      |

  Scenario Outline: Invalid Queen moves
    Given It is White's turn and the board looks like
      |   | A   | B   | C   | D   | E   | F   | G   | H   |
      | 1 | ROO | KNI | BIS |     | KIN | BIS | KNI | ROO |
      | 2 |     |     |     |     |     |     |     |     |
      | 3 |     |     |     | QUE |     |     |     |     |
      | 4 |     |     |     |     |     |     |     |     |
      | 5 |     |     |     |     | que |     |     |     |
      | 6 |     |     |     |     |     |     |     |     |
      | 7 | paw | paw | paw | paw |     | paw | paw | paw |
      | 8 | roo | kni | bis | kin |     | bis | kni | roo |
    When the following moves are made
      | <Invalid Moves> |
    Then it is the turn of White
    Examples:
      | Invalid Moves |
      | 5E->7D        |