package com.mehranj73.expression



class Multiplication(val left: Expression, val right: Expression) : Expression() {

    override fun toString(): String {
        return "$left * $right";
    }
}