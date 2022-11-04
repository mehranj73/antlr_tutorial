package com.mehranj73.expression

class ExpressionProcessor(val list: MutableList<Expression>, ){
    val values: HashMap<String, Int> = hashMapOf()

    fun getEvaluationResults(): MutableList<String> {
        val evaluations = mutableListOf<String>()

        list.forEach{e ->
            if (e is VariableDeclaration){
                val decl: VariableDeclaration = e
                values[decl.id] = decl.value
            }
            else {
                val input = e.toString()
                val result = getEvalResults(e)
                evaluations.add("$input is $result")
            }

        }
            return evaluations

    }

    private fun getEvalResults(e: Expression): Int {
        var result = 0
        if (e is Number){
            val num: Number = e
            result = num.num
        }
       else if (e is Variable){
            val vara: Variable = e
            result = values[vara.id]!!
        }
        else if(e is Addition){
            val add: Addition = e;
            val left = getEvalResults(add.left)
            val right = getEvalResults(add.right)
            result = left + right

        } else {
            val multiply: Multiplication = e as Multiplication;
            val left = getEvalResults(multiply.left)
            val right = getEvalResults(multiply.right)
            result = left * right
        }


        return result

    }

}