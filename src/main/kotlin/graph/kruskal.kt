package graph

fun kruskal(input: Graph): Graph {
	// list verts to preform kruskal
	val verts = input.verts.toList()
	// at first each vert is in a separate union with size 1
	val unions = verts.map { it }.toMutableList()
	val sizes = unions.map { 1 }.toMutableList()
	// edges to iterate throw
	val edges = input.edges.sortedBy { it.weight }.asSequence()

	// fin union of given vertex plus compress path
	fun find(v: Vertex): Vertex {
		val i = verts.indexOf(v)
		if (unions[i] != v)
			unions[i] = find(unions[i])
		return unions[i]
	}

	// joins two union to one
	fun union(v1: Vertex, v2: Vertex) {
		val (i1, i2) = verts.indexOf(v1) to verts.indexOf(v2)
		if (sizes[i2] > sizes[i1]) return union(v2, v1)
		val (p1, p2) = find(v1) to find(v2)
		val (pi1, pi2) = verts.indexOf(p1) to verts.indexOf(p2)
		unions[pi2] = unions[pi1]
		sizes[pi1] += sizes[pi2]
	}

	// result graph
	val result = Graph()

	edges
		.filterNot { find(it.first) == find(it.second) } // filter loops
		.forEach {
			union(it.first, it.second) // join two unions of edge verts
			result.addEdge(it) // add that edge to result graph
		}

	// result graph has same terminals
	input.terms.forEach(result::addTerminal)

	// quick fix graph
	result.bake()

	return result
}
