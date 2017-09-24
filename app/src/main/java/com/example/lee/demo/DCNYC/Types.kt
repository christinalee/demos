package com.example.lee.demo.DCNYC


class Dog


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

























// Why is this cool? Well we have null safety, because you can only assign a value to
// something that is a super type of it

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




















