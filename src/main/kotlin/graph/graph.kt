@file:Suppress("EXPERIMENTAL_FEATURE_WARNING")

package graph

inline class Vertex(val key: String) {
	override fun toString() = "V($key)"
}

class Edge(val first: Vertex, val second: Vertex, val weight: Float = 1.0f) {
	override fun toString() = "E($weight)<$first->$second>"
}

class Graph {
	val edges: Set<Edge> = mutableSetOf()
	val verts: Set<Vertex> = mutableSetOf()
	val terms: Set<Vertex> = mutableSetOf()

	override fun toString() = """
	|G(
	|	V: $verts
	|	T: $terms
	|	E: $edges
	|)
	""".trimMargin()

	fun addEdge(edge: Edge) {
		(verts as MutableSet)
		(edges as MutableSet)

		verts.add(edge.first)
		verts.add(edge.second)
		edges.add(edge)
	}

	fun addTerminal(term: Vertex) {
		(terms as MutableSet)
		//if (term in verts)
		terms.add(term)
	}

	// remove not exists terminals
	fun bake() {
		(terms as MutableSet)
		terms.removeIf { !verts.contains(it) }
	}
}

fun inputGraph(): Graph {
	val graph = Graph()

	println("enter uni-directional graph.graph edges:")
	while (true) {
		val edge = inputEdge() ?: break
		graph.addEdge(edge)
	}

	inputVertexes("enter terminal vertexes or enter to end:")
		?.forEach(graph::addTerminal)

	println("You input this graph.graph: ")
	println(graph)

	return graph
}

fun inputEdge(): Edge? {
	val verts = inputVertexes("Enter vertex 1 <space> vertex 2 [<space> weight] or enter to end: ")
		?: return null
	if (verts.size < 2) return null
	val edge = Edge(verts[0], verts[1], verts.getOrNull(2)?.key?.toFloatOrNull() ?: 1.0f)
	print("add edge: ")
	println(edge)
	return edge
}

fun inputVertexes(msg: String? = null): List<Vertex>? {
	if (msg != null) print(msg)
	val inp = readLine()?.trim() ?: return null
	return inp
		.split(" ")
		.filter(String::isNotBlank)
		.map(::Vertex)
}


