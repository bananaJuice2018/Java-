// Java program to illustrate the
// concept of Abstraction








abstract class Shape {
    String color;
    // An abstract function
    abstract void draw();

    // these are abstract methods
    abstract double area();
    public abstract String toString();

    // abstract class can have the constructor
    public Shape(String color)
    {
        System.out.println("Shape constructor called");
        this.color = color;
    }

    // this is a concrete method
    public String getColor() { return color; }
}



class Circle extends Shape {
    double radius;

    public Circle(String color, double radius)
    {
        // calling Shape constructor
        super(color);
        System.out.println("Circle constructor called");
        this.radius = radius;
    }

    @Override
    double area() {
        return Math.PI * Math.pow(radius, 2);
    }
}










public class Hello {
    public void sayHello(String name) {
        System.out.println("Hello " + name + "!");
    }

    public static void main(String[] args) {
       Hello hello = new Hello();
       hello.sayHello("Bob");
    }
}
