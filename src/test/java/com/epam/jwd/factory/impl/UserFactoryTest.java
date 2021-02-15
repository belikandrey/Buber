package com.epam.jwd.factory.impl;

import com.epam.jwd.domain.impl.User;
import com.epam.jwd.domain.impl.UserRole;
import com.epam.jwd.exception.FactoryException;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class UserFactoryTest {
    private final String login = "andreyka";
    private final String password = "1234567";
    private final List<UserRole> userRoles = Collections.singletonList(UserRole.CLIENT);
    private final List<UserRole> secondUserRoles = Collections.singletonList(UserRole.ADMIN);
    private final User user = new User(login, password, userRoles);
    private final UserFactory userFactory = UserFactory.getInstance();

    @Test
    public void rightEqualsUsersTest() throws FactoryException {
        User testUser = userFactory.create(login, password, userRoles);
        assertEquals(testUser, user);
    }

    @Test
    public void wrongEqualsUsersTest() throws FactoryException {
        User testUser = userFactory.create(login, password, secondUserRoles);
        assertNotEquals(testUser, user);
    }

    @Test(expected = FactoryException.class)
    public void wrongUserTest() throws FactoryException {
        userFactory.create(login, password);
        Assert.fail("Expected exception");
    }
}
