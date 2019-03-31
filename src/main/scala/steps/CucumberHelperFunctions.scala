package steps

import ChessGame.AllPieces._
import ChessGame.Board
import cucumber.api.DataTable

import scala.collection.JavaConverters._


object CucumberHelperFunctions {

  def convert(boardState: DataTable): Board = {
    val boardAsSingleList = boardState.asList(classOf[String]).asScala.toList.toIndexedSeq
    val boardWithoutFileLetters = boardAsSingleList.drop(rowSize)

    def removeRankNumbersAndChangeType(state: IndexedSeq[String]): IndexedSeq[IndexedSeq[ChessPiece]] = {
      def twoDimensionalize(rest : IndexedSeq[String]): IndexedSeq[IndexedSeq[String]] = {
        val nextRow: IndexedSeq[String] = rest.slice(1, rowSize)
        val newRest: IndexedSeq[String] = rest.drop(rowSize)
        if (newRest.isEmpty) IndexedSeq(nextRow) else IndexedSeq(nextRow) ++ twoDimensionalize(newRest)
      }
      val rows: IndexedSeq[IndexedSeq[String]] = twoDimensionalize(state)
      rows.map(row => row.map(convert))
    }

    val state = removeRankNumbersAndChangeType(boardWithoutFileLetters)
    Board(state)
  }

  //ToDo: implement from string to get rid of these case statements
  def convert(piece: String): ChessPiece = {
    piece match { //how to differentiate between move once or twice for a pawn
      case WhitePawnName => WhitePawnCanMoveTwice
      case BlackPawnName => BlackPawnCanMoveTwice
      case "Kni" => Knight
      case "Bis" => Bishop
      case "Que" => Queen
      case "Kin" => King
      case "Roo" => Rook
      case _ => Space
    }
  }

  def convertMovesToList(dataTable: DataTable): List[String] = dataTable.asList(classOf[String]).asScala.toList
  private val rowSize = 9
}