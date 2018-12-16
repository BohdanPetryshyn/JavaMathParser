package MathParser;

public enum TokenType {
    VALUE(0),
    OPERATOR_PLUS(1),
    OPERATOR_MINUS(1),
    OPERATOR_MUL(2),
    OPERATOR_DIV(2),
    OPERATOR_POW(3),
    OPERATOR_LEFT_BRACKET(0),
    OPERATOR_RIGHT_BRACKET(100),
    OPERATOR_END(100);

    private int priority;
    TokenType(int priority){
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }
}
