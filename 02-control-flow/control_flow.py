from decimal import Decimal
from fractions import Fraction

# Question 1
print("----- Question 1 -----")

for x in range(1, 21):
    if x % 3 == 0 and x % 5 == 0:
        print("FizzBuzz")
    elif x % 3 == 0:
        print("Fizz")
    elif x % 5 == 0:
        print("Buzz")
    else:
        print(x)

# Question 2
print("----- Question 2 -----")

string_list = ["apple", "banana", "cherry", "date", "elderberry"]

# Index-based loop
for i in range(len(string_list)):
    print(f"Index: {i}, Value: {string_list[i]}")

# Python's for...in gives values, like JS for...of (JS for...in gives keys)
for value in string_list:
    print(f"Value: {value}")

# enumerate gives index and value together
for i, value in enumerate(string_list):
    print(f"Index: {i}, Value: {value}")

# Question 3
print("----- Question 3 -----")

num = 100
total_iterations = 0

while num >= 1:
    num /= 2
    total_iterations += 1

print(f"Total iterations: {total_iterations}")  # 7

# No do...while in Python: simulate with while True + break.
# The body always runs at least once, then the condition is checked.
num2 = 100
total_iterations2 = 0

while True:
    num2 /= 2
    total_iterations2 += 1
    if num2 < 1:
        break

print(f"Total iterations: {total_iterations2}")  # 7

# Question 4
print("----- Question 4 -----")

# Every value here is falsy: bool() returns False for all of them
falsy_list = [False, None, 0, 0.0, 0j, Decimal(0), Fraction(0, 1), "", (), [], {}, set(), range(0)]

for value in falsy_list:
    print(f"{value!r} -> {bool(value)}")

# Question 5
print("----- Question 5 -----")

num3 = 5

# match never falls through: only the first matching case runs,
# so "One, Two, ..." is never printed for 5 (opposite of Java's old switch)
match num3:
    case 1:
        print("One")
    case 2:
        print("Two")
    case 3:
        print("Three")
    case 4:
        print("Four")
    case 5:
        print("Five")
    case 1 | 2 | 3 | 4 | 5:
        print("One, Two, Three, Four or Five")
    case _:
        print("Something else")

# Question 7
print("----- Question 7 -----")


# No labeled break in Python: put the loops in a function and return
def find_pair(numbers: list[int], target: int) -> tuple[int, int] | None:
    for i in range(len(numbers)):
        for j in range(i + 1, len(numbers)):
            if numbers[i] + numbers[j] == target:
                return numbers[i], numbers[j]
    return None


numbers = [3, 8, 15, 21, 27, 34]
print(f"Pair for 42: {find_pair(numbers, 42)}")  # (8, 34)
print(f"Pair for 100: {find_pair(numbers, 100)}")  # None

# Question 8
print("----- Question 8 -----")

# True counts as an int: bool is a subclass of int in Python (not in Java)
collection = [1, "hello", [1, 2, 3], 42, "polyglot", [4, 5], True]

for item in collection:
    if isinstance(item, int):
        print(f"{item} is an integer")
    elif isinstance(item, str):
        print(f"{item} is a string")
    elif isinstance(item, list):
        print(f"{item} is a list")

# Question 9
print("----- Question 9 -----")

for item in collection:
    match item:
        case int():
            print(f"{item} is an integer")
        case str() if len(item) > 5:
            print(f"{item} is a long string")
        case str():
            print(f"{item} is a string")
        case list():
            print(f"{item} is a list")
        case _:
            print(f"{item} is something else")

# Question 10
print("----- Question 10 -----")

separator = "-" * 36

for i in range(1, 10):
    print(separator)
    for j in range(1, 10):
        print(f"{i * j:>3}", end="|")
    print()
print(separator)
