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
package yanitime4u.yanitime.model.code;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author jupitris
 * 
 */
public class GenderTest {

    @Test
    public void testGetEnumRepresentationOfNone() {
        try {
            Assert.assertEquals(Gender.NONE, Gender.valueOf(1));
        } catch (NoSuchFieldException e) {
            Assert.fail();
        }
    }

    @Test
    public void testGetEnumRepresentationOfMale() {
        try {
            Assert.assertEquals(Gender.MALE, Gender.valueOf(2));
        } catch (NoSuchFieldException e) {
            Assert.fail();
        }
    }

    @Test
    public void testGetEnumRepresentationOfFemale() {
        try {
            Assert.assertEquals(Gender.FEMALE, Gender.valueOf(3));
        } catch (NoSuchFieldException e) {
            Assert.fail();
        }
    }

    @Test
    public void testGetNotExistingValue01() {
        try {
            Gender.valueOf(0);
            Assert.fail();
        } catch (NoSuchFieldException e) {
            Assert.assertThat(e, CoreMatchers.instanceOf(NoSuchFieldException.class));
        }
    }

    @Test
    public void testGetNotExistingValue02() {
        try {
            Gender.valueOf(4);
            Assert.fail();
        } catch (NoSuchFieldException e) {
            Assert.assertThat(e, CoreMatchers.instanceOf(NoSuchFieldException.class));
        }
    }
}
