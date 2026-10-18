import sys
from decimal import Decimal
from typing import Final

# Question 1
name = "Ahmed"
print(name)

# Question 2
FINAL_NUMBER: Final = 10
#FINAL_NUMBER = 15  # pyright error; runs fine at runtime

# Question 3
# There is no max integer limit in Python
max_int = sys.maxsize
print(f"Max Int is {max_int}")
max_int = max_int + 1
print(f"Max Int is + 1  {max_int}")

# Question 4
division: int = 7 // 2  # 3
print(f"Int answer is {division}")

division2: float = 7 / 2  # 3.5
print(f"Float answer is {division2}")

# Question 5
var = "ahmed"
var = 10
var = 10.5

# Question 7
name1 = "Max"
name2 = "Max"

if name1 is name2:
    print("They are the same")
else:
    print("they are different")

# Question 8
number1 = 127
number2 = 127
number3 = 128
number4 = 128

if number1 is number2:
    print("127 is the same")
else:
    print("127 is different")

if number3 is number4:
    print("128 is the same")
else:
    print("128 is different")

# Question 9
multi_line_string = f"""
Hello
My name is {name}
"""
print(multi_line_string)

# Question 10
print("0.1+0.2 = ")
print(0.1 + 0.2)

num1 = Decimal("0.1")
num2 = Decimal("0.2")

print("0.1+0.2 = (With Decimal) ")
print(num1 + num2)
