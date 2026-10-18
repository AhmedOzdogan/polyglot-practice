import math
from typing import Callable, overload

# Question 1
print("----- Question 1 -----")


# Type hints are not enforced at runtime, so check explicitly.
# bool is excluded because it is a subclass of int.
def greater(num1: int, num2: int) -> int:
    for num in (num1, num2):
        if not isinstance(num, int) or isinstance(num, bool):
            raise TypeError("Both arguments must be integers")
    return max(num1, num2)


result_q1 = greater(1, 10)
# wrong_result_q1 = greater(0.5, 10)  # TypeError: Both arguments must be integers
print(f"Greater number is {result_q1}")

# Question 2
print("----- Question 2 -----")


def multiply(num1: int, num2: int = 10) -> int:
    for num in (num1, num2):
        if not isinstance(num, int) or isinstance(num, bool):
            raise TypeError("Both arguments must be integers")
    return num1 * num2


result_q2_1 = multiply(10)  # 100
print(f"Result 1 is {result_q2_1}")

result_q2_2 = multiply(10, 2)  # 20
print(f"Result 2 is {result_q2_2}")

# Default is still 10: numbers can't be changed in place (a list default could be)
result_q2_3 = multiply(10)  # 100
print(f"Result 3 is {result_q2_3}")

# Question 3
print("----- Question 3 -----")


# Option 1: an optional parameter decides circle vs rectangle
def area_optional(a: float, b: float | None = None) -> float:
    if b is None:
        return math.pi * a * a  # circle
    return a * b  # rectangle


print(f"Circle (optional param): {area_optional(5)}")
print(f"Rectangle (optional param): {area_optional(10, 5)}")


# Option 2: @overload declares the call forms for the type checker (like TS overload signatures).
# Only the last def runs. "/" makes parameters positional-only, so the different
# names (radius/width vs a) don't matter: callers can't pass them by keyword.
@overload
def area(radius: float, /) -> float: ...
@overload
def area(width: float, height: float, /) -> float: ...
def area(a: float, b: float | None = None, /) -> float:
    if b is None:
        return math.pi * a * a  # circle
    return a * b  # rectangle


result_q3_1 = area(5)
result_q3_2 = area(10, 5)

print(f"The area of a circle is {result_q3_1}")
print(f"The area of a rectangle is {result_q3_2}")

# Question 4
print("----- Question 4 -----")


# *args collects all arguments into a tuple
def my_sum(*args: int) -> int:
    total = 0
    for arg in args:
        total += arg
    return total


result_q4_1 = my_sum(1, 2, 3)  # 6
print(f"Sum of 1, 2, 3: {result_q4_1}")

numbers = list(range(101))  # 0..100 (end excluded, so 101)

# * unpacks the list: each element becomes a separate argument
result_q4_2 = my_sum(*numbers)  # 5050
print(f"Sum of 0..100: {result_q4_2}")

# Question 5
print("----- Question 5 -----")


# *args collects extra positional arguments (tuple), **kwargs extra keyword arguments (dict)
def show_args(*args, **kwargs) -> None:
    print(args)  # (1, 2, 3, 4)
    print(kwargs)  # {'name': 'Ahmed'}


show_args(1, 2, 3, 4, name="Ahmed")

# Keyword arguments: Python matches them by NAME, not by position
def describe_person(name: str, age: int, city: str = "Berlin") -> None:
    print(f"{name} is {age} years old and lives in {city}")


describe_person("Ahmed", 30, "Munich")  # positional: matched by order
describe_person(city="Munich", age=30, name="Ahmed")  # keyword: any order, same result
describe_person("Ahmed", city="Munich", age=30)  # mixed: positional first, then keyword
describe_person("Ahmed", age=30)  # city skipped: uses the default "Berlin"

# describe_person(name="Ahmed", 30)  # SyntaxError: positional argument follows keyword argument
# describe_person("Ahmed", agee=30)  # TypeError: unexpected keyword argument 'agee'

# Question 6
print("----- Question 6 -----")


# Python passes references to objects. num += 5 makes num point to a NEW int
# (ints are immutable); pop() changes the same list object the caller has.
def modify_values(num: int, values: list[int]) -> None:
    num += 5
    values.pop()
    print(f"Inside function: number = {num}, list = {values}")


number = 5
numbers2 = [1, 2, 3]
modify_values(number, numbers2)
print(f"After call: number = {number}, list = {numbers2}")  # 5, [1, 2]

# Question 7 is Java-only (static vs instance methods)

# Question 8
print("----- Question 8 -----")


# "return a, b" builds a tuple; the parentheses are optional
def who_are_you() -> tuple[str, int]:
    return "Ahmed", 30


who_am_i = who_are_you()
print(f"Tuple: {who_am_i}, {type(who_am_i)}")  # ('Ahmed', 30), <class 'tuple'>

my_name, my_age = who_are_you()  # tuple unpacking
print(f"Unpacked: {my_name}, {my_age}")

# Question 9
print("----- Question 9 -----")


def factorial(n: int) -> int:
    if n <= 1:  # base case
        return 1
    return n * factorial(n - 1)


print(f"Factorial of 20 is {factorial(20)}")  # 2432902008176640000
print(f"Factorial of 25 is {factorial(25)}")  # 15511210043330985984000000, Python int never overflows

# Question 10
print("----- Question 10 -----")


def add_ten(num: int) -> int:
    return num + 10


# Callable[[int], int] = "a function that takes an int and returns an int"
def do_twice(f: Callable[[int], int], value: int) -> int:
    return f(f(value))


print(f"0 + 10 twice is {do_twice(add_ten, 0)}")  # 20
print(f"0 + 10 twice is (lambda) {do_twice(lambda x: x + 10, 0)}")  # 20
