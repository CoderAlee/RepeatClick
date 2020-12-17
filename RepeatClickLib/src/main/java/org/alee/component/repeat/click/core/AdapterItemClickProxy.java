package org.alee.component.repeat.click.core;

import android.view.View;
import android.widget.AdapterView;

import androidx.annotation.NonNull;

import org.alee.component.repeat.click.ClickDeduplication;

/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2020/12/17
 * @description: xxxx
 *
 *********************************************************/
final class AdapterItemClickProxy implements AdapterView.OnItemClickListener {
    private final AdapterView.OnItemClickListener mOriginListener;

    AdapterItemClickProxy(@NonNull AdapterView.OnItemClickListener listener) {
        mOriginListener = listener;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (!ClickDeduplication.isFastDoubleClick(view, RepeatedClickHelper.getInterval())) {
            if (null == mOriginListener) {
                return;
            }
            mOriginListener.onItemClick(parent, view, position, id);
        }
    }
}
