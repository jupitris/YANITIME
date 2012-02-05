/**
 * Copyright (c) 2012 Jupitris on Labs.
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
package yanitime4u.yanitime.logic;

import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slim3.datastore.Datastore;
import org.slim3.datastore.EntityNotFoundRuntimeException;
import org.slim3.tester.AppEngineTestCase;

import yanitime4u.yanitime.condition.UserCondition;
import yanitime4u.yanitime.model.Users;
import yanitime4u.yanitime.model.code.Gender;

import com.google.appengine.api.datastore.Key;

/**
 * @author jupitris
 * 
 */
public class UserLogicImplTest extends AppEngineTestCase {

    private final UserLogicImpl logic = new UserLogicImpl();

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        Datastore.setGlobalCipherKey("zaq12wsxcde34rfv");
    }

    @Override
    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }

    @Test
    public void testCreate01() {
        Users expected = new Users();
        expected.setUserId("testUser");
        expected.setUserName("testUserName");
        expected.setPassword("password");
        expected.setEmail("test@yanitime4u.com");
        expected.setNickName("testNickName");
        expected.setAge(20);
        expected.setGender(Gender.MALE);

        Users actual = logic.create(expected);

        Assert.assertNotNull(actual);
        Assert.assertNotNull(actual.getKey().getId());
        Assert.assertEquals(expected.getUserId(), actual.getUserId());
        Assert.assertEquals(expected.getUserName(), actual.getUserName());
        Assert.assertEquals(expected.getPassword(), actual.getPassword());
        Assert.assertEquals(expected.getEmail(), actual.getEmail());
        Assert.assertEquals(expected.getNickName(), actual.getNickName());
        Assert.assertEquals(expected.getAge(), actual.getAge());
        Assert.assertEquals(expected.getGender(), actual.getGender());
        Assert.assertEquals(Long.valueOf(1L), actual.getVersion());
    }

    @Test
    public void testCreate02() {
        Users expected = new Users();

        Users actual = logic.create(expected);

        Assert.assertNotNull(actual);
        Assert.assertNotNull(actual.getKey().getId());
        Assert.assertNull(actual.getUserId());
        Assert.assertNull(actual.getUserName());
        Assert.assertNull(actual.getPassword());
        Assert.assertNull(actual.getEmail());
        Assert.assertNull(actual.getNickName());
        Assert.assertNull(actual.getAge());
        Assert.assertNull(actual.getGender());
        Assert.assertEquals(Long.valueOf(1L), actual.getVersion());
    }

    @Test
    public void testCreateE01() {
        try {
            logic.create(null);
            Assert.fail("assertion is failure.");
        } catch (RuntimeException e) {
            Assert.assertThat(e, CoreMatchers.instanceOf(IllegalArgumentException.class));
        }
    }

    @Test
    public void testFindByKey01() {
        Users expected = new Users();
        expected.setKey(Datastore.createKey(Users.class, 1L));
        expected.setUserId("testUser");
        expected.setUserName("testUserName");
        expected.setPassword("password");
        expected.setEmail("test@yanitime4u.com");
        expected.setNickName("testNickName");
        expected.setAge(20);
        expected.setGender(Gender.MALE);

        // create test data.
        logic.create(expected);

        Users actual = logic.findByKey(1L);

        Assert.assertNotNull(actual);
        Assert.assertEquals(expected.getKey().getId(), actual.getKey().getId());
        Assert.assertEquals(expected.getUserId(), actual.getUserId());
        Assert.assertEquals(expected.getUserName(), actual.getUserName());
        Assert.assertEquals(expected.getPassword(), actual.getPassword());
        Assert.assertEquals(expected.getEmail(), actual.getEmail());
        Assert.assertEquals(expected.getNickName(), actual.getNickName());
        Assert.assertEquals(expected.getAge(), actual.getAge());
        Assert.assertEquals(expected.getGender(), actual.getGender());
    }

    @Test
    public void testFindByKeyE01() {
        try {
            logic.findByKey(1L);
            Assert.fail("assertion is failure.");
        } catch (RuntimeException e) {
            Assert.assertThat(e, CoreMatchers.instanceOf(EntityNotFoundRuntimeException.class));
        }
    }

    @Test
    public void testFindByKeyE02() {
        try {
            logic.findByKey(null);
            Assert.fail("assertion is failure.");
        } catch (RuntimeException e) {
            Assert.assertThat(e, CoreMatchers.instanceOf(IllegalArgumentException.class));
        }
    }

    @Test
    public void testFindByCondition01() {
        Users expected1 = new Users();
        expected1.setUserId("testUser1");
        expected1.setUserName("testUserName1");
        expected1.setPassword("password1");
        expected1.setEmail("test1@yanitime4u.com");
        expected1.setNickName("testNickName1");
        expected1.setAge(20);
        expected1.setGender(Gender.MALE);

        // create test data.
        logic.create(expected1);

        Users expected2 = new Users();
        expected2.setUserId("testUser2");
        expected2.setUserName("testUserName2");
        expected2.setPassword("password2");
        expected2.setEmail("test2@yanitime4u.com");
        expected2.setNickName("testNickName2");
        expected2.setAge(30);
        expected2.setGender(Gender.FEMALE);

        // create test data.
        logic.create(expected2);

        // create condition
        UserCondition condition = new UserCondition();
        condition.setUserId("testUser1");
        condition.setUserName("testUserName1");
        condition.setEmail("test1@yanitime4u.com");
        condition.setNickName("testNickName1");

        List<Users> actuals = logic.findByCondition(condition);

        Assert.assertNotNull(actuals);
        Assert.assertEquals(1, actuals.size());

        for (Users actual : actuals) {
            Assert.assertEquals(expected1.getUserId(), actual.getUserId());
            Assert.assertEquals(expected1.getUserName(), actual.getUserName());
            Assert.assertEquals(expected1.getPassword(), actual.getPassword());
            Assert.assertEquals(expected1.getEmail(), actual.getEmail());
            Assert.assertEquals(expected1.getNickName(), actual.getNickName());
            Assert.assertEquals(expected1.getAge(), actual.getAge());
            Assert.assertEquals(expected1.getGender(), actual.getGender());
        }
    }

    @Test
    public void testFindByCondition02() {
        Users expected1 = new Users();
        expected1.setUserId("testUser1");
        expected1.setUserName("testUserName1");
        expected1.setPassword("password1");
        expected1.setEmail("test1@yanitime4u.com");
        expected1.setNickName("testNickName1");
        expected1.setAge(20);
        expected1.setGender(Gender.MALE);

        // create test data.
        logic.create(expected1);

        Users expected2 = new Users();
        expected2.setUserId("testUser2");
        expected2.setUserName("testUserName2");
        expected2.setPassword("password2");
        expected2.setEmail("test2@yanitime4u.com");
        expected2.setNickName("testNickName2");
        expected2.setAge(30);
        expected2.setGender(Gender.FEMALE);

        // create test data.
        logic.create(expected2);

        // create condition
        UserCondition condition = new UserCondition();
        condition.setUserId("testUser1");

        List<Users> actuals = logic.findByCondition(condition);

        Assert.assertNotNull(actuals);
        Assert.assertEquals(1, actuals.size());

        for (Users actual : actuals) {
            Assert.assertEquals(expected1.getUserId(), actual.getUserId());
            Assert.assertEquals(expected1.getUserName(), actual.getUserName());
            Assert.assertEquals(expected1.getPassword(), actual.getPassword());
            Assert.assertEquals(expected1.getEmail(), actual.getEmail());
            Assert.assertEquals(expected1.getNickName(), actual.getNickName());
            Assert.assertEquals(expected1.getAge(), actual.getAge());
            Assert.assertEquals(expected1.getGender(), actual.getGender());
        }
    }

    @Test
    public void testFindByCondition03() {
        Users expected1 = new Users();
        expected1.setUserId("testUser1");
        expected1.setUserName("testUserName1");
        expected1.setPassword("password1");
        expected1.setEmail("test1@yanitime4u.com");
        expected1.setNickName("testNickName1");
        expected1.setAge(20);
        expected1.setGender(Gender.MALE);

        // create test data.
        logic.create(expected1);

        Users expected2 = new Users();
        expected2.setUserId("testUser2");
        expected2.setUserName("testUserName2");
        expected2.setPassword("password2");
        expected2.setEmail("test2@yanitime4u.com");
        expected2.setNickName("testNickName2");
        expected2.setAge(30);
        expected2.setGender(Gender.FEMALE);

        // create test data.
        logic.create(expected2);

        // create condition
        UserCondition condition = new UserCondition();
        condition.setUserName("testUserName1");

        List<Users> actuals = logic.findByCondition(condition);

        Assert.assertNotNull(actuals);
        Assert.assertEquals(1, actuals.size());

        for (Users actual : actuals) {
            Assert.assertEquals(expected1.getUserId(), actual.getUserId());
            Assert.assertEquals(expected1.getUserName(), actual.getUserName());
            Assert.assertEquals(expected1.getPassword(), actual.getPassword());
            Assert.assertEquals(expected1.getEmail(), actual.getEmail());
            Assert.assertEquals(expected1.getNickName(), actual.getNickName());
            Assert.assertEquals(expected1.getAge(), actual.getAge());
            Assert.assertEquals(expected1.getGender(), actual.getGender());
        }
    }

    @Test
    public void testFindByCondition04() {
        Users expected1 = new Users();
        expected1.setUserId("testUser1");
        expected1.setUserName("testUserName1");
        expected1.setPassword("password1");
        expected1.setEmail("test1@yanitime4u.com");
        expected1.setNickName("testNickName1");
        expected1.setAge(20);
        expected1.setGender(Gender.MALE);

        // create test data.
        logic.create(expected1);

        Users expected2 = new Users();
        expected2.setUserId("testUser2");
        expected2.setUserName("testUserName2");
        expected2.setPassword("password2");
        expected2.setEmail("test2@yanitime4u.com");
        expected2.setNickName("testNickName2");
        expected2.setAge(30);
        expected2.setGender(Gender.FEMALE);

        // create test data.
        logic.create(expected2);

        // create condition
        UserCondition condition = new UserCondition();
        condition.setEmail("test1@yanitime4u.com");

        List<Users> actuals = logic.findByCondition(condition);

        Assert.assertNotNull(actuals);
        Assert.assertEquals(1, actuals.size());

        for (Users actual : actuals) {
            Assert.assertEquals(expected1.getUserId(), actual.getUserId());
            Assert.assertEquals(expected1.getUserName(), actual.getUserName());
            Assert.assertEquals(expected1.getPassword(), actual.getPassword());
            Assert.assertEquals(expected1.getEmail(), actual.getEmail());
            Assert.assertEquals(expected1.getNickName(), actual.getNickName());
            Assert.assertEquals(expected1.getAge(), actual.getAge());
            Assert.assertEquals(expected1.getGender(), actual.getGender());
        }
    }

    @Test
    public void testFindByCondition05() {
        Users expected1 = new Users();
        expected1.setUserId("testUser1");
        expected1.setUserName("testUserName1");
        expected1.setPassword("password1");
        expected1.setEmail("test1@yanitime4u.com");
        expected1.setNickName("testNickName1");
        expected1.setAge(20);
        expected1.setGender(Gender.MALE);

        // create test data.
        logic.create(expected1);

        Users expected2 = new Users();
        expected2.setUserId("testUser2");
        expected2.setUserName("testUserName2");
        expected2.setPassword("password2");
        expected2.setEmail("test2@yanitime4u.com");
        expected2.setNickName("testNickName2");
        expected2.setAge(30);
        expected2.setGender(Gender.FEMALE);

        // create test data.
        logic.create(expected2);

        // create condition
        UserCondition condition = new UserCondition();
        condition.setNickName("testNickName1");

        List<Users> actuals = logic.findByCondition(condition);

        Assert.assertNotNull(actuals);
        Assert.assertEquals(1, actuals.size());

        for (Users actual : actuals) {
            Assert.assertEquals(expected1.getUserId(), actual.getUserId());
            Assert.assertEquals(expected1.getUserName(), actual.getUserName());
            Assert.assertEquals(expected1.getPassword(), actual.getPassword());
            Assert.assertEquals(expected1.getEmail(), actual.getEmail());
            Assert.assertEquals(expected1.getNickName(), actual.getNickName());
            Assert.assertEquals(expected1.getAge(), actual.getAge());
            Assert.assertEquals(expected1.getGender(), actual.getGender());
        }
    }

    @Test
    public void testFindByCondition06() {
        Users expected1 = new Users();
        expected1.setUserId("testUser1");
        expected1.setUserName("testUserName1");
        expected1.setPassword("password1");
        expected1.setEmail("test1@yanitime4u.com");
        expected1.setNickName("testNickName1");
        expected1.setAge(20);
        expected1.setGender(Gender.MALE);

        // create test data.
        logic.create(expected1);

        Users expected2 = new Users();
        expected2.setUserId("testUser2");
        expected2.setUserName("testUserName2");
        expected2.setPassword("password2");
        expected2.setEmail("test2@yanitime4u.com");
        expected2.setNickName("testNickName2");
        expected2.setAge(30);
        expected2.setGender(Gender.FEMALE);

        // create test data.
        logic.create(expected2);

        // create condition
        UserCondition condition = new UserCondition();

        List<Users> actuals = logic.findByCondition(condition);

        Assert.assertNotNull(actuals);
        Assert.assertEquals(2, actuals.size());

        for (int i = 0; i < actuals.size(); i++) {
            Assert.assertEquals("testUser" + (i + 1), actuals.get(i).getUserId());
            Assert.assertEquals("testUserName" + (i + 1), actuals.get(i).getUserName());
            Assert.assertEquals("password" + (i + 1), actuals.get(i).getPassword());
            Assert.assertEquals("test" + (i + 1) + "@yanitime4u.com", actuals.get(i).getEmail());
            Assert.assertEquals("testNickName" + (i + 1), actuals.get(i).getNickName());
            Assert.assertEquals(Integer.valueOf(i == 0 ? 20 : 30), actuals.get(i).getAge());
            Assert.assertEquals(i == 0 ? Gender.MALE : Gender.FEMALE, actuals.get(i).getGender());
        }
    }

    @Test
    public void testFindByCondition07() {
        Users expected1 = new Users();
        expected1.setUserId("testUser1");
        expected1.setUserName("testUserName1");
        expected1.setPassword("password1");
        expected1.setEmail("test1@yanitime4u.com");
        expected1.setNickName("testNickName1");
        expected1.setAge(20);
        expected1.setGender(Gender.MALE);

        // create test data.
        logic.create(expected1);

        Users expected2 = new Users();
        expected2.setUserId("testUser2");
        expected2.setUserName("testUserName2");
        expected2.setPassword("password2");
        expected2.setEmail("test2@yanitime4u.com");
        expected2.setNickName("testNickName2");
        expected2.setAge(30);
        expected2.setGender(Gender.FEMALE);

        // create test data.
        logic.create(expected2);

        // create condition
        UserCondition condition = new UserCondition();
        condition.setUserId("testUser3");

        List<Users> actuals = logic.findByCondition(condition);

        Assert.assertNotNull(actuals);
        Assert.assertEquals(0, actuals.size());
    }

    @Test
    public void testFindByConditionE01() {
        try {
            logic.findByCondition(null);
            Assert.fail("assertion is failure.");
        } catch (RuntimeException e) {
            Assert.assertThat(e, CoreMatchers.instanceOf(IllegalArgumentException.class));
        }
    }

    @Test
    public void testUpdate01() {
        Users expected1 = new Users();
        expected1.setUserId("testUser1");
        expected1.setUserName("testUserName1");
        expected1.setPassword("password1");
        expected1.setEmail("test1@yanitime4u.com");
        expected1.setNickName("testNickName1");
        expected1.setAge(20);
        expected1.setGender(Gender.MALE);

        // create test data.
        Users users1 = logic.create(expected1);

        Users expected2 = new Users();
        expected2.setUserId("testUser2");
        expected2.setUserName("testUserName2");
        expected2.setPassword("password2");
        expected2.setEmail("test2@yanitime4u.com");
        expected2.setNickName("testNickName2");
        expected2.setAge(30);
        expected2.setGender(Gender.FEMALE);

        // create test data.
        logic.create(expected2);

        Map<String, Object> input = new HashMap<String, Object>();
        input.put("age", 40);
        input.put("email", "testUpdate@yanitime4u.com");
        input.put("gender", Gender.NONE);
        input.put("nickName", "testUpdateNickName");
        input.put("userId", "testUpdateUser");
        input.put("userName", "testUpdateUserName");

        Users actual = logic.update(users1.getKey(), users1.getVersion(), input);

        Assert.assertNotNull(actual);
        Assert.assertEquals(users1.getKey().getId(), actual.getKey().getId());
        Assert.assertEquals(Long.valueOf(2L), actual.getVersion());
        Assert.assertEquals(input.get("userId"), actual.getUserId());
        Assert.assertEquals(input.get("userName"), actual.getUserName());
        Assert.assertEquals(expected1.getPassword(), actual.getPassword());
        Assert.assertEquals(input.get("email"), actual.getEmail());
        Assert.assertEquals(input.get("nickName"), actual.getNickName());
        Assert.assertEquals(input.get("age"), actual.getAge());
        Assert.assertEquals(input.get("gender"), actual.getGender());
    }

    @Test
    public void testUpdateE01() {
        Users expected1 = new Users();
        expected1.setUserId("testUser1");
        expected1.setUserName("testUserName1");
        expected1.setPassword("password1");
        expected1.setEmail("test1@yanitime4u.com");
        expected1.setNickName("testNickName1");
        expected1.setAge(20);
        expected1.setGender(Gender.MALE);

        // create test data.
        Users users1 = logic.create(expected1);

        Users expected2 = new Users();
        expected2.setUserId("testUser2");
        expected2.setUserName("testUserName2");
        expected2.setPassword("password2");
        expected2.setEmail("test2@yanitime4u.com");
        expected2.setNickName("testNickName2");
        expected2.setAge(30);
        expected2.setGender(Gender.FEMALE);

        // create test data.
        logic.create(expected2);

        Map<String, Object> input = new HashMap<String, Object>();
        input.put("age", 40);
        input.put("email", "testUpdate@yanitime4u.com");
        input.put("gender", Gender.NONE);
        input.put("nickName", "testUpdateNickName");
        input.put("userId", "testUpdateUser");
        input.put("userName", "testUpdateUserName");

        try {
            logic.update(null, users1.getVersion(), input);
            Assert.fail("assertion is failure.");
        } catch (RuntimeException e) {
            Assert.assertThat(e, CoreMatchers.instanceOf(IllegalArgumentException.class));
        }
    }

    @Test
    public void testUpdateE02() {
        Users expected1 = new Users();
        expected1.setUserId("testUser1");
        expected1.setUserName("testUserName1");
        expected1.setPassword("password1");
        expected1.setEmail("test1@yanitime4u.com");
        expected1.setNickName("testNickName1");
        expected1.setAge(20);
        expected1.setGender(Gender.MALE);

        // create test data.
        Users users1 = logic.create(expected1);

        Users expected2 = new Users();
        expected2.setUserId("testUser2");
        expected2.setUserName("testUserName2");
        expected2.setPassword("password2");
        expected2.setEmail("test2@yanitime4u.com");
        expected2.setNickName("testNickName2");
        expected2.setAge(30);
        expected2.setGender(Gender.FEMALE);

        // create test data.
        logic.create(expected2);

        Map<String, Object> input = new HashMap<String, Object>();
        input.put("age", 40);
        input.put("email", "testUpdate@yanitime4u.com");
        input.put("gender", Gender.NONE);
        input.put("nickName", "testUpdateNickName");
        input.put("userId", "testUpdateUser");
        input.put("userName", "testUpdateUserName");

        try {
            logic.update(users1.getKey(), null, input);
            Assert.fail("assertion is failure.");
        } catch (RuntimeException e) {
            Assert.assertThat(e, CoreMatchers.instanceOf(IllegalArgumentException.class));
        }
    }

    @Test
    public void testUpdateE03() {
        Users expected1 = new Users();
        expected1.setUserId("testUser1");
        expected1.setUserName("testUserName1");
        expected1.setPassword("password1");
        expected1.setEmail("test1@yanitime4u.com");
        expected1.setNickName("testNickName1");
        expected1.setAge(20);
        expected1.setGender(Gender.MALE);

        // create test data.
        Users users1 = logic.create(expected1);

        Users expected2 = new Users();
        expected2.setUserId("testUser2");
        expected2.setUserName("testUserName2");
        expected2.setPassword("password2");
        expected2.setEmail("test2@yanitime4u.com");
        expected2.setNickName("testNickName2");
        expected2.setAge(30);
        expected2.setGender(Gender.FEMALE);

        // create test data.
        logic.create(expected2);

        Map<String, Object> input = new HashMap<String, Object>();
        input.put("age", 40);
        input.put("email", "testUpdate@yanitime4u.com");
        input.put("gender", Gender.NONE);
        input.put("nickName", "testUpdateNickName");
        input.put("userId", "testUpdateUser");
        input.put("userName", "testUpdateUserName");

        try {
            logic.update(users1.getKey(), users1.getVersion(), null);
            Assert.fail("assertion is failure.");
        } catch (RuntimeException e) {
            Assert.assertThat(e, CoreMatchers.instanceOf(IllegalArgumentException.class));
        }
    }

    @Test
    public void testUpdateE04() {
        Users expected1 = new Users();
        expected1.setUserId("testUser1");
        expected1.setUserName("testUserName1");
        expected1.setPassword("password1");
        expected1.setEmail("test1@yanitime4u.com");
        expected1.setNickName("testNickName1");
        expected1.setAge(20);
        expected1.setGender(Gender.MALE);

        // create test data.
        Users users1 = logic.create(expected1);

        Users expected2 = new Users();
        expected2.setUserId("testUser2");
        expected2.setUserName("testUserName2");
        expected2.setPassword("password2");
        expected2.setEmail("test2@yanitime4u.com");
        expected2.setNickName("testNickName2");
        expected2.setAge(30);
        expected2.setGender(Gender.FEMALE);

        // create test data.
        logic.create(expected2);

        Map<String, Object> input = new HashMap<String, Object>();
        input.put("age", 40);
        input.put("email", "testUpdate@yanitime4u.com");
        input.put("gender", Gender.NONE);
        input.put("nickName", "testUpdateNickName");
        input.put("userId", "testUpdateUser");
        input.put("userName", "testUpdateUserName");

        Key key = Datastore.createKey(Users.class, 1L);
        try {
            logic.update(key, users1.getVersion(), input);
            Assert.fail("assertion is failure.");
        } catch (RuntimeException e) {
            Assert.assertThat(e, CoreMatchers.instanceOf(EntityNotFoundRuntimeException.class));
        }
    }

    @Test
    public void testUpdateE05() {
        Users expected1 = new Users();
        expected1.setUserId("testUser1");
        expected1.setUserName("testUserName1");
        expected1.setPassword("password1");
        expected1.setEmail("test1@yanitime4u.com");
        expected1.setNickName("testNickName1");
        expected1.setAge(20);
        expected1.setGender(Gender.MALE);

        // create test data.
        Users users1 = logic.create(expected1);

        Users expected2 = new Users();
        expected2.setUserId("testUser2");
        expected2.setUserName("testUserName2");
        expected2.setPassword("password2");
        expected2.setEmail("test2@yanitime4u.com");
        expected2.setNickName("testNickName2");
        expected2.setAge(30);
        expected2.setGender(Gender.FEMALE);

        // create test data.
        logic.create(expected2);

        Map<String, Object> input = new HashMap<String, Object>();
        input.put("age", 40);
        input.put("email", "testUpdate@yanitime4u.com");
        input.put("gender", Gender.NONE);
        input.put("nickName", "testUpdateNickName");
        input.put("userId", "testUpdateUser");
        input.put("userName", "testUpdateUserName");

        try {
            logic.update(users1.getKey(), 10L, input);
            Assert.fail("assertion is failure.");
        } catch (RuntimeException e) {
            Assert.assertThat(e, CoreMatchers.instanceOf(ConcurrentModificationException.class));
        }
    }

    @Test
    public void testDelete01() {
        Users expected1 = new Users();
        expected1.setUserId("testUser1");
        expected1.setUserName("testUserName1");
        expected1.setPassword("password1");
        expected1.setEmail("test1@yanitime4u.com");
        expected1.setNickName("testNickName1");
        expected1.setAge(20);
        expected1.setGender(Gender.MALE);

        // create test data.
        Users users1 = logic.create(expected1);

        Users expected2 = new Users();
        expected2.setUserId("testUser2");
        expected2.setUserName("testUserName2");
        expected2.setPassword("password2");
        expected2.setEmail("test2@yanitime4u.com");
        expected2.setNickName("testNickName2");
        expected2.setAge(30);
        expected2.setGender(Gender.FEMALE);

        // create test data.
        Users users2 = logic.create(expected2);

        logic.delete(users1.getKey(), users1.getVersion());

        UserCondition condition = new UserCondition();
        List<Users> actuals = logic.findByCondition(condition);

        Assert.assertNotNull(actuals);
        Assert.assertEquals(1, actuals.size());

        for (Users actual : actuals) {
            Assert.assertEquals(users2.getUserId(), actual.getUserId());
            Assert.assertEquals(users2.getUserName(), actual.getUserName());
            Assert.assertEquals(users2.getPassword(), actual.getPassword());
            Assert.assertEquals(users2.getEmail(), actual.getEmail());
            Assert.assertEquals(users2.getNickName(), actual.getNickName());
            Assert.assertEquals(users2.getAge(), actual.getAge());
            Assert.assertEquals(users2.getGender(), actual.getGender());
        }
    }

    @Test
    public void testDeleteE01() {
        Users expected1 = new Users();
        expected1.setUserId("testUser1");
        expected1.setUserName("testUserName1");
        expected1.setPassword("password1");
        expected1.setEmail("test1@yanitime4u.com");
        expected1.setNickName("testNickName1");
        expected1.setAge(20);
        expected1.setGender(Gender.MALE);

        // create test data.
        Users users1 = logic.create(expected1);

        Users expected2 = new Users();
        expected2.setUserId("testUser2");
        expected2.setUserName("testUserName2");
        expected2.setPassword("password2");
        expected2.setEmail("test2@yanitime4u.com");
        expected2.setNickName("testNickName2");
        expected2.setAge(30);
        expected2.setGender(Gender.FEMALE);

        // create test data.
        logic.create(expected2);

        try {
            logic.delete(null, users1.getVersion());
            Assert.fail("assertion is failure.");
        } catch (RuntimeException e) {
            Assert.assertThat(e, CoreMatchers.instanceOf(IllegalArgumentException.class));
        }
    }

    @Test
    public void testDeleteE02() {
        Users expected1 = new Users();
        expected1.setUserId("testUser1");
        expected1.setUserName("testUserName1");
        expected1.setPassword("password1");
        expected1.setEmail("test1@yanitime4u.com");
        expected1.setNickName("testNickName1");
        expected1.setAge(20);
        expected1.setGender(Gender.MALE);

        // create test data.
        Users users1 = logic.create(expected1);

        Users expected2 = new Users();
        expected2.setUserId("testUser2");
        expected2.setUserName("testUserName2");
        expected2.setPassword("password2");
        expected2.setEmail("test2@yanitime4u.com");
        expected2.setNickName("testNickName2");
        expected2.setAge(30);
        expected2.setGender(Gender.FEMALE);

        // create test data.
        logic.create(expected2);

        try {
            logic.delete(users1.getKey(), null);
            Assert.fail("assertion is failure.");
        } catch (RuntimeException e) {
            Assert.assertThat(e, CoreMatchers.instanceOf(IllegalArgumentException.class));
        }
    }

    @Test
    public void testDeleteE03() {
        Users expected1 = new Users();
        expected1.setUserId("testUser1");
        expected1.setUserName("testUserName1");
        expected1.setPassword("password1");
        expected1.setEmail("test1@yanitime4u.com");
        expected1.setNickName("testNickName1");
        expected1.setAge(20);
        expected1.setGender(Gender.MALE);

        // create test data.
        Users users1 = logic.create(expected1);

        Users expected2 = new Users();
        expected2.setUserId("testUser2");
        expected2.setUserName("testUserName2");
        expected2.setPassword("password2");
        expected2.setEmail("test2@yanitime4u.com");
        expected2.setNickName("testNickName2");
        expected2.setAge(30);
        expected2.setGender(Gender.FEMALE);

        // create test data.
        logic.create(expected2);

        Key key = Datastore.createKey(Users.class, 1L);
        try {
            logic.delete(key, users1.getVersion());
            Assert.fail("assertion is failure.");
        } catch (RuntimeException e) {
            Assert.assertThat(e, CoreMatchers.instanceOf(EntityNotFoundRuntimeException.class));
        }
    }

    @Test
    public void testDeleteE04() {
        Users expected1 = new Users();
        expected1.setUserId("testUser1");
        expected1.setUserName("testUserName1");
        expected1.setPassword("password1");
        expected1.setEmail("test1@yanitime4u.com");
        expected1.setNickName("testNickName1");
        expected1.setAge(20);
        expected1.setGender(Gender.MALE);

        // create test data.
        Users users1 = logic.create(expected1);

        Users expected2 = new Users();
        expected2.setUserId("testUser2");
        expected2.setUserName("testUserName2");
        expected2.setPassword("password2");
        expected2.setEmail("test2@yanitime4u.com");
        expected2.setNickName("testNickName2");
        expected2.setAge(30);
        expected2.setGender(Gender.FEMALE);

        // create test data.
        logic.create(expected2);

        try {
            logic.delete(users1.getKey(), 10L);
            Assert.fail("assertion is failure.");
        } catch (RuntimeException e) {
            Assert.assertThat(e, CoreMatchers.instanceOf(ConcurrentModificationException.class));
        }
    }

}
