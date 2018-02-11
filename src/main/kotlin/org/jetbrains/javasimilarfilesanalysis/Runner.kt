package org.jetbrains.javasimilarfilesanalysis

import org.jetbrains.java2psi.Runner
import org.json.JSONArray
import java.io.File

class Runner {
    companion object {
        fun run(sourcesDir1: String, sourcesDir2: String) {
            val tmpDataDir = "./data"
            val psiDir = "$tmpDataDir/psi"
            val psiVectorsSparsed = "$tmpDataDir/psi_vectors_sparsed"
            val featuresFile = "$tmpDataDir/features.json"
            val featuresConfigFile = "$tmpDataDir/features_config.json"
            val similarityOutputFile = "./similarities.json"

            Runner.run(sourcesDir1, "$psiDir/1")
            Runner.run(sourcesDir2, "$psiDir/2")

            PythonRunner.run("ast-ngram-extractor", mapOf("i" to psiDir, "o" to featuresFile))
            FeaturesConfigurator.createConfiguration(featuresFile, featuresConfigFile)

            ToolchainRunner.run(tmpDataDir, featuresFile, featuresConfigFile, 1)
            ToolchainRunner.run(tmpDataDir, featuresFile, featuresConfigFile, 2)

            val vectorsSets = JsonVectorsLoader.load("$psiVectorsSparsed/1", "$psiVectorsSparsed/2")
            val similarityList = SimilarityEvaluator.evaluate(vectorsSets.first, vectorsSets.second)

            File(similarityOutputFile).writeText(JSONArray(similarityList).toString())
            File(tmpDataDir).deleteRecursively()
        }
    }
}