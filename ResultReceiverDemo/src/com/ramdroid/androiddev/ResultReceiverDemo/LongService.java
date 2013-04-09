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
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;

public class LongService extends IntentService {

    public static final String REQUEST_RECEIVER_EXTRA = "LongServiceReceiverExtra";
    public static final int RESULT_ID_QUOTE = 42;

    private ResultReceiver resultReceiver;

    public LongService() {
        super("LongService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        resultReceiver = intent.getParcelableExtra(REQUEST_RECEIVER_EXTRA);

        // we are already in a separate thread here, so we can do some long operation
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }

        Bundle resultData = new Bundle();
        resultData.putString("result", "Here is another text and it's unbelievable!");
        resultReceiver.send(RESULT_ID_QUOTE, resultData);

        stopSelf();
    }
}
