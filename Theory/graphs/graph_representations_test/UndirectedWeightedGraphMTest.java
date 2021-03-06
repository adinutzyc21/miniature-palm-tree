package graph_representations_test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import graph_representations_src.UndirectedWeightedGraphM;
import graph_util.Vertex;

public class UndirectedWeightedGraphMTest {
	// number of vertices
	int numVert = 5;
	// maximum number of vertices before resize
	int capacity = 6;

	// vertices in graph
	Vertex<Character>[] vert;

	UndirectedWeightedGraphM<Character> G;

	@SuppressWarnings("unchecked")
	@Before
	public void setUp() throws Exception {
		// vertices for the matrix graphs
		vert = new Vertex[numVert];
		// add a character label A-E
		for (int v = 0; v < numVert; v++)
			vert[v] = new Vertex<Character>((char) (v + 'A'));

		// undirected unweighted graph represented as an adjacency matrix
		G = new UndirectedWeightedGraphM<Character>(capacity, vert);
		G.addEdge(0, 1);
		G.addEdge(1, 2);
		G.addEdge(0, 3, 3);
		G.addEdge(3, 4, 2);
	}

	@Test
	public void testSetup() {
		assertEquals(capacity, G.getCapacity());
		assertEquals(numVert, G.getNumVertices());

		// vertex labels
		assertEquals('A', (char) G.getVertexLabel(0));
		assertEquals('B', (char) G.getVertexLabel(1));
		assertEquals('C', (char) G.getVertexLabel(2));
		assertEquals('D', (char) G.getVertexLabel(3));
		assertEquals('E', (char) G.getVertexLabel(4));

		assertNull(G.getVertexLabel(-1));
		assertNull(G.getVertexLabel(5));

		// test set up correctly
		assertTrue(G.isEdge(0, 1));
		assertTrue(G.isEdge(1, 0));

		assertFalse(G.isEdge(0, 2));

		assertTrue(G.isEdge(3, 4));
		assertTrue(G.isEdge(4, 3));

		assertFalse(G.isEdge(1, 3));

		// add edge
		G.addEdge(0, 2);
		assertTrue(G.isEdge(0, 2));
		assertTrue(G.isEdge(2, 0));

		// remove edge
		G.removeEdge(2, 0);
		assertFalse(G.isEdge(0, 2));
		assertFalse(G.isEdge(2, 0));

		// out of bounds vertices for isEdge
		assertFalse(G.isEdge(-1, 3));
		assertFalse(G.isEdge(1, 8));

		// get the weights
		assertEquals(0, G.getWeight(0, 1));
		assertEquals(0, G.getWeight(1, 0));
		assertEquals(3, G.getWeight(0, 3));
		assertEquals(2, G.getWeight(4, 3));

		// remove edge 0,1
		G.removeEdge(0, 1);
		assertEquals(Integer.MAX_VALUE, G.getWeight(1, 0));
		assertFalse(G.isEdge(0, 1));
	}
}
