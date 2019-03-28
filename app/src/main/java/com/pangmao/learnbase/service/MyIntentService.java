package com.pangmao.learnbase.service;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;

import com.pangmao.learnbase.util.LogUtil;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class MyIntentService extends IntentService {

    private static final String ACTION_FOO = "com.pangmao.learnbase.service.action.FOO";

    private static final String EXTRA_PARAM1 = "com.pangmao.learnbase.service.extra.PARAM1";
    private static final String EXTRA_PARAM2 = "com.pangmao.learnbase.service.extra.PARAM2";

    public MyIntentService() {
        super("MyIntentService");
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    public static void startActionFoo(Context context, String param1, String param2) {
        Intent intent = new Intent(context, MyIntentService.class);
        intent.setAction(ACTION_FOO);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_FOO.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionFoo(param1, param2);
            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionFoo(String param1, String param2) {
        // TODO: Handle action Foo
        LogUtil.log("参数一:" + param1);
        LogUtil.log("参数二:" + param2);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtil.log("IntentService onDestroy");
    }
}
