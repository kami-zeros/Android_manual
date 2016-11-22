// Generated code from Butter Knife. Do not modify!
package com.myproject.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class LoginActivity$$ViewBinder<T extends com.myproject.activity.LoginActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131099683, "field 'etUsername'");
    target.etUsername = finder.castView(view, 2131099683, "field 'etUsername'");
    view = finder.findRequiredView(source, 2131099684, "field 'etPassword'");
    target.etPassword = finder.castView(view, 2131099684, "field 'etPassword'");
    view = finder.findRequiredView(source, 2131099747, "field 'login_title'");
    target.login_title = finder.castView(view, 2131099747, "field 'login_title'");
    view = finder.findRequiredView(source, 2131099685, "method 'login'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.login(p0);
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
    view = finder.findRequiredView(source, 2131099686, "method 'quxiao'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.quxiao(p0);
        }
      });
    view = finder.findRequiredView(source, 2131099687, "method 'regist'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.regist(p0);
        }
      });
  }

  @Override public void unbind(T target) {
    target.etUsername = null;
    target.etPassword = null;
    target.login_title = null;
  }
}
