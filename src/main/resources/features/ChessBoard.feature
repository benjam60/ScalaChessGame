Feature: Ensures chessboard initializes correctly

  @ExampleFeature
  Scenario: When a game begins, there will be a chessboard with the correct pieces
    Given a new chess game
    Then the board should look like
      | Roo  | Kni  | Bis  | Que  | Kin  | Bis  | Kni  | Roo  |
      | Paw  | Paw  | Paw  | Paw  | Paw  | Paw  | Paw  | Paw  |
      |      |      |      |      |      |      |      |      |
      |      |      |      |      |      |      |      |      |
      |      |      |      |      |      |      |      |      |
      |      |      |      |      |      |      |      |      |
      | Paw  | Paw  | Paw  | Paw  | Paw  | Paw  | Paw  | Paw  |
      | Roo  | Kni  | Bis  | Kin  | Que  | Bis  | Kni  | Roo  |


