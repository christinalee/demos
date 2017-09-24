package com.example.lee.demo.Scratch;

import android.support.annotation.Nullable;

import com.example.lee.demo.Scratch.AnimalGenerator.Dog.FurType;


public class AnimalGenerator {
    private static final String TAG = "TAG";

    static class Dog {
        enum FurType {
            Furry,
            NotFurry
        }

        FurType _furType;
        String _name;

        Dog(FurType furType, String name) {
            _furType = furType;
            _name = name;
        }

        String getName() {
            return _name;
        }
    }


    // *****************************************************
    // region Pragmatic
    // *****************************************************

    @Nullable
    static Dog getDog() {
        int typeOfDog = (int) System.currentTimeMillis() % 3;
        switch(typeOfDog) {
            case 0:
                return new Dog(FurType.Furry, "Rodger");
            case 1:
                return new Dog(FurType.NotFurry, "Larry");
            case 2:
                return null;
            default:
                return null;
        }
    }

    // *****************************************************
    // endregion Pragmatic
    // *****************************************************

}
