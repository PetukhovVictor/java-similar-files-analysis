package org.jetbrains.javasimilarfilesanalysis

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import java.io.File

typealias NamedVectorList = ArrayList<Pair<String, ArrayList<Int>>>

class JsonVectorsLoader {
    companion object {
        private fun readFile(file: File): String {
            return file.readText()
        }

        private fun walkDirectory(dirPath: String, endsWith: String, callback: (String, String) -> Unit) {
            File(dirPath).walkTopDown().forEach {
                if (it.isFile && it.name.endsWith(endsWith)) {
                    callback(JsonVectorsLoader.readFile(it), it.relativeTo(File(dirPath)).toString())
                }
            }
        }

        private fun assembleVectorsSet(folder: String, vectorsSet: NamedVectorList): NamedVectorList {
            JsonVectorsLoader.walkDirectory(folder, "java.json") { content: String, filename: String ->
                vectorsSet.add(Pair(filename, ObjectMapper().readValue(content)))
            }

            return vectorsSet
        }

        fun load(folder1: String, folder2: String): Pair<NamedVectorList, NamedVectorList> {
            val vectorsSet1 = assembleVectorsSet(folder1, arrayListOf())
            val vectorsSet2 = assembleVectorsSet(folder2, arrayListOf())

            return Pair(vectorsSet1, vectorsSet2)
        }
    }
}