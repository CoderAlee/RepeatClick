package org.alee.component.repeat.click.core;

import android.app.Activity;
import android.view.View;

import androidx.fragment.app.Fragment;

/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2020/12/17
 * @description: xxxx
 *
 *********************************************************/
public final class RepeatedClickHelper {

    private static long sInterval = 1000L;
    private final AndroidClickHook mClickHook;

    private RepeatedClickHelper() {
        mClickHook = new AndroidClickHook();
    }

    static long getInterval() {
        return sInterval;
    }

    public static void setInterval(long interval) {
        sInterval = interval;
    }

    public RepeatedClickHelper hook(View view) {
        mClickHook.hookViewClick(view);
        return getInstance();
    }

    /**
     * 获取单例对象
     *
     * @return {@link RepeatedClickHelper}
     */
    public static RepeatedClickHelper getInstance() {
        return RepeatClickHelperHolder.INSTANCE;
    }

    public RepeatedClickHelper hook(Fragment fragment) {
        mClickHook.hookViewClick(fragment);
        return getInstance();
    }

    public RepeatedClickHelper hook(Activity activity) {
        mClickHook.hookViewClick(activity);
        return getInstance();
    }

    /**
     * 静态内部类持有外部对象实现单利方式
     */
    private static class RepeatClickHelperHolder {
        private static RepeatedClickHelper INSTANCE = new RepeatedClickHelper();
    }
}
