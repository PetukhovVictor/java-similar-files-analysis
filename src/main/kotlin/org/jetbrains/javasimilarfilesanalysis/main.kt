package org.jetbrains.javasimilarfilesanalysis

import com.xenomachina.argparser.ArgParser
import org.jetbrains.java2psi.Runner as Java2Psi

fun main(args : Array<String>) {
    val parser = ArgParser(args)
    val sourcesDir1 by parser.storing("--sources_1", help="path to first dir with java source code files to java2psi extraction")
    val sourcesDir2 by parser.storing("--sources_2", help="path to second dir with java source code files to java2psi extraction")

    Runner.run(sourcesDir1, sourcesDir2)
}
