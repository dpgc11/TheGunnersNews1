package com.example.android.thegunnersnews.activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.thegunnersnews.R;

/**
 * Created by yogesh on 3/5/17.
 */

public class IntroFragment2 extends Fragment {

    TextView headingTextView;
    ImageView aspirantImageView;
    Animation animSlideUp;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_intro, container, false);
        headingTextView = (TextView) view.findViewById(R.id.tv_heading);
        aspirantImageView = (ImageView) view.findViewById(R.id.iv_aspirant);

        Typeface custom_font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/AlexBrush-Regular.ttf");

        headingTextView.setTypeface(custom_font);

        // Load the animation like this
       animSlideUp  = AnimationUtils.loadAnimation(getActivity(),
                R.anim.slide_up_from_bottom);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        aspirantImageView.startAnimation(animSlideUp);
    }
}
