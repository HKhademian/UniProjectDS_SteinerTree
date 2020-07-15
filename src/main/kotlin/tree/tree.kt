package tree

import graph.Vertex

class Tree(val key: Vertex, var parent: Tree? = null) {
	val children: List<Tree> = mutableListOf()

	override fun toString() =
		write().toString()
}

val Tree.root: Tree get() = parent?.root ?: this

inline val Tree.hasChild get() = children.isNotEmpty()
inline val Tree.hasParent get() = parent != null
inline val Tree.isLeaf get() = !hasChild
inline val Tree.isRoot get() = !hasParent
val Tree.leaves: List<Tree>
	get() = if (isLeaf) listOf(this) else children.map { it.leaves }.flatten()


fun Tree.delete() {
	(parent?.children as? MutableList)?.remove(this)
	parent = null
}

fun Tree.write(
	buffer: StringBuffer = StringBuffer(),
	prefix: String = "",
	childrenPrefix: String = ""
): StringBuffer {
	buffer.append(prefix)
	buffer.append(key)
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
