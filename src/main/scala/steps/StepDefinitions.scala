package steps
import cucumber.api.scala.{EN, ScalaDsl}
import java.util.List;
import cucumber.api.DataTable;
import org.slf4j.LoggerFactory
import scala.collection.JavaConversions._


class StepDefinitions extends ScalaDsl with EN {
  private val log = LoggerFactory.getLogger(classOf[StepDefinitions])
  private val runner = new Runner


  Given("""^a new chess game$"""){ () =>
  }
  //read it in as a datatable b/c the pipes are already there
  Then("^the board should look like$"){ (datatable : DataTable) =>
    val x = datatable.
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
