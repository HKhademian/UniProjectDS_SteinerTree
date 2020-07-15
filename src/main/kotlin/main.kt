import graph.inputGraph
import method1.method1 as method

fun main() {
	val input = inputGraph()
	method(input) { msg, obj ->
		println(msg)
		println(obj)
	}
	// println("result:")
	// println(result)
}
