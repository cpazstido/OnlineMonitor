package com.hy.onlinemonitor.utile;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.hy.onlinemonitor.R;

/**
 * Created by 24363 on 2015/9/2.
 */
public class ShowUtile {

    public static AlertDialog getDialog(Activity activity,String title){
        Log.i("Dialog", "show");
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        final View view = LayoutInflater.from(activity).inflate(
                R.layout.dialog_progress, null,false);

        View img1 = view.findViewById(R.id.pd_circle1);
        View img2 = view.findViewById(R.id.pd_circle2);
        View img3 = view.findViewById(R.id.pd_circle3);
        TextView dialog_title = (TextView) view.findViewById(R.id.dialog_title);

        if(title != null){
            dialog_title.setText(title);
        }

        int ANIMATION_DURATION = 400;

        Animator anim1 = setRepeatableAnim(activity, img1, ANIMATION_DURATION, R.animator.growndisappear);
        Animator anim2 = setRepeatableAnim(activity, img2, ANIMATION_DURATION, R.animator.growndisappear);
        Animator anim3 = setRepeatableAnim(activity, img3, ANIMATION_DURATION, R.animator.growndisappear);

        setListeners(img1, anim1, anim2, ANIMATION_DURATION);
        setListeners(img2, anim2, anim3, ANIMATION_DURATION);
        setListeners(img3, anim3, anim1, ANIMATION_DURATION);

        anim1.start();
        builder.setView(view);
        AlertDialog ad = builder.create();
        ad.setCanceledOnTouchOutside(false);
        ad.getWindow().setLayout(dpToPx(200, activity), dpToPx(125, activity));
        return ad;
    }

    public static int dpToPx(int i, Context mContext) {

        DisplayMetrics displayMetrics = mContext.getResources()
                .getDisplayMetrics();
        return (int) ((i * displayMetrics.density) + 0.5);

    }

    private static Animator setRepeatableAnim(Activity activity, View target, final int duration, int animRes){
        final Animator anim = AnimatorInflater.loadAnimator(activity, animRes);
        anim.setDuration(duration);
        anim.setTarget(target);
        return anim;
    }

    private static void setListeners(final View target, Animator anim, final Animator animator, final int duration){
        anim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animat) {
                if (target.getVisibility() == View.INVISIBLE) {
                    target.setVisibility(View.VISIBLE);
                }
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        animator.start();
                    }
                }, duration - 100);
            }

            @Override
            public void onAnimationEnd(Animator animator) {

            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
    }

    static Toast toast = null;

    public static void snackBarShow(@Nullable View view,String showText){
        Snackbar snackbar = Snackbar.make(view, showText, Snackbar.LENGTH_SHORT)
                .setAction("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });
        snackbar.show();
    }
    public static void noJurisdictionToast(Context mContext){
        String showText = "无权限访问,请联系管理员修改权限.";
        if (null == toast) {
            toast = Toast.makeText(mContext, showText, Toast.LENGTH_SHORT);
        } else {
            toast.cancel();
            toast = Toast.makeText(mContext, showText, Toast.LENGTH_SHORT);
        }
        toast.show();
    }
}
