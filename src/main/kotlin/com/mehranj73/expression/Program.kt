package com.mehranj73.expression



class Program(var expressions: MutableList<Expression> = mutableListOf()) {


    fun addExpression(e: Expression) {
        expressions.add(e)
    }

}