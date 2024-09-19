package myjava.annotation.calcengine;

@CommandKeyword(value = "sub")
public class Subtracter implements MathProcessing {
    @Override
    public double doCalculation(double leftVal, double rightVal) {
        return leftVal - rightVal;
    }
}
