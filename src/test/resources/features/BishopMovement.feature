Feature: Ensures correct Bishop movement

  Scenario Outline: Bishop valid moves
    Given It is White's turn and the board looks like
      |   | A | B   | C | D   | E | F | G | H |
      | 1 |   |     |   |     |   |   |   |   |
      | 2 |   | BIS |   |     |   |   |   |   |
      | 3 |   |     |   |     |   |   |   |   |
      | 4 |   |     |   | paw |   |   |   |   |
      | 5 |   |     |   |     |   |   |   |   |
      | 6 |   |     |   |     |   |   |   |   |
      | 7 |   |     |   |     |   |   |   |   |
      | 8 |   |     |   |     |   |   |   |   |
    When the following moves are made
      | <Valid Moves> |
    Then it is the turn of Black
    Examples:
      | Valid Moves |
      | 2B->1A      |
      | 2B->1C      |
      | 2B->3A      |
      | 2B->3C      |
      | 2B->4D      |


  Scenario Outline: Bishop invalid moves
    Given It is White's turn and the board looks like
      |   | A   | B   | C | D   | E | F   | G   | H |
      | 1 |     |     |   |     |   |     |     |   |
      | 2 |     | BIS |   |     |   |     |     |   |
      | 3 | PAW |     |   |     |   |     |     |   |
      | 4 |     |     |   | paw |   |     |     |   |
      | 5 |     |     |   |     |   |     | PAW |   |
      | 6 |     |     |   |     |   | BIS |     |   |
      | 7 |     |     |   |     |   |     |     |   |
      | 8 |     |     |   |     |   |     |     |   |
    When the following moves are made
      | <Invalid Moves> |
    Then it is the turn of White
    Examples:
      | Invalid Moves |
#      | 2B->5E        |
#      | 2B->3A        |
#      | 2B->3B        |
#      | 2B->1B        |
#      | 2B->2A        |
#      | 2B->4A        |
      | 6F->4H        |
    #add all 4 directions, with one and more spaces
