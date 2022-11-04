package com.mehranj73.expression



data class Variable(val id: String): Expression(){

    override fun toString(): String {
        return id
    }
}