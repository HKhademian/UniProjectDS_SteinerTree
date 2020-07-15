package method1

import data.graph01
import graph.*
import tree.*

/**
 * in this method, as text of project said:
 *
 * first `method1.kruskal` it
 *
 * second remove extra edges:
 * * first convert it to a simple tree
 * * second remove non terminal leafs
 * * repeat second till no actions preformed
 *
 * third it's result of method1
 *
 * -----
 * COST:
 * t<- term count
 * n<-vert count
 * m<-edge count
 * 1. kruskal with path comp = m
 * 2. graph to tree = O(n^2)
 * 3. delete non-term leafs = O(n)*O(t*logt)
 */
fun method1(input: Graph, log: (msg: String, data: Any?) -> Unit = { _, _ -> }): Pair<Graph, Graph> {
	log("------\ninput:", input)

	val k = kruskal(input)
	log("-------\nkruskal graph:", k)

	val t = k.toTree()
	log("-----\nkruskal tree:", t)

	val result = k.clone()

	while (true) {
		val leaves = t.leaves.filterNot { it.key in k.terms }
		if (leaves.isEmpty()) break
		leaves.forEach { tree ->
			tree.delete() // delete from tree
			(result.edges as MutableSet).removeIf { it.first == tree.key || it.second == tree.key }
			(result.verts as MutableSet).removeIf { it == tree.key }
		}
		// log("******\nrem.leaf:", t)
	}
	log("---------\nmin tree:", t)

	log("-------\nmin graph:", result)

	return result to k
}

fun main() {
	method1(graph01) { msg, obj ->
		println(msg)
		println(obj)
	}
}
