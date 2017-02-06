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

package com.sylversky.indexablelistview.scroller;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class RecyclerViewIndexScroller extends IndexScroller {

	private RecyclerView listView = null;

	public RecyclerViewIndexScroller(Context context, RecyclerView listView) {
		super(context);
		this.listView = listView;
	}

	public void setAdapter(RecyclerView.Adapter adapter) {
		super.setAdapter((Indexer) adapter);
	}

	@Override
	protected void setSelection(int position) {
		if(position!=-1) {
			((LinearLayoutManager)this.listView.getLayoutManager()).scrollToPositionWithOffset(position,0);
		}
		this.listView.invalidate();
	}

	@Override
	protected void invalidate() {
		this.listView.invalidate();
	}
}
