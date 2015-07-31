package com.erikle2.main;

import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.LinearLayout;

/**
 * Class to create animations to more expand and lees expand a list item
 */
public class ExpandAnimation extends Animation {

    /**
     * View to animate
     */
    private View mAnimatedView;
    /**
     * Params to transform view
     */
    private LinearLayout.LayoutParams mViewLayoutParams;
    /**
     * state of expand obj
     */
    private boolean mIsVisibleAfter;
    /**
     * If it was ended
     */
    private boolean mWasEndedAlready = false;
    /**
     * Varible holders
     */
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


    /**
     * Function that executes animation
     * @param interpolatedTime
     * @param t
     */
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

    /**
     * Returns visibility of expand obj
     * @return
     */
    public boolean isExpanded(){
        return !mIsVisibleAfter;
    }
}