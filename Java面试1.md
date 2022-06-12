## Java面试1

**Exception Handling**

When programming, you have to choose your error handling strategy:

* Return codes - you will need to identify error codes and add `if` blocks to handle the results.  It can be difficult sometimes to run the correct code under different error conditions that might happen at different times.
* Exception handling - you will need to identify exception classes and add `try` and `catch` blocks.  While more coding is required, exceptions will automatically route control of the software to the `catch` block which can make it easier to define what to do if errors occur during normal processing.  Responding to exceptions can occur at any place in the calling tree (e.g. a previous function call can handle the error).

Just like C++ and Python, Java provides exception handling.  You can throw an exception from a function and catch it in another function.  

```java
public void doSomething() throws Exception {
   // Do some stuff
   if (error) {
      throw new Exception("An Error Occurred");
   }
   // Do some other stuff
}
```

In the code above, notice that the exception is an object of an `Exception` class.  You can create your own exception classes (that can store additional data and provide additional functions) by extending the `Exception` class (inheriting).  Another benefit of creating a new `Exception` class is for readability in your code.  It becomes very clear when an `Exception` occurs what kind it was because you created a new class customized for that error.

```java
public class MyException extends Exception {
    public MyException(String errorMessage) {
        super(errorMessage);
        // I can have additional data initialized here
    }
}
```

In the example above, the constructor in `MyException` has to call the constructor in `Exception` explicitly.  The `super()` function will call the non-default constructor in the `Exception` class that takes a single `String`.  We can now modify the `doSomething` function to throw this new `MyException` as well:

```java
public void doSomething() throws Exception {
   // Do some stuff
   if (error) {
      throw new Exception("An Error Occurred");
   }
   else if (otherError) {
       throw new MyException("The Other Error Occurred")
   }
   // Do some other stuff
}
```

The calling function must catch the exception or pass it along to the next function.  To pass it along, the function declaration can just write `throws Exception`.  To catch it, you need to use the try/catch syntax.  The `try` block should be put around at least the code that an `throw` the exception.  Sometimes its better to put more code in the `try` block.  One approach is to say that everything in the `try` block represents the success case (which could be a lot of code).

```java
try
{
    doSomething();  // This function can throw an exception
    // Do some other code.
    // If exception is thrown in doSomething, then this other code won't run.
}
catch (MyException mye) {
    System.out.println("MyException Occurred!!! Details: " + mye.getMessage()); 
}
catch (Exception e) {
    // When you catch an exception, you can print it out like this:
    System.out.println("Exception Occurred!!!  Details: " + e.getMessage());
}
```

In the example above, notice that you can catch more than one `Exception`.  `MyException` was listed first since technically `MyException` "isa" an `Exception`.

In Java, there are 2 types of Exceptions.  If you throw `Exception` or if you use your own class that inherited from `Exception`, then this must be caught.  If you throw a `RuntimeException` or if you use your own class that inherited `RuntimeException`, then this exception does not have to be caught in the code.  If the `RuntimeException` occurs, then the program will still stop.  The most common `RuntimeException` that you will see is the `NullPointerException`.

**Variables in Java**

In Java, all variables (native types or objects of classes), must be declared before they are used.  You should ensure that your variables are initialized before use.  You can initialize a variable of a class type to null if you don't want to create the object yet.

```java
Product p = null;
// Do some code
p = new Product();
```

When you pass an object to a function via a parameter, the address (think pointer in C++) is passed by value.  This means that the function will have access to the object and therefore be able to modify it.  When you pass a variable of native type (eg., int, float, char, bool), it is passed by value.  A benefit of the pass by value system is you don't have to concern yourself with whether the function parameter can be modified or not.  In C++, you had to specify if an object was to be passed by value or reference.  If it was passed by value, then a copy was made.  You do not have to worry about this scenario in Java.

We can see an example of this in the `hashUserPassword` function.  Notice that the `User` object is passed in (address passed by value) and can be changed.  The salt and hash are set.  The password is also cleared `user.setPassword("");` When you return back to the `main` function, the `User` object reflects the changes that occurred in the function.

When you receive an object into a function, you should be careful to ensure that the object you received is not `null`.  When you see `NullPointerException` when you run your program, then either you forgot to create the object with a `new` or you forgot to check for `null`.

If a variable is equal to an object and then you set it equal to a different object, then Java will take care of deleting the old object when it determines it is no longer being used by anyone else.

When you create variables, you are encouraged to use the camelBack case (capitalize the first letter of each word except the first letter).  However, there is no mandatory rule that you have to follow.  The use of underscore is common in some of the examples especially from private data.  You are not expected to follow this pattern.

**Regular Expression**

If you look in the JavaDoc (JDK 10: https://docs.oracle.com/javase/10/docs/api/index.html?overview-summary.html) under the String class, you will a function called matches.  This function allows you to create regular expression statements that are used to match text you are searching for.  Regular expression makes it easier to match more complicated strings. 

In the teacher solution, it used: .*\\d+.*  This means match any string that has at least one digit (.* means match 0 or more of anything and \\d+ means match at least one (the +) digit (the \\d ... java requires an extra slash ... just like in C++).  This is a powerful technique to learn.  Here is a website to learn more about the syntax:  https://regexone.com/

There are multiple ways to use regular expression in Java.  The easiest is to use the matches function from the String class (https://docs.oracle.com/en/java/javase/12/docs/api/java.base/java/lang/String.html#matches(java.lang.String)).  

The other option (instead of regular expression since this was a simple test), is to loop through each character of the password and count the digits.

```java
boolean hasDigit = false;
for (int i=0; i<password.length; i++) {
    if (Character.isDigit(password[i])) {
        hasDigit = true;
    }
}
if (!hasDigit) {
    throw new WeakPasswordException("Password must contain at least 1 digit.");
}
```

**Classes and Objects**

In Java, everything is a class.  If you think about the benefits of creating a class in C++, a class allows you to build a collection of libraries with API's that other's can use build their software.  Since Java requires everything to be a class, programmers employ this reuse strategy with their software.  

Java class names usually always match the same name as the code file name.  This occurs because we are usually always writing public classes.  If we removed the word "public", then the class would only be available within the package.  When we do this, the requirement to have the name match the file goes away.  The reason we have the name match the file for public classes is so our software can find the desired code within the package by name (literally find the filename for a class because it has the same name as the class).  Incidentally, if I do have a non-public class, I can put as many classes as I want in a single file. 

You are able to extend any class to make a more specialized class.  However, if a class is marked "final" then you cannot extend it further.

There are times when you only need a class in your class.  The use of nested or inner classes is supported within the language as well.  Usually these nested classes are marked as private.

Note that when you use the `new` operator, you are creating an object of a class.  We pass data into an object via function parameters.  Each object has its own copy of member data within it.

**UML Diagrams**

This is a course is design and development.  Whenever we tackle a project or look at a new design pattern, we will rely on UML to communicate the design architecture with each other.  UML diagrams are good way to organize data, functions, and relationships.  While you will only be graded on one UML diagram that you make for your android app, you are encouraged to draw UML diagrams with all of your object oriented projects.  You can create a free account on www.lucidchart.com to make UML diagrams electronically.

Relationships are drawn as follows:

- Filled in Diamond - HAS-A
- Open Triangle - IS-A
- Arrow - One classes uses the other (simple relationship)

IntelliJ does have support UML diagrams.  You can read more here:  https://www.jetbrains.com/help/idea/class-diagram.html 



**Arrays**

The Java Collection Framework defines several classes for collections like List and Map.  However, if you want an old fashioned array, you will declare it like this:

```java
DataType[] arrayVariable;
```

The DataType can be any class or native type.  If you want the length of the array (since these are dynamic), you used:

```java
arrayVariable.length
```

If you wanted to use an array to keep track of locations, consider the Point class built into the JDK.  If you needed more dimensions, you could create your own class.

**Singleton Pattern**

Static is usually just used for the main function (see discussion of Static below).  The Singleton Design Pattern is another user of the static.  A Singleton Class is one for which one and only one object can be created.  Here is a link for more information: https://sourcemaking.com/design_patterns/singleton

**Setters and Getters**

Frequently we will have the need to set member data in an class using constructors or setter functions.  When we do this, we may need to use `this` (just like C++, in python its like "self"):

```java
public Asteroid(Point point) {
    this.point = point
}
```

If we use different names in the parameter, then we can avoid using `this`.

Getters are also common if you member data is `private`.  Many classes built into Java will have getters, setters, and various forms of constructors that allow you to create and modify objects.

**Scanner**

The Scanner class is used to read from standard input (like `input` in Python or `cin` in C++).  Scanner is nice because it has multiple functions that combine all the capabilities we had in C++ (`cin`, `getline`) into one class.  To use it, you need to create a Scanner object first:

```java
Scanner scanner = new Scanner(System.in);
word = scanner.next(); // Read one word
line = scanner.nextLine(); // Read one line
```

The scanner is similar in another way to the `getline` function in C++ in that you have to specify the stream `cin` (C++) or `System.in` (Java) when you use it.

If you will use this in multiple functions in your class, then you can make it private member data. 
More information: https://docs.oracle.com/en/java/javase/15/docs/api/java.base/java/util/Scanner.html

**While Loops**

Asking the user to re-enter the password was not necessary in the assignment, however it can be an exercise to complete.  Java has both a while loop and a do while loop.  A do while loop should be used when you need to do something at least once.  If the password example, we wanted the user to provide a password at least once.  If there was a problem with it (too short or missing a number), then we would continue the loop.

```java
User user;
boolean validPassword = false;
do {
    System.out.print("Enter password: ");
    password = scanner.nextLine();
    user = new User(password);
    try {
        NSALoginController.hashUserPassword(user);
        validPassword = true;
    } catch (WeakPasswordException e) {
        System.out.println("Weak Password Error: " + e.getMessage());
    } catch (Exception e) {
        System.out.println("General Exception: " + e.toString());
    }
} while (!validPassword);
```

Notice an interesting side effect.  If we used a while loop instead, its possible that user never gets set.  However, when you use a do-while loop, its guaranteed that user will get set.

**Access Rights / Scope / Encapsulation**

In Java, there are 4 access types:

- Public - Accessible by anyone 
- Private - Accessible only in the class
- Protected - Accessible only in the class or in derived classes
- Nothing - If you don't specify one of the above, then it is called Package Scope.  This means it is accessible by any class in the package.

In general, we keep member data private (encapsulated) and provide appropriate getters and setters as desired.  However, you will find situations later in the semester where we might be better served by having our member data public (think about the `struct` in C++).

**Overrides**

In IntellJ, using the shortcut Ctrl-O will prompt you to select functions you can override from inherited classes.  The list will include any overridable functions including:
* Public or Protected Functions (unless they are marked final)
* Functions in interfaces

When you do an override, IntelliJ will automatically put the decorator `@override` in front of the function.  This decorator (something added to a function to provide extra capability) does not affect the behavior of the function which means its optional.  If its included, then IntelliJ will check to see if really is overriding something in a base class.  If it does not (in other words, its not what you were expecting), then you will get an error message.  This can be thought of as self-documentation in the code which is enforced by the compiler.

**Final**

The keyword `final` is used for these purposes:
* If used on member data in a class, then the data cannot be changed after its initialized.  This is like `const` in C++.
* If used on member function in a class, then the function can not be overriden.
* If used on a class, then another class cannot inherit it (in other words, the class cannot be derived any further).

**Static**

You don't need to initialize an object of the class in order to call that method. Call method directly, `Math.min(5, 10)` or `Math.random()`

Static member data in a class will exist whether an object is created or not.  All objects of the same class will share the static member data.  Static member functions in a class can be executed whether an object exists or not.  Main is a great example of a static member function.  The main function is called before an object of the class is created.  Actually, the main function usually create the first object.  

Static member functions cannot use member data unless the member data is static.

why use Static keyword in Java? To efficiently manage memory, access only a couple of methods or variables of a class.

**Interfaces**

Interfaces in Java are a form of class that can be inherited by other classes and interfaces.  In Java, you can only inherit (extends) one class but you can inherit (implements) multiple interfaces.

An interface contains function declarations only (no implementation).  This is just like pure virtual functions in C++.  Here is an example of an interface in Java and in C++:

```java
public interface Messenger {
    public int sendMessage(String message);
    public int receiveMessage(String message);
}
```

Use interfaces in the following cases:
1) You need to do multiple inheritance
2) You have a common interface that needs to be implemented by un-related classes (they don't have the same base class).  This is the most common reason for creating an interface.

If you have dervied classes from a common base class but only some of those derived classes share a common interface.  The use of the `instanceof` operator can help you determine if an object of the base class implemented those interfaces.  Note that `null instanceof MyClass` will always be false.


**Abstract function**

Interfaces in Java are a form of class that can be inherited by other classes and interfaces.  In Java, you can only inherit (extends) one class but you can inherit (implements) multiple interfaces.

An interface contains function declarations only (





**Inheritance and Super**

When a class inherits another class using extends, the constructor in the derived class must explicitly call the constructor in the base class.  This is done with `super()`.  The parameters you put into super are passed into the base class constructor.

```java
public class Parent {
    public Parent(int param1, String param2) {
    }
}

public class Child extends Parent {
    public Child() {
        super(32, "dog");
    }
}
```

**Inheritance Scenarios**

Here are some specific scenarios and their impacts:
1. If Class A extends Class B and Class B extends Class C, then Class A has inherited all the characteristics of both classes B and C.
2. If Class A extends Class B and Class B implements Interface C, then class A does not implement Interface C.  Interface C is only implemented by Class B.  Class A inherits all the implemented functions in Class B.
3. An Interface B can extend (not implement) multiple Interfaces.  If Interface B extends Interface C, D, and E and Class A implements B, then Class A will need to implement all functions in B, C, D, and E.

Java does not allow you to extend more than one class.  In C++ you can extend multiple classes.  One reason Java avoids this is because of the so-called "diamond" problem.  If Class A extends Class B and Class C, this could bring in characteristics from two base classes.  However, what if both of these classes B and C extended Class D.  The potential exists that Classes B and C would provide their own overriden implementations for a function in class D.  Technically, Class A now inherits the characteristics of B, C, and D.  However, for that one function that was overriden, there is now confusion (and a compiler error) about which one to use in Class A.  This website offers an example: https://medium.freecodecamp.org/multiple-inheritance-in-c-and-the-diamond-problem-7c12a9ddbbec

**Packages**

In Java, all code is usually organized into Packages (which is a fancy name for "directory" or "folder").  You could write code without putting it into a package, but this it not ideal especially in an IDE like IntelliJ.  Packages are usually named based on author, company, and/or project.  Some will use their website name (e.g. `com.google.pakagename`).  When you write larger software, it can be covenant to organized the code into separate sub-packages.  This gives you the benefit of developing software in different teams and creating reuse opportunities.  Each sub-package would be in its own sub-directory.

**Constructors**

In Java (as with other programming languages), a constructor is used to initialize an object.  If you use `new` to create a new object in Java, the constructor will be called.  You can define more than one constructor if you want different ways of initializing the object (usually based on data passed in from the user when they called `new`).  The goal of every constructor is to initialize all the member data.

```java
public class User {
    private String name;
    private String password;
    
    public User() {
        name = "";
        password = "";
    }
    
    public User(String name) {
        this.name = name; // "this" means that we are referring to the member data
        password = "";
    }
    
    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }
    
    public static void main(String[] args) {
        // Three users each created using a different constructor
        User user1 = new User();
        User user2 = new User("Bob");
        User user3 = new User("Sue","123456!");
    }
}
```
**toString()**
In IntellJ, using the shortcut Ctrl-O will prompt you to select functions you can override from inherited classes.  The list will include any overridable functions including:
* Public or Protected Functions (unless they are marked final)
* Functions in interfaces

**Memory in Java**

In Java, objects are always created dynamically on the heap.  In C++, we had a choice whether we wanted to create something on the stack (which meant it went away when the function was done) or on the heap.  When an object is no longer being used, you don't have to "delete" the object.  The object will be automatically deleted in a process called garbage collection.  

When you pass objects in Java, you are passing the real object.  Its not a copy of the object.  Thankfully it passes just the address of the object so it doesn't take much time.  This is similar to the behavior of pass by reference in C++.  In reality, this is a little incorrect.  Technically it's more like passing a pointer by value in C++.  Since you have the address (the pointer) you can change the object. 

When you run a java program, you are starting the Java Virtual Machine (JVM) which has available to it to create the objects your code will create.  Usually the memory in the JVM is sufficient for your programs.  However, if you needed more, you can set the VM options (-Xmx) in IntelliJ (or at the java command line).  

**Problem Solving**

1. Write small program first. Never try to write the entire piece of software all at once.  For example, if you wanted to implement the weak password checks in the stretch, it would be better to write a new piece of software with just a main function where you practiced different ideas with the String class.  Once you understood how to analyze a string, then you can modify the code in `NSALoginController`.


2. Draw picture or Diagram. From your interpretation, write or draw a design (think structure charts, flow charts, UML diagram).  Then implement one piece at a time always testing in main as you go.  Never write too much code without testing.

**Resources**

Free Safari Books @ BYU-I Library:
* Head First Java: https://learning.oreilly.com/library/view/head-first-java/0596009208/
* Cracking the coding interview: https://www.amazon.com/Cracking-Coding-Interview-Programming-Questions
