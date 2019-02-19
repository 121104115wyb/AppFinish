package com.btzh.appfinish;

import android.app.ActivityManager;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

import com.btzh.appfinish.util.BtzhActivityManager;

import java.util.Timer;
import java.util.TimerTask;

/**
 * app退出的几种方式总结
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**************************方式2***************************/
    @Override
    public void onBackPressed() {
        //（2）监听backpress，点击两次推出app
        exitBy2Click();
    }

    private boolean isExit;

    private void exitBy2Click() {
        Timer tExit = new Timer();
        if (!isExit) {
            isExit = true; // 准备退出
            Toast.makeText(this, "再次返回退出应用", Toast.LENGTH_SHORT).show();
            tExit.schedule(new TimerTask() {
                public void run() {
                    isExit = false; // 取消退出
                }
            }, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务
        } else {
            finish();
        }
    }

    /**************************方式2***************************/

    /**************************方式3***************************/
    private boolean isExit2;

    /**
     * 双击返回键退出
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (isExit2) {
                this.finish();
            } else {
                Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
                isExit2 = true;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isExit2 = false;
                    }
                }, 2000);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
/**************************方式3***************************/

    /**************************方式1***************************/
    @Override
    protected void onDestroy() {
        super.onDestroy();

        //1.主界面退出循环遍历所有activity然后退出
        BtzhActivityManager.getInstance().finishAllActivity();
    }

    /*****************************************************/


    /***************************方式4**************************/
    //简单粗暴直接杀死，不推荐
    private void finishApp() {
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
//     ActivityManager manager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
//     manager.killBackgroundProcesses(getPackageName());
    }
    /***************************方式4**************************/


    /***************************方式5**************************/
    //使用广播，个人感觉有点小题大做
    /***************************方式5**************************/


}
