// Generated code from Butter Knife. Do not modify!
package com.myproject.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class MeiNvActivity$$ViewBinder<T extends com.myproject.activity.MeiNvActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131099747, "field 'meinv_title' and method 'bianhua'");
    target.meinv_title = finder.castView(view, 2131099747, "field 'meinv_title'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.bianhua(p0);
        }
      });
    view = finder.findRequiredView(source, 2131099746, "method 'back'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.back(p0);
        }
      });
  }

  @Override public void unbind(T target) {
    target.meinv_title = null;
  }
}
