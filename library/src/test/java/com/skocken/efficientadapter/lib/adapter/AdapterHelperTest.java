package com.skocken.efficientadapter.lib.adapter;

import org.junit.Test;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

public class AdapterHelperTest {

    @Test
    public void testIsAssignableFrom_Correct() throws Exception {
        Class<?>[] params = new Class[]{Vehicle.class, Car.class, Dog.class};
        assertTrue(AdapterHelper.isAssignableFrom(params,
                                                  Vehicle.class, Car.class, Animal.class));
    }

    @Test
    public void testIsAssignableFrom_WrongParameterLength() throws Exception {
        Class<?>[] params = new Class[]{Vehicle.class, Car.class, Animal.class};
        assertFalse(AdapterHelper.isAssignableFrom(params, Vehicle.class));
    }
    @Test
    public void testIsAssignableFrom_Incorrect() throws Exception {
        Class<?>[] params = new Class[]{Vehicle.class, Car.class, Animal.class};
        assertFalse(AdapterHelper.isAssignableFrom(params,
                                                   Vehicle.class, Car.class, Car.class));
    }

    private static class Vehicle {

    }

    private static class Car extends Vehicle {

    }

    private static class Animal {

    }

    private static class Dog extends Animal {

    }
}