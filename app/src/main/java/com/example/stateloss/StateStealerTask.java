package com.example.stateloss;

import android.os.AsyncTask;

/**
 * Assists in losing our state
 *
 * @author bherbst
 */
public class StateStealerTask extends AsyncTask<Void, Void, Void> {
    private final Callback callback;

    public StateStealerTask(Callback callback) {
        this.callback = callback;
    }

    @Override
    protected Void doInBackground(Void... voids) {

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        callback.onStateStealerDone();
    }

    interface Callback {
        void onStateStealerDone();
    }
}
