package myjava.annotation.calcengine;

@CommandKeyword("pow")
public class PowerOf {
    public double calculate(double leftVal, double rightVal) {
        // formattedOutput = leftVal + " to the power of " + rightVal + " is " +
        // product;
        return Math.pow(leftVal, rightVal);
    }
}
