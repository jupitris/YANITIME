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
package yanitime4u.yanitime.logic;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.Map;

import org.slim3.datastore.Datastore;
import org.slim3.datastore.FilterCriterion;
import org.slim3.datastore.ModelQuery;
import org.slim3.gen.util.StringUtil;
import org.slim3.util.BeanUtil;

import yanitime4u.yanitime.condition.UserCondition;
import yanitime4u.yanitime.meta.UsersMeta;
import yanitime4u.yanitime.model.Users;
import yanitime4u.yanitime.util.AssertionUtil;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;

/**
 * @author jupitris
 * 
 */
public class UserLogicImpl implements UserLogic {

    private final UsersMeta meta = UsersMeta.get();

    /*
     * (non-Javadoc)
     * 
     * @see yanitime4u.yanitime.logic.UserLogic#findByKey(java.lang.Long)
     */
    @Override
    public Users findByKey(Long id) {
        AssertionUtil.assertNotNull(id);
        Key key = Datastore.createKey(Users.class, id);
        return Datastore.get(meta, key);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * yanitime4u.yanitime.logic.UserLogic#findByCondition(yanitime4u.yanitime.condition.UserCondition
     * )
     */
    @Override
    public List<Users> findByCondition(UserCondition condition) {
        AssertionUtil.assertNotNull(condition);
        ModelQuery<Users> query = Datastore.query(meta);
        FilterCriterion[] filters = createFilterByCondition(condition);

        if (filters.length > 0) {
            query = query.filter(filters);
        }
        return query.asList();
    }

    private FilterCriterion[] createFilterByCondition(UserCondition condition) {
        List<FilterCriterion> filters = new ArrayList<FilterCriterion>();

        if (!StringUtil.isEmpty(condition.getUserId())) {
            filters.add(meta.userId.equal(condition.getUserId()));
        }

        if (!StringUtil.isEmpty(condition.getUserName())) {
            filters.add(meta.userName.equal(condition.getUserName()));
        }

        if (!StringUtil.isEmpty(condition.getEmail())) {
            filters.add(meta.email.equal(condition.getEmail()));
        }

        if (!StringUtil.isEmpty(condition.getNickName())) {
            filters.add(meta.nickName.equal(condition.getNickName()));
        }

        return filters.toArray(new FilterCriterion[filters.size()]);
    }

    /*
     * (non-Javadoc)
     * 
     * @see yanitime4u.yanitime.logic.UserLogic#create(yanitime4u.yanitime.model.Users)
     */
    @Override
    public Users create(Users users) {
        AssertionUtil.assertNotNull(users);
        Transaction tx = Datastore.beginTransaction();
        try {
            Datastore.put(users);
            tx.commit();
            return users;
        } catch (ConcurrentModificationException e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see yanitime4u.yanitime.logic.UserLogic#update(com.google.appengine.api.datastore.Key,
     * java.lang.Long, java.util.Map)
     */
    @Override
    public Users update(Key key, Long version, Map<String, Object> input) {
        AssertionUtil.assertNotNull(key);
        AssertionUtil.assertNotNull(version);
        AssertionUtil.assertNotNull(input);

        Transaction tx = Datastore.beginTransaction();
        try {
            Users latest = Datastore.get(meta, key, version);
            BeanUtil.copy(input, latest);
            Datastore.put(tx, latest);
            tx.commit();
            return latest;
        } catch (ConcurrentModificationException e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see yanitime4u.yanitime.logic.UserLogic#delete(com.google.appengine.api.datastore.Key,
     * java.lang.Long)
     */
    @Override
    public void delete(Key key, Long version) {
        AssertionUtil.assertNotNull(key);
        AssertionUtil.assertNotNull(version);

        Transaction tx = Datastore.beginTransaction();
        try {
            Users latest = Datastore.get(meta, key, version);
            Datastore.delete(latest.getKey());
            tx.commit();
        } catch (ConcurrentModificationException e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        }
    }

}
