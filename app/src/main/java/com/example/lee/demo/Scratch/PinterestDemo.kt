package com.example.lee.demo.Scratch

import android.util.Log
import android.view.View
import android.widget.LinearLayout

/**
 * Created by lee on 9/19/17.
 */

class PinterestDemo {
    companion object {
        const val TAG: String = "TAG"
    }

    // *****************************************************
    // region Practical
    // *****************************************************

    fun practical() {
        // Platform types
//        val doggo: Dog = AnimalGenerator.getDog()
//        doggo?.name

    }

    // *****************************************************
    // endregion Practical
    // *****************************************************



    // *****************************************************
    // region Concise
    // *****************************************************

    fun concise(requirePrecondition: View?) {
        // 1: Control statements are expressions
        val view = requirePrecondition ?: return



        // 2: Smart cast, generated getters/setters
        if (view is LinearLayout) {
            val orientation = view.orientation
        }
//
//

        // 3: Clicklisteners
        // Long way
        val clickListener = object : View.OnClickListener {
            override fun onClick(v: View?) {
                Log.d(TAG, "This is basically how you need to do click listeners in java")
            }
        }
        view.setOnClickListener(clickListener)

        // vs

        // Shorter way
        view.setOnClickListener {
            Log.d(TAG, "This is a much shorter click listener")
        }




        // 4: Data classes
        data class Person(val name: String,
                          val favoriteNumber: Int = 7,
                          val hasDog: Boolean = true)
        val Bob = Person("Bob")
        val Trudy = Person("Trudy", hasDog = false)

        val areSamePeople = Bob == Trudy
        val bobsId = Bob.hashCode()
        val TrudysTwin = Trudy.copy(favoriteNumber = 3)
    }

    // 5: Class boilerplate reduction
    class AClass(val something: Long) {
        val somethingFromSomething = something + 2

        constructor(something: Long, somethingElse: Int) : this(something)

        init {
            something
        }

        fun useSomething() {
            something
        }
    }

    // *****************************************************
    // endregion Concise
    // *****************************************************



    // *****************************************************
    // region Safe
    // *****************************************************

    enum class SomeEnum { One, Two, Three, Four }
    fun safe() {
        // 1: Mutability
        var fieldThatShouldntChange = 42
        fieldThatShouldntChange = 43


        // 2: Nullability
        val nullableThing: String? = null
        val alsoNullable: String = if (1 == 1) {
            "string"
        } else {
            "otherString"
        }


        // 3: Exhaustivity
        val someSpecificEnum = SomeEnum.Three
        val exhaustiveWhen = when(someSpecificEnum) {
            SomeEnum.One -> "One"
            SomeEnum.Two, SomeEnum.Three -> "Two or three"
            SomeEnum.Four -> "Four"
        }
    }
    // *****************************************************
    // endregion Safe
    // *****************************************************



    // *****************************************************
    // region Interoperable
    // *****************************************************

    fun interoperable() {
        // 1: Property syntax covnersion
        val aVar = PinterestJavaDemo().javaVariable

        // 2: Interfile code calling
        val str: String = PinterestJavaDemo.aJavaMethodThatReturnsSomething()
        val newStr = "Here is a longer string with the old string appended: $str"
        PinterestJavaDemo.aJavaMethodThatNeedsAnArg(newStr)
    }

    // *****************************************************
    // endregion Interoperable
    // *****************************************************
}