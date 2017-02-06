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

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sylversky.indexablelistview.scroller.Indexer;
import com.sylversky.indexablelistview.section.AlphabetSection;

import java.util.ArrayList;
import java.util.List;

public class MyAdapterRecyclerView extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Indexer {
    private Context context;
    private List<String> stringList = new ArrayList();
    private AlphabetSection alphabetSection;

    public MyAdapterRecyclerView(Context context){
        this.context = context;
        this.alphabetSection = new AlphabetSection(this);
    }

    public void setStringList(List<String> stringList) {
        this.stringList = stringList;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.setData(stringList.get(position));
    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }

    @Override
    public String getComponentName(int position) {
        return stringList.get(position);
    }

    @Override
    public Object[] getSections() {
        return alphabetSection.getArraySections();
    }

    @Override
    public int getPositionForSection(int i) {
        return alphabetSection.getPositionForSection(i, getItemCount());
    }

    @Override
    public int getSectionForPosition(int i) {
        return 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView text;
        public ViewHolder(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.text);
        }

        public void setData(String name){
            text.setText(name);
        }
    }
}
