package org.jetbrains.javasimilarfilesanalysis

object SimilarityEvaluator {
    fun calculateEuclidianDistance(vector1: ArrayList<Int>, vector2: ArrayList<Int>): Double {
        var sum = 0.0
        vector1.indices.forEach {
            sum += Math.pow(vector1[it].toDouble() - vector2[it].toDouble(), 2.0)
        }
        return Math.sqrt(sum)
    }

    fun evaluate(firstSet: NamedVectorList, secondSet: NamedVectorList): List<ArrayList<String>> {
        val vectorsVisited = mutableMapOf<Int, MutableSet<Int>>()
        val vectorsDistances = mutableListOf<ArrayList<String>>()

        firstSet.withIndex().forEach {
            val firstIndex = it.index
            val firstFilename = it.value.first
            val firstVector = it.value.second
            vectorsVisited[firstIndex] = mutableSetOf()
            secondSet.withIndex().forEach {
                val secondIndex = firstIndex + it.index
                val secondVisited = vectorsVisited[secondIndex]
                if (secondVisited == null || !secondVisited.contains(firstIndex)) {
                    val secondFilename = it.value.first
                    val secondVector = it.value.second
                    vectorsDistances.add(arrayListOf("$firstFilename:$secondFilename", calculateEuclidianDistance(firstVector, secondVector).toString()))
                    vectorsVisited[firstIndex]?.add(secondIndex)
                }
            }
        }

        return vectorsDistances.sortedWith(compareBy({ it[1].toDouble() }))
    }
}