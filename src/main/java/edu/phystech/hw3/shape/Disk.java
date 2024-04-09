package edu.phystech.hw3.shape;


public class Disk extends Shape {
    private double radius;

    public Disk(double radius) {
        this.radius = radius;
    }

    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }
}
