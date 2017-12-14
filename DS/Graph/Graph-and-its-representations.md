---
author: rovo98
description: notebook, learning from geeksforgeeks
---

# Graph and its representtations

### Leading

Graph is a data struture that consists of following two components:

- A finte set of **vertices** alse called as nodes.
- A finte set of **ordered pair of the form (u,v) called as edge. The pair
  is ordered because (u,v) is not same as (v,u) in case of d8rected graph(di-graph)**.
  The pair of form (u,v) indicates that there is an edge from vertex u to vertex v. **The
  edges may contain weight/value/cost**.
  
- Graph's applications :
	
	Graphs are used to represent many real life applications: Graphs are used to represent
networks. The networks may include paths in a city or telephone network or circuit network.
Graphs are also used in social networks like linkedIn, facebook. For example, in facebook,
each person is represented with a vertex(or node). **Each node is a structure and contains 
information like person id, name, gender and locale. And there are more applications of graph.
Following is an example undirected graph with 5 verteces.

![graph-example]()

### representations of graph

Following two are the most commonly used representations of graph.
- 1.Adjacency Matriv.
- 2.Adjacency List.

	There are other representations also like, incidence Matrix and incidence List. **The choice 
of the graph representation is situationsecific**. It totally depends on the typy of operations 
to be performed and ease of use.

#### Adjacency Matrix

	Adjacency Matrix is a 2D array of size V x V where V is the number of vertices in a graph. Let
the 2D array be adj[][], a slot adj[i][j] = 1 indicates that there is an edge from vertex i to vertex
j. **Adjacency matrix for underected graph is always symmetric. Adjacency Matrix is also used to 
represent weighted graphs. if adj[i][j] = w, then there is an edge from vertex i to vertex j with 
weight w.

The adjacency matrix for the above example graph is :

![adjacency-matrix-example]()

- Pros: Representation is easier to implement and follow. Removing an edge takes O(1) time.
	Queries like whether there is an edge from vertex u to vertex v are efficient and can be done O(1).

- Cons: Consumes more space $O(V^2)$. Even if the graph is sparse(contains less number of edges), it 
	consumes the same space. Adding a vertex is $O(V^2)$ time.
	
	
#### Adjacency List :

	An array of linked lists is used. Size of the array is equal to number of vertices. Let the array be 
array[]. **An entry array[i] represents the linked list of vertices adjacent to the i-th vertex. This
representation can also be used to represent a weighted graph. The weights of edges can be stored in 
nodes of linked lists. Following is adjacency list representation of the above graph.

![adjacency-list-example]()

Below is the java code for adjacency list representation of an undirected graph:

```java
import java.util.LinkedList ;

public class GraphDemo {
	// A user define class to represent a graph.
	// A graph is an array of adjacency lists.
	// Size of array will be V (number of the vertices in graph).
	static class graph {
		int V ;
		LinkedList<Integer> adjListArray[] ;
		
		// consttuctor
		Graph(int V) {
			this.V = V ;
			// define the size of array as number of vertices
			this.adjListArray = new LinkedList[V] ;
			
			// Create a new list for each vertex
			// Suck that adjacent nodes can be stored
			for (int i=0; i < V; i++) {
				adjListArray[i] = new LinkedList<>() ;
			}
		}
		
		// Adds vertex to the graph
		/**
		*
		* @param index    the index of the array of the vertices
		*/
		static void addVertex(int index) {
			// if the index large than V, print error information to stderr
			// and return.
			if (index > this.V) {
				System.err.println("the index is greater than the size of the array of vertices") ;
				return ;
			}
			// Create a new list for the vertex
			// such that adjacent nodes can be stored.
			this.adjListArray[index] = new LinkedList<>() ;
		}
		// Adds an edge to an undirected graph
		// adds an edge from vertex src to vertex dest
		static void addEdge(Graph graph, int src, int dest) {
			// if the vertex not in the graph, print error information and return 
			if (graph.adjListArray[src] == null || graph.adjListArray[dest] == null) {
				System.err.println("There are vertices not in the graph") ;
				return ;
			}
			// Add an edge from src to dest.
			graph.adjListArray[src].addFirst(dest) ;
			// Since graph is undirected, add an edge to src also
			graph.adjListArray[dest].addFirst(src) ;
		}
		static void printGraph(Graph graph) {
			for (int v=0; v < graph.V; v++) {
				System.out.println("Adjacency list of vertex " + v) ;
				System.out.print("head") ;
				for (Integer pCrawl : graph.adjListArray[v]) {
					System.out.print(" -> "+pCrawl) ;
				}
				System.out.println("\n") ;
			}
		}
	}
	// Driver the program to test above functions
	public static void main(String args[]) {
		// create the graph given in above figure
		int V = 5 ;
		Graph graph = new Graph(V) ;
		addEdge(graph, 0, 1) ;
		addEdge(graph, 0, 4) ;
		addEdge(graph, 1, 2) ;
		addEdge(graph, 1, 3) ;
		addEdge(graph, 1, 4) ;
		addEdge(graph, 2, 3) ;
		addEdge(graph, 3, 4) ;
		
		// print the adjacency list representation of the above graph
		printGraph(graph) ;
	}
}
```

Output :
```txt
 Adjacency list of vertex 0
 head -> 4-> 1

 Adjacency list of vertex 1
 head -> 4-> 3-> 2-> 0

 Adjacency list of vertex 2
 head -> 3-> 1

 Adjacency list of vertex 3
 head -> 4-> 2-> 1

 Adjacency list of vertex 4
 head -> 3-> 1-> 0
```

- Pros: Saves space O(|V|+|E|) . In the worst case, there can be C(V, 2) number of edges in a graph thus consuming O(V^2) space. Adding a vertex is easier.

- Cons: Queries like whether there is an edge from vertex u to vertex v are not efficient and can be done O(V).
