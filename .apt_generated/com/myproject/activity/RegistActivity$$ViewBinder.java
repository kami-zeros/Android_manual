// Generated code from Butter Knife. Do not modify!
package com.myproject.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class RegistActivity$$ViewBinder<T extends com.myproject.activity.RegistActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131099747, "field 'regist_title'");
    target.regist_title = finder.castView(view, 2131099747, "field 'regist_title'");
    view = finder.findRequiredView(source, 2131099715, "field 'etPassword'");
    target.etPassword = finder.castView(view, 2131099715, "field 'etPassword'");
    view = finder.findRequiredView(source, 2131099717, "field 'radioG_gender'");
    target.radioG_gender = finder.castView(view, 2131099717, "field 'radioG_gender'");
    view = finder.findRequiredView(source, 2131099713, "field 'etUsername'");
    target.etUsername = finder.castView(view, 2131099713, "field 'etUsername'");
    view = finder.findRequiredView(source, 2131099711, "field 'Avatar' and method 'setAvatar'");
    target.Avatar = finder.castView(view, 2131099711, "field 'Avatar'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.setAvatar(p0);
        }
      });
    view = finder.findRequiredView(source, 2131099720, "method 'regist'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.regist(p0);
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
    target.regist_title = null;
    target.etPassword = null;
    target.radioG_gender = null;
    target.etUsername = null;
    target.Avatar = null;
  }
}
