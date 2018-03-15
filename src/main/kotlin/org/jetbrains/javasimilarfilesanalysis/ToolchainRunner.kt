package org.jetbrains.javasimilarfilesanalysis

object ToolchainRunner {
    fun run(tmpDir: String, featuresFile: String, featuresConfigFile: String, sourcesSetNumber: Int) {
        val psiDir = "$tmpDir/psi/$sourcesSetNumber"
        val psiVectorsDir = "$tmpDir/psi_vectors/$sourcesSetNumber"
        val psiVectorsSparsedDir = "$tmpDir/psi_vectors_sparsed/$sourcesSetNumber"

        PythonRunner.run("ast-set2matrix",
                mapOf("s" to "asts2vectors", "i" to psiDir, "o" to psiVectorsDir, "-features_file" to featuresConfigFile))
        PythonRunner.run("ast-set2matrix",
                mapOf("s" to "sparse_transformation", "i" to psiVectorsDir, "o" to psiVectorsSparsedDir, "-all_features_file" to featuresFile))
    }
}