package org.alee.component.repeat.click.core;

import android.app.Activity;
import android.view.ListenerInfoMapping;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewMapping;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2020/12/17
 * @description: xxxx
 *
 *********************************************************/
final class AndroidClickHook {

    void hookViewClick(@NonNull View view) {
        startHook(view);
    }

    void hookViewClick(@NonNull Fragment fragment) {
        startHook(fragment.getView());
    }

    void hookViewClick(@NonNull Activity activity) {
        startHook(activity.getWindow().getDecorView());
    }

    private void startHook(View view) {
        if (null == view) {
            return;
        }
        if (view instanceof ViewGroup) {
            ViewGroup temp = (ViewGroup) view;
            if (temp instanceof RecyclerView) {
                hookRecyclerView((RecyclerView) temp);
            } else if (temp instanceof AdapterView) {
                hookAbsListView((AdapterView) temp);
            } else {
                hookViewGroup(temp);
            }
            return;
        }
        hookView(view);
    }

    private void hookRecyclerView(@NonNull RecyclerView view) {
        //TODO RecyclerView item 的自动hook
    }

    private void hookAbsListView(@NonNull AdapterView adapterView) {
        AdapterView.OnItemClickListener listener = adapterView.getOnItemClickListener();
        if (null == listener) {
            return;
        }
        if (listener instanceof AdapterItemClickProxy) {
            return;
        }
        adapterView.setOnItemClickListener(new AdapterItemClickProxy(listener));
    }

    private void hookViewGroup(@NonNull ViewGroup viewGroup) {
        hookView(viewGroup);
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View child = viewGroup.getChildAt(i);
            startHook(child);
        }
    }

    private void hookView(@NonNull View view) {
        if (!view.isClickable()) {
            return;
        }
        try {
            Object listenerInfo = ViewMapping.getListenerInfo.call(view);
            View.OnClickListener onClickListener = ListenerInfoMapping.mOnClickListener.get(listenerInfo);
            if (null == onClickListener) {
                return;
            }
            if (onClickListener instanceof OnClickListenerProxy) {
                return;
            }
            ListenerInfoMapping.mOnClickListener.set(listenerInfo, new OnClickListenerProxy(onClickListener));
        } catch (Throwable ignored) {
        }
    }
}
