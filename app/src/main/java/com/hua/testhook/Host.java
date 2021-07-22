package com.hua.testhook;

import android.util.Log;

public class Host {
    private static final String TAG = "Host";
    public Host() {
    }

    public void fuck(){
        Log.d(TAG, "fuck: ");
        test();
    }

    public void fuck(int i){
        Log.d(TAG, "fuck() called with: i = [" + i + "]");
    }

    private void test(){

    }
}
