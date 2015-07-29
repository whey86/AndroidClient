package com.erikle2.main;

import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.LinearLayout;

/**
 * Created by Erik on 2015-07-26.
 */
public class ExpandAnimation extends Animation {

    private View mAnimatedView;
    private LinearLayout.LayoutParams mViewLayoutParams;
    private boolean mIsVisibleAfter;
    private boolean mWasEndedAlready = false;

    private int mMarginStart, mMarginEnd;

    /**
     * Initialize the animation
     *
     * @param view     The layout we want to animate
     * @param duration The duration of the animation, in ms
     */
    public ExpandAnimation(View view, int duration, int state) {
        setDuration(duration);
        mAnimatedView = view;
        mViewLayoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
        swapState(state);
    }

    public void swapState(int state) {

        // decide to show or hide the view


        if (state == 1) mIsVisibleAfter = true;
        else
 mIsVisibleAfter = (mAnimatedView.getVisibility() == View.VISIBLE);
        Log.e("State", "" + state);
        Log.e("Is hidden", "" + mIsVisibleAfter);
        mMarginStart = mViewLayoutParams.bottomMargin;
        mMarginEnd = (mMarginStart == 0 ? (0 - mAnimatedView.getHeight()) : 0);

        if (mIsVisibleAfter) {
            mAnimatedView.setVisibility(View.GONE);
        } else {
            mAnimatedView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);


        if (interpolatedTime < 1.0f) {

            // Calculating the new bottom margin, and setting it
            mViewLayoutParams.bottomMargin = mMarginStart
                    + (int) ((mMarginEnd - mMarginStart) * interpolatedTime);

            // Invalidating the layout, making us seeing the changes we made
            mAnimatedView.requestLayout();
            // Making sure we didn't run the ending before (it happens!)
        } else if (!mWasEndedAlready) {
            mViewLayoutParams.bottomMargin = mMarginEnd;
            mAnimatedView.requestLayout();

            Log.e("Is hidden2", "" + mIsVisibleAfter);
            if (mIsVisibleAfter) {
                mAnimatedView.setVisibility(View.GONE);
            }
            mWasEndedAlready = true;
        }


    }
}