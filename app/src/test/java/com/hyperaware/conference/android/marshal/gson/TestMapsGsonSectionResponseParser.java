/*
 * Copyright 2015 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hyperaware.conference.android.marshal.gson;

import com.hyperaware.conference.android.eventmobi.model.MapItem;
import com.hyperaware.conference.android.eventmobi.model.MapsSectionResponse;
import com.hyperaware.conference.android.eventmobi.model.Section;
import com.hyperaware.conference.android.eventmobi.model.SectionResponse;
import com.hyperaware.conference.android.eventmobi.parser.gson.GsonSectionResponseParser;

import org.junit.Test;

import java.io.InputStream;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class TestMapsGsonSectionResponseParser {

    @Test
    public void test() throws Exception {
        GsonSectionResponseParser<MapItem, MapsSectionResponse> parser =
            new GsonSectionResponseParser<>(MapsSectionResponse.class);
        InputStream is = getClass().getResourceAsStream("/section_maps.json");
        SectionResponse<MapItem> response = parser.parse(is);
        is.close();

        assertNotNull(response);

        assertEquals("success", response.getStatus());

        Section<MapItem> section = response.getSection();
        assertNotNull(section);
        assertEquals("122378", section.getId());
        assertEquals("9849", section.getEventId());
        assertEquals("Maps", section.getName());
        assertEquals("maps", section.getType());

        List<MapItem> items = section.getItems();
        assertNotNull(items);
        assertEquals(2, items.size());

        MapItem item0 = items.get(0);
        assertNotNull(item0);
        assertEquals("15167", item0.getId());
        assertEquals("Stubbed Name", item0.getName());
        assertEquals("stubbed.gif", item0.getFilename());
        assertFalse(item0.isGoogleMap());

        MapItem item1 = items.get(1);
        assertNotNull(item1);
        assertEquals("15166", item1.getId());
        assertEquals("Stubbed Google Map", item1.getName());
        assertTrue(item1.isGoogleMap());
        assertEquals("https://maps.google.com/whateverthisis", item1.getGoogleMapUrl());
    }

}
