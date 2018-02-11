# java-similar-files-analysis

Java source codes similar analysis

## Program use

You run the program on the two folders with java source code files which you want to compare to similarities. The program analyzes only files with the `java` extension.

The program use PSI representation source code (parsing using [java2psi](https://github.com/PetukhovVictor/java2psi)).

Also the program use [ast-ngram-extractor](https://github.com/PetukhovVictor/ast-ngram-extractor) to generating n-grams by psi and [ast-set2matrix](https://github.com/PetukhovVictor/ast-set2matrix) to transform psi to vectors via generated n-grams.

At the output, the program will generate a ranked similarity set of pairs of files.

### Program arguments

* `--sources_1`: path to first folder with java source code files;
* `--sources_2`: path to second folder with java source code files.

### How to run

To run program you must run `main` function in `main.kt`, not forgetting to set the program arguments.

Also you run jar downloading it from the [release files](https://github.com/PetukhovVictor/java-similar-files-analysis/releases).
