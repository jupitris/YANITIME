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

import org.slim3.util.StringUtil;

/**
 * @author jupitris
 * 
 */
public final class AssertionUtil {

    /**
     * Private constructor.
     */
    private AssertionUtil() {
        throw new AssertionError();
    }

    /**
     * Assert that a string is not <code>null</code> or not <code>empty</code>.
     * 
     * @param s
     *            the string to check.
     */
    public static void assertNotBlank(String s) {
        if (StringUtil.isEmpty(s)) {
            throw new IllegalArgumentException("argument cannot be null or empty.");
        }
    }

    /**
     * Assert that an object is not <code>null</code>.
     * 
     * @param o
     *            the object to check.
     */
    public static void assertNotNull(Object o) {
        if (o == null) {
            throw new IllegalArgumentException("argument cannot be null.");
        }
    }
}
