package myjava.lambda;

import java.util.Random;
import java.util.function.DoubleSupplier;
import java.util.function.IntSupplier;

public class PrimitiveLambdas {

    private final static Random R = new Random();

    public static void main(String[] args) {
        IntSupplier is = () -> R.nextInt();
        System.out.println(is.getAsInt());
        
        DoubleSupplier ds = R::nextDouble;
        System.err.println(ds.getAsDouble());
    }

}
