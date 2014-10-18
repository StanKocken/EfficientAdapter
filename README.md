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
            TextView textView
                        = (TextView) findViewByIdEfficient(R.id.title_textview);
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

- Heterogenous list (different kind of objects, layout, viewholder…)
- Let the element be clickable

## License

* [MIT](http://opensource.org/licenses/MIT)

## Contributing

Please fork this repository and contribute back using
[pull requests](https://github.com/******/pulls).

Any contributions, large or small, major features, bug fixes, additional
language translations, unit/integration tests are welcomed and appreciated
but will be thoroughly reviewed and discussed.
