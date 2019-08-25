Feature: Ensures correct knight movement

  Scenario Outline: Knight valid moves
    Given It is White's turn and the board looks like
      |   | A   | B   | C   | D   | E   | F   | G   | H   |
      | 1 | ROO |     | BIS | QUE | KIN | BIS | KNI | ROO |
      | 2 | PAW | PAW | PAW | PAW | PAW | PAW | PAW | PAW |
      | 3 |     |     | KNI |     |     |     |     |     |
      | 4 |     |     |     |     |     |     |     |     |
      | 5 |     |     |     |     |     |     |     |     |
      | 6 |     |     |     |     |     |     |     |     |
      | 7 | paw | paw | paw | paw | paw | paw | paw | paw |
      | 8 | roo | kni | bis | que | kin | bis | kni | roo |
    When the following moves are made
      | <Valid Moves> |
    Then it is the turn of Black
    Examples:
      | Valid Moves |
      | 3C->5B      |
      | 3C->4A      |
      | 3C->4E      |
      | 3C->1B      |
      | 3C->5D      |


  Scenario Outline: Knight invalid moves
    Given It is White's turn and the board looks like
      |   | A   | B   | C   | D   | E   | F   | G   | H   |
      | 1 | ROO |     | BIS | QUE | KIN | BIS | KNI | ROO |
      | 2 | PAW | PAW | PAW | PAW | PAW | PAW | PAW | PAW |
      | 3 |     |     | KNI |     |     |     |     |     |
      | 4 |     |     |     |     |     |     |     |     |
      | 5 |     |     |     |     |     |     |     |     |
      | 6 |     |     |     |     |     |     |     |     |
      | 7 | paw | paw | paw | paw | paw | paw | paw | paw |
      | 8 | roo | kni | bis | que | kin | bis | kni | roo |
    When the following moves are made
      | <Invalid Moves> |
    Then it is the turn of White
    Examples:
      | Invalid Moves |
      | 3C->4C        |
      | 3C->5C        |
      | 3C->3A        |
      | 3C->5A        |
      | 3C->4B        |
