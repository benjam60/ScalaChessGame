Feature: Ensures correct King movement

  Scenario Outline: King valid moves
    Given It is White's turn and the board looks like
      |   | A | B   | C | D | E | F | G | H |
      | 1 |   |     |   |   |   |   |   |   |
      | 2 |   | KIN |   |   |   |   |   |   |
      | 3 |   |     |   |   |   |   |   |   |
      | 4 |   |     |   |   |   |   |   |   |
      | 5 |   |     |   |   |   |   |   |   |
      | 6 |   |     |   |   |   |   |   |   |
      | 7 |   |     |   |   |   |   |   |   |
      | 8 |   |     |   |   |   |   |   |   |
    When the following moves are made
      | <Valid Moves> |
    Then it is the turn of Black
    Examples:
      | Valid Moves |
      | 2B->1A      |
      | 2B->1B      |
      | 2B->1C      |
      | 2B->2C      |
      | 2B->3C      |
      | 2B->3B      |
      | 2B->3A      |
      | 2B->2A      |

  Scenario Outline: King invalid moves
    Given It is White's turn and the board looks like
      |   | A | B   | C | D | E | F | G | H |
      | 1 |   |     |   |   |   |   |   |   |
      | 2 |   | kin |   |   |   |   |   |   |
      | 3 |   |     |   |   |   |   |   |   |
      | 4 |   |     |   |   |   |   |   |   |
      | 5 |   |     |   |   |   |   |   |   |
      | 6 |   |     |   |   |   |   |   |   |
      | 7 |   |     |   |   |   |   |   |   |
      | 8 |   |     |   |   |   |   |   |   |
    When the following moves are made
      | <Invalid Moves> |
    Then it is the turn of White
    Examples:
      | Invalid Moves |
      | 2B->4E        |
      | 2B->4D      |
      | 2B->4B      |
      | 2B->2E      |
