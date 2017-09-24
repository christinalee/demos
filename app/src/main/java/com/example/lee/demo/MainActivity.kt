package com.example.lee.demo

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import java.net.URI
import java.util.concurrent.TimeUnit

//fun String.somethingelse(): Int {
//    return length
//}
//class MainActivity : AppCompatActivity() {
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//    }
//
//
//    private fun String.something(): Int {
//        return this.length
//    }
//
//
//    // *******************************************************
//    // region Elvis operator --- Type system (Lee)
//    // *******************************************************
//
//    fun elvisAndTypeExample() {
//
//    }
//
//    // Unit
//
//    // Void and unit are essentially the same concept.
//    // They're used to indicate that a function is called
//    // only for its side effects. The difference is that
//    // unit actually has a value. That means that unit functions
//    // can be used generically wherever a generic first
//    // class function is required. The C derived languages
//    // miss out by treating void functions as functions that
//    // don't return anything instead of as functions that
//    // don't return anything meaningful.
//    // - James Iry
//
//    val thing = Unit
//
//    interface Parameterized<in T, out R> {
//        fun computeSomething(t: T) : R
//    }
//
//    // example ideas
//    class Result<in Q, out T> {
//        fun invoke(f: Q): T {
//            TODO()
//        }
//    }
//    // some apis return boolean == success
//    // some api no op
//
//
//    class Example : Parameterized<String, Unit> {
//        override fun computeSomething(t: String) {
//            TODO("not implemented")
//        }
//
//        fun somethingElse() {
//            val arg: Unit = computeSomething("String")
//        }
//    }
//
//
//    fun defaultReturn(): Unit {
//
//    }
//
//
//    // Any vs object (show primitives)
//    // TODO
//
//    // T? --> T
//    fun nullability() {
//        var anInt: Int = 1
//        var anNumber: Number = 2.0
//
//        anInt = anNumber
//
//        val string = "String"
//        val nullableThing: String? = string
//        val nonNull: String = nullableThing
//    }
//
//    // explain type uncertainty across kotlin java boundaries
//    // explain the decision to make platform types because of the permutations
//
//    fun something(): Any {
//        TODO()
//    }
//
//    // Nothing type
//    fun nothing(nullable: String?) {
//        // Why does this work?
////         TODO()
//        val nonNull: String = nullable ?: throw NotImplementedError()
//
//        val thing: Nothing = TODO() // Nothing type indicates the function never completes
//        // That's why the assignment is unreachable
//    }
//
//    // *******************************************************
//    // endregion Elvis operator --- Type system (Lee)
//    // *******************************************************
//
//
//    // *******************************************************
//    // region Generics (Lee)
//    // *******************************************************
//
//    // 1
//    // Consumer in, producer out
//    abstract class CIPOExample<T> {
//        abstract fun produceT(): T
//
//        abstract fun consumeT(t: T)
//
//        abstract fun consumeAndProduce(t: T): T
//    }
//
//    // 2
//    // Intellij will suggest you constrain this, but if both work, why should you?
//    class IntelliJHintExample<out T> {
//        fun produceT() : T {
//            TODO()
//        }
//    }
//    val classInstance: IntelliJHintExample<Int> = IntelliJHintExample()
//
//
//    // 3
//    // PECS -- producer extends, consumer super
//
//    // Empty class hierarchy
//    open class One
//    open class Two: One()
//    class Three : Two()
//
//    class Producer<out T> {
//        fun produceT() : T {
//            TODO()
//        }
//    }
//
//    class Consumer<in T> {
//        fun consumeT(t: T) {
//            TODO()
//        }
//    }
//
//    // insert java code snippet ? super Type
//
//    // Why do you want to use in/out keywords?
//    fun consumerSuper() {
//        val fooIn = Consumer<Two>()
//
//        // Two is the super of Three, so this works for `in`
//        val thisBreaks: Consumer<Three> = fooIn // todo vocab on super/child?
//    }
//
//    // insert java code snippet ? super Type
//
//    fun producerExtends() {
//        val fooOut = Producer<Three>()
//        // Three extends Two, so this works for `out`
//        val thisBreaks: Producer<Two> = fooOut
//    }
//
//
//    // 4
//    // Not T to T
//    interface Base<in T: Number> {
//        fun consumeT(t: T)
//    }
//    class Implementing : Base<Number> {
//        override fun consumeT(t: Int) {
//            TODO("not implemented")
//        }
//
//    }
//
//    fun genericsExample() {
//        // variance relates Both to Both and not T to T
//        // in/out will affect assining one foo to another foo, but not change what T Both can be
//        // parameterized by
//
////        use example where you change T's and show that in/out doesn't have an affect but
////        that when you try to assign one foo to a variable with another foo type it can break
////
////        differentiate between Conusmer in, producer out and PECS (producer extends, consumer super)
//    }
//
//    // *******************************************************
//    // endregion Generics (Lee)
//    // *******************************************************
//
//
//    // *******************************************************
//    // region Crossinline/inlining in general/reified (Lee)
//    // *******************************************************
//
//    // NOTES:
//    // Inline to reduce cost of lambdas
//
//    // What is the cost of a lambda? --> What is a lambda
//
//    // How does inlining reduce the cost of lambdas?
//
//    // What is the cost of inlining?
//    // Gotchas (returns, sequences maybe)
//    // Inlining small functions otherwise generated code gets huge
//    // Inlined lambdas can return from top level func, non inlined lambdas cannot
//    // break and continue not supported yet, only return
//
//    // Related concepts
//    // noinline -- if you only want some of the lambdas to be inlined and not others
//    // cross inline
//    // you want a function to be inlined, but you are passing a lambda to another
//    // context within that inlined function, and so non-local control flow is not allowed
//    // Show difference between crossinline and noinline
//
//    // crossinline
//    inline fun ArrayList<Int>.performAction(crossinline action: (Int) -> Int) {
//        AsyncTask.execute {
//            for (i in 0..this.size) {
//                this[i] = action(this[i])
//            }
//        }
//    }
//
//    fun inliningWTF() {
//        val someArray = arrayListOf<Int>(1, 2, 3)
//        someArray.performAction { it + 1 }
//    }
//
//
//    // *******************************************************
//    // endregion Crossinline/inlining in general/reified (Lee)
//    // *******************************************************
//
//
//
//
//
//}
