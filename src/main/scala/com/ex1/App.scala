package ex
import org.neo4j.graphdb.factory._
import org.neo4j.graphdb.{DynamicRelationshipType, GraphDatabaseService, Node}
//kmr
import org.neo4j.cypher.ExecutionEngine

object App {

  val db: GraphDatabaseService = new GraphDatabaseFactory().newEmbeddedDatabase("target/db");
  var engine: ExecutionEngine = new ExecutionEngine(db)
  def main(args: Array[String]): Unit = {
    implicit val graphDb = new GraphDatabaseFactory().newEmbeddedDatabase("/tmp/neo_cypher1");
    println("db running")
    op2
    db.shutdown()
    println("db shutdown")
  }

  def createNode() = {
    db.createNode()
  }

  def relate(a: Node, b: Node) {
    a.createRelationshipTo(b, DynamicRelationshipType.withName("r"))
  }

///////
  def op2 ={
    println(
      engine.execute("""CREATE (movie:Movie { title:"The Matrix",released:1997 })""").dumpToString()
      + engine.execute("""CREATE (movie:Movie { title:"The Matrix",released:2993 })""").dumpToString()
    )
    println(
      engine.execute("""MATCH (movie:Movie { title:"The Matrix"})
RETURN movie""").dumpToString()
    )
  }
}
