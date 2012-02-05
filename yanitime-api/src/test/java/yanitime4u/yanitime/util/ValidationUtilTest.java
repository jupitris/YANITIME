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
package yanitime4u.yanitime.util;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author jupitris
 * 
 */
public class ValidationUtilTest {

    @Test
    public void testIsNumeric01() {
        Assert.assertTrue(ValidationUtil.isNumeric("123"));
    }

    @Test
    public void testIsNumeric02() {
        Assert.assertFalse(ValidationUtil.isNumeric("abc"));
    }

    @Test
    public void testIsNumeric03() {
        Assert.assertFalse(ValidationUtil.isNumeric("12abc"));
    }

    @Test
    public void testIsNumeric04() {
        Assert.assertFalse(ValidationUtil.isNumeric("abc12"));
    }

    @Test
    public void testIsNumeric05() {
        Assert.assertFalse(ValidationUtil.isNumeric("!#$"));
    }

    @Test
    public void testIsNumericE01() {
        try {
            ValidationUtil.isNumeric(null);
        } catch (RuntimeException e) {
            Assert.assertThat(e, CoreMatchers.instanceOf(IllegalArgumentException.class));
        }
    }

    @Test
    public void testIsNumericE02() {
        try {
            ValidationUtil.isNumeric("");
        } catch (RuntimeException e) {
            Assert.assertThat(e, CoreMatchers.instanceOf(IllegalArgumentException.class));
        }
    }
}
