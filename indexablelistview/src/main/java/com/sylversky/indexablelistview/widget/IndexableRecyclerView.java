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

package com.sylversky.indexablelistview.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.sylversky.indexablelistview.scroller.RecyclerViewIndexScroller;

public class IndexableRecyclerView extends RecyclerView {

    private RecyclerViewIndexScroller mScroller;
    private boolean invisible;

    public IndexableRecyclerView(Context context) {
        super(context);
        init();
    }

    public IndexableRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public IndexableRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public void init() {
        mScroller = new RecyclerViewIndexScroller(getContext(), this);
        addItemDecoration(itemDecoration);
    }

    public void setInvisibleIndexer(boolean invisibleIndexer) {
        this.invisible = invisibleIndexer;
        mScroller.setInvisible(invisibleIndexer);
    }

    public void setIndexTypeface(Typeface typeface){
        mScroller.setDefaultTypeface(typeface);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        return mScroller.onTouchEvent(e) || super.onTouchEvent(e);
    }

    @Override
    public boolean fling(int velocityX, int velocityY) {
        mScroller.show();
        return super.fling(velocityX, velocityY);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        return mScroller.contains(e.getX(), e.getY()) || super.onInterceptTouchEvent(e);
    }

    @Override
    public void setAdapter(Adapter adapter) {
        super.setAdapter(adapter);
        mScroller.setAdapter(adapter);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mScroller.onSizeChanged(w, h, oldw, oldh);
        if(getChildCount() > 0 && !invisible) {
            if (h < oldh) {
                mScroller.setInvisible(true);
            } else {
                mScroller.setInvisible(false);
            }
        }
    }

    ItemDecoration itemDecoration = new ItemDecoration() {
        @Override
        public void onDrawOver(Canvas c, RecyclerView parent, State state) {
            super.onDrawOver(c, parent, state);
            mScroller.draw(c);
        }
    };
}
