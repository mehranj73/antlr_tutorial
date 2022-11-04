package com.mehranj73.expression

import org.antlr.v4.runtime.*

class MyErrorListener() : BaseErrorListener() {

    companion object {
        var hasError = false
    }

    override fun syntaxError(
        recognizer: Recognizer<*, *>?,
        offendingSymbol: Any?,
        line: Int,
        charPositionInLine: Int,
        msg: String?,
        e: RecognitionException?
    ) {
        hasError = true

        var stack: List<String> = (recognizer as Parser).ruleInvocationStack
        stack = stack.reversed()
        System.err.println("Syntax Error!")
        System.err.println("Token \" ${(offendingSymbol as Token).text} \"\n (line: $line column: ${charPositionInLine + 1})\n: $msg")
        System.err.println("Rule Stack: $stack")

    }

}