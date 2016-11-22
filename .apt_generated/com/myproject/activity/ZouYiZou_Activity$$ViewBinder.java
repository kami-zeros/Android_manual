// Generated code from Butter Knife. Do not modify!
package com.myproject.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class ZouYiZou_Activity$$ViewBinder<T extends com.myproject.activity.ZouYiZou_Activity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131099731, "field 'edCity'");
    target.edCity = finder.castView(view, 2131099731, "field 'edCity'");
    view = finder.findRequiredView(source, 2131099732, "field 'search' and method 'search'");
    target.search = finder.castView(view, 2131099732, "field 'search'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.search(p0);
        }
      });
    view = finder.findRequiredView(source, 2131099733, "field 'choice' and method 'choice'");
    target.choice = finder.castView(view, 2131099733, "field 'choice'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.choice(p0);
        }
      });
  }

  @Override public void unbind(T target) {
    target.edCity = null;
    target.search = null;
    target.choice = null;
  }
}
