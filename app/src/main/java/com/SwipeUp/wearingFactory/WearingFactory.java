package com.SwipeUp.wearingFactory;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.util.TypedValue;
import com.SwipeUp.swipeUp.MainActivity;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 *     Given some specific characteristics, an instance of this class returns the Drawable of a wearing that
 *     has that characteristics
 */
public class WearingFactory {
    private MainActivity mainActivity;
    private int position;
    private Drawable[] drawables;
    private ImageDownloader imageDownloader;
    private int availableImages;
    // sets this variable to false in order to disable connection requests to server and work with
    // available images in assets folder
    public static boolean DEBUG_CONNECTION = true;

    public WearingFactory(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        position = -1;
        if(DEBUG_CONNECTION) imageDownloader = new ImageDownloader(mainActivity.getResources());

        AssetManager assetManager = mainActivity.getAssets();
        createDrawables(assetManager);

    }

    /**
     * Private method called by the constructor
     */
    private void createDrawables(AssetManager assetManager) {
        try {
            String[] images = assetManager.list("clothes");
            availableImages = images.length;
            drawables = new Drawable[availableImages];
            InputStream inputStream;
            Resources resources = mainActivity.getResources();
            for (int i = 0; i < images.length; i++) {
                inputStream = mainActivity.getAssets().open("clothes/" + images[i]);
                Drawable drawable = Drawable.createFromResourceStream(resources, new TypedValue(), inputStream, null);
                drawables[i] = drawable;
            }
        }
        catch (IOException e) {
            Log.e("Wearing factory", "Error in initialization of the images");
            e.printStackTrace();
        }

        if(DEBUG_CONNECTION) drawables[0] = imageDownloader.downloadRandomImage();
    }

    /**
     * @return the number of available images in the assets folder
     */
    public int getAvailableImages()
    {
        return availableImages;
    }

    /**
     * @return current index in iteration process
     */
    public int getPosition(){
        return position;
    }

    /**
     * @param position the index to which the iteration is set
     */
    public void setPosition(int position){
        this.position = position;
    }

    /**
     * @return the next image to be drawn on the screen
     */
    public Drawable getNextImage()
    {
        position=(position + 1)%availableImages;
        return drawables[position];
    }

    /**
     * @return the previous image to be drawn on the screen
     */
    public Drawable getPreviousImage()
    {
        position=(position -1);
        if(position<0)
            position+=availableImages;
        return drawables[position];
    }

    /**
     * Method not used yet, called by the neural network
     */
    public void getWearing(int color, int wtype)
    {
        System.out.println("I'm giving you a "+color+" wear");
        System.out.println("I'm giving you a "+wtype);
        
    }

}
