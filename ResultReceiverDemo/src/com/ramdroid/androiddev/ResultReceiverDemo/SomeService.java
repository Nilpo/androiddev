package com.ramdroid.androiddev.ResultReceiverDemo;

/**
 *    Copyright 2013 by Ronald Ammann (ramdroid)

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 */

import android.app.IntentService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.ResultReceiver;

public class SomeService extends IntentService {

    public static final String ACTION_SEND_SOMETHING = "com.ramdroid.androiddev.ACTION_SEND_SOMETHING";

    public static final String RECEIVER_EXTRA = "SomeServiceReceiverExtra";

    public static final int DATA = 42;
    public static final int FINISHED = 43;

    private ResultReceiver resultReceiver;

    public SomeService() {
        super("SomeService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        resultReceiver = intent.getParcelableExtra(RECEIVER_EXTRA);

        Bundle resultStarted = new Bundle();
        resultStarted.putString("result", "The service started.");
        resultReceiver.send(DATA, resultStarted);

        // register intent receiver
        IntentFilter filter = new IntentFilter();
        filter.addAction(ACTION_SEND_SOMETHING);
        SomeReceiver receiver = new SomeReceiver();
        registerReceiver(receiver, filter);

        // we are already in a separate thread here, so we can do some long operation
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
        }

        // send a response
        Bundle resultFinished = new Bundle();
        resultFinished.putString("result", "The service finished.");
        resultReceiver.send(FINISHED, resultFinished);

        // we're done
        unregisterReceiver(receiver);
    }

    private class SomeReceiver extends BroadcastReceiver {

        int counter = 0;

        @Override
        public void onReceive(final Context context, Intent intent) {
            counter += 1;
            Bundle resultData = new Bundle();
            resultData.putString("result", "Here's some number: " + counter);
            resultReceiver.send(DATA, resultData);
        }
    }
}
