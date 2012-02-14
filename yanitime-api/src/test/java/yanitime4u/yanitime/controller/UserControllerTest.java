/**
 * Copyright (c) 2011 Jupitris on Labs.
 *
 * Permission is hereby granted, free of charge, to any person
 * obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 */
package yanitime4u.yanitime.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slim3.datastore.Datastore;
import org.slim3.datastore.EntityNotFoundRuntimeException;
import org.slim3.tester.AppEngineTestCase;

import yanitime4u.yanitime.condition.UserCondition;
import yanitime4u.yanitime.logic.UserLogic;
import yanitime4u.yanitime.model.Places;
import yanitime4u.yanitime.model.Users;
import yanitime4u.yanitime.model.code.Gender;

/**
 * @author jupitris
 * 
 */
public class UserControllerTest extends AppEngineTestCase {

    private final UserController controller = new UserController();

    @Mock
    private UserLogic mock;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        MockitoAnnotations.initMocks(this);
    }

    @Override
    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }

    @Test
    public void testFindById01() {
        // create return value of mock
        Users expected = new Users();
        expected.setKey(Datastore.createKey(Users.class, 1L));
        expected.setUserId("testUser");
        expected.setUserName("testUserName");
        expected.setEmail("testEmail");
        expected.setNickName("testNickName");
        expected.setPassword("password");
        expected.setAge(20);
        expected.setGender(Gender.MALE);
        expected.setVersion(1L);

        Mockito.when(mock.findByKey(1L)).thenReturn(expected);
        controller.setUserLogic(mock);

        Map<String, Long> input = new HashMap<String, Long>();
        input.put("id", 1L);
        Users actual = controller.findById(input);

        Assert.assertNotNull(actual);
        Assert.assertEquals(expected.getKey().getId(), actual.getKey().getId());
        Assert.assertEquals(expected.getUserId(), actual.getUserId());
        Assert.assertEquals(expected.getUserName(), actual.getUserName());
        Assert.assertEquals(expected.getEmail(), actual.getEmail());
        Assert.assertEquals(expected.getNickName(), actual.getNickName());
        Assert.assertEquals(expected.getPassword(), actual.getPassword());
        Assert.assertEquals(expected.getAge(), actual.getAge());
        Assert.assertEquals(expected.getGender(), actual.getGender());
        Assert.assertEquals(expected.getVersion(), actual.getVersion());
    }

    @Test
    public void testFindByIdE01() {
        // create return value of mock
        Users expected = new Users();
        expected.setKey(Datastore.createKey(Users.class, 1L));
        expected.setUserId("testUser");
        expected.setUserName("testUserName");
        expected.setEmail("testEmail");
        expected.setNickName("testNickName");
        expected.setPassword("password");
        expected.setAge(20);
        expected.setGender(Gender.MALE);
        expected.setVersion(1L);

        Mockito.when(mock.findByKey(2L)).thenThrow(EntityNotFoundRuntimeException.class);
        controller.setUserLogic(mock);

        Map<String, Long> input = new HashMap<String, Long>();
        input.put("id", 2L);
        try {
            controller.findById(input);
            Assert.fail("assertion is failure.");
        } catch (RuntimeException e) {
            Assert.assertThat(e, CoreMatchers.instanceOf(EntityNotFoundRuntimeException.class));
        }
    }

    @Test
    public void testFindByIdE02() {
        try {
            controller.findById(null);
            Assert.fail("assertion is failure.");
        } catch (RuntimeException e) {
            Assert.assertThat(e, CoreMatchers.instanceOf(IllegalArgumentException.class));
        }
    }

    @Test
    public void testFindByIdE03() {
        Map<String, Object> input = new HashMap<String, Object>();
        input.put("id", "abcd");
        try {
            controller.findById(null);
            Assert.fail("assertion is failure.");
        } catch (RuntimeException e) {
            Assert.assertThat(e, CoreMatchers.instanceOf(IllegalArgumentException.class));
        }
    }

    @Test
    public void testFindByIdE04() {
        Map<String, Long> input = new HashMap<String, Long>();
        input.put("id", null);
        try {
            controller.findById(input);
            Assert.fail("assertion is failure.");
        } catch (RuntimeException e) {
            Assert.assertThat(e, CoreMatchers.instanceOf(IllegalArgumentException.class));
        }
    }

    @Test
    public void testFindByIdE05() {
        Map<String, Long> input = new HashMap<String, Long>();
        input.put("idd", 1234L);
        try {
            controller.findById(input);
            Assert.fail("assertion is failure.");
        } catch (RuntimeException e) {
            Assert.assertThat(e, CoreMatchers.instanceOf(IllegalArgumentException.class));
        }
    }

    @Test
    public void testFind01() {
        // create return value of mock
        Users expected = new Users();
        expected.setKey(Datastore.createKey(Users.class, 1L));
        expected.setUserId("testUser");
        expected.setUserName("testUserName");
        expected.setEmail("testEmail");
        expected.setNickName("testNickName");
        expected.setPassword("password");
        expected.setAge(20);
        expected.setGender(Gender.MALE);
        expected.setVersion(1L);
        List<Users> expectations = new ArrayList<Users>();
        expectations.add(expected);

        // create condition
        UserCondition condition = new UserCondition();
        condition.setEmail("testEmail");

        Mockito.when(mock.findByCondition(condition)).thenReturn(expectations);
        controller.setUserLogic(mock);

        List<Users> actuals = controller.find(condition);

        Assert.assertNotNull(actuals);
        Assert.assertEquals(1, actuals.size());
        for (Users actual : actuals) {
            Assert.assertEquals(expected.getKey().getId(), actual.getKey().getId());
            Assert.assertEquals(expected.getUserId(), actual.getUserId());
            Assert.assertEquals(expected.getUserName(), actual.getUserName());
            Assert.assertEquals(expected.getEmail(), actual.getEmail());
            Assert.assertEquals(expected.getNickName(), actual.getNickName());
            Assert.assertEquals(expected.getPassword(), actual.getPassword());
            Assert.assertEquals(expected.getAge(), actual.getAge());
            Assert.assertEquals(expected.getGender(), actual.getGender());
            Assert.assertEquals(expected.getVersion(), actual.getVersion());
        }
    }

    @Test
    public void testFindE01() {
        try {
            controller.find(null);
            Assert.fail("assertion is failure.");
        } catch (RuntimeException e) {
            Assert.assertThat(e, CoreMatchers.instanceOf(IllegalArgumentException.class));
        }
    }

    @Test
    public void testCreate01() {
        // create return value of mock
        Users expected = new Users();
        expected.setKey(Datastore.createKey(Users.class, 1L));
        expected.setUserId("testUser");
        expected.setUserName("testUserName");
        expected.setEmail("testEmail");
        expected.setNickName("testNickName");
        expected.setPassword("password");
        expected.setAge(20);
        expected.setGender(Gender.MALE);
        expected.setVersion(1L);

        // create register data
        Users register = new Users();
        register.setUserId("testUser");
        register.setUserName("testUserName");
        register.setEmail("testEmail");
        register.setNickName("testNickName");
        register.setPassword("password");
        register.setAge(20);
        register.setGender(Gender.MALE);

        Mockito.when(mock.create(register)).thenReturn(expected);
        controller.setUserLogic(mock);

        Users actual = controller.create(register);

        Assert.assertNotNull(actual);
        Assert.assertEquals(1L, actual.getKey().getId());
        Assert.assertEquals(expected.getUserId(), actual.getUserId());
        Assert.assertEquals(expected.getUserName(), actual.getUserName());
        Assert.assertEquals(expected.getEmail(), actual.getEmail());
        Assert.assertEquals(expected.getNickName(), actual.getNickName());
        Assert.assertEquals(expected.getPassword(), actual.getPassword());
        Assert.assertEquals(expected.getAge(), actual.getAge());
        Assert.assertEquals(expected.getGender(), actual.getGender());
        Assert.assertEquals(expected.getVersion(), actual.getVersion());
    }

    @Test
    public void testCreateE01() {
        try {
            controller.create(null);
            Assert.fail("assertion is failure.");
        } catch (RuntimeException e) {
            Assert.assertThat(e, CoreMatchers.instanceOf(IllegalArgumentException.class));
        }
    }

    @Test
    public void testUpdate01() {
        // create return value of mock
        Users expected = new Users();
        expected.setKey(Datastore.createKey(Users.class, 1L));
        expected.setUserId("testUser");
        expected.setUserName("testUserName");
        expected.setEmail("testEmail");
        expected.setNickName("testNickName");
        expected.setPassword("password");
        expected.setAge(20);
        expected.setGender(Gender.MALE);
        expected.setVersion(2L);

        // create register data
        Users register = new Users();
        register.setKey(Datastore.createKey(Users.class, 1L));
        register.setUserId("testUser");
        register.setUserName("testUserName");
        register.setEmail("testEmail");
        register.setNickName("testNickName");
        register.setPassword("password");
        register.setAge(20);
        register.setGender(Gender.MALE);
        register.setVersion(1L);

        Map<String, Object> input = new HashMap<String, Object>();
        input.put("age", register.getAge());
        input.put("email", register.getEmail());
        input.put("gender", register.getGender());
        input.put("nickName", register.getNickName());
        input.put("userId", register.getUserId());
        input.put("userName", register.getUserName());

        Mockito.when(mock.update(register.getKey(), register.getVersion(), input)).thenReturn(
                expected);
        controller.setUserLogic(mock);

        Users actual = controller.update(register);

        Assert.assertNotNull(actual);
        Assert.assertEquals(1L, actual.getKey().getId());
        Assert.assertEquals(expected.getUserId(), actual.getUserId());
        Assert.assertEquals(expected.getUserName(), actual.getUserName());
        Assert.assertEquals(expected.getEmail(), actual.getEmail());
        Assert.assertEquals(expected.getNickName(), actual.getNickName());
        Assert.assertEquals(expected.getPassword(), actual.getPassword());
        Assert.assertEquals(expected.getAge(), actual.getAge());
        Assert.assertEquals(expected.getGender(), actual.getGender());
        Assert.assertEquals(expected.getVersion(), actual.getVersion());
    }

    @Test
    public void testUpdateE01() {
        try {
            controller.update(null);
            Assert.fail("assertion is failure.");
        } catch (RuntimeException e) {
            Assert.assertThat(e, CoreMatchers.instanceOf(IllegalArgumentException.class));
        }
    }

    @Test
    public void testDelete01() {
        // create register data
        Users register = new Users();
        register.setKey(Datastore.createKey(Users.class, 1L));
        register.setUserId("testUser");
        register.setUserName("testUserName");
        register.setEmail("testEmail");
        register.setNickName("testNickName");
        register.setPassword("password");
        register.setAge(20);
        register.setGender(Gender.MALE);
        register.setVersion(1L);

        Mockito.doNothing().when(mock).delete(Datastore.createKey(Places.class, 1L), 1L);
        controller.setUserLogic(mock);

        controller.delete(Datastore.createKey(Users.class, 1L), 1L);
    }

    @Test
    public void testDeleteE01() {
        try {
            controller.delete(null, 1L);
            Assert.fail("assertion is failure.");
        } catch (RuntimeException e) {
            Assert.assertThat(e, CoreMatchers.instanceOf(IllegalArgumentException.class));
        }
    }

    @Test
    public void testDeleteE02() {
        try {
            controller.delete(Datastore.createKey(Places.class, 1L), null);
            Assert.fail("assertion is failure.");
        } catch (RuntimeException e) {
            Assert.assertThat(e, CoreMatchers.instanceOf(IllegalArgumentException.class));
        }
    }

}
