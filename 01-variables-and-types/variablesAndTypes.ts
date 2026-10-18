// Question 1
const name = "Ahmed";
console.log(name);

// Question 2
const count = 1;
// count = 2; // Error: cannot assign to a const, it must be let
console.log(count);

// Question 3
// TS has no int type: integers are only exact up to MAX_SAFE_INTEGER
const num = Number.MAX_SAFE_INTEGER;
console.log(num);
const num2 = num + 1;
const num3 = num + 2;
console.log(num2, num3);

if (num2 === num3) {
	console.warn("Precision lost: +1 and +2 give the same result");
} else {
	console.info("They are different");
}

// bigint has no upper limit, like Python int
const big = BigInt(Number.MAX_SAFE_INTEGER) + 2n;
console.log("bigint:", big);

// Question 4
// No integer division operator; Math.trunc matches Java (rounds toward zero)
const div1 = Math.trunc(7 / 2);
const div2 = 7 / 2;

console.log("Integer answer is", div1);
console.log("Float answer is", div2);
console.log("Negative: floor", Math.floor(-7 / 2), "trunc", Math.trunc(-7 / 2));

// Question 5
let type1: number = 5;
let type2 = 5; // inferred as number

// @ts-expect-error string is not assignable to number
type1 = "Ahmed";
// @ts-expect-error inferred type is still number
type2 = "Ahmed";

// Runs anyway: tsx strips types without checking them
console.log("Type 1 is", type1);
console.log("Type 2 is", type2);

// Question 7
// Strings are primitives: === always compares content
const str1 = "Ahmed";
const str2 = "Ahm" + "ed".toLowerCase();
console.log("Strings equal:", str1 === str2);

// Objects compare by reference, like Java ==
const obj1 = { name: "Ahmed" };
const obj2 = { name: "Ahmed" };
console.log("Objects equal:", obj1 === obj2);

// Question 8
// Numbers are never boxed, so there is no cache: === always compares values
const n127a = 127;
const n127b = 127;
const n128a = 128;
const n128b = 128;
console.log("127 is the same:", n127a === n127b);
console.log("128 is the same:", n128a === n128b);

// Question 9
const multiLine = `Hello
My name is ${name}
`;
console.log(multiLine);

// Question 10
console.log(0.1 + 0.2);

// No decimal type natively; toFixed only rounds for display
console.log((1.005).toFixed(2)); // "1.00", not "1.01"

// Exact alternative: calculate in integer cents
const cents = 10 + 20;
console.log(cents / 100);
