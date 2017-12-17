---
author: rovo98
description: note, learning from geeksforgeeks
---

# Disjoin Set (Or Union-Find) 

  Detect Cycle in an Undirected Graph
  

### introduction

  A disjoint-set data structure is a data structure that keeps track of a set of elements
partitioned into a number of disjoin (non-overlapping) subsets. A union-find algorithm is 
an algorithm that performs two useful operations on such a data structure:

- **Find**: Determine which subset a particular element is in. This can be used for determing
if two elements are in the same subset.

- **Union**: Join two subsets into a single subset.


  We will discuss an application of Disjoint Set Data Structure. The application
is to check whether a given graph contains a cycle or not.

**Union-Find Algorithm** can be used to check whether an undirected graph contains
cycle or not. Note that we have discussed an algorithm to detect cycle. This is another
method based on Union-Find. **This method assumes that graph doesn't contains any self-loops**.

We can keeps track of the subsets in a 1D array, lets call it parent[].

Let us consider the following graph:

![detect-cycle-graph-example](https://github.com/rovo98/java-learning/blob/master/images/DS/Graph/Cycle-in-graph.png)

For each edge, make subsets using both the vertices of the edge. If both the 
vertices are in the same subset, a cycle is found.

Initially, all slots of parent array are initialized to -1 (means there is only one
item in every subset).

```txt
0  1  2
-1 -1 -1
```

Now process all edges one by one.

**Edge 0-1**: Find the subsets in which vertices 0 and 1 are. Since they are in 
different subsets, we take the union of them. For taking the union, either make 
node 0 as parent of node 1 or vice-versa.

```txt
0  1  2        <-------- 1 is made parent of 0 (1 is now representative of subset {0,1}
1  -1 -1
```

**Edge: 1-2**: 1 is in the subset 1 and 2 is in the subset 2. So, take union.

```txt
0  1  2       <--------- 2 is made parent of 1 (2 is now representative of subset {0, 1, 2}
1  2  -1
```

**Edge: 0-2**: 0 is in the subset 2 and 2 is in the subset 2 too. Hence, includeing this edge 
forms a cycle.

How subset of 0 is same as 2?
0->1->2 // 1 is parent of 0 and 2 is parent of 1


### Java implementation of above algorithm

```java
import java.util.* ;
import java.lang.* ;

class Graph {
	int V ;       //the number of the vertices
	int E ;       //the number of the edges
	Edge edge[] ; //collection of all edges
	
	class Edge {
		int src, dest ;
	};
	
	// Create a graph with V vertices and E edges
	public Graph(int V, int E) {
		this.V = V ;
		this.E = E ;
		edge = new Edge[E] ;
		for (int i=0; i<E; i++) {
			edge[i] = new Edge() ;
		}
	}
	
	// A utility function to find the subset of an element
	public int find(int[] parent, int i) {
		if (parent[i] == -1) {
			return i ;
		}
		return find(parent, parent[i]) ;
	}
	// A utility function to union two subsets
	public void union(int[] parent, int x, int y) {
		// find the representative of the subset that x/y in
		int xset = find(parent, x) ;
		int yset = find(parent, y) ;
		parent[xset] = yset ;  // or parent[yset] = xset
	}
	public boolean isCycle(Graph graph) {
		// Allocate memory for create V subsets
		int parent[] = new int[V] ;
		
		// Initialize all subsets as single element set
		for (int i=0; i<graph.V; i++) { 
			// -1 means the representative of the subset is itsself
			parent[i] = -1 ;
		}
		
		// Iterate through all edges of graph, find out subset of both vertices 
		// of every edge, if both subsets are same, then there is cycle in graph.
		for (int i=0; i<graph.E; i++) {
			int x = graph.find(parent, x) ;
			int y = graph.find(parent, y) ;
			if (x == y) {
				return true ;
			}
			graph.union(parent, x, y) ;
		}
		return false ;
	}
	// Driver the program to test above functions
	public static void main(String[] args) {
		/* Create the graph above
		0
		| \
		|  \
		|   \
		1----2 */
		int V = 3, E = 3 ;
		Graph graph = new Graph(V,E) ;
		
		// add edge 0-1
		graph.edge[0].src = 0 ;
		graph.edge[0].dest = 1 ;
		
		// add edge 0-2
		graph.edge[1].src = 0 ;
		graph.edge[1].dest = 2 ;
		
		// add edge 1-2
		graph.edge[2].src = 1 ;
		graph.edge[2].dest = 2 ;
		
		if (graph.isCycle(graph)) {
			System.out.println("graph contains cycle") ;
		}
		else {
			System.out.println("graph doesn't contains cycle") ;
		}
	}
}
```

Output :

```txt
graph contains cycle
```

Note that the implementation of union() and find() is navie and takes O(n) time
in worst case. These method can be improved to O(logn) using **Union by Rank or 
Height**.

