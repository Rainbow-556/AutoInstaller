package rainbow556.autoinstaller;

import android.accessibilityservice.AccessibilityService;
import android.annotation.TargetApi;
import android.os.Build;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import java.util.List;

/**
 * Created by lixiang on 2016/9/13.
 */
public class AutoInstallerService extends AccessibilityService{
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event){
        JLog.d("---------------------------start----------------------------------");
        int eventType = event.getEventType();//事件类型
        JLog.e("packageName:" + event.getPackageName() + "");//响应事件的包名，也就是哪个应用才响应了这个事件
        JLog.e("source:" + event.getSource() + "");//事件源信息
        JLog.e("source class:" + event.getClassName() + "");//事件源的类名，比如android.widget.TextView
        JLog.e("event type(int):" + eventType + "");
        switch(eventType){
            case AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED:// 通知栏事件
                JLog.e("event type:TYPE_NOTIFICATION_STATE_CHANGED");
                break;
            case AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED://窗体状态改变
                JLog.e("event type:TYPE_WINDOW_STATE_CHANGED");
                break;
            case AccessibilityEvent.TYPE_VIEW_ACCESSIBILITY_FOCUSED://View获取到焦点
                JLog.e("event type:TYPE_VIEW_ACCESSIBILITY_FOCUSED");
                break;
            case AccessibilityEvent.TYPE_GESTURE_DETECTION_START:
                JLog.e("event type:TYPE_VIEW_ACCESSIBILITY_FOCUSED");
                break;
            case AccessibilityEvent.TYPE_GESTURE_DETECTION_END:
                JLog.e("event type:TYPE_GESTURE_DETECTION_END");
                break;
            case AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED:
                JLog.e("event type:TYPE_WINDOW_CONTENT_CHANGED");
                break;
            case AccessibilityEvent.TYPE_VIEW_CLICKED:
                JLog.e("event type:TYPE_VIEW_CLICKED");
                break;
            case AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED:
                JLog.e("event type:TYPE_VIEW_TEXT_CHANGED");
                break;
            case AccessibilityEvent.TYPE_VIEW_SCROLLED:
                JLog.e("event type:TYPE_VIEW_SCROLLED");
                break;
            case AccessibilityEvent.TYPE_VIEW_TEXT_SELECTION_CHANGED:
                JLog.e("event type:TYPE_VIEW_TEXT_SELECTION_CHANGED");
                break;
        }
        for(CharSequence txt : event.getText()){
            JLog.e("text:" + txt);//输出当前事件包含的文本信息
        }
        JLog.d("-----------------------------end--------------------------------");
        try{
            findAndPerformActionButton("替换");
            findAndPerformActionButton("安装");
            findAndPerformActionButton("打开");
        }catch(Exception e){
        }
    }

    @Override
    public void onInterrupt(){
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void findAndPerformActionButton(String text){
        if(getRootInActiveWindow() == null)//取得当前激活窗体的根节点
            return;
        //通过文字找到当前的节点
        List<AccessibilityNodeInfo> nodes = getRootInActiveWindow().findAccessibilityNodeInfosByText(text);
        for(int i = 0; i < nodes.size(); i++){
            AccessibilityNodeInfo node = nodes.get(i);
            // 执行点击行为
            if(node.getClassName().equals("android.widget.Button") && node.isEnabled()){
                node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void findAndPerformActionTextView(String text){
        if(getRootInActiveWindow() == null)
            return;
        //通过文字找到当前的节点
        List<AccessibilityNodeInfo> nodes = getRootInActiveWindow().findAccessibilityNodeInfosByText(text);
        for(int i = 0; i < nodes.size(); i++){
            AccessibilityNodeInfo node = nodes.get(i);
            // 执行按钮点击行为
            if(node.getClassName().equals("android.widget.TextView") && node.isEnabled()){
                node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
            }
        }
    }
}
