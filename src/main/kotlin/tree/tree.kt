package tree

import graph.Graph
import graph.Vertex
import sun.jvm.hotspot.oops.CellTypeState.value
import java.lang.RuntimeException

class Tree(val key: Vertex, val parent: Tree? = null) {
	val children: List<Tree> = mutableListOf()
}

val Tree.root: Tree get() = parent?.root ?: this

inline val Tree.hasChild get() = children.isNotEmpty()
inline val Tree.hasParent get() = parent != null
inline val Tree.isLeaf get() = !hasChild
inline val Tree.isRoot get() = !hasParent

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

fun Tree.write(
	buffer: StringBuffer = StringBuffer(),
	prefix: String = "",
	childrenPrefix: String = ""
): StringBuffer {
	buffer.append(prefix)
	buffer.append(value)
	buffer.append("\n")

	fun writeChild(child: Tree?, hasNext: Boolean, childrenPrefix: String): StringBuffer {
		val pref = childrenPrefix + if (hasNext) "├─── " else "└─── "
		return if (child != null) {
			val childPref = childrenPrefix + if (hasNext) "│    " else "     "
			child.write(buffer, pref, childPref)
		} else {
			buffer.append(pref)
			buffer.append("X")
			buffer.append('\n')
			buffer
		}
	}

	//children.drop(1).asReversed().forEach {
	//	writeChild(it, true, childrenPrefix)
	//}
	//if (children.isNotEmpty())
	//	writeChild(children.first(), false, childrenPrefix)

	children.dropLast(1).forEach {
		writeChild(it, true, childrenPrefix)
	}
	if (children.isNotEmpty())
		writeChild(children.last(), false, childrenPrefix)

	return buffer
}
