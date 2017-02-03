# IndexableListView
iPhone like fast scroll ListView on Android.
base on https://github.com/woozzu/IndexableListView

Whats New
=========
- Supported for ListView and RecyclerView
- Dynamicaly invisible/visible indexer
- Set default font for indexer
- Auto hide indexer when keyboard show up

Usage
=====
- For RecyclerView

  Create Adapter:
  - implement interface Indexer on adapter
  - implements all related methods
  - use that methods like sample below
  - on getIndexer() part, means you need to return your String label for each elements member
  
  
  ```
  public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Indexer{

    private Context context
    private List<String> nameList = new ArrayList();
    private Section section;

    public MyAdapter(Context context){
      this.context = context;
      this.section = new Section();
      this.section.setIndexer(this);
    }

    @Override
    public Object[] getSections() {
        return section.getSections();
    }

    @Override
    public int getPositionForSection(int i) {
        return section.getPositionForSection(i, getItemCount());
    }

    @Override
    public int getSectionForPosition(int i) {
        return 0;
    }

    @Override
    public String getIndexer(int position) {
        String item = nameList.get(position);
        return item;
    }

    ...
}
  ```
  
  Create IndexableRecyclerView on xml layout:
  
  ```
  <com.sylversky.indexablelistview.widget.IndexableRecyclerView
        android:id="@+id/listView"
        android:layout_width="match_parent"
        android:layout_height="match_parent">
    </com.sylversky.indexablelistview.widget.IndexableRecyclerView>
  ```
  
  On java code part, just do like the ordinary:
  
  ```
  MyAdapter adapter = new MyAdapter(getContext());
  ...
  IndexableRecyclerView listView = (IndexableRecyclerView) findViewById(R.id.listView);
  listView.setLayoutManager(new LinearLayoutManager(getContext()));
  listView.setAdapter(adapter);
  
  ```
  
  
  
- For ListView

   Create Adapter:
  - implement interface Indexer on adapter
  - implements all related methods
  - use that methods like sample below
  - on getIndexer() part, means you need to return your String label for each elements member
  
  
  ```
  public class MyAdapter extends extends BaseAdapter implements Indexer{

    private Context context
    private List<String> nameList = new ArrayList();
    private Section section;

    public MyAdapter(Context context){
      this.context = context;
      this.section = new Section();
      this.section.setIndexer(this);
    }

    @Override
    public Object[] getSections() {
        return section.getSections();
    }

    @Override
    public int getPositionForSection(int i) {
        return section.getPositionForSection(i, getItemCount());
    }

    @Override
    public int getSectionForPosition(int i) {
        return 0;
    }

    @Override
    public String getIndexer(int position) {
        String item = nameList.get(position);
        return item;
    }

    ...
}
  ```
  
  Create IndexableListView on xml layout:
  
  ```
  <com.sylversky.indexablelistview.widget.IndexableListView
        android:id="@+id/listView"
        android:layout_width="match_parent"
        android:layout_height="match_parent">
    </com.sylversky.indexablelistview.widget.IndexableListView>
  ```
  
  On java code part, just do like the ordinary:
  
  ```
  MyAdapter adapter = new MyAdapter(getContext());
  ...
  IndexableListView listView = (IndexableListView) findViewById(R.id.listView);
  listView.setAdapter(adapter);
  
  ```
  
- Other:


  Set default font for indexer:
  ```
  Typeface font = Typeface.createFromAsset(context.getAssets(), "myfont.ttf");
  listView.setIndexTypeface(font);
  ```
  Hide indexer:
  
  ```
  listView.setInvisibleIndexer(true);
  ```
