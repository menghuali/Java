package myjava.generics;

import java.util.ArrayList;
import java.util.List;

public class WildcardSample {

    private interface Shape {
        double circumference();

        double area();
    }

    private static class Squar implements Shape {

        private int side;

        public Squar(int sideLength) {
            this.side = sideLength;
        }

        @Override
        public double circumference() {
            return side * 4;
        }

        @Override
        public double area() {
            return side * side;
        }

    }

    private static class Rectangle implements Shape {

        private int length;
        private int width;

        public Rectangle(int length, int width) {
            this.length = length;
            this.width = width;
        }

        @Override
        public double circumference() {
            return (length + width) * 2;
        }

        @Override
        public double area() {
            return length * width;
        }

    }

    private static class Circle implements Shape {

        private int radius;

        public Circle(int radius) {
            this.radius = radius;
        }

        @Override
        public double circumference() {
            return 2 * Math.PI * radius;
        }

        @Override
        public double area() {
            return Math.PI * radius * radius;
        }        
    }

    private static void printShape(List<? extends Shape> shapes) {
        for (Shape shape : shapes) {
            System.out.printf("{circumference:%s, area:%s}, ", shape.circumference(), shape.area());
        }
        System.err.println();
    }

    public static void main(String[] args) {
        List<Squar> squars = new ArrayList<>();
        squars.add(new Squar(2)); 
        squars.add(new Squar(3)); 
        printShape(squars);

        List<Rectangle> rectangles = new ArrayList<>();
        rectangles.add(new Rectangle(2, 3));
        rectangles.add(new Rectangle(4, 5));
        printShape(rectangles);

        List<Circle> circles = new ArrayList<>();
        circles.add(new Circle(2));
        circles.add(new Circle(3));
        printShape(circles);
    }

}
