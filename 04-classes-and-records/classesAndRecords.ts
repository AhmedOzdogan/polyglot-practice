// Question 1
console.log("----- Question 1 -----");

class Point {
	// public in front of a parameter declares the field AND assigns it
	constructor(
		public x: number,
		public y: number,
	) {}

	// No origin parameter needed: the origin is always (0, 0)
	distanceToOrigin(): number {
		return Math.sqrt(this.x ** 2 + this.y ** 2);
	}
}

const pointQ1_1 = new Point(3, 4);
console.log(`Distance to the origin is ${pointQ1_1.distanceToOrigin()}`); // 5

const pointQ1_2 = new Point(5, 12);
console.log(`Distance to the origin is ${pointQ1_2.distanceToOrigin()}`); // 13

// Question 2
console.log("----- Question 2 -----");

class PrivatePoint {
	#y: number; // JS private: declared in the class body, enforced at runtime

	constructor(
		private x: number, // TS private: checked by the compiler only
		y: number,
	) {
		this.#y = y;
	}

	getX(): number {
		return this.x;
	}

	getY(): number {
		return this.#y;
	}

	distanceToOrigin(): number {
		return Math.sqrt(this.x ** 2 + this.#y ** 2);
	}
}

const pointQ2 = new PrivatePoint(3, 4);
console.log("Printed object (x shows, #y is hidden):", pointQ2);
console.log(`Getters: x = ${pointQ2.getX()}, y = ${pointQ2.getY()}`);
console.log(`Distance to the origin is ${pointQ2.distanceToOrigin()}`);

// TS private: compile error from outside...
// @ts-expect-error Property 'x' is private
console.log(`Outside access to private x (still runs): ${pointQ2.x}`);
// ...but at runtime it's a normal property, so a cast gets around it
// biome-ignore lint/suspicious/noExplicitAny: demo of bypassing private
console.log(`Outside access to private x via cast: ${(pointQ2 as any).x}`);

// JS private: pointQ2.#y is a syntax error outside the class, and no cast helps
// biome-ignore lint/suspicious/noExplicitAny: demo that # can't be bypassed
console.log(`Outside access to #y via cast: ${(pointQ2 as any)["#y"]}`); // undefined

// Question 3
console.log("----- Question 3 -----");

class CountedPoint {
	static count = 0; // static: one copy on the class, shared by all instances

	constructor(
		public x: number,
		public y: number,
	) {
		CountedPoint.count++; // through the class name, not this.count
	}
}

const pointQ3_1 = new CountedPoint(1, 2);
const pointQ3_2 = new CountedPoint(3, 4);
const pointQ3_3 = new CountedPoint(5, 6);
console.log(`Total points created: ${CountedPoint.count}`); // 3

// Question 4
console.log("----- Question 4 -----");

class Person {
	constructor(
		public name: string,
		public age: number,
	) {}

	// No override keyword: that's only for classes that extend another class
	toString(): string {
		return `Person(name=${this.name}, age=${this.age})`;
	}
}

class PlainPerson {
	constructor(public name: string) {}
}

const bob = new Person("Bob", 30);
console.log("console.log ignores toString:", bob); // Person { name: 'Bob', age: 30 }
console.log(`Template literal calls toString: ${bob}`); // Person(name=Bob, age=30)
console.log(`Plain class without toString: ${new PlainPerson("Ann")}`); // [object Object]

// Question 5
console.log("----- Question 5 -----");

class Student {
	constructor(
		public name: string,
		public studentId: number,
	) {}
}

const newStudents = [
	new Student("Bob", 101),
	new Student("Jack", 102),
	new Student("Isac", 103),
	new Student("Yamal", 101), // same id as Bob
];

// A Set compares objects by identity, and JS has no equals/hashCode to override
const studentSet = new Set(newStudents);
console.log(`Set of objects: ${studentSet.size} students`); // 4

// Workaround: key a Map by the field that defines "equal" (numbers compare by value)
const classroom = new Map<number, Student>();
for (const s of newStudents) {
	if (!classroom.has(s.studentId)) classroom.set(s.studentId, s); // keep the first one
}
console.log(`Map keyed by id: ${classroom.size} students`); // 3

// Question 6-7
console.log("----- Question 6-7 -----");

// Closest TS match to a Java record / frozen dataclass: all fields readonly.
// Unlike those, TS generates no equals/hashCode/toString, so toString is written by hand.
class Book {
	constructor(
		public readonly id: number,
		public readonly name: string,
		public readonly page: number,
		public readonly price: number,
		public readonly quantity: number,
	) {
		// Validate the parameters; the shortcut has already assigned the fields
		if (!Number.isInteger(id) || id < 0) {
			throw new Error(`ID must be a whole number >= 0, got ${id}`);
		}
		if (name.trim() === "") {
			throw new Error("Name must not be empty");
		}
		if (!Number.isInteger(page) || page < 0) {
			throw new Error(`Page must be a whole number >= 0, got ${page}`);
		}
		if (!Number.isFinite(price) || price < 0) {
			throw new Error(`Price must be >= 0, got ${price}`);
		}
		if (!Number.isInteger(quantity) || quantity < 0) {
			throw new Error(`Quantity must be a whole number >= 0, got ${quantity}`);
		}
	}

	// Computed, not stored: can't be set from outside and can't go stale
	get totalPrice(): number {
		return this.price * this.quantity;
	}

	toString(): string {
		return `Book(${this.id}, ${this.name}, ${this.page} pages, $${this.price} x ${this.quantity} = $${this.totalPrice})`;
	}

	// Returns a new Book instead of mutating this one (Question 8)
	withNewNameAndId(newId: number, newName: string): Book {
		return new Book(newId, newName, this.page, this.price, this.quantity);
	}
}

const book1 = new Book(101, "Dune", 412, 18, 3);
console.log(`Valid book: ${book1}`);

// @ts-expect-error Cannot assign to 'price' because it is a read-only property
book1.price = 0; // tsc rejects this, but tsx skips type checks and the runtime allows it
console.log(`After the "forbidden" write, price is ${book1.price}`); // 0: readonly is compile-time only

// Invalid inputs are rejected at construction
const badBooks: [number, string, number, number, number][] = [
	[102, "   ", 300, 10, 1], // empty name
	[103, "1984", -328, 12, 5], // negative pages
	[104, "Sapiens", 443, -22, 4], // negative price
	[105, "Clean Code", 464, 35, 1.5], // fractional quantity
];
for (const args of badBooks) {
	try {
		new Book(...args);
	} catch (e) {
		console.log(`Rejected: ${(e as Error).message}`);
	}
}

// Question 8
console.log("----- Question 8 -----");

// Fresh book: book1's price was changed to 0 by the readonly demo above
const original = new Book(201, "Dune", 412, 18, 3);
const newBook = original.withNewNameAndId(202, "Dune Hardcover");
console.log(`Original: ${original}`); // unchanged
console.log(`New:      ${newBook}`);
console.log(`Same object? ${original === newBook}`); // false

// Copies go through the constructor, so validation still applies
try {
	original.withNewNameAndId(203, "");
} catch (e) {
	console.log(`Rejected copy: ${(e as Error).message}`);
}

// What stops a direct mutation? Only tsc: readonly is gone at runtime
// @ts-expect-error Cannot assign to 'name' because it is a read-only property
original.name = "Changed";
console.log(`After readonly write: ${original.name}`); // Changed

// Object.freeze blocks it at runtime too (ES modules run in strict mode, so it throws)
const frozen = Object.freeze(new Book(204, "1984", 328, 12, 5));
try {
	// @ts-expect-error Cannot assign to 'name' because it is a read-only property
	frozen.name = "Changed";
} catch (e) {
	console.log(`Frozen write blocked: ${(e as Error).message}`);
}

// Question 8
// Question 10
console.log("----- Question 10 -----");

// #balance (runtime-private) + a getter with no setter: deposit/withdraw are the only ways in
class BankAccount {
	readonly id: string = crypto.randomUUID();
	#balance = 0;

	constructor(readonly owner: string) {
		if (owner.trim() === "") {
			throw new Error("Owner name must not be empty");
		}
	}

	get balance(): number {
		return this.#balance;
	}

	deposit(amount: number): void {
		// !isFinite also rejects NaN, which would otherwise pass amount <= 0
		if (!Number.isFinite(amount) || amount <= 0) {
			throw new Error(
				`Deposit amount must be a positive number, got ${amount}`,
			);
		}
		this.#balance += amount;
	}

	withdraw(amount: number): void {
		// Validate the input first, then check whether it's affordable
		if (!Number.isFinite(amount) || amount <= 0) {
			throw new Error(
				`Withdrawal amount must be a positive number, got ${amount}`,
			);
		}
		if (amount > this.#balance) {
			throw new Error(
				`Insufficient funds: balance ${this.#balance}, tried to withdraw ${amount}`,
			);
		}
		this.#balance -= amount;
	}

	toString(): string {
		return `BankAccount(owner=${this.owner}, balance=${this.#balance})`;
	}
}

const account = new BankAccount("Bob Marley");
console.log(`New account: ${account}`);

account.deposit(1000);
account.deposit(500); // a second deposit proves it adds instead of replacing
console.log(`After depositing 1000 and 500: ${account.balance}`); // 1500

account.withdraw(400);
console.log(`After withdrawing 400: ${account.balance}`); // 1100

// Every invalid operation fails loudly
const invalidOperations: [string, () => void][] = [
	["empty owner", () => new BankAccount("   ")],
	["negative deposit", () => account.deposit(-100)],
	["zero deposit", () => account.deposit(0)],
	["NaN deposit", () => account.deposit(Number.NaN)],
	["Infinity deposit", () => account.deposit(Number.POSITIVE_INFINITY)],
	["negative withdrawal", () => account.withdraw(-100)],
	["overdraft", () => account.withdraw(5000)],
	[
		"direct write",
		() => {
			// @ts-expect-error Cannot assign to 'balance' because it is a read-only property
			account.balance = 1_000_000; // getter without setter: throws in strict mode
		},
	],
];
for (const [label, operation] of invalidOperations) {
	try {
		operation();
		console.log(`NOT rejected (${label}): this is a bug`);
	} catch (e) {
		console.log(`Rejected (${label}): ${(e as Error).message}`);
	}
}

console.log(`Balance unchanged after all rejections: ${account.balance}`); // 1100
