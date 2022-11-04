package com.mehranj73.expression

import com.mehranj73.ExprBaseVisitor
import com.mehranj73.ExprParser

class AntlrToProgram() : ExprBaseVisitor<Program>() {

    lateinit var semanticErrors: MutableList<String>

    override fun visitProgram(ctx: ExprParser.ProgramContext?): Program {
        val prog = Program()

        semanticErrors = mutableListOf()
        val exprVisitor = AntlrToExpression(semanticErrors)

        var i = 0
        while (i < ctx!!.childCount) {
            if (i == ctx.childCount - 1) {

            } else {
                prog.addExpression(exprVisitor.visit(ctx.getChild(i)))
            }

            i++
        }

        return prog
    }

}