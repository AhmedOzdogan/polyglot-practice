// Question 1
console.log("----- Question 1 -----");

// No typeof check needed inside TS: the type checker rejects non-numbers.
// NaN is still a number, though: Math.max(NaN, 10) returns NaN.
function greater(num1: number, num2: number): number {
	return Math.max(num1, num2);
}

const resultQ1 = greater(5, 10);
// const wrongResultQ1 = greater("a", 10); // Type error, but tsx would still run it
console.log(`Greater number is ${resultQ1}`);

// Question 2
console.log("----- Question 2 -----");

// Default values work like Python, but are created fresh on every call
function multiply(num1: number, num2 = 10): number {
	return num1 * num2;
}

const resultQ2_1 = multiply(10); // 100
console.log(`Result 1 is ${resultQ2_1}`);

const resultQ2_2 = multiply(10, 2); // 20
console.log(`Result 2 is ${resultQ2_2}`);

// Question 3
console.log("----- Question 3 -----");

// Overload signatures describe the allowed calls; the one implementation below handles both
function area(radius: number): number;
function area(width: number, height: number): number;
function area(a: number, b?: number): number {
	if (b === undefined) {
		return Math.PI * a * a; // circle: πr²
	}
	return a * b; // rectangle
}

const resultQ3_1 = area(5);
const resultQ3_2 = area(10, 5);

console.log(`The area of a circle is ${resultQ3_1}`);
console.log(`The area of a rectangle is ${resultQ3_2}`);

// Question 4
console.log("----- Question 4 -----");

// Rest parameter: collects all arguments into an array
function mySum(...numbers: number[]): number {
	let total = 0;
	for (const n of numbers) {
		total += n;
	}
	return total;
}

console.log(`Sum of 1, 2, 3: ${mySum(1, 2, 3)}`); // 6

const numbers = [...Array(101).keys()]; // 0..100
// Spread: each element becomes a separate argument
console.log(`Sum of 0..100: ${mySum(...numbers)}`); // 5050

// Question 5
console.log("----- Question 5 -----");

// No keyword arguments in TS: pass one object and destructure it by name, with defaults
function describePerson({
	name,
	age,
	city = "Berlin",
}: {
	name: string;
	age: number;
	city?: string;
}): void {
	console.log(`${name} is ${age} years old and lives in ${city}`);
}

describePerson({ name: "Ahmed", age: 30, city: "Munich" });
describePerson({ city: "Munich", age: 30, name: "Ahmed" }); // any order, same result
describePerson({ name: "Ahmed", age: 30 }); // city uses the default "Berlin"
// describePerson({ name: "Ahmed", agee: 30 }); // Type error: 'agee' does not exist in type

// Question 6
console.log("----- Question 6 -----");

// Same rule as Java: a number is copied, an array is passed as a reference to the same array
function modifyValues(num: number, array: number[]): void {
	num += 5;
	array.pop();
	console.log(`Inside function: number = ${num}, array = [${array}]`);
}

const number = 5;
const numbers2 = [1, 2, 3];
modifyValues(number, numbers2);
console.log(`After call: number = ${number}, array = [${numbers2}]`); // 5, [1,2]

// Question 7 is Java-only (static vs instance methods)

// Question 8
console.log("----- Question 8 -----");

// Option 1: a tuple type (fixed-length array with a type per position)
type MyTuple = [string, number];

function whoAreYou(): MyTuple {
	return ["Ahmed", 30]; // brackets required: "return a, b" would return only b
}

const [name, age] = whoAreYou(); // array destructuring
console.log(`Tuple: ${name}, ${age}`);

// Option 2: an object type (named fields, clearer at the call site)
type Person = { name: string; age: number };

function whoAreYouObject(): Person {
	return { name: "Ahmed", age: 30 };
}

const { name: personName, age: personAge } = whoAreYouObject(); // object destructuring, renamed
console.log(`Object: ${personName}, ${personAge}`);

// Question 9
console.log("----- Question 9 -----");

function factorialNumber(n: number): number {
	if (n <= 1) return 1; // base case
	return n * factorialNumber(n - 1);
}

console.log(`Factorial of 20 is ${factorialNumber(20)}`); // 2432902008176640000
console.log(`Factorial of 25 is ${factorialNumber(25)}`); // 1.5511210043330986e+25, past 2^53 number loses precision

function factorialBigint(n: number): bigint {
	if (n <= 1) return 1n;
	return BigInt(n) * factorialBigint(n - 1); // stays bigint the whole way, so no precision loss
}

console.log(`Factorial of 20 is (bigint) ${factorialBigint(20)}`); // 2432902008176640000
console.log(`Factorial of 25 is (bigint) ${factorialBigint(25)}`); // 15511210043330985984000000

// Question 10
console.log("----- Question 10 -----");

function addTen(num: number): number {
	return num + 10;
}

function doTwice(f: (x: number) => number, value: number): number {
	return f(f(value));
}

console.log(`0 + 10 twice is ${doTwice(addTen, 0)}`); // 20
console.log(`0 + 10 twice is (lambda) ${doTwice((x) => x + 10, 0)}`); // 20
