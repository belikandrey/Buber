package com.epam.jwd.factory.impl;

import com.epam.jwd.domain.impl.Car;
import com.epam.jwd.domain.impl.Driver;
import com.epam.jwd.exception.FactoryException;
import com.epam.jwd.factory.AbstractFactory;

public class CarFactory implements AbstractFactory<Car> {

    private CarFactory(){}

    private static CarFactory carFactory;

    public static CarFactory getInstance(){
        if(carFactory==null){
            carFactory = new CarFactory();
        }
        return carFactory;
    }

    @Override
    public Car create(Object... args) throws FactoryException {
        switch (args.length){
            case 6 :
                return new Car((long) args[0], (Driver) args[1], (String) args[2], (String) args[3], (String)args[4], (String) args[5]);
            case 5:
                return new Car((Driver) args[0], (String) args[1], (String) args[2], (String) args[3], (String) args[4]);
            default:
                throw new FactoryException("Car factory : Illegal arguments length : "+args.length);
        }
    }
}
