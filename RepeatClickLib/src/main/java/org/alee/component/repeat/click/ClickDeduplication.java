package org.alee.component.repeat.click;

import android.view.View;

import androidx.collection.LruCache;

/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2020/12/17
 * @description: xxxx
 *
 *********************************************************/
public final class ClickDeduplication {

    private static final LruCache<Integer, Long> HIT_POOL = new LruCache<>(1 << 6);

    /**
     * 判断是否快速点击
     *
     * @param v        被点击的控件
     * @param interval 两次点击事件的最小间隔时间
     * @return 结果
     */
    public static boolean isFastDoubleClick(View v, long interval) {
        int viewId = System.identityHashCode(v);
        Long lastClickTime = HIT_POOL.get(viewId);
        long time = System.currentTimeMillis();
        if (null == lastClickTime) {
            save(viewId, time);
            return false;
        }
        long timeInterval = Math.abs(time - lastClickTime);
        if (timeInterval < interval) {
            return true;
        }
        save(viewId, time);
        return false;
    }

    private static void save(int viewId, long clickTime) {
        HIT_POOL.put(viewId, clickTime);
    }
}
