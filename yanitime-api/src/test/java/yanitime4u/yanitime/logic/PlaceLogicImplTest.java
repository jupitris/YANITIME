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

import yanitime4u.yanitime.condition.PlaceCondition;
import yanitime4u.yanitime.model.Places;

import com.google.appengine.api.datastore.GeoPt;

/**
 * @author jupitris
 * 
 */
public class PlaceLogicImplTest extends AppEngineTestCase {

    private final PlaceLogic logic = new PlaceLogicImpl();

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @Override
    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }

    @Test
    public void testCreate01() {
        GeoPt coordinate = new GeoPt((float) 70.01, (float) 50.3);

        Places expected = new Places();
        expected.setPlaceName("testPlaceName");
        expected.setUserId("testUser");
        expected.setComment("testComment");
        expected.setCoordinate(coordinate);

        Places actual = logic.create(expected);

        Assert.assertNotNull(actual);
        Assert.assertNotNull(actual.getKey().getId());
        Assert.assertEquals(expected.getPlaceName(), actual.getPlaceName());
        Assert.assertEquals(expected.getUserId(), actual.getUserId());
        Assert.assertEquals(expected.getComment(), actual.getComment());
        Assert.assertEquals(expected.getCoordinate(), actual.getCoordinate());
        Assert.assertEquals(Long.valueOf(1L), actual.getVersion());
    }

    @Test
    public void testCreate02() {
        Places expected = new Places();

        Places actual = logic.create(expected);

        Assert.assertNotNull(actual);
        Assert.assertNotNull(actual.getKey().getId());
        Assert.assertNull(actual.getPlaceName());
        Assert.assertNull(actual.getUserId());
        Assert.assertNull(actual.getComment());
        Assert.assertNull(actual.getCoordinate());
        Assert.assertEquals(Long.valueOf(1L), actual.getVersion());
    }

    @Test
    public void testCreateE01() {
        try {
            logic.create(null);
        } catch (RuntimeException e) {
            Assert.assertThat(e, CoreMatchers.instanceOf(IllegalArgumentException.class));
        }
    }

    @Test
    public void testFindByKey01() {
        GeoPt coordinate = new GeoPt((float) 70.01, (float) 50.3);

        Places expected = new Places();
        expected.setKey(Datastore.createKey(Places.class, 1L));
        expected.setPlaceName("testPlaceName");
        expected.setUserId("testUser");
        expected.setComment("testComment");
        expected.setCoordinate(coordinate);

        // create test data.
        logic.create(expected);

        Places actual = logic.findByKey(1L);

        Assert.assertNotNull(actual);
        Assert.assertEquals(expected.getKey().getId(), actual.getKey().getId());
        Assert.assertEquals(expected.getPlaceName(), actual.getPlaceName());
        Assert.assertEquals(expected.getUserId(), actual.getUserId());
        Assert.assertEquals(expected.getComment(), actual.getComment());
        Assert.assertEquals(expected.getCoordinate(), actual.getCoordinate());
    }

    @Test
    public void testFindByKeyE01() {
        try {
            logic.findByKey(1L);
        } catch (RuntimeException e) {
            Assert.assertThat(e, CoreMatchers.instanceOf(EntityNotFoundRuntimeException.class));
        }
    }

    @Test
    public void testFindByKeyE02() {
        try {
            logic.findByKey(null);
        } catch (RuntimeException e) {
            Assert.assertThat(e, CoreMatchers.instanceOf(IllegalArgumentException.class));
        }
    }

    @Test
    public void testFindByCondition01() {
        GeoPt coordinate1 = new GeoPt((float) 70.01, (float) 50.3);

        Places expected1 = new Places();
        expected1.setPlaceName("testPlaceName1");
        expected1.setUserId("testUser1");
        expected1.setComment("testComment1");
        expected1.setCoordinate(coordinate1);

        // create test data.
        logic.create(expected1);

        GeoPt coordinate2 = new GeoPt((float) 10.01, (float) 0.3);

        Places expected2 = new Places();
        expected2.setPlaceName("testPlaceName2");
        expected2.setUserId("testUser2");
        expected2.setComment("testComment2");
        expected2.setCoordinate(coordinate2);

        // create test data.
        logic.create(expected2);

        // create condition
        PlaceCondition condition = new PlaceCondition();
        condition.setPlaceName("testPlaceName1");
        condition.setCoordinate(coordinate1);

        List<Places> actuals = logic.findByCondition(condition);

        Assert.assertNotNull(actuals);
        Assert.assertEquals(1, actuals.size());

        for (Places actual : actuals) {
            Assert.assertEquals(expected1.getPlaceName(), actual.getPlaceName());
            Assert.assertEquals(expected1.getComment(), actual.getComment());
            Assert.assertEquals(expected1.getUserId(), actual.getUserId());
        }
    }

    @Test
    public void testFindByCondition02() {
        GeoPt coordinate1 = new GeoPt((float) 70.01, (float) 50.3);

        Places expected1 = new Places();
        expected1.setPlaceName("testPlaceName1");
        expected1.setUserId("testUser1");
        expected1.setComment("testComment1");
        expected1.setCoordinate(coordinate1);

        // create test data.
        logic.create(expected1);

        GeoPt coordinate2 = new GeoPt((float) 10.01, (float) 0.3);

        Places expected2 = new Places();
        expected2.setPlaceName("testPlaceName2");
        expected2.setUserId("testUser2");
        expected2.setComment("testComment2");
        expected2.setCoordinate(coordinate2);

        // create test data.
        logic.create(expected2);

        // create condition
        PlaceCondition condition = new PlaceCondition();
        condition.setPlaceName("testPlaceName2");

        List<Places> actuals = logic.findByCondition(condition);

        Assert.assertNotNull(actuals);
        Assert.assertEquals(1, actuals.size());

        for (Places actual : actuals) {
            Assert.assertEquals(expected2.getPlaceName(), actual.getPlaceName());
            Assert.assertEquals(expected2.getComment(), actual.getComment());
            Assert.assertEquals(expected2.getUserId(), actual.getUserId());
        }
    }

    @Test
    public void testFindByCondition03() {
        GeoPt coordinate1 = new GeoPt((float) 70.01, (float) 50.3);

        Places expected1 = new Places();
        expected1.setPlaceName("testPlaceName1");
        expected1.setUserId("testUser1");
        expected1.setComment("testComment1");
        expected1.setCoordinate(coordinate1);

        // create test data.
        logic.create(expected1);

        GeoPt coordinate2 = new GeoPt((float) 10.01, (float) 0.3);

        Places expected2 = new Places();
        expected2.setPlaceName("testPlaceName2");
        expected2.setUserId("testUser2");
        expected2.setComment("testComment2");
        expected2.setCoordinate(coordinate2);

        // create test data.
        logic.create(expected2);

        // create condition
        PlaceCondition condition = new PlaceCondition();
        condition.setCoordinate(coordinate1);

        List<Places> actuals = logic.findByCondition(condition);

        Assert.assertNotNull(actuals);
        Assert.assertEquals(1, actuals.size());

        for (Places actual : actuals) {
            Assert.assertEquals(expected1.getPlaceName(), actual.getPlaceName());
            Assert.assertEquals(expected1.getComment(), actual.getComment());
            Assert.assertEquals(expected1.getUserId(), actual.getUserId());
        }
    }

    @Test
    public void testFindByCondition04() {
        GeoPt coordinate1 = new GeoPt((float) 70.01, (float) 50.3);

        Places expected1 = new Places();
        expected1.setPlaceName("testPlaceName1");
        expected1.setUserId("testUser1");
        expected1.setComment("testComment1");
        expected1.setCoordinate(coordinate1);

        // create test data.
        logic.create(expected1);

        GeoPt coordinate2 = new GeoPt((float) 10.01, (float) 0.3);

        Places expected2 = new Places();
        expected2.setPlaceName("testPlaceName2");
        expected2.setUserId("testUser2");
        expected2.setComment("testComment2");
        expected2.setCoordinate(coordinate2);

        // create test data.
        logic.create(expected2);

        // create condition
        PlaceCondition condition = new PlaceCondition();

        List<Places> actuals = logic.findByCondition(condition);

        Assert.assertNotNull(actuals);
        Assert.assertEquals(2, actuals.size());

        for (int i = 0; i < actuals.size(); i++) {
            Assert.assertEquals("testPlaceName" + (i + 1), actuals.get(i).getPlaceName());
            Assert.assertEquals("testComment" + (i + 1), actuals.get(i).getComment());
            Assert.assertEquals("testUser" + (i + 1), actuals.get(i).getUserId());
        }
    }

    @Test
    public void testFindByCondition05() {
        GeoPt coordinate1 = new GeoPt((float) 70.01, (float) 50.3);

        Places expected1 = new Places();
        expected1.setPlaceName("testPlaceName1");
        expected1.setUserId("testUser1");
        expected1.setComment("testComment1");
        expected1.setCoordinate(coordinate1);

        // create test data.
        logic.create(expected1);

        GeoPt coordinate2 = new GeoPt((float) 10.01, (float) 0.3);

        Places expected2 = new Places();
        expected2.setPlaceName("testPlaceName2");
        expected2.setUserId("testUser2");
        expected2.setComment("testComment2");
        expected2.setCoordinate(coordinate2);

        // create test data.
        logic.create(expected2);

        // create condition
        PlaceCondition condition = new PlaceCondition();
        condition.setPlaceName("testPlaceName3");

        List<Places> actuals = logic.findByCondition(condition);

        Assert.assertNotNull(actuals);
        Assert.assertEquals(0, actuals.size());
    }

    @Test
    public void testFindByConditionE01() {
        try {
            logic.findByCondition(null);
        } catch (RuntimeException e) {
            Assert.assertThat(e, CoreMatchers.instanceOf(IllegalArgumentException.class));
        }
    }

    @Test
    public void testUpdate01() {
        GeoPt coordinate1 = new GeoPt((float) 70.01, (float) 50.3);

        Places expected1 = new Places();
        expected1.setPlaceName("testPlaceName1");
        expected1.setUserId("testUser1");
        expected1.setComment("testComment1");
        expected1.setCoordinate(coordinate1);

        // create test data.
        Places places1 = logic.create(expected1);

        GeoPt coordinate2 = new GeoPt((float) 10.01, (float) 0.3);

        Places expected2 = new Places();
        expected2.setPlaceName("testPlaceName2");
        expected2.setUserId("testUser2");
        expected2.setComment("testComment2");
        expected2.setCoordinate(coordinate2);

        // create test data.
        logic.create(expected2);

        Map<String, Object> input = new HashMap<String, Object>();
        input.put("placeName", "testUpdatePlaceName");
        input.put("coordinate", coordinate2);
        input.put("comment", "testUpdateComment");

        Places actual = logic.update(places1.getKey(), places1.getVersion(), input);

        Assert.assertNotNull(actual);
        Assert.assertEquals(places1.getKey().getId(), actual.getKey().getId());
        Assert.assertEquals(Long.valueOf(2), actual.getVersion());
        Assert.assertEquals(input.get("placeName"), actual.getPlaceName());
        Assert.assertEquals(input.get("comment"), actual.getComment());
    }

    @Test
    public void testUpdateE01() {
        GeoPt coordinate1 = new GeoPt((float) 70.01, (float) 50.3);

        Places expected1 = new Places();
        expected1.setPlaceName("testPlaceName1");
        expected1.setUserId("testUser1");
        expected1.setComment("testComment1");
        expected1.setCoordinate(coordinate1);

        // create test data.
        Places places1 = logic.create(expected1);

        GeoPt coordinate2 = new GeoPt((float) 10.01, (float) 0.3);

        Places expected2 = new Places();
        expected2.setPlaceName("testPlaceName2");
        expected2.setUserId("testUser2");
        expected2.setComment("testComment2");
        expected2.setCoordinate(coordinate2);

        // create test data.
        logic.create(expected2);

        Map<String, Object> input = new HashMap<String, Object>();
        input.put("placeName", "testUpdatePlaceName");
        input.put("coordinate", coordinate2);
        input.put("comment", "testUpdateComment");

        try {
            logic.update(null, places1.getVersion(), input);
        } catch (RuntimeException e) {
            Assert.assertThat(e, CoreMatchers.instanceOf(IllegalArgumentException.class));
        }
    }

    @Test
    public void testUpdateE02() {
        GeoPt coordinate1 = new GeoPt((float) 70.01, (float) 50.3);

        Places expected1 = new Places();
        expected1.setPlaceName("testPlaceName1");
        expected1.setUserId("testUser1");
        expected1.setComment("testComment1");
        expected1.setCoordinate(coordinate1);

        // create test data.
        Places places1 = logic.create(expected1);

        GeoPt coordinate2 = new GeoPt((float) 10.01, (float) 0.3);

        Places expected2 = new Places();
        expected2.setPlaceName("testPlaceName2");
        expected2.setUserId("testUser2");
        expected2.setComment("testComment2");
        expected2.setCoordinate(coordinate2);

        // create test data.
        logic.create(expected2);

        Map<String, Object> input = new HashMap<String, Object>();
        input.put("placeName", "testUpdatePlaceName");
        input.put("coordinate", coordinate2);
        input.put("comment", "testUpdateComment");

        try {
            logic.update(places1.getKey(), null, input);
        } catch (RuntimeException e) {
            Assert.assertThat(e, CoreMatchers.instanceOf(IllegalArgumentException.class));
        }
    }

    @Test
    public void testUpdateE03() {
        GeoPt coordinate1 = new GeoPt((float) 70.01, (float) 50.3);

        Places expected1 = new Places();
        expected1.setPlaceName("testPlaceName1");
        expected1.setUserId("testUser1");
        expected1.setComment("testComment1");
        expected1.setCoordinate(coordinate1);

        // create test data.
        Places places1 = logic.create(expected1);

        GeoPt coordinate2 = new GeoPt((float) 10.01, (float) 0.3);

        Places expected2 = new Places();
        expected2.setPlaceName("testPlaceName2");
        expected2.setUserId("testUser2");
        expected2.setComment("testComment2");
        expected2.setCoordinate(coordinate2);

        // create test data.
        logic.create(expected2);

        Map<String, Object> input = new HashMap<String, Object>();
        input.put("placeName", "testUpdatePlaceName");
        input.put("coordinate", coordinate2);
        input.put("comment", "testUpdateComment");

        try {
            logic.update(places1.getKey(), places1.getVersion(), null);
        } catch (RuntimeException e) {
            Assert.assertThat(e, CoreMatchers.instanceOf(IllegalArgumentException.class));
        }
    }
}
