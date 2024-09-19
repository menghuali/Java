package myjava.annotation.calcengine;

@CommandKeyword(value = "multi")
public class Multiplier implements MathProcessing {
    @Override
    public double doCalculation(double leftVal, double rightVal) {
        return leftVal * rightVal;
    }
}
