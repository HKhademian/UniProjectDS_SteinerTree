package graph

fun graph(init: Graph.() -> Unit = {}) =
	Graph().apply(init).also(Graph::bake)

fun Graph.t(key: Any) =
	v(key).also(this::addTerminal)

fun Graph.e(edge: Edge) =
	addEdge(edge)

fun v(key: Any) = when (key) {
	is Vertex -> key
	is String -> Vertex(key)
	else -> Vertex(key.toString())
}

infix fun Pair<Any, Any>.w(weight: Number) =
	Edge(v(first), v(second), weight.toDouble())

fun Graph.print() =
	println(this)

fun Vertex.clone() =
	this // Vertex(ket)

fun Edge.clone() =
	this // Edge(first, second, weight)

fun Graph.clone() = let { me ->
	graph {
		me.edges.forEach { addEdge(it.clone()) }
		me.terms.forEach { addTerminal(it.clone()) }
	}
}

val Graph.weight
	get() = edges.sumByDouble { it.weight }
