package rainbow556.autoinstaller;
/**
 * Created by lixiang on 2016/9/13.
 */

import android.util.Log;

/**
 * Log工具类
 *
 * @author Carl.li
 */
public final class JLog{
    private JLog(){
        throw new UnsupportedOperationException("让你初始化！");
    }

    /**
     * 是否需要打印bug，可以在application的onCreate函数里面初始化
     */
    public static boolean isDebug = true;
    public static final String TAG_NET_LOG = "JfzNetLog";
    private static final String TAG = "lx";

    // 下面四个是默认tag的函数
    public static void i(Object msg){
        if(isDebug)
            Log.i(TAG, msg.toString());
    }

    public static void d(Object msg){
        if(isDebug)
            Log.d(TAG, msg.toString());
    }

    public static void e(Object msg){
        if(isDebug)
            Log.e(TAG, msg.toString());
    }

    public static void v(Object msg){
        if(isDebug)
            Log.v(TAG, msg.toString());
    }

    // 自定义tag
    public static void i(String tag, Object msg){
        if(isDebug)
            Log.i(tag, msg.toString());
    }

    public static void d(String tag, Object msg){
        if(isDebug)
            Log.d(tag, msg.toString());
    }

    public static void e(String tag, Object msg){
        if(isDebug)
            Log.e(tag, msg.toString());
    }

    public static void v(String tag, Object msg){
        if(isDebug)
            Log.v(tag, msg.toString());
    }
}
