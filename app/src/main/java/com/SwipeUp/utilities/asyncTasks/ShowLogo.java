package com.SwipeUp.utilities.asyncTasks;

import android.arch.lifecycle.ViewModelStoreOwner;
import android.os.AsyncTask;
import android.util.Log;

import com.SwipeUp.shuffleManagement.ShuffleFragment;

public class ShowLogo extends AsyncTask<ViewModelStoreOwner, Void, ViewModelStoreOwner> {
    /**
     * @param viewModelStoreOwners just the shuffleActivity
     * @return the shuffleActivity if the sleep was not interrupted, null otherwise
     */
    @Override
    protected ViewModelStoreOwner doInBackground(ViewModelStoreOwner... viewModelStoreOwners) {

        try {
            Log.i("Logo", "Logo task started");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Log.i("Logo", "Logo task interrupted");
            return null;
        }

        return viewModelStoreOwners[0];
    }

    @Override
    protected void onPostExecute(ViewModelStoreOwner aViewModelStoreOwner) {
        if(aViewModelStoreOwner != null)
            ((ShuffleFragment)aViewModelStoreOwner).showSwipeUpImage();
    }

}
