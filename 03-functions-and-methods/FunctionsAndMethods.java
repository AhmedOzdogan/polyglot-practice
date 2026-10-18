import java.math.BigInteger;
import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.IntStream;

public class FunctionsAndMethods {
    public static void main(String[] args) {

        // Question 1
        System.out.println("----- Question 1 -----");

        int resultQ1 = greater(5, 10);
        // int wrongResultQ1 = greater("a", 10); // Compile error: String cannot be converted to int
        System.out.println("Greater number is " + resultQ1);

        // Question 2
        System.out.println("----- Question 2 -----");

        int resultQ2_1 = multiply(10);    // 100
        int resultQ2_2 = multiply(10, 2); // 20

        System.out.println("Result 1 is " + resultQ2_1);
        System.out.println("Result 2 is " + resultQ2_2);

        // Question 3
        System.out.println("----- Question 3 -----");

        double resultQ3_1 = area(5);     // circle; int 5 widens to double automatically
        double resultQ3_2 = area(10, 5); // rectangle

        System.out.println("The area of a circle is " + resultQ3_1);
        System.out.println("The area of a rectangle is " + resultQ3_2);

        // Question 4
        System.out.println("----- Question 4 -----");

        System.out.println("Sum of 1, 2, 3: " + mySum(1, 2, 3)); // 6

        int[] numbers = IntStream.rangeClosed(0, 100).toArray(); // 0..100 (end included)
        // No spread operator: an existing array can be passed directly to varargs
        System.out.println("Sum of 0..100: " + mySum(numbers)); // 5050

        // Question 5
        System.out.println("----- Question 5 -----");

        // No keyword arguments in Java. Option 1: a parameter object (still positional)
        describePerson(new Person("Ahmed", 30, "Munich"));

        // Option 2: a builder, where each call names the value it sets (any order)
        describePerson(new PersonBuilder().city("Munich").age(30).name("Ahmed").build());
        describePerson(new PersonBuilder().name("Ahmed").age(30).build()); // city keeps its default
        
        // Question 6
        System.out.println("----- Question 6 -----");

        int number = 10;
        int[] numbers2 = {1, 2, 3};
        modifyValues(number, numbers2);
        // The int is unchanged (the method got a copy); the array changed (same array object)
        System.out.println("After call: number = " + number + ", array = " + Arrays.toString(numbers2));

        // Question 7
        System.out.println("----- Question 7 -----");

        // Static methods belong to the class: main (also static) can call them directly
        System.out.println("Static call: " + greater(3, 7));

        // sayHello("ahmed"); // Error: non-static method sayHello(String) cannot be referenced from a static context
        // Instance methods belong to an object, so create one first
        FunctionsAndMethods greeter = new FunctionsAndMethods();
        greeter.sayHello("Ahmed");

        // Question 8
        System.out.println("----- Question 8 -----");

        // One record comes back; read each value with its accessor method
        Identity me = whoAreYou();
        System.out.println("Record: " + me.name() + ", " + me.age());

        // Java 21 record pattern: the closest thing to destructuring
        if (whoAreYou() instanceof Identity(String myName, int myAge)) {
            System.out.println("Pattern: " + myName + ", " + myAge);
        }

        // Question 9
        System.out.println("----- Question 9 -----");

        System.out.println("Factorial of 20 is (long) " + factorialLong(20)); // 2432902008176640000
        System.out.println("Factorial of 25 is (long) " + factorialLong(25)); // 7034535277573963776, overflowed past Long.MAX_VALUE

        System.out.println("Factorial of 20 is (BigInteger) " + factorialBigInteger(20)); // 2432902008176640000
        System.out.println("Factorial of 25 is (BigInteger) " + factorialBigInteger(25)); // 15511210043330985984000000

        // Question 10
        System.out.println("----- Question 10 -----");

        // FunctionsAndMethods::addTen is a method reference: it passes addTen itself, without calling it
        System.out.println("0 + 10 twice is " + doTwice(FunctionsAndMethods::addTen, 0)); // 20
        // a lambda does the same inline, no named method needed
        System.out.println("0 + 10 twice is (lambda) " + doTwice(x -> x + 10, 0)); // 20
    }

    // Java has no tuples: declare a record to hold the two values
    record Identity(String name, int age) {}

    static Identity whoAreYou() {
        return new Identity("Ahmed", 30);
    }

    // Instance field and method: each object has its own name
    String name = "";

    void sayHello(String name) {
        this.name = name; // this.name = the field, name = the parameter
        System.out.println("Hello " + this.name);
    }

    // No runtime type check needed: Java refuses to compile a call with wrong types
    static int greater(int num1, int num2) {
        return Math.max(num1, num2);
    }

    // No default parameters in Java: overload instead
    static int multiply(int num1, int num2) {
        return num1 * num2;
    }

    // Short version supplies the "default" and delegates to the full version
    static int multiply(int num1) {
        return multiply(num1, 10);
    }

    // Overloading: same name, Java picks the method by the number/types of arguments
    static double area(double radius) {
        return Math.PI * radius * radius; // circle: πr²
    }

    static double area(double width, double height) {
        return width * height; // rectangle
    }

    // Varargs: inside the method, numbers is just an int[]
    static int mySum(int... numbers) {
        int totalSum = 0;
        for (int number : numbers) {
            totalSum += number;
        }
        return totalSum;
    }

    // Parameter object: groups the values into one type (records are covered in topic 04)
    record Person(String name, int age, String city) {}

    // Builder: each method sets one named value and returns the builder, so calls can be chained
    static class PersonBuilder {
        private String name;
        private int age;
        private String city = "Berlin"; // default

        PersonBuilder name(String name) {
            this.name = name;
            return this;
        }

        PersonBuilder age(int age) {
            this.age = age;
            return this;
        }

        PersonBuilder city(String city) {
            this.city = city;
            return this;
        }

        Person build() {
            return new Person(name, age, city);
        }
    }

    static void describePerson(Person person) {
        System.out.println(person.name() + " is " + person.age() + " years old and lives in " + person.city());
    }

    // Java is always pass-by-value: an int is copied, an array's reference is copied.
    // Reassigning num only changes the copy; changing an element changes the shared array.
    static void modifyValues(int num, int[] array) {
        num += 5;
        array[0] = 0;
        System.out.println("Inside method: number = " + num + ", array = " + Arrays.toString(array));
    }

    // Recursive: calls itself with a smaller n until the base case
    static long factorialLong(int n) {
        if (n <= 1) return 1; // base case
        return n * factorialLong(n - 1);
    }

    static BigInteger factorialBigInteger(int n) {
        if (n <= 1) return BigInteger.ONE;
        return BigInteger.valueOf(n).multiply(factorialBigInteger(n - 1));
    }

    static int addTen(int num) {
        return num + 10;
    }

    // Function<Integer, Integer> = "a function that takes an Integer and returns an Integer"
    static int doTwice(Function<Integer, Integer> f, int value) {
        return f.apply(f.apply(value)); // Java calls it with .apply(x), not f(x)
    }
}
