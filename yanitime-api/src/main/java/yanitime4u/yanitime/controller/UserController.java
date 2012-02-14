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
import java.util.List;
import java.util.Map;

import yanitime4u.yanitime.condition.UserCondition;
import yanitime4u.yanitime.logic.UserLogic;
import yanitime4u.yanitime.logic.UserLogicImpl;
import yanitime4u.yanitime.model.Users;
import yanitime4u.yanitime.util.AssertionUtil;

import com.google.appengine.api.datastore.Key;

/**
 * @author jupitris
 * 
 */
public class UserController {

    private UserLogic userLogic = new UserLogicImpl();

    /**
     * @param userLogic
     *            the userLogic to set
     */
    public void setUserLogic(UserLogic userLogic) {
        this.userLogic = userLogic;
    }

    public Users findById(Map<String, Long> param) {
        AssertionUtil.assertNotNull(param);
        AssertionUtil.assertNotNull(param.get("id"));
        return userLogic.findByKey(param.get("id"));
    }

    public List<Users> find(UserCondition condition) {
        AssertionUtil.assertNotNull(condition);
        return userLogic.findByCondition(condition);
    }

    public Users create(Users users) {
        AssertionUtil.assertNotNull(users);
        return userLogic.create(users);
    }

    public Users update(Users users) {
        AssertionUtil.assertNotNull(users);
        Map<String, Object> input = new HashMap<String, Object>();
        input.put("age", users.getAge());
        input.put("email", users.getEmail());
        input.put("gender", users.getGender());
        input.put("nickName", users.getNickName());
        input.put("userId", users.getUserId());
        input.put("userName", users.getUserName());
        return userLogic.update(users.getKey(), users.getVersion(), input);
    }

    public void delete(Key id, Long version) {
        userLogic.delete(id, version);
    }
}
