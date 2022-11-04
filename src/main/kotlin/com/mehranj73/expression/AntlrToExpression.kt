package com.mehranj73.expression


import com.mehranj73.ExprBaseVisitor
import com.mehranj73.ExprParser
import com.mehranj73.expression.*
import com.mehranj73.expression.Number
import org.antlr.v4.runtime.Token


class AntlrToExpression(var semanticErrors: MutableList<String>) : ExprBaseVisitor<Expression>() {


    var vars: MutableList<String> = mutableListOf()

    override fun visitDeclaration(ctx: ExprParser.DeclarationContext?): Expression {
        val idToken = ctx!!.ID().symbol
        val line = idToken.line
        val column = idToken.charPositionInLine + 1
        val id: String = ctx.getChild(0).text
        if (vars.contains(id)) {
            semanticErrors.add("Error: variable $id already declared ($line, $column)")
        } else {
            vars.add(id)
        }
        val type: String = ctx.getChild(2).text
        val value: Int = ctx.NUM().text.toInt()
        return VariableDeclaration(id, type, value)


    }

    override fun visitMultiplication(ctx: ExprParser.MultiplicationContext?): Expression {
        val left: Expression = visit(ctx!!.getChild(0))
        val right: Expression = visit(ctx.getChild(2))
        return Multiplication(left, right)
    }

    override fun visitAddition(ctx: ExprParser.AdditionContext?): Expression {
        val left: Expression = visit(ctx!!.getChild(0))
        val right: Expression = visit(ctx.getChild(2))
        return Addition(left, right)

    }

    override fun visitVariable(ctx: ExprParser.VariableContext?): Expression {
        val idToken: Token = ctx!!.ID().symbol
        val column = idToken.charPositionInLine + 1
        val line = idToken.line

        val id = ctx.getChild(0).text
        if (!vars.contains(id)) {
            semanticErrors.add("Error: variable $id not declared ($line, $column)")
        }
        return Variable(id)

    }

    override fun visitNumber(ctx: ExprParser.NumberContext?): Expression {
        val numText = ctx?.getChild(0)?.text
        val num = numText?.toInt()
        return Number(num ?: 1)


    }
}