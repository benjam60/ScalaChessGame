Feature: Ensures all pieces' movement follows certain rules

  Scenario Outline: Can't eat your own pieces
    Given It is white's turn and the board looks like
      |   | A   | B   | C   | D   | E   | F   | G   | H   |
      | 1 | Roo | Kni | Bis | Que | Kin | Bis | Kni | Roo |
      | 2 | Paw | Paw |     | Paw | Paw | Paw | Paw | Paw |
      | 3 |     |     |     |     |     |     |     |     |
      | 4 |     |     |     | Paw |     |     |     |     |
      | 5 |     |     |     |     |     |     |     |     |
      | 6 |     |     |     |     |     |     |     |     |
      | 7 | paw | paw | paw |     | paw | paw | paw | paw |
      | 8 | Roo | Kni | Bis | Kin | Que | Bis | Kni | Roo |
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


  Scenario: You can't eat your own piece

