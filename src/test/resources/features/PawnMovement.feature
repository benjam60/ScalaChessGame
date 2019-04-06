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

  Scenario: A pawn can move two spaces once and then only one space
    Given a new chess game
    When the following moves are made
      | 2G->3G |
      | 7B->5B |
      | 3G->5G |
      | 5B->4B |
      | 5G->6G |
    Then the board should look like
      |   | A   | B   | C   | D   | E   | F   | G   | H   |
      | 1 | Roo | Kni | Bis | Que | Kin | Bis | Kni | Roo |
      | 2 | Paw | Paw | Paw | Paw | Paw | Paw |     | Paw |
      | 3 |     |     |     |     |     |     |     |     |
      | 4 |     | paw |     |     |     |     |     |     |
      | 5 |     |     |     |     |     |     |     |     |
      | 6 |     |     |     |     |     |     | Paw |     |
      | 7 | paw |     | paw | paw | paw | paw | paw | paw |
      | 8 | Roo | Kni | Bis | Kin | Que | Bis | Kni | Roo |

  Scenario: A pawn can eat an opposing piece at one space diagnol
    Given a new chess game
    When the following moves are made
      | 2C->4C |
      | 7D->5D |
      | 4C->5D |
    Then the board should look like
      |   | A   | B   | C   | D   | E   | F   | G   | H   |
      | 1 | Roo | Kni | Bis | Que | Kin | Bis | Kni | Roo |
      | 2 | Paw | Paw |     | Paw | Paw | Paw | Paw | Paw |
      | 3 |     |     |     |     |     |     |     |     |
      | 4 |     |     |     |     |     |     |     |     |
      | 5 |     |     |     | Paw |     |     |     |     |
      | 6 |     |     |     |     |     |     |     |     |
      | 7 | paw | paw | paw |     | paw | paw | paw | paw |
      | 8 | Roo | Kni | Bis | Kin | Que | Bis | Kni | Roo |


#  Scenario: A pawn should not be able to jump over its own pieces
#
#    Scenario: can't move diagnolly 2 spaces
#
#      Scenario: can not move backwards!
