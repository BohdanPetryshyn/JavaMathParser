package MathParser;

public abstract class Token {
    protected TokenType type;

    abstract TokenType getType();
}
