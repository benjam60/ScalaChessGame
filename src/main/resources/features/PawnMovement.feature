Feature: Ensure proper Pawn movement

  Scenario: A piece can be moved on the board and the board's state will update
    Given a new chess game
    When the following moves are made
      | 2B->3B |
      | 7H->5H |
    Then the board should look like
      |   | A   | B   | C   | D   | E   | F   | G   | H   |
      | 1 | Roo | Kni | Bis | Que | Kin | Bis | Kni | Roo |
      | 2 | Paw |     | Paw | Paw | Paw | Paw | Paw | Paw |
      | 3 |     | Paw |     |     |     |     |     |     |
      | 4 |     |     |     |     |     |     |     |     |
      | 5 |     |     |     |     |     |     |     | paw |
      | 6 |     |     |     |     |     |     |     |     |
      | 7 | paw | paw | paw | paw | paw | paw | paw |     |
      | 8 | Roo | Kni | Bis | Kin | Que | Bis | Kni | Roo |


  Scenario: When you try to move a pawn 2 spaces twice, then it'll only move 2 spaces once
    Given a new chess game
    When the following moves are made
      | 2B->4B |
      | 7B->5B |
      | 4B->6B |
      | 5B->3B |
    Then the board should look like
      |   | A   | B   | C   | D   | E   | F   | G   | H   |
      | 1 | Roo | Kni | Bis | Que | Kin | Bis | Kni | Roo |
      | 2 | Paw |     | Paw | Paw | Paw | Paw | Paw | Paw |
      | 3 |     |     |     |     |     |     |     |     |
      | 4 |     | Paw |     |     |     |     |     |     |
      | 5 |     | paw |     |     |     |     |     |     |
      | 6 |     |     |     |     |     |     |     |     |
      | 7 | paw |     | paw | paw | paw | paw | paw | paw |
      | 8 | Roo | Kni | Bis | Kin | Que | Bis | Kni | Roo |

  Scenario Outline: Ensure pawn type changes when moving it two spaces
    Given In a new game, it is the turn of <Color>
    When the following moves are made
      | <Move> |
    Then position <position> contains a <Color> pawn that can move once
    Examples:
      | Color | Move   | position |
      | Black | 7B->5B | 5B       |
      | White | 2C->4C | 4C       |

  Scenario: Ensure pawn type changes when moving it two spaces
    Given In a new game, it is the turn of Black
    When the following moves are made
      | 7B->5B |
    Then position 5B contains a Black pawn that can move once

  Scenario: Valid moves for black pawns
    Given In a new game, it is the turn of White
    When the following moves are made
      | 2C->4C |
    Then position 4C contains a White pawn that can move once
