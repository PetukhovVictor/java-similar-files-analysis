package org.jetbrains.javasimilarfilesanalysis

import java.io.File
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.json.JSONArray
import org.json.JSONObject


class FeaturesConfigurator {
    companion object {
        private fun convertToArray(features: Map<String, Int>): ArrayList<JSONArray> {
            val featuresArray = arrayListOf<JSONArray>()

            features.map {
                featuresArray.add(JSONArray(it.key.split(':')))
            }

            return featuresArray
        }

        private fun wrap(features: JSONArray): JSONArray {
            val root = JSONArray()
            val allNgramConfigItem = JSONObject()

            allNgramConfigItem.put("type", "all_ngrams")
            allNgramConfigItem.put("params", JSONObject(
                    mapOf("n" to 3, "max_distance" to 3, "no_normalize" to true, "include_strict" to features))
            )

            root.put(allNgramConfigItem)

            return root
        }

        fun write(featuresConfigFile: String, configurationJson: JSONArray) {
            File(featuresConfigFile).writeText(configurationJson.toString())
        }

        fun createConfiguration(featuresFile: String, featuresConfigFile: String) {
            val objectMapper = ObjectMapper()
            val features = objectMapper.readValue<Map<String, Int>>(File(featuresFile))
            val arrayFeatures = JSONArray(convertToArray(features))
            val configurationJson = wrap(arrayFeatures)

            write(featuresConfigFile, configurationJson)
        }
    }
}