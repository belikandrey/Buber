package com.epam.jwd.factory.impl;

import com.epam.jwd.domain.impl.User;
import com.epam.jwd.domain.impl.UserRole;
import com.epam.jwd.exception.FactoryException;
import com.epam.jwd.factory.AbstractFactory;

import java.util.List;

public class UserFactory implements AbstractFactory<User> {

    private UserFactory(){}

    private static UserFactory userFactory;

    public static UserFactory getInstance(){
        if(userFactory==null){
            userFactory = new UserFactory();
        }
        return userFactory;
    }

    @Override
    public User create(Object... args) throws FactoryException {
        switch (args.length){
            case 4:
                return new User((long) args[0], (String) args[1], (String) args[2], (List<UserRole>) args[3]);
            case 3:
                return new User((String) args[0], (String) args[1],(List<UserRole>) args[2]);
            default:
                throw new FactoryException("User factory : Illegal arguments length : "+args.length);
        }
    }
}
