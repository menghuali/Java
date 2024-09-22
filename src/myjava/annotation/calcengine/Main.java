package myjava.annotation.calcengine;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

public class Main {
    private static Object[] processors = new Object[] { new Adder(), new Subtracter(), new Multiplier(),
            new Divider(), new PowerOf() };

    public static void main(String[] args) {
        System.out.println("Enter an operation and two numbers:");
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();

        String[] parts = userInput.split(" ");
        String keyword = parts[0];
        double leftVal = valueFromWord(parts[1]);
        double rightVal = valueFromWord(parts[2]);

        process(keyword, leftVal, rightVal);
        scanner.close();
    }

    private static void process(String keyword, double leftVal, double rightVal) {
        Object processor = retrieveProcessor(keyword);
        double result = 0d;
        if (processor instanceof MathProcessing)
            result = ((MathProcessing) processor).doCalculation(leftVal, rightVal);
        else {
            result = handleCalculation(processor, leftVal, rightVal);
        }
        System.out.println("result = " + result);
    }

    private static double handleCalculation(Object processor, double leftVal, double rightVal) {
        double result = 0d;
        try {
            CommandKeyword cmdKwd = processor.getClass().getAnnotation(CommandKeyword.class);
            Method method = processor.getClass().getMethod(cmdKwd.method(), double.class, double.class);
            result = (double) method.invoke(processor, leftVal, rightVal);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException("Cannot invoke the processor method.", e);
        }
        return result;
    }

    private static Object retrieveProcessor(String keyword) {
        // switch (keyword) {
        // case "add":
        // return new Adder();
        // case "sub":
        // return new Subtracter();
        // case "multi":
        // return new Multiplier();
        // case "div":
        // return new Divider();
        // }

        // Replace above switch with annotation.
        for (Object processor : processors) {
            if (keyword.equals(processor.getClass().getAnnotation(CommandKeyword.class).value())) {
                return processor;
            }
        }
        return null;

    }

    static double valueFromWord(String word) {
        String[] numberWords = {
                "zero", "one", "two", "three", "four",
                "five", "six", "seven", "eight", "nine"
        };
        double value = -1d;
        for (int index = 0; index < numberWords.length; index++) {
            if (word.equals(numberWords[index])) {
                value = index;
                break;
            }
        }
        if (value == -1d)
            value = Double.parseDouble(word);

        return value;
    }

}
