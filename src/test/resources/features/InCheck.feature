Feature: Situations where we are in check

  Scenario: Black is in Check
    Given It is White's turn and the board looks like
      |   | A   | B   | C   | D   | E   | F   | G   | H   |
      | 1 | ROO | KNI | BIS | QUE | KIN | BIS | KNI | ROO |
      | 2 | PAW | PAW | PAW |     | PAW | PAW | PAW | PAW |
      | 3 |     |     |     |     |     |     |     |     |
      | 4 |     |     |     |     |     |     |     |     |
      | 5 |     |     |     | PAW |     |     |     |     |
      | 6 |     |     |     |     |     |     |     |     |
      | 7 | paw | paw | paw | paw |     | paw | paw | paw |
      | 8 | roo | kni | bis | kin | que | bis | kni | roo |
    When the following moves are made
      | 1C->5G |
    Then Black is in check