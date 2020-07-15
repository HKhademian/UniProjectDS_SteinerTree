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
	Edge(v(first), v(second), weight.toFloat())

fun Graph.print() =
	println(this)
