import graph.weight
import puc.readGraph
import puc.writeGraph
import java.io.File
import kotlin.system.exitProcess
import method1.method1 as method

fun main(vararg args: String) {
	val path = args.getOrElse(0) {
		print("Enter .stp file path: ")
		readLine() ?: exitProcess(0)
	}
	val inFile = File(path)
	if (!inFile.exists()) {
		println("Invalid path")
		exitProcess(0)
	}

	return process(inFile)
}

fun process(inFile: File) {
	if (!inFile.isFile)
		return inFile.listFiles()?.forEach(::process) ?: Unit

	println("\n***************")

	// read graph
	val input = readGraph(inFile)

	// compute result
	val (result, kruskal) = method(input) // { msg, obj -> println("$msg:\n$obj") }
	println("\nkruskal:${kruskal.weight}\nCost: ${result.weight}\nEdges: ${result.edges.size}")

	// store result
	val outFile = File(inFile.parent, "${inFile.nameWithoutExtension}.out")
	println("write result to output file:\n${outFile.absolutePath}")
	writeGraph(outFile, result)
}
