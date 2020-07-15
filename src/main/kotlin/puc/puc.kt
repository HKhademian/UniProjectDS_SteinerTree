package puc

import graph.*
import java.io.File

fun readGraph(path: String) =
	readGraph(File(path))

fun readGraph(file: File) =
	graph {
		file.bufferedReader().use { buffer ->
			while (!"SECTION Graph".contentEquals(buffer.readLine())); // skip to edges

			//val nodeCount = buffer.readLine().split(" ")[1].toInt()
			buffer.readLine() // skip nodeCount

			val edgeCount = buffer.readLine().split(" ")[1].toInt()
			(0 until edgeCount).forEach { _ ->
				val line = buffer.readLine().split(" ")
				e(line[1] to line[2] w line[3].toDouble())
			}

			while (!"Section Terminals".contentEquals(buffer.readLine())); // skip to terminals

			val terminalCount = buffer.readLine().split(" ")[1].toInt()
			(0 until terminalCount).forEach { _ ->
				val line = buffer.readLine().split(" ")
				t(line[1])
			}

			Unit
		}
	}

fun writeGraph(file: File, graph: Graph) =
	file.bufferedWriter().use { buffer ->
		buffer.write("Cost ${graph.weight}")
		buffer.newLine()

		buffer.write("Edges ${graph.edges.size}")
		buffer.newLine()

		graph.edges.forEach {
			buffer.write("E ${it.first.key} ${it.second.key}")
			buffer.newLine()
		}

		Unit
	}
