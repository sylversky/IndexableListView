# IndexableListView
iPhone like fast scroll ListView on Android.
base on https://github.com/woozzu/IndexableListView
![alt text](image.png)

Whats New
=========
- Supported for ListView and RecyclerView
- Dynamicaly invisible/visible indexer
- Set default font for indexer
- Auto hide indexer when keyboard show up

Usage
=====
Add on your project level build.gradle repositories:
(this is optional, already available on jcenter)

```
allprojects {
    repositories {
        ...
        maven {
            url 'https://dl.bintray.com/sylversky/AndroidLibrary/'
        }
    }
}
```


Add on your app level build.gradle dependencies:
```
dependencies{
compile 'com.sylversky.library:indexablelistview:1.0.1'
}
```

IMPLEMENTATION
==============
See example code(/app/src/main/java) for better explanation

Create ListView
===============
On XML Layout :

- For RecyclerView

```
  <com.sylversky.indexablelistview.widget.IndexableRecyclerView
       android:id="@+id/listView"
       android:layout_width="match_parent"
       android:layout_height="match_parent">
   </com.sylversky.indexablelistview.widget.IndexableRecyclerView>
```
  
- For ListView :
  
```
  <com.sylversky.indexablelistview.widget.IndexableListView
       android:id="@+id/listView"
       android:layout_width="match_parent"
       android:layout_height="match_parent">
   </com.sylversky.indexablelistview.widget.IndexableListView>
```
  

Create Adapter
==============
  
- Implement Indexer interface on your adapter class.
  
  - For RecyclerView:
```
  public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Indexer {
    ...
  }
```
  
  - For ListView:
```
  public class MyAdapter extends BaseAdapter implements Indexer {
    ...
  }
```

- Implement all related methods.

```
    @Override
    public String getComponentName(int position) {
        return null;
    }

    @Override
    public Object[] getSections() {
        return new Object[]();
    }

    @Override
    public int getPositionForSection(int i) {
        return 0;
    }

    @Override
    public int getSectionForPosition(int i) {
        return 0;
    }
```

- Declare and initialize object AlphabetSection on adapter:

```
    private AlphabetSection alphabetSection;

    public MyAdapter(Context context){
        this.context = context;
        this.alphabetSection = new AlphabetSection(this);
    }
    ...
```

- Return item name on getComponentName():

```
    @Override
    public String getComponentName(int position) {
        return items.get(position);
    }
    ...
```

- Return secton array from alphabetSection on geSection():
```
    @Override
    public Object[] getSections() {
        return alphabetSection.getArraySections();
    }
    ...
```

- Return getPositionForSection from alphabetSection on getPositionForSection():
```
    @Override
    public int getPositionForSection(int i) {
        return alphabetSection.getPositionForSection(i, getItemCount());
    }
```

- Just leave it 0 on getSectionForPosition():
```
    @Override
    public int getSectionForPosition(int i) {
        return 0;
    }
```

Initialize RecyclerView/ListView
================================
  - For RecyclerView :
```
    listView = (IndexableRecyclerView) findViewById(R.id.listView);
    listView.setLayoutManager(new LinearLayoutManager(this));

    myAdapterRecyclerView = new MyAdapter(this);
    listView.setAdapter(myAdapterRecyclerView);
```
  
  - For ListView :
```
    listView = (IndexableListView) findViewById(R.id.listView);
  
    myAdapterListView = new MyAdapter(this);
    listView.setAdapter(myAdapterListView);
```
  
Other
=====

- Set default font for indexer:
  ```
  Typeface font = Typeface.createFromAsset(context.getAssets(), "myfont.ttf");
  listView.setIndexTypeface(font);
  ```

- Hide indexer:
  
  ```
  listView.setInvisibleIndexer(true);
  ```

Create Custom Section
=====================
If you need other than alphabet sections, you can create your own section with this class.

- Extend class Section:
```
  public class CustomSection extends Section {
  ...
  }
```

- Implement all related method:
```
  public class CustomSection extends Section {
  
      @Override
      public Object[] getArraySections() {
      }
      
      @Override
      public int getPositionForSection(int section, int totalComponent) {
      }
```

- Declare your String section:
```
  public class CustomSection extends Section {
      private String mSections = "#ABCDEFGHIJKLMNOPQRSTUVWXYZ";
      
      @Override
      public Object[] getArraySections() {
      }
      
      @Override
      public int getPositionForSection(int section, int totalComponent) {
      }
```

- Return String array of your section on getArraySections() :
```
    @Override
    public Object[] getArraySections() {
        String[] sections = new String[mSections.length()];
        for (int i = 0; i < mSections.length(); i++) {
            sections[i] = String.valueOf(mSections.charAt(i));
        }
        return sections;
    }
```

- Write your logic for checking string name on getPositionForSection():
```
    //This is example for checking name for alphabet Section
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
```
