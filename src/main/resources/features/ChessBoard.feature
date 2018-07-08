Feature: Ensures chessboard initializes correctly

  @ExampleFeature
  Scenario: When a game begins, there will be a chessboard with the correct pieces
    Given a new chess game
    Then the board should look like
      | col1 | col2 | col3 | col4 | col5 | col6 | col7 | col8 |
      | Roo  | Kni  | Bis  | Que  | Kin  | Bis  | Kni  | Roo  |
      | Paw  | Paw  | Paw  | Paw  | Paw  | Paw  | Paw  | Paw  |
      |      |      |      |      |      |      |      |      |
      |      |      |      |      |      |      |      |      |
      |      |      |      |      |      |      |      |      |
      |      |      |      |      |      |      |      |      |
      | Paw  | Paw  | Paw  | Paw  | Paw  | Paw  | Paw  | Paw  |
      | Roo  | Kni  | Bis  | Kin  | Que  | Bis  | Kni  | Roo  |


