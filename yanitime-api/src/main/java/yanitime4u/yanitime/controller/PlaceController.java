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

import yanitime4u.yanitime.condition.PlaceCondition;
import yanitime4u.yanitime.logic.PlaceLogic;
import yanitime4u.yanitime.logic.impl.PlaceLogicImpl;
import yanitime4u.yanitime.model.Places;

import com.google.appengine.api.datastore.Key;

/**
 * @author jupitris
 * 
 */
public class PlaceController {

    private final PlaceLogic placeLogic = new PlaceLogicImpl();

    public Places findById(Map<String, Object> param) {
        return placeLogic.findByKey(Long.valueOf(param.get("id").toString()));
    }

    public List<Places> find(PlaceCondition condition) {
        return placeLogic.findByCondition(condition);
    }

    public Places create(Places places) {
        return placeLogic.create(places);
    }

    public void update(Places places) {
        Map<String, Object> input = new HashMap<String, Object>();
        input.put("placeName", places.getPlaceName());
        input.put("coordinate", places.getCoordinate());
        input.put("comment", places.getComment());
        placeLogic.update(places.getKey(), places.getVersion(), input);
    }

    public void delete(Key id, Long version) {
        placeLogic.delete(id, version);
    }
}
