package org.jetbrains.javasimilarfilesanalysis

import java.io.BufferedReader
import java.io.InputStreamReader

object PythonRunner {
    private const val interpreter = "python3"

    private fun getArgsStr(args: Map<String, String>): String {
        var argsStr = ""
        args.map { argsStr += " -${it.key} ${it.value}" }
        return argsStr
    }

    private fun printResult(process: Process) {
        val stdInput = BufferedReader(InputStreamReader(process.inputStream))
        var str = stdInput.readLine()

        while (str != null) {
            println(str)
            str = stdInput.readLine()
        }
    }

    fun run(libName: String, args: Map<String, String>) {
        val process = Runtime.getRuntime().exec("$interpreter ./libs/$libName/main.py${getArgsStr(args)}")
        printResult(process)
    }
}