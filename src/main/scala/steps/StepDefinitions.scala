package steps
import cucumber.api.scala.{EN, ScalaDsl}
import java.util.List;
import cucumber.api.DataTable;
import org.slf4j.LoggerFactory
import scala.collection.JavaConverters._


class StepDefinitions extends ScalaDsl with EN {
  private val log = LoggerFactory.getLogger(classOf[StepDefinitions])
  private val runner = new Runner


  Given("""^a new chess game$"""){ () =>
  }
  //read it in as a datatable b/c the pipes are already there
  Then("^the board should look like$"){ (datatable : DataTable) =>
    //val x = datatable.asMaps(classOf[String], classOf[Any]).asScala.map(_.asScala.toMap).toList
   // val x = datatable.asList(classOf[String]).asScala.toList
    val board = CucumberHelperFunctions.convert(datatable)
    println(board.toString)
    //println(x.map(row =>row.mkString("")).mkString("\n"))



   // val y = x.toList
    //val z = y.map(tr => tr.toString())
 //   println(z.mkString("\n"))

    /*
    val rows = datatable.getRows()
    val cols = rows.toArray.toList
    val y = cols.map(x => x.toString)
    for {
      row <- y
    } println(row)*/
  }

}
