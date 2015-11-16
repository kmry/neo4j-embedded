package ex1
import org.neo4j.graphdb.factory._
import org.neo4j.graphdb.{DynamicRelationshipType, GraphDatabaseService, Node}
import org.neo4j.cypher.ExecutionEngine

object App {
  val db: GraphDatabaseService = new GraphDatabaseFactory().newEmbeddedDatabase("tmp/neo_cypher_test");
  val engine: ExecutionEngine = new ExecutionEngine(db)

  val cyphers= Array(
    """CREATE (movie:Movie { title:"The Matrix",released:1997 })""",
    """CREATE (movie:Movie { title:"The Matrix",revised:2003 })""",
    """
    MATCH (movie:Movie { title:"The Matrix"})
    RETURN movie
    """,
    """
    MATCH (movie:Movie {title:"The Matrix"})
    SET movie.japanese="マトリックス"
    RETURN movie.title, movie.japanese, movie.released
    """,
    """ MATCH (movie:Movie { title:"The Matrix"}) RETURN movie"""
  )

  def op ={
    val ns = Stream.from(1).iterator //counter
    cyphers.foreach{ c =>
      println (s"コマンド${ns.next} :")
      println (engine.execute(c).dumpToString())
    }
  }
  def main(args: Array[String]): Unit = {
    println("db running")
    op
    db.shutdown()
    println("db shutdown")
  }

}
