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

import java.util.HashMap;
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

import yanitime4u.yanitime.logic.PlaceLogic;
import yanitime4u.yanitime.model.Places;

import com.google.appengine.api.datastore.GeoPt;

/**
 * @author jupitris
 * 
 */
public class PlaceControllerTest extends AppEngineTestCase {

    private final PlaceController controller = new PlaceController();

    @Mock
    private PlaceLogic mock;

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
        Places expected = new Places();
        expected.setKey(Datastore.createKey(Places.class, 1L));
        expected.setUserId("testUser");
        expected.setPlaceName("testPlaceName");
        expected.setComment("testComment");
        expected.setCoordinate(new GeoPt((long) 1.1, (long) 10.0));
        expected.setVersion(1L);

        Mockito.when(mock.findByKey(1L)).thenReturn(expected);
        controller.setPlaceLogic(mock);

        Map<String, Object> input = new HashMap<String, Object>();
        input.put("id", 1L);
        Places actual = controller.findById(input);

        Assert.assertNotNull(actual);
        Assert.assertEquals(expected.getKey().getId(), actual.getKey().getId());
        Assert.assertEquals(expected.getUserId(), actual.getUserId());
        Assert.assertEquals(expected.getPlaceName(), actual.getPlaceName());
        Assert.assertEquals(expected.getComment(), actual.getComment());
        Assert.assertEquals(expected.getCoordinate(), actual.getCoordinate());
        Assert.assertEquals(expected.getVersion(), actual.getVersion());
    }

    @Test
    public void testFindByIdE01() {
        // create return value of mock
        Places expected = new Places();
        expected.setKey(Datastore.createKey(Places.class, 1L));
        expected.setUserId("testUser");
        expected.setPlaceName("testPlaceName");
        expected.setComment("testComment");
        expected.setCoordinate(new GeoPt((long) 1.1, (long) 10.0));
        expected.setVersion(1L);

        Mockito.when(mock.findByKey(2L)).thenThrow(EntityNotFoundRuntimeException.class);
        controller.setPlaceLogic(mock);

        Map<String, Object> input = new HashMap<String, Object>();
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
}
