import java.util.HashSet;
import java.util.Objects;
import java.util.UUID;

public class ClassesAndRecords {
    public static void main(String[] args) {

        // Question 1
        System.out.println("----- Question 1 -----");

        Point pointQ1_1 = new Point(3, 4);
        System.out.println("Distance to the origin is " + pointQ1_1.distanceToOrigin()); // 5.0

        Point pointQ1_2 = new Point(5, 12);
        System.out.println("Distance to the origin is " + pointQ1_2.distanceToOrigin()); // 13.0

        // Question 2
        System.out.println("----- Question 2 -----");

        PrivatePoint pointQ2 = new PrivatePoint(3, 4);
        System.out.println("Public label from outside: " + pointQ2.label);
        // System.out.println(pointQ2.x); // Compile error: x has private access in PrivatePoint
        System.out.println("Getters: x = " + pointQ2.getX() + ", y = " + pointQ2.getY());

        pointQ2.setX(10);
        System.out.println("After setX(10): x = " + pointQ2.getX());
        System.out.println("Distance to the origin is " + pointQ2.distanceToOrigin());

        // Question 3
        System.out.println("----- Question 3 -----");

        new CountedPoint(1, 2);
        new CountedPoint(3, 4);
        new CountedPoint(5, 6);
        System.out.println("Total points created: " + CountedPoint.getCount()); // 3

        // Question 4
        System.out.println("----- Question 4 -----");

        Person bob = new Person("Bob", 30);
        System.out.println("println calls toString: " + bob); // Person(name=Bob, age=30)
        System.out.println("Without override: " + new PlainStudent("Ann", 1)); // PlainStudent@1b6d3586

        // Question 5
        System.out.println("----- Question 5 -----");

        // Without equals/hashCode: compared by identity, so all 4 are kept
        HashSet<PlainStudent> plainStudents = new HashSet<>();
        plainStudents.add(new PlainStudent("Bob", 101));
        plainStudents.add(new PlainStudent("Jack", 102));
        plainStudents.add(new PlainStudent("Isac", 103));
        plainStudents.add(new PlainStudent("Yamal", 101)); // same id as Bob
        System.out.println("Without equals/hashCode: " + plainStudents.size() + " students"); // 4

        // With equals/hashCode on studentId: Yamal is a duplicate of Bob
        HashSet<Student> classroom = new HashSet<>();
        classroom.add(new Student("Bob", 101));
        classroom.add(new Student("Jack", 102));
        classroom.add(new Student("Isac", 103));
        classroom.add(new Student("Yamal", 101)); // duplicate: not added
        System.out.println("With equals/hashCode: " + classroom.size() + " students"); // 3

        // Question 6-7
        System.out.println("----- Question 6-7 -----");

        // By hand: a class with final fields and a hand-written toString
        BookClass bookQ6_1 = new BookClass(101, "Dune", 412, 18, 3);
        System.out.println("Class: " + bookQ6_1); // without toString: BookClass@54bedef2

        // Record: one header line, the compiler generates the rest
        BookRecord bookQ6_2 = new BookRecord(102, "1984", 328, 12, 5);
        BookRecord bookQ6_3 = new BookRecord(102, "1984", 328, 12, 5);
        System.out.println("Record: " + bookQ6_2); // generated toString
        System.out.println("Accessor name(): " + bookQ6_2.name()); // not getName()
        System.out.println("Total price: " + bookQ6_2.totalPrice()); // 60
        System.out.println("equals (same values): " + bookQ6_2.equals(bookQ6_3)); // true: compares all fields
        System.out.println("== (same object?): " + (bookQ6_2 == bookQ6_3)); // false: two objects
        // bookQ6_2.price = 0; // Compile error: price has private access in BookRecord

        // Invalid inputs are rejected by the compact constructor
        try {
            new BookRecord(103, "   ", 300, 10, 1);
        } catch (IllegalArgumentException e) {
            System.out.println("Rejected: " + e.getMessage());
        }
        try {
            new BookRecord(104, "Sapiens", -443, 22, 4);
        } catch (IllegalArgumentException e) {
            System.out.println("Rejected: " + e.getMessage());
        }
        try {
            new BookRecord(105, null, 464, 35, 1);
        } catch (IllegalArgumentException e) {
            System.out.println("Rejected: " + e.getMessage());
        }

        // Question 8
        System.out.println("----- Question 8 -----");

        BookRecord original = new BookRecord(201, "Dune", 412, 18, 3);
        BookRecord copy = original.withNewNameAndId(202, "Dune Hardcover");
        System.out.println("Original: " + original); // unchanged
        System.out.println("Copy:     " + copy);
        System.out.println("Same object? " + (original == copy)); // false

        // Copies go through the compact constructor, so validation still applies
        try {
            original.withNewNameAndId(203, "");
        } catch (IllegalArgumentException e) {
            System.out.println("Rejected copy: " + e.getMessage());
        }

        // What stops a direct mutation? The compiler: record fields are private final, and no setters exist
        // original.name = "Changed"; // Compile error: name has private access in BookRecord
        // (inside the record itself, the error would be: cannot assign a value to final variable name)

        // Question 9
        System.out.println("----- Question 9 -----");

        // Static nested: created through the class name, no Library needed
        Library.Address address = new Library.Address("Main Street 1");
        System.out.println("Address: " + address.street);

        // Inner: created through a Library object with outer.new
        Library central = new Library("Central");
        Library.Shelf shelf = central.new Shelf("A");
        System.out.println(shelf.describe()); // Shelf A in Central
        // Library.Shelf wrong = new Library.Shelf("B"); // Compile error: an enclosing instance that contains Library.Shelf is required
    
        // Question 10
        System.out.println("----- Question 10 -----");

        BankAccount account = new BankAccount("Bob Marley");
        System.out.println("New account: " + account);

        account.deposit(1000);
        account.deposit(500); // a second deposit proves it adds instead of replacing
        System.out.println("After depositing 1000 and 500: " + account.getBalance()); // 1500

        account.withdraw(400);
        System.out.println("After withdrawing 400: " + account.getBalance()); // 1100

        // Every invalid operation fails loudly
        expectRejected("empty owner", () -> new BankAccount("   "));
        expectRejected("null owner", () -> new BankAccount(null));
        expectRejected("negative deposit", () -> account.deposit(-100));
        expectRejected("zero deposit", () -> account.deposit(0));
        expectRejected("overflow deposit", () -> account.deposit(Integer.MAX_VALUE));
        expectRejected("negative withdrawal", () -> account.withdraw(-100));
        expectRejected("overdraft", () -> account.withdraw(5000));
        // account.balance = 1_000_000; // Compile error: balance has private access in BankAccount

        System.out.println("Balance unchanged after all rejections: " + account.getBalance()); // 1100
    }

    // Runs an operation that should throw, and reports what happened
    static void expectRejected(String label, Runnable operation) {
        try {
            operation.run();
            System.out.println("NOT rejected (" + label + "): this is a bug");
        } catch (IllegalArgumentException | ArithmeticException e) {
            System.out.println("Rejected (" + label + "): " + e.getMessage());
        }
    }
}

// Not public: only one public top-level class per file, and it must match the file name
class Point {
    double x;
    double y;

    // Constructor: same name as the class, no return type
    Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // No origin parameter needed: the origin is always (0, 0)
    double distanceToOrigin() {
        return Math.sqrt(x * x + y * y);
    }
}

class PrivatePoint {
    public String label = "P"; // public: readable and writable from outside
    private double x;          // private: only code inside this class can touch it
    private double y;

    PrivatePoint(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // Getters run inside the class, so they can read private fields
    double getX() {
        return x;
    }

    double getY() {
        return y;
    }

    // Setter only for x: y stays read-only from outside
    void setX(double x) {
        this.x = x;
    }

    double distanceToOrigin() {
        return Math.sqrt(x * x + y * y);
    }
}

class CountedPoint {
    // static: one copy on the class, shared by all instances.
    // private: outside code can't overwrite the count, only read it via getCount()
    private static int count = 0;

    double x;
    double y;

    CountedPoint(double x, double y) {
        this.x = x;
        this.y = y;
        count++;
    }

    static int getCount() {
        return count;
    }
}

class Person {
    String name;
    int age;

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person(name=" + name + ", age=" + age + ")";
    }
}

class PlainStudent {
    String name;
    int studentId;

    PlainStudent(String name, int studentId) {
        this.name = name;
        this.studentId = studentId;
    }
}

class Student {
    String name;
    int studentId;

    Student(String name, int studentId) {
        this.name = name;
        this.studentId = studentId;
    }

    // Parameter must be Object: equals(Student) would be an overload, never called by HashSet
    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // same object
        if (!(o instanceof Student other)) return false; // type check + cast in one step
        return studentId == other.studentId;
    }

    // Must use the same fields as equals, or equal students land in different buckets
    @Override
    public int hashCode() {
        return Objects.hash(studentId);
    }
}

// The manual version of what a record generates (equals/hashCode left out for brevity)
class BookClass {
    private final int id;
    private final String name;
    private final int page;
    private final int price;
    private final int quantity;
    private final int totalPrice; // final fields must be assigned exactly once, in the constructor

    BookClass(int id, String name, int page, int price, int quantity) {
        this.id = id;
        this.name = name;
        this.page = page;
        this.price = price;
        this.quantity = quantity;
        this.totalPrice = price * quantity; // after price and quantity are set
    }

    @Override
    public String toString() {
        return String.format("BookClass(id=%d, name=%s, page=%d, price=%d, quantity=%d, totalPrice=%d)",
                id, name, page, price, quantity, totalPrice);
    }
}

// Generates: private final fields, constructor, accessors (id(), name()...),
// equals, hashCode and toString. Records are also final (can't be extended).
record BookRecord(int id, String name, int page, int price, int quantity) {

    // Compact constructor: no parameter list; fields are assigned after this body runs
    BookRecord {
        if (id < 0) {
            throw new IllegalArgumentException("ID cannot be negative, got " + id);
        }
        // Check null first: calling isBlank() on null throws NullPointerException
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be empty, got " + name);
        }
        if (page < 0) {
            throw new IllegalArgumentException("Page cannot be negative, got " + page);
        }
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative, got " + price);
        }
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative, got " + quantity);
        }
    }

    // Records can't have extra stored fields, so derived values are methods
    int totalPrice() {
        return price * quantity;
    }

    // Returns a new record instead of mutating this one; Java 21 has no built-in copy helper
    BookRecord withNewNameAndId(int newId, String newName) {
        return new BookRecord(newId, newName, page, price, quantity);
    }

}

// Question 9: one outer class with both kinds of nested class
class Library {
    private String name;

    Library(String name) {
        this.name = name;
    }

    // Static nested: belongs to the class, not to a Library object.
    // Created on its own, but has no outer object to read fields from.
    static class Address {
        String street;

        Address(String street) {
            this.street = street;
        }

        // String getLibraryName() { return name; } // Compile error: non-static variable name cannot be referenced from a static context
    }

    // Inner (non-static): every Shelf belongs to one Library object
    // and can read its fields directly, even private ones.
    class Shelf {
        String label;

        Shelf(String label) {
            this.label = label;
        }

        String describe() {
            return "Shelf " + label + " in " + Library.this.name; // Library.this = the outer object
        }
    }

    
}

// Question 10: private balance, no setter; deposit/withdraw are the only ways to change it
class BankAccount {
    private final UUID id;      // final: assigned once, never changes
    private final String owner;
    private int balance;        // the only field that changes

    BankAccount(String owner) {
        // Validate first, then assign. Check null before calling isBlank() on it.
        if (owner == null || owner.isBlank()) {
            throw new IllegalArgumentException("Owner name must not be empty, got " + owner);
        }
        this.id = UUID.randomUUID();
        this.owner = owner;
        this.balance = 0;
    }

    public UUID getId() {
        return id;
    }

    public String getOwner() {
        return owner;
    }

    public int getBalance() {
        return balance;
    }

    public void deposit(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive, got " + amount);
        }
        // Plain + would silently wrap past Integer.MAX_VALUE into a negative balance;
        // addExact throws ArithmeticException instead
        balance = Math.addExact(balance, amount);
    }

    public void withdraw(int amount) {
        // Validate the input first, then check whether it's affordable
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive, got " + amount);
        }
        if (amount > balance) {
            throw new IllegalArgumentException(
                    "Insufficient funds: balance " + balance + ", tried to withdraw " + amount);
        }
        balance -= amount;
    }

    @Override
    public String toString() {
        return "BankAccount(owner=" + owner + ", balance=" + balance + ")";
    }
}