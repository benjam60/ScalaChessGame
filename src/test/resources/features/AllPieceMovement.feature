Feature: Ensures all pieces' movement follows certain rules

  Scenario Outline: Can't eat your own pieces
    Given It is White's turn and the board looks like
      |   | A   | B   | C   | D   | E   | F   | G   | H   |
      | 1 | ROO | KNI | BIS | QUE | KIN | BIS | KNI | ROO |
      | 2 | PAW | PAW |     | PAW | PAW | PAW | PAW | PAW |
      | 3 |     |     |     |     |     |     |     |     |
      | 4 |     |     |     | PAW |     |     |     |     |
      | 5 |     |     |     |     |     |     |     |     |
      | 6 |     |     |     |     |     |     |     |     |
      | 7 | paw | paw | paw | paw | paw | paw | paw | paw |
      | 8 | roo | kni | bis | kin | que | bis | kni | roo |
    When the following moves are made
      | <Invalid Moves> |
    Then it is the turn of White
    Examples:
      | Invalid Moves |
      | 3C->4D        |
      | 4D->3C        |
      | 2D->3C        |
      | 3D->2C        |
      | 2D->4D        |
      | 2D->2D        |


  Scenario: You can't eat your own piece

