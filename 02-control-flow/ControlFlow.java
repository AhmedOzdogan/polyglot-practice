import java.util.List;

public class ControlFlow {
    public static void main(String[] args) {

        // Question 1
        System.out.println("----- Question 1 -----");

        for (int i = 1; i <= 20; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                System.out.println("FizzBuzz");
            } else if (i % 3 == 0) {
                System.out.println("Fizz");
            } else if (i % 5 == 0) {
                System.out.println("Buzz");
            } else {
                System.out.println(i);
            }
        }

        // Question 2
        System.out.println("----- Question 2 -----");

        List<String> fruits = List.of("apple", "banana", "cherry", "date", "elderberry");

        // Index-based loop: List uses size() and get(i), arrays use length and [i]
        for (int i = 0; i < fruits.size(); i++) {
            System.out.println("Index: " + i + ", Value: " + fruits.get(i));
        }

        // Enhanced for: read ":" as "in" (like TS for...of, Python for...in)
        for (String fruit : fruits) {
            System.out.println("Value: " + fruit);
        }

        // Question 3
        System.out.println("----- Question 3 -----");

        double num = 100;
        int iterations = 0;

        while (num >= 1) {
            num /= 2;
            iterations++;
        }
        System.out.println("while iterations: " + iterations); // 7

        // do...while runs the body once before checking: starting below 1 still gives 1
        double num2 = 0.5;
        int iterations2 = 0;

        do {
            num2 /= 2;
            iterations2++;
        } while (num2 >= 1);
        System.out.println("do...while iterations: " + iterations2); // 1

        // Question 4
        System.out.println("----- Question 4 -----");

        int count = 10;

        // if (count) { } // Error: incompatible types: int cannot be converted to boolean
        // Java has no truthy/falsy: a condition must be a boolean
        if (count != 0) {
            System.out.println("Count is non-zero");
        }

        // Emulating falsy checks by hand. List.of rejects null, so this is an array.
        Object[] values = {null, 0, "", false, 1, "Hello", true, List.of(1, 2, 3), List.of()};

        for (Object value : values) {
            if (value == null || value.equals(0) || value.equals("") || value.equals(false)) {
                System.out.println("Falsy-like: " + value);
            } else {
                System.out.println("Truthy-like: " + value);
            }
        }

        // Question 5
        System.out.println("----- Question 5 -----");

        int level = 1;

        // Old-style switch: without break, execution falls through into the next case
        switch (level) {
            case 1:
                System.out.println("Level 1");
                // missing break: falls through
            case 2:
                System.out.println("Level 2");
                break;
            case 3:
                System.out.println("Level 3");
                break;
            default:
                System.out.println("Unknown level");
        }

        // Arrow switch: never falls through, no break needed
        switch (level) {
            case 1 -> System.out.println("Level 1");
            case 2 -> System.out.println("Level 2");
            case 3 -> System.out.println("Level 3");
            default -> System.out.println("Unknown level");
        }

        // Question 6
        System.out.println("----- Question 6 -----");

        List<String> days = List.of("Monday", "Saturday", "Sunday", "Funday");

        for (String day : days) {
            // Switch expression: produces a value, ends with ;
            String typeOfDay = switch (day) {
                case "Monday", "Tuesday", "Wednesday", "Thursday", "Friday" -> "weekday";
                case "Saturday", "Sunday" -> {
                    System.out.println("No work on " + day);
                    yield "weekend"; // yield = the value of a { } block
                }
                default -> "invalid";
            };
            System.out.println(day + " is " + typeOfDay);
        }

        // Question 7
        System.out.println("----- Question 7 -----");

        int target = 100;

        // Labeled break exits both loops at once
        outer:
        for (int j = 0; j < 100; j++) {
            for (int k = 0; k < 100; k++) {
                if (j + k == target) {
                    System.out.println("Pair found: " + j + " and " + k);
                    break outer;
                }
            }
        }

        // Question 8
        System.out.println("----- Question 8 -----");

        // 'a' is a char (boxed to Character), "a" is a String
        Object[] mixed = {1, 'a', "Hello", "Polyglot", true, 3.14, List.of(1, 2)};

        // instanceof with a binding variable: s is already a String inside the branch
        for (Object obj : mixed) {
            if (obj instanceof Integer n) {
                System.out.println(n + " is an Integer");
            } else if (obj instanceof String s) {
                System.out.println(s + " is a String of length " + s.length());
            } else if (obj instanceof List<?> l) {
                System.out.println(l + " is a List of size " + l.size());
            } else {
                System.out.println(obj + " is another type");
            }
        }

        // Question 9
        System.out.println("----- Question 9 -----");

        for (Object obj : mixed) {
            switch (obj) {
                // Guarded case must come before the plain String case
                case String s when s.length() > 5 -> System.out.println(s + " is a long String");
                case String s -> System.out.println(s + " is a String");
                case Integer n -> System.out.println(n + " is an Integer");
                case Character c -> System.out.println(c + " is a Character");
                case Boolean b -> System.out.println(b + " is a Boolean");
                // Required: a pattern switch on Object must cover every value
                default -> System.out.println(obj + " is another type");
            }
        }

        // Question 10
        System.out.println("----- Question 10 -----");

        String separator = "-".repeat(36);

        for (int row = 1; row <= 9; row++) {
            System.out.println(separator);
            for (int col = 1; col <= 9; col++) {
                // print/printf stay on the same line; %3d = integer, right-aligned, width 3
                System.out.printf("%3d|", row * col);
            }
            System.out.println(); // end the row
        }
        System.out.println(separator);
    }
}
