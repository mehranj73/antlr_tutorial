import com.mehranj73.ExprLexer
import com.mehranj73.ExprParser
import com.mehranj73.expression.AntlrToProgram
import com.mehranj73.expression.ExpressionProcessor
import com.mehranj73.expression.MyErrorListener
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream

fun main() {

    val text = """
        3 + 4
        y: INT = 5
        y + 5
    """
    val lexer = ExprLexer(CharStreams.fromString(text))
    val tokens = CommonTokenStream(lexer)
    val parser = ExprParser(tokens)
    parser.removeErrorListeners()
    parser.addErrorListener(MyErrorListener())

    val antlrAST = parser.prog()

    if (!MyErrorListener.hasError) {
        val progVisitor = AntlrToProgram()
        val prog = progVisitor.visit(antlrAST)

        if (progVisitor.semanticErrors.isEmpty()) {
            val ep = ExpressionProcessor(prog.expressions)
            ep.getEvaluationResults().forEach {
                println(it)
            }
        } else {
            progVisitor.semanticErrors.forEach {
                println(it)
            }
        }
    }

}
