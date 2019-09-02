# Efficient Adapter for Android

Create a new adapter for a [RecyclerView](https://developer.android.com/jetpack/androidx/releases/recyclerview) or [ViewPager](https://developer.android.com/reference/kotlin/androidx/viewpager/widget/ViewPager) is now much easier.

## Overview

Create a list of elements into a [RecyclerView](https://developer.android.com/jetpack/androidx/releases/recyclerview) or [ViewPager](https://developer.android.com/reference/kotlin/androidx/viewpager/widget/ViewPager) is not that easy for a beginner, and repetitive for others. The goal of this library is to simplify that for you.

## How does it work?

Create a class ViewHolder (`BookViewHolder` for example). The method `updateView` will be call with the object, when an update of your view is require:

    public class BookViewHolder extends EfficientViewHolder<Book> {
        public BookViewHolder(View itemView) {  super(itemView); }

        @Override
        protected void updateView(Context context, Book object) {
            TextView textView = findViewByIdEfficient(R.id.title_textview);
            textView.setText(object.getTitle());
			// or just
			setText(R.id.title_textview, object.getTitle());
        }
    }

Give this ViewHolder class to the constructor of the adapter (SimpleAdapter) of your [RecyclerView](https://developer.android.com/jetpack/androidx/releases/recyclerview), with the resource id of your item view and the list of objects:

    EfficientRecyclerAdapter<Plane> adapter = new EfficientRecyclerAdapter<Plane>(R.layout.item_book, BookViewHolder.class, listOfBooks);
    recyclerView.setAdapter(adapter);

And that's it!

It's also working with a [ViewPager](https://developer.android.com/reference/kotlin/androidx/viewpager/widget/ViewPager):

    EfficientPagerAdapter<Plane> adapter = new EfficientPagerAdapter<Plane>(R.layout.item_book, BookViewHolder.class, listOfBooks);
    viewPager.setAdapter(adapter);

## Other features

### Heterogenous list
For a list of different kind of objects, layout, viewholder…

    EfficientRecyclerAdapter adapter = new EfficientRecyclerAdapter(generateListObjects()) {
        @Override
        public int getItemViewType(int position) {
            if (get(position) instanceof Plane) {
                return VIEW_TYPE_PLANE;
            } else {
                return VIEW_TYPE_BOOK;
            }
        }

        @Override
        public Class<? extends EfficientViewHolder> getViewHolderClass(int viewType) {
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
        public int getLayoutResId(int viewType) {
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

Because `findViewById(int)` is CPU-consuming (this is why we use the ViewHolder pattern), this library use a `findViewByIdEfficient(int id)` into the ViewHolder class.

You can use it like a `findViewById(int id)` but the view return will be cached to be returned into the next call.

Your view id should be unique into your view hierarchy, but sometimes is not that easy (with an include for example). It's now easier to find a subview by specify the parent of this subview with `findViewByIdEfficient(int parentId, int id)` to say "the view with this id into the parent with this id".


### Let the element be clickable

Your ViewHolder class can override the method `isClickable()` to tell is this element is clickable or not.

By default, the view is clickable if you have a listener on your adapter.

## Proguard

This library includes the proguard configuration file.
If you want to add it manually:

    -keepclassmembers public class * extends com.skocken.efficientadapter.lib.viewholder.EfficientViewHolder {
        public <init>(...);
    }

## Gradle

    dependencies {
        compile 'com.skocken:efficientadapter:2.4.0'
    }

## Android Support library

If you are still using the deprecated Android Support Library (instead of AndroidX), please use the dependency 2.3.X instead.

## License

* [Apache 2.0](http://www.apache.org/licenses/LICENSE-2.0.html)

## Contributing

Please fork this repository and contribute back using
[pull requests](https://github.com/StanKocken/EfficientAdapter/pulls).

Any contributions, large or small, major features, bug fixes, additional
language translations, unit/integration tests are welcomed and appreciated
but will be thoroughly reviewed and discussed.
