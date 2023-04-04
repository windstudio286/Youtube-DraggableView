package com.sucho.draggableplayer;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.google.android.exoplayer2.ui.PlayerView;
public class CustomExoPlayerView extends PlayerView{
    public CustomExoPlayerView(Context context) {
        super(context);
    }

    public CustomExoPlayerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomExoPlayerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                showController();
            }
        }
        return false;
    }
}
