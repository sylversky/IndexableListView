/*
 * Modified by Leonardus Ardyandhita 2017
 * Based on https://github.com/woozzu/IndexableListView
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sylversky.indexablelistview.section;

import com.sylversky.indexablelistview.scroller.Indexer;
import com.sylversky.indexablelistview.scroller.StringMatcher;

public class AlphabetSection extends Section {
    private String mSections = "#ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public AlphabetSection(Indexer indexer) {
        super(indexer);
    }

    @Override
    public Object[] getArraySections() {
        String[] sections = new String[mSections.length()];
        for (int i = 0; i < mSections.length(); i++) {
            sections[i] = String.valueOf(mSections.charAt(i));
        }
        return sections;
    }

    @Override
    public int getPositionForSection(int section, int totalComponent) {
            for (int j = 0; j < totalComponent; j++) {
                if (section == 0) {
                    // For numeric section
                    for (int k = 0; k <= 9; k++) {
                        String value = getIndexer().getComponentName(j).toUpperCase();
                        if (StringMatcher.match(String.valueOf(value.charAt(0)), String.valueOf(k)))
                            return j;
                    }
                } else {
                    String value = getIndexer().getComponentName(j).toUpperCase();
                    if (StringMatcher.match(String.valueOf(value.charAt(0)), String.valueOf(mSections.charAt(section))))
                        return j;
                }
            }
        return -1;
    }
}
