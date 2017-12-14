---
author: rovo98
description: note, leaning from geeksforgeeks
---

# Breath First Traversal or BFS for a Graph

### introduction

  Breath First Traversal(or Search) for a graph is similar to Breath First Traversal
of a tree. **The only catch here is, unlike trees, graphs may contain cycles, so we 
may come to the same node again.** To avoid processing a node more than once, we 
use a boolean visited array. For simplicity, it is assumed that all vertices are 
reachable from the starting vertex.

  For example, in the following graph, we start travelsal vertex 2. When we come to 
vertex 0, we look for all adjacent vertices of it. 2 is also an adjacent vertex of 0.
if we don't visited vertices, then 2 will be processed again and it will become a 
non-terminating process. A Breath Traversal of the following graph is 2, 0, 3, 1.

![Breath-First-Traversal-example]()

### Java implementation of simple Breath First Traversal from a given source.

  LinkedList is used to store lists of adjacent nodes and queue of nodes needed for BFS traversal.
  
```java
// Java program to print BFS traversal from a given source vertex.
// BFS(int startNode) traverses vertices reachable from startNode.
import java.util.* ;
  
// This class represents a directed graph using adjacency list
// representation
  
class Graph {
    private int V ; // the num of the vertices
	private LinkedList<Integer> adjListArray[] ; // adjacency lists
	
	// constructor
	public Graph(int V) {
		this.V = V ;
		this.adjListArray = new LinkedList[V] ;
		for (int i=0; i < V; i++) {
			adjListArray[i] = new LinkedList<>() ;
		}
	}
	// Function to add an edge into the graph 
	public void addEdge(int src, int dest) {
		// add an edge from vertex src to vertex dest into the directed graph
		adjListArray[src].add(dest) ;
	}
	// print BFS travel from a given source startNode
	public void BFS(int startNode) {
		// Marked all vertices as not visited(By default, set as false)
		boolean visited[] = new boolean[this.V] ;
		
		// Create a queue for BFS
		LinkedList<Integer> queue = new LinkedList<Integer>() ;
		
		// Marked the current node as visited and enqueue it.
		visited[startNode] = true ;
		queue.addLast(startNode) ;
		
		while (queue.size != 0) {
			// Dequeue a vertex from queue and print it
			startNode = queue.removeFirst() ;
			System.out.print(startNode+" -> ") ;
			
			// Get all adjacent vertices of the dequeued vertex startNode
			// if a adjacent has not been visited then mark it and enqueue it.
			Iterator<Integer> it = adjListArray[startNode].listIterator() ;
			while (it.hasNext()) {
				int next = it.next() ;
				if (!visited[next]) {
					visited[next] = true ;
					queue.addLast(next) ;
				}
			}
		}
	}
	// Driver the program to test the BFS function
	public static void main(String args[]) {
		Graph g = new Graph() ;
		g.addEdge(0,1) ;
		g.addEdge(0,2) ;
		g.addEdge(1,2) ;
		g.addEdge(2,0) ;
		g.addEdge(2,3) ;
		g.addEdge(3,3) ;
		
		System.out.println("Following is Breath First Traversal(starting from vertex 2)") ;
		
		g.BFS(2) ;
	}
}
```

Output :
```txt
Following is Breath First Traversal(starting from vertex 2)
2 -> 0 -> 3 -> 1
```
  
### Noted
  
    Note that above code traversal only the vertices reachable from a given source vertex.
All vertices may not be reachable from a given vertex(example Disconnected graph). To print
all vertices, we can modify the BFS function to do travel starting from all nodes one by one

Time Complexity: O(V+E) where V is number of vertices in the graph and E is number of edge of the graph.
