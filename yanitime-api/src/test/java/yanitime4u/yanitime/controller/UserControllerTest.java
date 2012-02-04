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

import static org.junit.Assert.assertTrue;
import net.arnx.jsonic.web.RESTServlet;

import org.eclipse.jetty.testing.HttpTester;
import org.eclipse.jetty.testing.ServletTester;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author jupitris
 * 
 */
public class UserControllerTest {

    private ServletTester tester;
    private HttpTester request;
    private HttpTester response;

    @Before
    public void setUp() throws Exception {
        this.tester = new ServletTester();
        this.tester.setContextPath("/");
        this.tester.addServlet(RESTServlet.class, "*.json");
        this.tester.start();
    }

    @After
    public void tearDown() throws Exception {
        this.tester.stop();
    }

    @Test
    public void testFind01() throws Exception {
        this.request = new HttpTester("UTF-8");
        this.response = new HttpTester("UTF-8");
        this.request.setMethod("GET");
        this.request.setHeader("Host", "tester");
        this.request.setVersion("HTTP/1.0");
        this.request.setURI("/user.json");
        this.response.parse(tester.getResponses(request.generate()));

        assertTrue(response.getMethod() == null);
        // assertEquals(200, this.response.getStatus());
    }
}
