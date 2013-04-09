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

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.ResultReceiver;

public class SomeService extends Service {

    public static final String REQUEST_RECEIVER_EXTRA = "SomeServiceReceiverExtra";
    public static final int RESULT_ID_QUOTE = 42;

    private ResultReceiver resultReceiver;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        resultReceiver = intent.getParcelableExtra(REQUEST_RECEIVER_EXTRA);

        Bundle resultData = new Bundle();
        resultData.putString("result", "This is some text returned by the service.");
        resultReceiver.send(RESULT_ID_QUOTE, resultData);

        stopSelf();
        return START_NOT_STICKY;
    }
}
