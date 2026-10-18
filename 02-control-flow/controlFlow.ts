// Question 1
console.log("----- Question 1 -----");

for (let x = 1; x <= 20; x++) {
	if (x % 3 === 0 && x % 5 === 0) {
		console.log("FizzBuzz");
	} else if (x % 3 === 0) {
		console.log("Fizz");
	} else if (x % 5 === 0) {
		console.log("Buzz");
	} else {
		console.log(x);
	}
}

// Question 2
console.log("----- Question 2 -----");

const stringList = ["apple", "banana", "cherry", "date", "elderberry"];

// for...of gives values (like Python's for...in)
for (const fruit of stringList) {
	console.log(fruit);
}

// for...in gives keys: for arrays, the indices as strings ("0", "1", ...). Avoid it on arrays.
for (const index in stringList) {
	console.log(stringList[index]);
}

// Index-based loop
for (let i = 0; i < stringList.length; i++) {
	console.log(stringList[i]);
}

// Question 3
console.log("----- Question 3 -----");

// Starting at 0 shows the difference: while checks first, do...while runs once
let num = 0;
let iteration = 0;

while (num > 1) {
	num /= 2;
	iteration++;
}

console.log(`The iteration count is ${iteration} and the final number is ${num}`);

let num2 = 0;
let iteration2 = 0;

do {
	num2 /= 2;
	iteration2++;
} while (num2 > 1);

console.log(`The iteration count for do..while is ${iteration2} and the final number is ${num2}`);

// Question 4
console.log("----- Question 4 -----");

// JS falsy values: false, 0, -0, 0n, "", null, undefined, NaN.
// Everything else is truthy, including {} and [] (unlike Python).
const falsyValues: unknown[] = [false, 0, "", null, undefined, NaN, -0, 0n, {}, []];

for (const value of falsyValues) {
	if (!value) {
		console.log(`${value} is falsy`);
	} else {
		console.info(`${value} is truthy`);
	}
}

// Question 5
console.log("----- Question 5 -----");

const mixedList: (number | boolean | string | null | undefined | unknown[])[] = [
	1,
	false,
	true,
	"",
	"hello",
	null,
	undefined,
	[],
];

// switch compares with ===. Empty stacked cases fall through on purpose;
// noFallthroughCasesInSwitch only rejects a case with code and no break.
// NaN can never match a case, because NaN === NaN is false.
for (const value of mixedList) {
	switch (value) {
		case 0:
		case false:
		case "":
		case null:
		case undefined:
			console.log(`${value} is falsy`);
			break;
		default:
			console.log(`${value} is truthy`);
			break;
	}
}

// Question 7
console.log("----- Question 7 -----");

const target = 55;

// A labeled break exits both loops at once
outer: for (let i = 1; i <= 100; i++) {
	for (let j = 1; j <= 100; j++) {
		if (i + j === target) {
			console.log(`Pair found: (${i}, ${j})`);
			break outer;
		}
	}
}

// Question 8
console.log("----- Question 8 -----");

// typeof narrows the type inside each branch; arrays need Array.isArray (typeof [] is "object")
for (const item of mixedList) {
	if (typeof item === "number") {
		console.log(`Number found: ${item}`);
	} else if (typeof item === "string") {
		console.log(`String found: ${item}`);
	} else if (typeof item === "boolean") {
		console.log(`Boolean found: ${item}`);
	} else if (Array.isArray(item)) {
		console.log(`Array found: ${item}`);
	} else if (item === null) {
		console.log("Null value found");
	} else if (item === undefined) {
		console.log("Undefined value found");
	}
}

// Question 10
console.log("----- Question 10 -----");

// console.log always ends the line, so build each row as a string first
const separator = "-".repeat(36);

for (let i = 1; i <= 9; i++) {
	console.log(separator);
	let row = "";
	for (let j = 1; j <= 9; j++) {
		row += `${String(i * j).padStart(3)}|`;
	}
	console.log(row);
}
console.log(separator);
