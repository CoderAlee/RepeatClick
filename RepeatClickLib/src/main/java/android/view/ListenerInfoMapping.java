package android.view;

import org.alee.reflex.ReflexClass;
import org.alee.reflex.ReflexObject;

/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2020/12/17
 * @description: xxxx
 *
 *********************************************************/
public final class ListenerInfoMapping {
    public static ReflexObject<View.OnClickListener> mOnClickListener;

    static {
        ReflexClass.load(ListenerInfoMapping.class, "android.view.View$ListenerInfo");
    }
}
