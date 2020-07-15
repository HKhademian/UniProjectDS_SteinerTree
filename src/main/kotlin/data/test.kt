package data

import graph.e
import graph.graph
import graph.t
import graph.w

val test1 = graph {
	e("a" to "b" w 1)
	e("a" to "c" w 2)

	t("a")
}
