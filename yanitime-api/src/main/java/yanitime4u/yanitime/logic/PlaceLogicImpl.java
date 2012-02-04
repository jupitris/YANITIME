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

import yanitime4u.yanitime.condition.PlaceCondition;
import yanitime4u.yanitime.meta.PlacesMeta;
import yanitime4u.yanitime.model.Places;
import yanitime4u.yanitime.util.AssertionUtil;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;

/**
 * @author jupitris
 * 
 */
public class PlaceLogicImpl implements PlaceLogic {

    private final PlacesMeta meta = PlacesMeta.get();

    /*
     * (non-Javadoc)
     * 
     * @see yanitime4u.yanitime.logic.PlaceLogic#findByKey(java.lang.Long)
     */
    @Override
    public Places findByKey(Long id) {
        AssertionUtil.assertNotNull(id);
        Key key = Datastore.createKey(Places.class, id);
        return Datastore.get(meta, key);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * yanitime4u.yanitime.logic.PlaceLogic#findByCondition(yanitime4u.yanitime.condition.PlaceCondition
     * )
     */
    @Override
    public List<Places> findByCondition(PlaceCondition condition) {
        AssertionUtil.assertNotNull(condition);
        ModelQuery<Places> query = Datastore.query(meta);
        FilterCriterion[] filters = createFilterByCondition(condition);

        if (filters.length > 0) {
            query = query.filter(filters);
        }
        return query.asList();
    }

    private FilterCriterion[] createFilterByCondition(PlaceCondition condition) {
        List<FilterCriterion> filters = new ArrayList<FilterCriterion>();

        if (!StringUtil.isEmpty(condition.getPlaceName())) {
            filters.add(meta.placeName.equal(condition.getPlaceName()));
        }

        if (condition.getCoordinate() != null) {
            filters.add(meta.coordinate.equal(condition.getCoordinate()));
        }

        return filters.toArray(new FilterCriterion[filters.size()]);
    }

    /*
     * (non-Javadoc)
     * 
     * @see yanitime4u.yanitime.logic.PlaceLogic#create(yanitime4u.yanitime.model.Places)
     */
    @Override
    public Places create(Places place) {
        AssertionUtil.assertNotNull(place);
        Transaction tx = Datastore.beginTransaction();
        try {
            Places register = new Places();
            BeanUtil.copy(place, register);
            Datastore.put(register);
            tx.commit();
            return register;
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
     * @see yanitime4u.yanitime.logic.PlaceLogic#update(com.google.appengine.api.datastore.Key,
     * java.lang.Long, java.util.Map)
     */
    @Override
    public Places update(Key key, Long version, Map<String, Object> input) {
        AssertionUtil.assertNotNull(key);
        AssertionUtil.assertNotNull(version);
        AssertionUtil.assertNotNull(input);

        Transaction tx = Datastore.beginTransaction();
        try {
            Places latest = Datastore.get(meta, key, version);
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
     * @see yanitime4u.yanitime.logic.PlaceLogic#delete(com.google.appengine.api.datastore.Key,
     * java.lang.Long)
     */
    @Override
    public void delete(Key key, Long version) {
        AssertionUtil.assertNotNull(key);
        AssertionUtil.assertNotNull(version);

        Transaction tx = Datastore.beginTransaction();
        try {
            Places latest = Datastore.get(meta, key, version);
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
