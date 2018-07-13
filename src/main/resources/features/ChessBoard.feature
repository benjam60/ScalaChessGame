Feature: Ensures chessboard initializes correctly

  Scenario: When a game begins, there will be a chessboard with the correct pieces
    Given a new chess game
    Then the board should look like
      | Roo | Kni | Bis | Que | Kin | Bis | Kni | Roo |
      | Paw | Paw | Paw | Paw | Paw | Paw | Paw | Paw |
      | 2,0 | 2,1 | 2,2 | 2,3 | 2,4 | 2,5 | 2,6 | 2,7 |
      | 3,0 | 3,1 | 3,2 | 3,3 | 3,4 | 3,5 | 3,6 | 3,7 |
      | 4,0 | 4,1 | 4,2 | 4,3 | 4,4 | 4,5 | 4,6 | 4,7 |
      | 5,0 | 5,1 | 5,2 | 5,3 | 5,4 | 5,5 | 5,6 | 5,7 |
      | Paw | Paw | Paw | Paw | Paw | Paw | Paw | Paw |
      | Roo | Kni | Bis | Kin | Que | Bis | Kni | Roo |


