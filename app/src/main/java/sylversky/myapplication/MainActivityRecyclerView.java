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

package sylversky.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.sylversky.indexablelistview.widget.IndexableRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivityRecyclerView extends AppCompatActivity {

    private IndexableRecyclerView listView;
    private MyAdapterRecyclerView myAdapterRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        listView = (IndexableRecyclerView) findViewById(R.id.listView);
        listView.setLayoutManager(new LinearLayoutManager(this));

        myAdapterRecyclerView = new MyAdapterRecyclerView(this);
        listView.setAdapter(myAdapterRecyclerView);
        initData();
    }

    private void initData(){
        List<String> data = new ArrayList<>();
        data.add("Angelina Jolie");
        data.add("Brooke Burns");
        data.add("Cameron Diaz");
        data.add("Drew Barrymore");
        data.add("Emma Stone");
        data.add("Farah Fawcet");
        data.add("Greta Gerwig");
        data.add("Haley Hudson");
        data.add("Ingrid Bergman");
        data.add("Jodie Foster");
        data.add("Kiera Knightley");
        data.add("Leighton Meester");
        data.add("Marion Cotillard");
        data.add("Nataly Portman");
        data.add("Olivia Wilde");
        data.add("Paris Hilton");
        data.add("Rachel Mc Addams");
        data.add("Salma Hayek");
        data.add("Tessa Thompson");
        data.add("Vanessa Johanson");
        data.add("Zsa Zsa Gabor");

        myAdapterRecyclerView.setStringList(data);
    }
}
