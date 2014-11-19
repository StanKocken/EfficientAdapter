# Efficient Adapter for Android

An efficient adapter to make the use of [RecyclerView](https://developer.android.com/reference/android/support/v7/widget/RecyclerView.html) much easier.

## Overview

For many years, I used a custom kind of Adapter with my ListView.

I adapted it to work with this new [RecyclerView](https://developer.android.com/reference/android/support/v7/widget/RecyclerView.html) and release it as a public repository.

Create a list of elements into a list is not that easy for a beginner, and repetitive for others. My goal is to simplify that for you.

## How does it work?

You create a class that will be your Controller of your view. I give you your object, your view, then you do whatever you want:

    public class BookViewHolder extends AbsViewHolder<Book> {
        public BookViewHolder(View itemView) {  super(itemView); }

        @Override
        protected void updateView(Context context, Book object) {
            TextView textView = (TextView) findViewByIdEfficient(R.id.title_textview);
            textView.setText(object.getTitle());
        }
    }

Then you set your adapter into your RecyclerView:

    SimpleAdapter adapter = new SimpleAdapter<Plane>(
        R.layout.item_book, BookViewHolder.class,
        listOfBooks);
    recyclerView.setAdapter(adapter);

And that's it, you have your list of books!

## Other features

### Heterogenous list
For a list of different kind of objects, layout, viewholder…

    HeterogeneousAdapter adapter = new HeterogeneousAdapter(generateListObjects()) {
        @Override
        public int getItemViewType(int position) {
            if (get(position) instanceof Plane) {
                return VIEW_TYPE_PLANE;
            } else {
                return VIEW_TYPE_BOOK;
            }
        }

        @Override
        protected Class<? extends AbsViewHolder> getViewHolderClass(int viewType) {
            switch (viewType) {
                case VIEW_TYPE_BOOK:
                    return BookViewHolder.class;
                case VIEW_TYPE_PLANE:
                    return PlaneViewHolder.class;
                default:
                    return null;
            }
        }

        @Override
        protected int getLayoutResId(int viewType) {
            switch (viewType) {
                case VIEW_TYPE_BOOK:
                    return R.layout.item_book;
                case VIEW_TYPE_PLANE:
                    return R.layout.item_plane;
                default:
                    return 0;
            }
        }
    };

### Efficient findViewById()

Because `findViewById(int id)` is consuming (this is why we use the ViewHolder pattern), I introduced a `findViewByIdEfficient(int id)` into the ViewHolder class.

You can use it like a `findViewById(int id)` but the view return will be cached to be returned in the next call.

Your view id should be unique into your view hierarchy, but sometimes is not that easy (with an include for example). It's now easier to find a subview by specify the parent of this subview with `findViewByIdEfficient(int parentId, int id)`


### Let the element be clickable

Your ViewHolder class can override the method `isClickable()` to tell is this element is clickable or not.

## Proguard

To be compatible with proguard you need to add this lines in your file

    -keepclassmembers public class * extends com.skocken.efficientadapter.lib.viewholder.AbsViewHolder {
        public <init>(...);
    }

## Gradle

    dependencies {
        compile 'com.skocken:efficientadapter.lib:1.0'
    }


## License

* [Apache 2.0](http://www.apache.org/licenses/LICENSE-2.0.html)

## Contributing

Please fork this repository and contribute back using
[pull requests](https://github.com/StanKocken/EfficientAdapter/pulls).

Any contributions, large or small, major features, bug fixes, additional
language translations, unit/integration tests are welcomed and appreciated
but will be thoroughly reviewed and discussed.