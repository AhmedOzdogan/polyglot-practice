import math
from dataclasses import FrozenInstanceError, dataclass, field, replace
import uuid

# Question 1
print("----- Question 1 -----")


class Point:
    def __init__(self, x: float, y: float):
        self.x = x  # instance variables: each object gets its own copy
        self.y = y

    # No origin parameter needed: the origin is always (0, 0)
    def distance_to_origin(self) -> float:
        return math.sqrt(self.x**2 + self.y**2)


point_q1_1 = Point(3, 4)
print(f"Distance to the origin is {point_q1_1.distance_to_origin()}")  # 5.0

point_q1_2 = Point(5, 12)
print(f"Distance to the origin is {point_q1_2.distance_to_origin()}")  # 13.0

# Question 2
print("----- Question 2 -----")


class PrivatePoint:
    def __init__(self, x: float, y: float):
        self._x = x  # single underscore: private by convention only
        self.__y = y  # double underscore: Python renames it to _PrivatePoint__y

    # Plain getters, Java style: p.get_x()
    def get_x(self) -> float:
        return self._x

    def get_y(self) -> float:
        return self.__y  # inside the class, __y works normally

    # Pythonic getter: read like a field (p.y), but a method runs.
    # No setter, so p.y = 10 raises AttributeError (read-only).
    @property
    def y(self) -> float:
        return self.__y

    def distance_to_origin(self) -> float:
        return math.sqrt(self._x**2 + self.__y**2)


point_q2 = PrivatePoint(3, 4)
print(f"Getters: x = {point_q2.get_x()}, y = {point_q2.get_y()}, property y = {point_q2.y}")
print(f"Distance to the origin is {point_q2.distance_to_origin()}")

# Outside the class, _x is still reachable: nothing stops you
print(f"Outside access to _x: {point_q2._x}")

# Outside the class, __y fails because the name was mangled
try:
    print(point_q2.__y)
except AttributeError as e:
    print(f"Outside access to __y failed: {e}")

# Not truly private: the mangled name still works at runtime.
# Pylance doesn't model mangling, so it flags this line; Python runs it fine.
print(f"Mangled name: {point_q2._PrivatePoint__y}")  # pyright: ignore[reportAttributeAccessIssue]

# Question 3
print("----- Question 3 -----")


class CountedPoint:
    count = 0  # class variable: one copy, shared by all instances

    def __init__(self, x: float, y: float):
        self.x = x
        self.y = y
        # Write through the class name. self.count += 1 would create a new
        # instance variable on this object and leave the class count at 0.
        CountedPoint.count += 1


point_q3_1 = CountedPoint(1, 2)
point_q3_2 = CountedPoint(3, 4)
point_q3_3 = CountedPoint(5, 6)
print(f"Total points created: {CountedPoint.count}")  # 3

# Question 4
print("----- Question 4 -----")


class Person:
    def __init__(self, name: str, age: int):
        self.name = name
        self.age = age

    # For developers: exact, looks like code. Also the fallback for print()
    def __repr__(self):
        return f"Person(name={self.name!r}, age={self.age})"

    # For users: friendly. print() and f-strings use this one
    def __str__(self):
        return f"{self.name}, {self.age} years old"


bob = Person("Bob", 30)
print(f"repr(): {bob!r}")  # Person(name='Bob', age=30)
print(f"str():  {bob}")  # Bob, 30 years old
print(f"In a list (uses repr): {[bob]}")

# Question 5
print("----- Question 5 -----")


# Before: no __eq__/__hash__, so Python compares by identity (same object in memory)
class PlainStudent:
    def __init__(self, name: str, student_id: int):
        self.name = name
        self.student_id = student_id


plain_students = {
    PlainStudent("Bob", 101),
    PlainStudent("Jack", 102),
    PlainStudent("Isac", 103),
    PlainStudent("Yamal", 101),  # same id as Bob, but a different object
}
print(f"Without __eq__/__hash__: {len(plain_students)} students")  # 4


# After: two students are equal if their ids match
class Student:
    def __init__(self, name: str, student_id: int):
        self.name = name
        self.student_id = student_id

    def __eq__(self, other):
        if not isinstance(other, Student):
            return NotImplemented
        return self.student_id == other.student_id

    # Equal objects must have equal hashes, so hash the same field __eq__ uses
    def __hash__(self):
        return hash(self.student_id)


# The set calls __hash__ to find a bucket, then __eq__ to check for duplicates
classroom = {
    Student("Bob", 101),
    Student("Jack", 102),
    Student("Isac", 103),
    Student("Yamal", 101),  # duplicate of Bob: not added
}
print(f"With __eq__/__hash__: {len(classroom)} students")  # 3

# Question 6-7
print("----- Question 6-7 -----")


# frozen=True: fields can't be reassigned, and __hash__ is generated.
# order=True: <, >, etc. compare the same fields as __eq__ (only id here).
@dataclass(frozen=True, order=True)
class Book:
    id: str  # the only field used for __eq__, __hash__ and ordering
    name: str = field(compare=False)
    page: int = field(compare=False)
    price: int = field(compare=False)
    quantity: int = field(compare=False)
    total_cost: int = field(init=False, compare=False)  # computed, not a constructor argument

    def __post_init__(self):
        if not self.name.strip():
            raise ValueError("Name must not be empty")
        if self.page < 0:
            raise ValueError(f"Page cannot be negative, got {self.page}")
        if self.price < 0:
            raise ValueError(f"Price cannot be negative, got {self.price}")
        if self.quantity < 0:
            raise ValueError(f"Quantity cannot be negative, got {self.quantity}")
        # Frozen blocks self.total_cost = ..., so set it through object.__setattr__
        object.__setattr__(self, "total_cost", self.price * self.quantity)

    # Returns a new Book instead of mutating this one (Question 8)
    def with_name_and_id(self, new_id: str, new_name: str) -> "Book":
        return Book(new_id, new_name, self.page, self.price, self.quantity)


books = [
    Book("B001", "Dune", 412, 18, 3),
    Book("B002", "1984", 328, 12, 5),
    Book("B003", "The Hobbit", 310, 15, 2),
    Book("B004", "Sapiens", 443, 22, 4),
    Book("B005", "Clean Code", 464, 35, 1),
    Book("B006", "Brave New World", 288, 11, 6),
    Book("B007", "The Alchemist", 197, 10, 8),
    Book("B008", "Atomic Habits", 320, 20, 3),
    Book("B009", "Pride and Prejudice", 279, 9, 7),
    Book("B001", "Dune (Paperback)", 412, 14, 2),  # same id as the first book
]
print(f"Books in list: {len(books)}")  # 10
print(f"Unique books in set: {len(set(books))}")  # 9: equality is by id only
print(f"Sorted by id: {[b.id for b in sorted(set(books))]}")

try:
    Book("B010", "Bad Book", -1, 10, 1)
except ValueError as e:
    print(f"Rejected: {e}")

# Question 8
print("----- Question 8 -----")

original = books[0]
new_book = original.with_name_and_id("B010", "Dune Hardcover")
print(f"Original: {original}")  # unchanged
print(f"New:      {new_book}")
print(f"Same object? {original is new_book}")  # False

# Built-in alternative: replace() copies with any fields changed, no method needed
print(f"replace(): {replace(original, price=25)}")

# Copies still go through __init__, so validation still applies
try:
    original.with_name_and_id("B011", "")
except ValueError as e:
    print(f"Rejected copy: {e}")

# Mutating directly fails at runtime (not before, like Java/TS)
try:
    original.id = "B019"  # pyright: ignore[reportAttributeAccessIssue]
except FrozenInstanceError as e:
    print(f"Mutation blocked: {e}")


# Question 10
print("----- Question 10 -----")


# Not frozen: an account's balance has to change. Instead, the balance is
# "private" with a read-only property, so deposit/withdraw are the only ways in.
class BankAccount:
    def __init__(self, owner: str):
        if not owner.strip():
            raise ValueError("Owner name must not be empty")
        self.id = str(uuid.uuid4())
        self.owner = owner
        self._balance = 0  # int, not float: money in whole units avoids rounding errors

    # No setter, so account.balance = 999 raises AttributeError
    @property
    def balance(self) -> int:
        return self._balance

    def deposit(self, amount: int) -> None:
        if amount <= 0:
            raise ValueError(f"Deposit amount must be positive, got {amount}")
        self._balance += amount

    def withdraw(self, amount: int) -> None:
        # Validate the input first, then check whether it's affordable
        if amount <= 0:
            raise ValueError(f"Withdrawal amount must be positive, got {amount}")
        if amount > self._balance:
            raise ValueError(f"Insufficient funds: balance {self._balance}, tried to withdraw {amount}")
        self._balance -= amount

    def __repr__(self):
        return f"BankAccount(owner={self.owner!r}, balance={self._balance})"


account = BankAccount("Bob Marley")
print(f"New account: {account}")

account.deposit(1000)
account.deposit(500)  # a second deposit proves it adds instead of replacing
print(f"After depositing 1000 and 500: {account.balance}")  # 1500

account.withdraw(400)
print(f"After withdrawing 400: {account.balance}")  # 1100

# Every invalid operation fails loudly
invalid_operations = [
    ("empty owner", lambda: BankAccount("   ")),
    ("negative deposit", lambda: account.deposit(-100)),
    ("zero deposit", lambda: account.deposit(0)),
    ("negative withdrawal", lambda: account.withdraw(-100)),
    ("overdraft", lambda: account.withdraw(5000)),
]
for label, operation in invalid_operations:
    try:
        operation()
    except ValueError as e:
        print(f"Rejected ({label}): {e}")

try:
    account.balance = 1_000_000  # pyright: ignore[reportAttributeAccessIssue]
except AttributeError as e:
    print(f"Rejected (direct write): {e}")

print(f"Balance unchanged after all rejections: {account.balance}")  # 1100
