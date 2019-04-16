package com.pangmao.learnbase.handler;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.pangmao.learnbase.util.LogUtil;

/**
 * @author wangmingzhi
 * AsyncTask<Params, Progress, Result>
 * Params: 在执行 AsyncTask时需要传入的参数，可用于在后台任务中使用。(参数为Void, 表示不需要传入参数给后台)
 * Progress: 后台任务执行时，传给前台数据。
 * Result: 当任务执行完毕后，如果需要对结果进行返回，则使用这里指定的泛型作为返回值类型。
 */
public class AsyncTaskDemo extends AsyncTask<AppCompatActivity, Integer, Boolean> {

    private Context context = null;

    /**
     * 会在后台任务开始执行之前调用，用于进行一些界面上的初始化操作.
     * 比如显示一个进度对话框
     */
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    /**
     * 所有代码都会在子线程中运行，我们应该在这里去处理所有的耗时任务.
     * 如果需要更新 UI元素,可以调用 publishProgress(Progress...)方法来完成
     * @param params           Params
     * @return                              Result
     */
    @Override
    protected Boolean doInBackground(AppCompatActivity... params) {
        LogUtil.log("runing");
        context = params[0];
        for (int i = 0; i < 10; i++){
            publishProgress(i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    /**
     * 当在后台任务中调用了 publishProgress(Progress...)方法后，这个方法就会很快被调用
     * @param values                            Progress
     */
    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        LogUtil.log("values:" + values);
    }

    /**
     * 当后台任务执行完毕并通过 return语句进行返回时(doInBackground()返回时)，这个方法就很快会被调用。返
     * 回的数据会作为参数传递到此方法中，可以利用返回的数据来进行一些 UI 操作.
     * @param aBoolean                          Result
     */
    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
        if (aBoolean){
            Toast.makeText(context, "AsyncTask End", Toast.LENGTH_SHORT).show();
        }
    }

}
