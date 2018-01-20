Change Log
===============================================================================

Version 2.2.2 *(2018-01-20)*
----------------------------

 * Improve compatibility with Kotlin (Null annotations, reserved keywords…)
 * Update dependencies versions

Version 2.2.1 *(2016-12-17)*
----------------------------

 * Added AdapterUpdater to do lazy updating of an EfficientAdapter
 * Update dependencies versions

Version 2.1.0 *(2016-04-30)*
----------------------------

 * Move the item click listener to the EfficientViewHolder.
 * Upload to JCenter instead of MavenCentral
 * Upgrade to support library


Version 2.0.3 *(2016-01-10)*
----------------------------

 * Added method with ViewHelper to minimize the code in the ViewHolders

Version 2.0.2 *(2015-10-06)*
----------------------------

 * Fix remove into EfficientRecyclerAdapter (called twice removeAt)

Version 2.0.1 *(2015-09-30)*
----------------------------

 * Fix notify on change not set to true by default

Version 2.0 *(2015-09-19)*
----------------------------

 * Re-write the adapter to be compatible with ViewPager

How to migrate from 1.x:
- the gradle import change from `com.skocken:efficientadapter.lib:1.2.+`  to `com.skocken:efficientadapter:2.0` (`.lib` disappear)
- Rename all `AbsViewHolderAdapter` to `EfficientAdapter`
- Rename all `AbsViewHolder` to `EfficientViewHolder`
- Rename all `HeterogeneousAdapter` and `SimpleAdapter` to `EfficientRecyclerAdapter`
- Rename all `AbsViewHolderAdapter.OnItemClickListener` by `EfficientAdapter.OnItemClickListener`
- The function `remove(int position)` changed to `removeAt(int position)`
- The function `moved(int from, int to)` changed to `move(int from, int to)`
- The method `protected Class<?> getViewHolderClass()` into the Adapter is going `public` now
- The method `protected int getLayoutResId()` into the Adapter is going `public` now
- Proguard: replace `AbsViewHolder` by `EfficientViewHolder`




Version 1.2.1 *(2015-02-17)*
----------------------------

 * The click listener can now be changed dynamically (the adapter will re-call isClickable() after updateView() of AbsViewHolder)

Version 1.2.0 *(2015-01-07)*
----------------------------

 * Add method to prevent calling notifyItem*** (issue #9)
 * Change the default "isClickable" method of ViewHolder to true if a listener is present

Version 1.1.0 *(2015-01-03)*
----------------------------

 * Add template to OnClickListener (following the recommendation of issue #8)
 * Fixed method argument name for getLayoutResId()

Version 1.0.0 *(2011-03-07)*
----------------------------
 * Initial release.