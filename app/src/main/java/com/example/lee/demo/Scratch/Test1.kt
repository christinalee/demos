package com.example.lee.demo.Scratch

class Dog

// *******************************************************
// region --- Type system (Lee)
// *******************************************************

// The Kotlin type system is the basis for many really cool things we can do.

// <Picture of type system>

// Subtype: A type A is a subtype of type B if A can be used anywhere B can
// E.g. Cat is a subtype of animal because anywhere you need an animal a cat will suffice

// In simple cases, a subtype can be used interchangeably with a subclass
val str: String = "string" // <-- String is the class and the type

// But this quickly falls apart with nullables
val str2: String? = "string" // <-- String is the class, String? is the type (can be null or can be string)

// In simple cases, classes can create two types class. With Collections, there can be near infinite
val coll1: List<Int> = TODO()
val coll2: List<String>? = TODO() // <-- List is the class, but these are all valid types
val coll3: List<List<Pair<Int, List<String>>>> = TODO()

// Why is this cool? Well we have null safety, because you can only assign a value to something that is a super type of it
val num: Number = Int.MAX_VALUE // Number is a super type of Int
val nullableStr: String? = null
val nonNullableStr: String = nullableStr // Won't compile, because String? is not a subtype of String

// Return to picture of the type system

// Stress Nothing and Any? types as the subtype and supertype of all types respectively
// They'll be relevant again in the star projections

// Fast blast Nothing type rundown
fun notImplemented(): List<List<List<Dog>>> {
    // Why does this work?
    TODO()
}


fun unreachable(someNullable: String?) {
    val nonNull: String = someNullable ?: throw NotImplementedError() // Won't ever assign if null

    val thing: Nothing = TODO() // Nothing type indicates the function never completes
                                // That's why the assignment is unreachable
}

// Any type vs object

// Any is the default super type of all classes (in the same way unit is the default return type)
class Something
// is equivalent to
class Something2: Any()

fun primitiveNiceties() {
    val primitive: Int = 1
    val anyField: Any = primitive // Kotlin "primitives" are all subtypes of Any
                                  // Boxing under the hood
}

// Any is a java object at runtime, but it is not the same as Object because at compile time it has
// only hashCode, equals, and toString
fun any() {
    val value: Any = 1
    value.hashCode()
    value.equals("other")
    value.toString()

    // Still have access to the object, but it's a warning
    (value as Object).notify()

    // As expected, Any is a subtype of Any?
    val any3: Any? = 2
    val any4: Any = any3 // Doesn't work
}


// Unit

// Void and unit are essentially the same concept.
// They're used to indicate that a function is called
// only for its side effects. The difference is that
// unit actually has a value. That means that unit functions
// can be used generically wherever a generic first
// class function is required. The C derived languages
// miss out by treating void functions as functions that
// don't return anything instead of as functions that
// don't return anything meaningful.
// - James Iry

fun defaultReturn(): Unit { }

val thing = Unit

// Why is this useful?
interface ResultOrError<out R: Any, out E: Exception> {
    // some apis return boolean == success
    // some api are side effects who's proper result type would be Unit
}
class ExampleResult : ResultOrError<Unit, UninitializedPropertyAccessException>


// NOT COVERED: Platform types


// *******************************************************
// endregion Elvis operator --- Type system (Lee)
// *******************************************************

















// *******************************************************
// region Generics (Lee)
// *******************************************************

// The Basics
// A - B - C - D - E - F - G, T is a type param with upper bound An-y?
fun <T> defaultUpper() { }
fun <T: Any?> explicitUpper() { }


fun <T> ruhRho(t: T) { // Default generic is nullable
    t.hashCode()
}
fun <T: Any> ohhKay(t: T) { // Non nullable generic
    t.hashCode()
}


// Multiple constraints
interface Animal
interface Fuzzy
fun <T> getFuzzyAnimal(): T where T : Animal, T: Fuzzy {
    TODO()
}

// TODO Insert "Erase" ASCII art

// Just like Java, Kotlin has type erasure, which means at run time you can't query types
fun <T> genericCollectionFun(list: Collection<T>) {
    if (list is List<Int>) {
        // ????
    }

    // Star projections!
    // star projection: no information about a generic argument


    if (list is List<*>) { // if (this is a list of any type)
        // WOOHOO!

        // But also, caution! Abuse of * can lead to runtime exceptions
        val unsafe = list as List<Int> // Will succeed at runtime
        val mightCrash: Int = unsafe.first() // Can crash at runtime
    }
}



// Hashtag Relatable:

interface MyInterface<T> {
    fun gimmeSomethingAwesome(): T
}

class WorksAnyways: MyInterface<String> {
    override fun gimmeSomethingAwesome(): String {
        return "This works anyways, why you so bossy?"
    }
}


// Da fuq is dis?
interface InOutExample<T> {
    fun consumer(t: T)

    fun producer(): T

    fun both(t: T): T
}

// We did it!
// Except... like, what does that mean?


// TODO insert VARIANCE ascii

// Empty class hierarchy
open class One
open class Two: One()
class Three : Two()

class Foo<T> {
    fun both(t: T): T {
        TODO()
    }
}

class FooOut<out T> {
    fun produceT() : T {
        TODO()
    }
}

class FooIn<in T> {
    fun consumeT(t: T) {
        TODO()
    }
}

// THE CONCEPT OF VARIANCE DESCRIBES HOW TYPES WITH THE SAME BASE TYPE AND
// DIFFERENT TYPE ARGUMENT RELATE TO EACH OTHER

// Important Reminders:
// 1) subtype == B is a subtype of A if anywhere A can be used B can be used
// 2) storing a value in a variable is only allowed when the value is a subtype of the variable type
// 3) Subclass != Subtype
val ex: Number = Integer.MAX_VALUE




// BREATH





// Slowly.





// Ok, we good?




// 3
// 2
// 1
// ... Let's go!




// invariant: if for any two different type parameters A and B,
// Both<A> is neither a subtype nor a supertype of Both<B>
val invariant1: Foo<Two> = Foo<One>()
val invariant2: Foo<Two> = Foo<Two>() // Invariant
val invariant3: Foo<Two> = Foo<Three>()

// Covariant: subtyping is preserved such
// A is a subtype of B, Both<A> is a subtype of Both<B>
val covariant1: FooOut<Two> = FooOut<One>()
val covariant2: FooOut<Two> = FooOut<Two>() // Covariant
val covariant3: FooOut<Two> = FooOut<Three>()

// Contravariant: subtyping is inverted
// B is a subtype of A, so Both<A> is a subtype of Both<B> <-- notice the reversal
val contra1: FooIn<Two> = FooIn<One>()
val contra2: FooIn<Two> = FooIn<Two>() // Contravariant
val contra3: FooIn<Two> = FooIn<Three>()


// Why do we care?



// VARIANCE RULES PROTECT A CLASS FROM MISUSE BY EXTERNAL CLIENTS <-- Listen up folks
























// 3
// PECS -- producer extends, consumer super

// Empty class hierarchy
//open class One
//open class Two: One()
//class Three : Two()



// insert java code snippet ? super Type

// Why do you want to use in/out keywords?
fun consumerSuper() {
    val fooIn = FooIn<Two>()

    // Two is the super of Three, so this works for `in`
    val thisBreaks: FooIn<Three> = fooIn // todo vocab on super/child?
}

// insert java code snippet ? super Type

fun producerExtends() {
    val fooOut = FooOut<Three>()
    // Three extends Two, so this works for `out`
    val thisBreaks: FooOut<Two> = fooOut
}


// 4
// Not T to T
interface Base<in T: Number> {
    fun consumeT(t: T)
}
//class Implementing : Base<Number> {
//    override fun consumeT(t: Int) {
//        TODO("not implemented")
//    }
//
//}

fun genericsExample() {
    // variance relates Both to Both and not T to T
    // in/out will affect assining one foo to another foo, but not change what T Both can be
    // parameterized by

//        use example where you change T's and show that in/out doesn't have an affect but
//        that when you try to assign one foo to a variable with another foo type it can break
//
//        differentiate between Conusmer in, producer out and PECS (producer extends, consumer super)
}

// *******************************************************
// endregion Generics (Lee)
// *******************************************************


// *******************************************************
// region Crossinline/inlining in general/reified (Lee)
// *******************************************************

// NOTES:
// Inline to reduce cost of lambdas

// What is the cost of a lambda? --> What is a lambda

// How does inlining reduce the cost of lambdas?

// What is the cost of inlining?
// Gotchas (returns, sequences maybe)
// Inlining small functions otherwise generated code gets huge
// Inlined lambdas can return from top level func, non inlined lambdas cannot
// break and continue not supported yet, only return

// Related concepts
// noinline -- if you only want some of the lambdas to be inlined and not others
// cross inline
// you want a function to be inlined, but you are passing a lambda to another
// context within that inlined function, and so non-local control flow is not allowed
// Show difference between crossinline and noinline

// crossinline
inline fun ArrayList<Int>.performAction(crossinline action: (Int) -> Int) {
//    AsyncTask.execute {
//        for (i in 0..this.size) {
//            this[i] = action(this[i])
//        }
//    }
}

fun inliningWTF() {
    val someArray = arrayListOf<Int>(1, 2, 3)
    someArray.performAction { it + 1 }
}


// *******************************************************
// endregion Crossinline/inlining in general/reified (Lee)
// *******************************************************




