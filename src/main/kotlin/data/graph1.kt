package data

import graph.e
import graph.graph
import graph.t
import graph.w

// graph.graph from https://homepage.univie.ac.at/ivana.ljubic/research/pcstp/
val graph01 = graph {
	t("A").let {
		e(it to "B" w 40)
		e(it to "x1" w 10)
	}

	t("B").let {
		e(it to "x1" w 1)
		e(it to "x2" w 1)
		e(it to "x3" w 1)
		e(it to "x4" w 10)
	}

	t("C").let {
		e(it to "x5" w 1)
		e(it to "x2" w 10)
	}

	t("D").let {
		e(it to "x2" w 10)
		e(it to "x3" w 10)
	}

	t("E").let {
		e(it to "x3" w 100)
		e(it to "x4" w 100)
	}

	e("x1" to "x2" w 10)
}
