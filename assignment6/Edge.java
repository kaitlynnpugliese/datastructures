public class Edge {

  public double distance;
  public Vertex source;
  public Vertex target;

  public Edge(Vertex vertex1, Vertex vertex2, double weight) {
    source = vertex1;
    target = vertex2;
    this.distance = weight;
  }

  public String toString() {
    return source + " - " + target;
  }


public int compareTo(Edge edge) {
	  if(this.distance > edge.distance){
		  return 1;
	  }
	  else if(this.distance < edge.distance){
		  return -1;
	  }
	  else{ return 0;}
}
}