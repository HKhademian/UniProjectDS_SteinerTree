package method1

import data.graph01
import graph.*

/**
 * in this method, as text of project said:
 * first `method1.kruskal` it
 * second remove extra edges
 */

fun main() {
	val input = graph01

	val result = method1(input)
	input.print()
	result.print()
}

fun method1(input: Graph): Graph {
	val k = kruskal(input)
	return k
}

fun kruskal(input: Graph): Graph {
	val verts = input.verts.toList()
	val unions = verts.map { it }.toMutableList()
	val sizes = unions.map { 1 }.toMutableList()
	val edges = input.edges.sortedBy { it.weight }.asSequence()

	fun find(v: Vertex): Vertex {
		val i = verts.indexOf(v)
		if (unions[i] != v)
			unions[i] = find(unions[i])
		return unions[i]
	}

	fun union(v1: Vertex, v2: Vertex) {
		val (i1, i2) = verts.indexOf(v1) to verts.indexOf(v2)
		if (sizes[i2] > sizes[i1]) return union(v2, v1)
		val (p1, p2) = find(v1) to find(v2)
		val (pi1, pi2) = verts.indexOf(p1) to verts.indexOf(p2)
		unions[pi2] = unions[pi1]
		sizes[pi1] += sizes[pi2]
	}

	val result = Graph()
	edges
		.filterNot { find(it.first) == find(it.second) } // filter loops
		.forEach {
			union(it.first, it.second)
			result.addEdge(it)
		}
	input.terms.forEach(result::addTerminal)
	result.bake()

	return result
}
