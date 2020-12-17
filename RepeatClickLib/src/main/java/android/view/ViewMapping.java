package android.view;

import org.alee.reflex.ReflexClass;
import org.alee.reflex.ReflexMethod;

/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2020/12/17
 * @description: xxxx
 *
 *********************************************************/
public final class ViewMapping {
    public static ReflexMethod<Boolean> getListenerInfo;

    static {
        ReflexClass.load(ViewMapping.class, View.class);
    }
}
