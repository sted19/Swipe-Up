package com.SwipeUp.utilities.wearingFactory;


import android.util.Log;

/**
 *     Given some specific characteristics, an instance of this class returns the Drawable of a wearing that
 *     has that characteristics
 */
public class WearingFactory {

    private MiniWearingFactory previous;
    private MiniWearingFactory current;
    private MiniWearingFactory next;

    private int next_pos = -1;
    private int previous_pos = -1;
    private int current_pos = -1;   //può non essere corretta: quando arrivo all ultimo fragment non viene contato perchè
                                    //non vengono creati nuovi fragment, quindi non viene aggionrnato

    private boolean second = false;
    private static WearingFactory instance;

    public static WearingFactory getInstance(){
        if(instance == null){
            instance = new WearingFactory();
        }
        return instance;
    }

    private WearingFactory() {}

    public void addMiniWearingFactory(MiniWearingFactory miniWearingFactory, int position){
        if(current_pos == -1){
            current_pos = 0;
            current = miniWearingFactory;
            second = true;
        }
        else if(second){
            next= miniWearingFactory;
            next_pos=position;
            second=false;
        }
        else if(position > current_pos){
            previous = current;
            current = next;
            next = miniWearingFactory;

            previous_pos=current_pos;
            current_pos=next_pos;
            next_pos=position;

        }
        else if(position < current_pos){
            next = current;
            current = previous;
            previous = miniWearingFactory;

            next_pos=current_pos;
            current_pos=previous_pos;
            previous_pos=position;
        }
    }

    /**
     * Sets to null all MiniWearing factories and current position to -1
     */
    public void resetWearingFactory(){
        previous = null;
        current = null;
        next = null;
        current_pos = -1;
    }

    public MiniWearingFactory getMiniWearingFactory(int position) {
        Log.e("position",position+"");
        Log.e("current",current_pos+"");
        if(previous_pos == position){
            Log.e("previous","prev");
            return previous;
        }
        else if(current_pos == position){
            Log.e("current","current");
            return current;
        }
        else
            return next;
    }
}