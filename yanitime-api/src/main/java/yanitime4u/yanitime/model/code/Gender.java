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
package yanitime4u.yanitime.model.code;

/**
 * @author jupitris
 * 
 */
public enum Gender {

    /** none. */
    NONE(1, "None"),

    /** male. */
    MALE(2, "Male"),

    /** female. */
    FEMALE(3, "Female");

    /** code. */
    private Integer code;

    /** value. */
    private String value;

    /**
     * 
     * @param code
     *            the code.
     * @param value
     *            the value.
     */
    private Gender(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    /**
     * @return the code
     */
    public Integer getCode() {
        return code;
    }

    /**
     * @param code
     *            the code to set
     */
    public void setCode(Integer code) {
        this.code = code;
    }

    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value
     *            the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Returns the enum representation of the integer code.
     * 
     * @param code
     *            the code.
     * @return the enum representation of the integer code.
     * @throws NoSuchFieldException
     *             unexpected error.
     */
    public static Gender valueOf(Integer code) throws NoSuchFieldException {
        for (Gender e : values()) {
            if (e.getCode().equals(code)) {
                return e;
            }
        }

        throw new NoSuchFieldException();
    }

}
