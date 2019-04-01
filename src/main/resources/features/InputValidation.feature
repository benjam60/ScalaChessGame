Feature: Input Validation

  Scenario Outline: A player can't give invalid moves, and it is that player's turn until getting a valid one
    Given In a new game, it is the turn of White
    When the following moves are made
      | 7B->4B          |
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
      | 8 | Roo | Kni | Bis | Kin | Que | Bis | Kni | Roo |
    Examples:
      | Illegal Moves |
      | 4A->2A        |
      | 0A->2A        |
      | 11->11        |
      | 2Q->3Q        |
      | A2->4A        |
      | toolong       |
      | short         |