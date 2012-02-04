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

import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slim3.datastore.Datastore;
import org.slim3.tester.AppEngineTestCase;

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
}
