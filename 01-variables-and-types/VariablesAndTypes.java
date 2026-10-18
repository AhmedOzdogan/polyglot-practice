import java.math.BigDecimal;

public class VariablesAndTypes {
    public static void main(String[] args) {

        // Question 1
        String name = "Ahmed";
        System.out.println("Hello, " + name + "!"); // Hello, Ahmed!

        // Question 2
        // final = cannot be reassigned (like TS const, Python Final)
        final String surname = "Max";
        // surname = "Jack"; // Error: cannot assign a value to final variable surname

        // Question 3
        // int is 32-bit and wraps around silently on overflow (no error, unlike Python int)
        int maxInt = Integer.MAX_VALUE;
        maxInt = maxInt + 1;
        System.out.println("Max int + 1: " + maxInt); // -2147483648 (Integer.MIN_VALUE)

        // Question 4
        int num1 = 7;
        int num2 = 2;
        // Cast happens before division, so this is double division
        double result = (double) num1 / num2;
        System.out.println("Result of division: " + result); // 3.5

        // int / int is always integer division (truncates toward zero)
        int result2 = num1 / num2;
        System.out.println("Result of integer division: " + result2); // 3

        // Question 5
        // var infers the type once at compile time; it cannot change later (unlike JS var)
        int a = 5;
        var b = 10; // inferred as int
        // a = "Hello"; // Error: incompatible types: String cannot be converted to int
        // b = "World"; // Error: incompatible types: String cannot be converted to int

        // Question 6
        long largeNumber = Long.MAX_VALUE; // 9223372036854775807
        // maxInt = largeNumber; // Error: incompatible types: possible lossy conversion from long to int
        // Explicit cast keeps only the lowest 32 bits; for Long.MAX_VALUE they are all 1s
        int castResult = (int) largeNumber;
        System.out.println("Cast result: " + castResult); // -1

        // Question 7
        // == compares references, .equals() compares content.
        // Equal literals share one pooled object, so new String forces a separate object.
        String str1 = "Hello";
        String str2 = new String("Hello");

        if (str1 == str2) {
            System.out.println("str1 and str2 reference the same object.");
        } else {
            System.out.println("str1 and str2 reference different objects.");
        }

        if (str1.equals(str2)) {
            System.out.println("str1 and str2 have the same content.");
        } else {
            System.out.println("str1 and str2 have different content.");
        }

        // Question 8
        // Integer.valueOf caches -128..127, so autoboxed 127s are the same object
        Integer a127a = 127;
        Integer a127b = 127;

        if (a127a == a127b) {
            System.out.println("a127a and a127b are equal.");
        } else {
            System.out.println("a127a and a127b are not equal.");
        }

        // 128 is outside the cache: two different objects, so == is false (use .equals())
        Integer a128a = 128;
        Integer a128b = 128;

        if (a128a == a128b) {
            System.out.println("a128a and a128b are equal.");
        } else {
            System.out.println("a128a and a128b are not equal.");
        }

        // Question 9
        // Text block: common indentation and trailing spaces are removed.
        // No interpolation, so fill in %s with .formatted
        String multiLineString = """
                Hello
                World
                My name is %s""".formatted(name);
        System.out.println(multiLineString);

        // No interpolation in Java: use String.format or .formatted with %s
        String formattedString = String.format("Hello\nWorld\nMy name is %s", name);
        System.out.println(formattedString);

        // Question 10
        // double is binary floating point, same as TS number and Python float
        double dec1 = 0.1;
        double dec2 = 0.2;
        double sum = dec1 + dec2;
        System.out.println("Sum: " + sum); // 0.30000000000000004

        // BigDecimal is exact; create it from a String, not a double
        BigDecimal bd1 = new BigDecimal("0.1");
        BigDecimal bd2 = new BigDecimal("0.2");
        BigDecimal bdSum = bd1.add(bd2);
        System.out.println("Accurate Sum: " + bdSum); // 0.3
    }
}
