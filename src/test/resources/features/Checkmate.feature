#Feature: Checkmate
#
#  Scenario: Black is in checkmate
#    Given It is White's turn and the board looks like
#      |   | A   | B   | C   | D   | E   | F   | G   | H   |
#      | 1 | ROO | KNI | BIS | QUE | KIN | BIS | KNI | ROO |
#      | 2 | PAW | PAW | PAW |     | PAW | PAW | PAW | PAW |
#      | 3 |     |     |     |     |     |     |     |     |
#      | 4 |     | paw |     | PAW |     | kni |     |     |
#      | 5 | BIS |     |     |     |     |     |     |     |
#      | 6 |     |     |     |     |     |     |     |     |
#      | 7 | paw |     |     | paw | paw | paw | paw | paw |
#      | 8 | roo |     | bis | kin | que | bis | kni | roo |
#    Then White wins