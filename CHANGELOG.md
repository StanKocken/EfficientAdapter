Change Log
===============================================================================

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
Initial release.