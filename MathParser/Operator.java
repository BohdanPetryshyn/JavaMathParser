package MathParser;

public class Operator extends Token {
    Operator(TokenType type){
        this.type = type;
    }

    @Override
    TokenType getType() {
        return type;
    }
}
