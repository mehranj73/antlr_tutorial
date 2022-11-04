package com.mehranj73.expression

data class Number(val num: Int): Expression() {

    override fun toString(): String {
        return num.toString()
    }
}