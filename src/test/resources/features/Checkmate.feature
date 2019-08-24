Feature: Checkmate

#  Scenario: Black is in checkmate
#    Given It is White's turn and the board looks like
#      |   | A   | B   | C   | D   | E   | F   | G   | H   |
#      | 1 | ROO | KNI | BIS | QUE | KIN | BIS | KNI | ROO |
#      | 2 | PAW | PAW |     | PAW | PAW | PAW | PAW | PAW |
#      | 3 |     |     |     |     |     |     |     |     |
#      | 4 |     |     |     |     |     |     |     |     |
#      | 5 |     |     |     |     |     |     |     |     |
#      | 6 |     |     | PAW |     |     |     |     |     |
#      | 7 | paw | paw |     | paw |     | paw | paw | paw |
#      | 8 | roo | kni | bis | kin | que | bis | kni | roo |
#    When the following moves are made
#      | 6C->7C |
#    Then Black is in check