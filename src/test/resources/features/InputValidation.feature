Feature: Input Validation

  Scenario Outline: A player can't give invalid moves, and it is that player's turn until getting a valid one
    Given In a new game, it is the turn of White
    When the following moves are made
      | <Illegal Moves> |
      | 2G->3G          |
    Then the board should look like
      |   | A   | B   | C   | D   | E   | F   | G   | H   |
      | 1 | Roo | Kni | Bis | Que | Kin | Bis | Kni | Roo |
      | 2 | Paw | Paw | Paw | Paw | Paw | Paw |     | Paw |
      | 3 |     |     |     |     |     |     | Paw |     |
      | 4 |     |     |     |     |     |     |     |     |
      | 5 |     |     |     |     |     |     |     |     |
      | 6 |     |     |     |     |     |     |     |     |
      | 7 | paw | paw | paw | paw | paw | paw | paw | paw |
      | 8 | roo | Kni | Bis | Kin | Que | Bis | Kni | roo |
    Examples:
      | Illegal Moves |
      | 7B->4B        |
      | 4A->2A        |
      | 0A->2A        |
      | 11->11        |
      | 2Q->3Q        |
      | A2->4A        |
      | toolong       |
      | short         |
      | 8A->9A        |
      | ~][\]<[       |
      | 2F->2F        |

  Scenario: A player can't take two turns in a row
    Given In a new game, it is the turn of White
    When the following moves are made
      | 2B->4B |
    Then it is the turn of Black