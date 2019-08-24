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

  Scenario: White is in Check
    Given It is Black's turn and the board looks like
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
      | 8F->4B |
    Then the board should look like
      |   | A   | B   | C   | D   | E   | F   | G   | H   |
      | 1 | ROO | KNI | BIS | QUE | KIN | BIS | KNI | ROO |
      | 2 | PAW | PAW | PAW |     | PAW | PAW | PAW | PAW |
      | 3 |     |     |     |     |     |     |     |     |
      | 4 |     | bis |     |     |     |     |     |     |
      | 5 |     |     |     | PAW |     |     |     |     |
      | 6 |     |     |     |     |     |     |     |     |
      | 7 | paw | paw | paw | paw |     | paw | paw | paw |
      | 8 | roo | kni | bis | kin | que |     | kni | roo |
    Then White is in check


  Scenario: If you are in check, you can't not get out of check
    Given It is Black's turn and their in check
      |   | A   | B   | C   | D   | E   | F   | G   | H   |
      | 1 | ROO | KNI | BIS | QUE | KIN | BIS | KNI | ROO |
      | 2 | PAW | PAW |     | PAW | PAW | PAW | PAW | PAW |
      | 3 |     |     |     |     |     |     |     |     |
      | 4 |     |     |     |     |     |     |     |     |
      | 5 |     |     |     |     |     |     |     |     |
      | 6 |     |     |     |     |     |     |     |     |
      | 7 | paw | paw | PAW | paw |     | paw | paw | paw |
      | 8 | roo | kni | bis | kin | que | bis | kni | roo |
    When the following moves are made
      | 7A->6A |
    Then it is the turn of Black