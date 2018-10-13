import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

public class Graph {

  // Keep a fast index to nodes in the map
  private Map<Integer, Vertex> vertexNames;
  int n;

  /**
   * Construct an empty Graph with a map. The map's key is the name of a vertex
   * and the map's value is the vertex object.
   */
  public Graph() {
    vertexNames = new HashMap<>();
  }

  /**
   * Adds a vertex to the graph. Throws IllegalArgumentException if two vertices
   * with the same name are added.
   * 
   * @param v
   *          (Vertex) vertex to be added to the graph
   */
  public void addVertex(Vertex v) {
    if (vertexNames.containsKey(v.name))
      throw new IllegalArgumentException("Cannot create new vertex with existing name.");
    vertexNames.put(v.name, v);
  }

  /**
   * Gets a collection of all the vertices in the graph
   * 
   * @return (Collection<Vertex>) collection of all the vertices in the graph
   */
  public Collection<Vertex> getVertices() {
    return vertexNames.values();
  }

  /**
   * Gets the vertex object with the given name
   * 
   * @param name
   *          (String) name of the vertex object requested
   * @return (Vertex) vertex object associated with the name
   */
  public Vertex getVertex(String name) {
    return vertexNames.get(name);
  }

  /**
   * Adds a directed edge from vertex u to vertex v
   * 
   * @param nameU
   *          (String) name of vertex u
   * @param nameV
   *          (String) name of vertex v
   * @param cost
   *          (double) cost of the edge between vertex u and v
   */
  public void addEdge(int nameU, int nameV, Double cost) {
    if (!vertexNames.containsKey(nameU))
      throw new IllegalArgumentException(nameU + " does not exist. Cannot create edge.");
    if (!vertexNames.containsKey(nameV))
      throw new IllegalArgumentException(nameV + " does not exist. Cannot create edge.");
    Vertex sourceVertex = vertexNames.get(nameU);
    Vertex targetVertex = vertexNames.get(nameV);
    Edge newEdge = new Edge(sourceVertex, targetVertex, cost);
    sourceVertex.addEdge(newEdge);
  }

  /**
   * Adds an undirected edge between vertex u and vertex v by adding a directed
   * edge from u to v, then a directed edge from v to u
   * 
   * @param name
   *          (String) name of vertex u
   * @param name2
   *          (String) name of vertex v
   * @param cost
   *          (double) cost of the edge between vertex u and v
   */
  public void addUndirectedEdge(int name, int name2, double cost) {
    addEdge(name, name2, cost);
    addEdge(name2, name, cost);
  }


  /**
   * Computes the euclidean distance between two points as described by their
   * coordinates
   * 
   * @param ux
   *          (double) x coordinate of point u
   * @param uy
   *          (double) y coordinate of point u
   * @param vx
   *          (double) x coordinate of point v
   * @param vy
   *          (double) y coordinate of point v
   * @return (double) distance between the two points
   */
  public double computeEuclideanDistance(double ux, double uy, double vx, double vy) {
    return Math.sqrt(Math.pow(ux - vx, 2) + Math.pow(uy - vy, 2));
  }

  /**
   * Computes euclidean distance between two vertices as described by their
   * coordinates
   * 
   * @param u
   *          (Vertex) vertex u
   * @param v
   *          (Vertex) vertex v
   * @return (double) distance between two vertices
   */
  public double computeEuclideanDistance(Vertex u, Vertex v) {
    return computeEuclideanDistance(u.x, u.y, v.x, v.y);
  }

  /**
   * Calculates the euclidean distance for all edges in the map using the
   * computeEuclideanCost method.
   */
  public void computeAllEuclideanDistances() {
    for (Vertex u : getVertices())
      for (Edge uv : u.adjacentEdges) {
        Vertex v = uv.target;
        uv.distance = computeEuclideanDistance(u.x, u.y, v.x, v.y);
      }
  }



  // STUDENT CODE STARTS HERE

  public void generateRandomVertices(int n) {    	

    vertexNames = new HashMap<>(); // reset the vertex hashmap
    
    //make n new vertices and add them to the vertexNames hashmap
    Random random = new Random();
    for(int i = 0; i < n; i++){
    	int x = random.nextInt(100);
    	int y = random.nextInt(100);
    	vertexNames.put(i, new Vertex(i, x, y));
    }
    
    //make a new graph with the new vertices
    for(Vertex start: this.getVertices()){
    	for(Vertex end: this.getVertices()){
    		if(!(start==end)){
    			start.adjacentEdges.add(new Edge(start, end, 0));
    		}
    	}
    }
    
    computeAllEuclideanDistances(); // compute distances
  }
  
  public Edge getMin(List<Edge> adjacentEdges){
	  Edge init = new Edge(null, null, 0.0);
	  Edge minimum = init;
	  for(Edge edge: adjacentEdges){

		  if((edge.compareTo(minimum)==-1 || minimum == init) && !edge.target.known){
			  minimum = edge;
		  }
	  }
	  return minimum;
  }

  public List<Edge> nearestNeighborTsp() {
	  //make sure the hashmap has vertices 
      if(!(vertexNames.isEmpty())){
    	  //make sure every time this method is called, known is reset
    	  for(Vertex vert: getVertices()){
    		  vert.known = false;
    	  }
    	  
    	  //make the list of nearest neighbors, start with
    	  //vertex 0
    	  LinkedList<Edge> nearest = new LinkedList<Edge>();
    	  Edge edge;
    	  Vertex vertex = vertexNames.get(0);
    	  if(vertex == null){
    		  System.out.println("null vertex found");
    	  }
    	  edge = getMin(vertex.adjacentEdges);
    	  while((edge.distance != 0.0)){
    		  //change to true and add to list
    		  vertex.known = true;
    		  nearest.add(edge);
    		  vertex = edge.target;
    		  edge = getMin(vertex.adjacentEdges);
    	  }
    	  return nearest;
      }
      else{
    	  return null;
      }
    
  }

  public List<Edge> bruteForceTsp() {
	  
	  if(!(vertexNames.isEmpty())){
		  //make copy of vertexNames list
		  ArrayList<Vertex> vertexList = new ArrayList<>();
		  int length = vertexNames.size();
		  for(int i = 0; i < length; i++ ){
			  vertexList.add(i,vertexNames.get(i));
		  }
		  
		  //permutations
		  List<Edge> shortestPath = getShortestPath(vToE(getPermutations(vertexList)));
		  return shortestPath;	  
	  }
	  else{
		  return null;
	  }
  }
  
  public ArrayList<ArrayList<Vertex>> getPermutations(ArrayList<Vertex> vertexList) {
	  if(vertexList.size() ==1){
		 ArrayList<ArrayList<Vertex>> newList = new ArrayList<>();
		 newList.add(vertexList);
		 return newList;
	  }
	  else{
		  //method to recursively permute the list to get every possibility of edge combinations
		  ArrayList<ArrayList<Vertex>> newList = new ArrayList<>();
	        for (Vertex x: vertexList) {
	            ArrayList<Vertex> subList = new ArrayList<>(vertexList);
	            subList.remove(x);
	            ArrayList<ArrayList<Vertex>> subListNew = getPermutations(subList);
	            for (ArrayList<Vertex> list2: subListNew) {
	                ArrayList<Vertex> another = new ArrayList<>();
	                another.add(x);
	                another.addAll(list2);
	                newList.add(another);
	            }
	        }
	        return newList;

	  }
	  
  }
  
  //converts list of vertex path lists to list of edge path lists
  public LinkedList<LinkedList<Edge>> vToE(ArrayList<ArrayList<Vertex>> permutations){
	  LinkedList<LinkedList<Edge>> edgePathList = new LinkedList<LinkedList<Edge>>();
	  for(ArrayList<Vertex> list: permutations){
		  //to complete cycle add first vertex to the end of the list
		  Vertex vertex = list.get(0);
		  list.add(vertex);
		  //make linked list for each edge path (each permutation will become one)
		  LinkedList<Edge> edgePath = new LinkedList<Edge>();
		  int length = list.size();
		  for(int i = 0; i<(length-1); i++){
			  
			 Edge current = new Edge(list.get(i), list.get(i+1), computeEuclideanDistance(list.get(i), list.get(i+1)));
	   	     //add current edge to edgePath
			 edgePath.add(current);
			  
		  }
		  //add edgePath to edgePathList
		  edgePathList.add(edgePath);
	  }
	  return edgePathList;
  }
  
  public LinkedList<Edge> getShortestPath(LinkedList<LinkedList<Edge>> pathList){
	  LinkedList<Edge> shortest = null;
	  double minDistance = Double.POSITIVE_INFINITY;
	  for(LinkedList<Edge> edgePath: pathList){
		  double currentDist = getDistance(edgePath);
		  if(currentDist < minDistance){
			  minDistance = currentDist;
			  shortest = edgePath;
		  }
	  }
	  return shortest;
  }
  
  public double getDistance(LinkedList<Edge> edgePath){
	  double distance = 0.0;
	  for(Edge edge: edgePath){
		  distance = distance + edge.distance;
	  }
	  return distance;
  }

  // STUDENT CODE ENDS HERE



  /**
   * Prints out the adjacency list of the graph for debugging
   */
  public void printAdjacencyList() {
    for (int u : vertexNames.keySet()) {
      StringBuilder sb = new StringBuilder();
      sb.append(u);
      sb.append(" -> [ ");
      for (Edge e : vertexNames.get(u).adjacentEdges) {
        sb.append(e.target.name);
        sb.append("(");
        sb.append(e.distance);
        sb.append(") ");
      }
      sb.append("]");
      System.out.println(sb.toString());
    }
  }
}
