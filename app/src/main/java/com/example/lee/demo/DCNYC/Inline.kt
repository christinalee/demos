package com.example.lee.demo.DCNYC

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


