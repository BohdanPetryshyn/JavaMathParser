package MathParser;

public class Value extends Token {

    private double value;
    private int length;

    Value(double value, int length){
        this.value = value;
        this.length = length;
        type = TokenType.VALUE;
    }

    @Override
    TokenType getType() {
        return type;
    }

    double getValue(){
        return value;
    }

    public int getLength() {
        return length;
    }
}
