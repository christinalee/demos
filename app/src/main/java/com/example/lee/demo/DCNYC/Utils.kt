package com.example.lee.demo.DCNYC


// Empty class setup
open class One
open class Two: One()
class Three : Two()

class Both<T> {
    fun both(t: T): T {
        TODO()
    }
}

class Producer<out T> {
    fun produceT() : T {
        TODO()
    }
}

class Consumer<in T> {
    fun consumeT(t: T) {
        TODO()
    }
}







// Random example classes
interface LivingThing
open class Plant: LivingThing
class Tree: Plant()

open class Mammal: LivingThing
class Cat: Mammal()
