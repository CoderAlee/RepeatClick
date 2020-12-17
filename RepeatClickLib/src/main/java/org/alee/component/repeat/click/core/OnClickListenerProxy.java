package org.alee.component.repeat.click.core;

import android.view.View;

import androidx.annotation.NonNull;

import org.alee.component.repeat.click.ClickDeduplication;

/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2020/12/17
 * @description: xxxx
 *
 *********************************************************/
final class OnClickListenerProxy implements View.OnClickListener {
    private final View.OnClickListener mOriginListener;

    OnClickListenerProxy(@NonNull View.OnClickListener originListener) {
        mOriginListener = originListener;
    }

    @Override
    public void onClick(View v) {
        if (!ClickDeduplication.isFastDoubleClick(v, RepeatedClickHelper.getInterval())) {
            if (null == mOriginListener) {
                return;
            }
            mOriginListener.onClick(v);
        }
    }
}
