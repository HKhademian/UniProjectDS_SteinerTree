package tree

import graph.*
import tree.*
import java.lang.RuntimeException

/**
 * to convert a graph to a tree:
 * first it most has no loops, else it stack-overflowed
 * I pick first terminal else first vertex else there is no tree and throws err
 * then I start follow all edges to create tree
 */
fun Graph.toTree(startingVertex: Vertex? = null): Tree {

	val rootV: Vertex =
		(if (startingVertex in verts) startingVertex else null) // prefer startingVertex
			?: terms.firstOrNull()  // else first terminal
			?: verts.firstOrNull() // else first vertex
			?: throw RuntimeException("no starting vert") // else no possible solution

	fun parse(tree: Tree) {
		edges.asSequence()
			.map { if (it.first == tree.key) it.second else if (it.second == tree.key) it.first else null }
			.filterNotNull()
			.filterNot { it == tree.parent?.key }
			.map { Tree(it, tree) }
			.onEach { (tree.children as MutableList).add(it) }
			.toList()
			.forEach(::parse)
	}

	val root = Tree(rootV)
	parse(root)

	return root
}
