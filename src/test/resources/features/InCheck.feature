Feature: Situations where we are in check

  Scenario: Black is in Check
    Given It is White's turn and the board looks like
      |   | A   | B   | C   | D   | E   | F   | G   | H   |
      | 1 | ROO | KNI | BIS | QUE | KIN | BIS | KNI | ROO |
      | 2 | PAW | PAW | PAW |     |     | PAW | PAW | PAW |
      | 3 |     |     |     | PAW | PAW |     |     |     |
      | 4 |     |     |     |     |     |     |     |     |
      | 5 |     |     |     |     |     |     |     |     |
      | 6 |     |     |     |     | kin |     |     |     |
      | 7 | paw | paw | paw | paw |     | paw | paw | paw |
      | 8 | roo | kni | bis | que |     | bis | kni | roo |
    When the following moves are made
      | 1D->4G |
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
      | 8 | roo | kni | bis | que | kin | bis | kni | roo |
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
      | 8 | roo | kni | bis | que | kin |     | kni | roo |
    Then White is in check


  Scenario: If you are in check, you must get yourself out of check
    Given It is Black's turn and they are in check
      |   | A   | B   | C   | D   | E   | F   | G   | H   |
      | 1 | ROO | KNI | BIS | QUE | KIN | BIS | KNI | ROO |
      | 2 | PAW | PAW |     | PAW | PAW | PAW | PAW | PAW |
      | 3 |     |     |     |     |     |     |     |     |
      | 4 |     |     |     |     |     |     |     |     |
      | 5 |     |     |     |     |     |     |     |     |
      | 6 |     |     |     |     |     |     |     |     |
      | 7 | paw | paw | paw | PAW |     | paw | paw | paw |
      | 8 | roo | kni | bis | que | kin | bis | kni | roo |
    When the following moves are made
      | 7A->6A |
    Then it is the turn of Black
    And Black is in check

  Scenario Outline: Black gets in check and then gets out of it
    Given It is Black's turn and they are in check
      |   | A   | B   | C   | D   | E   | F   | G   | H   |
      | 1 | ROO | KNI | BIS | QUE | KIN | BIS | KNI | ROO |
      | 2 | PAW | PAW |     | PAW | PAW | PAW | PAW | PAW |
      | 3 |     |     |     |     |     |     |     |     |
      | 4 |     |     |     |     |     |     |     |     |
      | 5 |     |     |     |     |     |     |     |     |
      | 6 |     |     |     |     |     |     |     |     |
      | 7 | paw | paw | paw | PAW |     | paw | paw | paw |
      | 8 | roo | kni | bis | que | kin | bis | kni | roo |
    When the following moves are made
      | <Moves> |
    Then it is the turn of White
    And Black is out of check
    Examples:
      | Moves  |
      | 8E->7D |
      | 8E->7E |

  Scenario Outline: Black gets in check and can only block it
    Given It is Black's turn and they are in check
      |   | A   | B   | C   | D   | E   | F   | G   | H   |
      | 1 |     | KNI | BIS | QUE | KIN | BIS | KNI | ROO |
      | 2 |     | PAW | PAW | PAW | PAW | PAW | PAW | PAW |
      | 3 |     |     |     |     | ROO |     |     |     |
      | 4 | PAW |     |     |     |     |     |     |     |
      | 5 |     |     |     |     |     |     |     |     |
      | 6 |     |     |     |     |     |     |     |     |
      | 7 | paw | paw | paw | paw |     | paw | paw | paw |
      | 8 | roo | kni | bis | que | kin | bis | kni | roo |
    When the following moves are made
      | <Moves> |
    Then it is the turn of White
    And Black is out of check
    Examples:
      | Moves  |
      | 8F->7E |
      | 8D->7E |