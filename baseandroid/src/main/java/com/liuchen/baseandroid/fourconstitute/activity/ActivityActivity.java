package com.liuchen.baseandroid.fourconstitute.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.liuchen.baseandroid.R;

/**
 * 1.activity的启动模式
 * 参考资料
 * https://www.jianshu.com/p/e8f05c27fcc4
 * https://www.cnblogs.com/claireyuancy/p/7387696.html
 * 2.activity的生命周期
 * 参考资料
 * https://blog.csdn.net/ttandroid/article/details/80926753
 * https://www.cnblogs.com/joinrudy/p/3465442.html
 * https://www.cnblogs.com/nylcy/p/6500832.html
 */
public class ActivityActivity extends AppCompatActivity implements View.OnClickListener {
    private Button activitySkipBtn;
    private static final String TAG = "Skip";

    /**
     * 当activity是被创建时候，会自动运行该方法
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: 第一个activity");
        setContentView(R.layout.activity_activity);
        activitySkipBtn = findViewById(R.id.activitySkipBtn);
        activitySkipBtn.setOnClickListener(this);
        /*1.activity的启动模式
        standard,singleTop,singleTask,singleInstance
        注意事项
        onNewIntent()的调用*/
    }

    /**
     * 当activity对用户可见时会调用onStart 而后如果Activity转入了前台就会调用onResume方法。
     * 如果此时直接屏幕熄灭或者用户按下home键则会直接调用onStop方法，当然这种情况比较极端。
     */
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: 第一个activity");
    }

    /**
     * 当activity开始与用户交互时，会调用onResume 此时，Activity 处于 Activity 堆栈的顶层，
     * 并具有用户输入焦点。当跳转另一个Activity，或者退出当前Activity后会调用onPause方法。
     */
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: 第一个activity");
    }

    /**
     * 当一个activity运行到onResume方法后，不管是这个activity要销毁还是要暂停或停止，都会调用该方法
     * onPause()用于提交未保存发生变化了的持久化数据，此方法通常用于确认对持久性数据的未保存更改、
     * 停止动画以及其他可能消耗 CPU 的内容，诸如此类。 它应该非常迅速地执行所需操作，因为它返回后，
     * 下一个 Activity 才能继续执行,所以不能执行耗时操作。而后正常情况下会调用onStop方法。
     * 但是有一种极端情况，就是如果这个时候快速让 当前Activity 返回前台，则会调用onResume方法。
     */
    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: 第一个activity");
    }

    /**
     * 当这个activity完全看不见的时候，会调用onStop方法，因为另一个activity会调用onResume并且覆盖这个activity
     */
    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: 第一个activity");
    }

    /**
     * 当activity销毁前会调用该方法，比如发生如下情况：activity调用了finish()方法来结束这个activity，
     * 或者因为系统为了节省空间而临时销毁这个activity，这两个情况可以通过isFinishing()方法判断
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: 第一个activity");
    }

    /**
     * 把activity从onStop状态唤醒时，会用onRestart方法，该方法优先于再次运行的onStart，
     * 运行完onRestart之后运行onStart
     */
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: 第一个activity");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activitySkipBtn:
                startActivity(new Intent(ActivityActivity.this, SkipActivity.class));
                break;
        }
    }
}
