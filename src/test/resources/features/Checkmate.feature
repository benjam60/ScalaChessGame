Feature: Checkmate

#  Scenario: Black is in checkmate
#    Given It is White's turn and the board looks like
#      |   | A   | B   | C   | D   | E   | F   | G   | H   |
#      | 1 | ROO | KNI | BIS | QUE | KIN | BIS | KNI | ROO |
#      | 2 | PAW | PAW | PAW |     |     | PAW | PAW | PAW |
#      | 3 |     |     |     | PAW |     |     |     |     |
#      | 4 |     |     |     |     | PAW |     |     |     |
#      | 5 |     |     |     |     |     |     | paw |     |
#      | 6 |     |     |     |     |     | paw |     |     |
#      | 7 | paw | paw | paw | paw | paw |     |     | paw |
#      | 8 | roo | kni | bis | que | kin | bis | kni | roo |
#    When the following moves are made
#      | 1D->5H |
#    Then White wins