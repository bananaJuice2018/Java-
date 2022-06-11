## Java面试 OOP


### 4. Abstraction ###

**重点!**: 
Abstraction is a process of hiding the implementation details from the user, only the functionality will be provided to the user. 

Ex: A car is viewed as a car rather than its individual components.
Consider a real-life example of a man driving a car. The man only knows that pressing the accelerators will increase the speed of a car or applying brakes will stop the car, but he does not know how on pressing the accelerator the speed is actually increasing, he does not know about the inner mechanism of the car or the implementation of the accelerator, brakes, etc in the car. This is what abstraction is.

Abstraction is detailed hiding(implementation hiding). It can avoids code duplication and increases reusability.
Abstraction is implemented using an abstract class and interface.


**Abstract Class:**

NOTE: An abstract class can not be directly instantiated with the new operator.

```java
// Java program to illustrate the
// concept of Abstraction
abstract class Shape {
    String color;

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

    @Override double area()
    {
        return Math.PI * Math.pow(radius, 2);
    }

    @Override public String toString()
    {
        return "Circle color is " + super.getColor()
                + "and area is : " + area();
    }
}
class Rectangle extends Shape {

    double length;
    double width;

    public Rectangle(String color, double length,
                     double width)
    {
        // calling Shape constructor
        super(color);
        System.out.println("Rectangle constructor called");
        this.length = length;
        this.width = width;
    }

    @Override double area() { return length * width; }

    @Override public String toString()
    {
        return "Rectangle color is " + super.getColor()
                + "and area is : " + area();
    }
}
public class Test {
    public static void main(String[] args)
    {
        Shape s1 = new Circle("Red", 2.2);
        Shape s2 = new Rectangle("Yellow", 2, 4);

        System.out.println(s1.toString());
        System.out.println(s2.toString());
    }
}

```

**Abstract method:**

An abstract method is a method that is declared without implementation.


**Interface:**

**Interface VS Abstract Class:**
1. Abstract class can have abstract and non-abstract methods. Interface can have only abstract methods.


2. Abstract class doesn't support multiple inheritance. Interface supports multiple inheritance.


3. An abstract class can have parameterized constructors and the default constructor is always present in an abstract class.





















### Four types of OOP

### 1. Polymorphism ###

Method overriding ->  Polymorphism

Overrides/overloading is part of polymorphism.
https://www.geeksforgeeks.org/object-oriented-programming-oops-concept-in-java/

**Overrides**
In Java, method overriding occurs when a subclass (child class) has the same method as the parent class. In other words, method overriding occurs when a subclass provides a particular implementation of a method declared by one of its parent classes. The ability for a subclass to override a method allows a class to inherit from a superclass with "near enough" actions and then change it as required.

In IntellJ, using the shortcut Ctrl-O will prompt you to select functions you can override from inherited classes.  The list will include any overridable functions including:
* Public or Protected Functions (unless they are marked final)
* Functions in interfaces

```java
class Vehicle {
    void engine() {
        System.out.println("This is a vehicle engine.");
    }
}

// Child Class
class Bike extends Vehicle {
    // Create a method as same as in parent class
    // But changing the output as bike engine
    void engine() {
        System.out.println("This is a bike engine.");
    }
}

// Chill Class
class Car extends Vehicle {
    // Create a method as same as in parent class
    // But changing the output as car engine
    void engine() {
        System.out.println("This is a car engine.");
    }
}

    public class CodeExample {
        public static void main(String[] arg) {
            Bike honda = new Bike(); // Create object for bike
            honda.engine(); // Calling engine method
    
            Car benz = new Car();
            benz.engine();
        }
    }
```






###2. Inheritance ###

Interfaces in Java are a form of class that can be inherited by other classes and interfaces.  In Java, you can only inherit (extends) one class but you can inherit (implements) multiple interfaces.

An interface contains function declarations only (no implementation).  This is just like pure virtual functions in C++.  Here is an example of an interface in Java and in C++:

```java
public interface Messenger {
    public int sendMessage(String message);
    public int receiveMessage(String message);
}

class Messenger
{
    public virtual int sendMessage(String message) = 0;
    public virtual int receiveMessage(String message) = 0;
}
```

Use interfaces in the following cases:
1) You need to do multiple inheritance
2) You have a common interface that needs to be implemented by un-related classes (they don't have the same base class).  This is the most common reason for creating an interface.

If you have dervied classes from a common base class but only some of those derived classes share a common interface.  The use of the `instanceof` operator can help you determine if an object of the base class implemented those interfaces.  Note that `null instanceof MyClass` will always be false.


###3. Encapsulation ###
1. Making the member variable or member methods of a class `private`.

2. public `Getter` and S`etter` to access the data. 

**Advantages of Encapsulation**:

* Data Hiding: The user will have no idea about the inner implementation of the class. It will not be visible to the user how the class is storing values in the variables.

* Increased Flexibility: We can make the variables of the class read-only or write-only depending on our requirement.

