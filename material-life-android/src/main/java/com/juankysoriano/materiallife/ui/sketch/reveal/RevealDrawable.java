package com.juankysoriano.materiallife.ui.sketch.reveal;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.view.animation.AccelerateInterpolator;

import com.juankysoriano.materiallife.R;
import com.juankysoriano.materiallife.ui.sketch.menu.NormalisedCenter;

public class RevealDrawable extends Drawable {

    private static final String RADIUS = "radius";
    private static final int REVEAL_DURATION = 300;
    private static final int HIDE_DURATION = 500;
    private static final AccelerateInterpolator ACCELERATE_INTERPOLATOR = new AccelerateInterpolator();
    private final Paint clearPaint;
    private int targetRadius;
    private int fromRadius;
    private int radius;
    private NormalisedCenter normalisedCenter;

    public static RevealDrawable newInstance(Context context) {
        Paint clearPaint = generatePaint(context);
        return new RevealDrawable(clearPaint);
    }

    private static Paint generatePaint(Context context) {
        Paint clearPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        int color = context.getResources().getColor(R.color.primary);
        clearPaint.setColor(color);
        clearPaint.setStyle(Paint.Style.FILL);
        return clearPaint;
    }

    protected RevealDrawable(Paint paint) {
        this.clearPaint = paint;
    }

    @Override
    public void draw(Canvas canvas) {
        float centerX = normalisedCenter.getX() * canvas.getWidth();
        float centerY = normalisedCenter.getY() * canvas.getHeight();
        canvas.drawCircle(centerX, centerY, radius, clearPaint);
    }

    @Override
    public void setAlpha(int alpha) {
        clearPaint.setAlpha(alpha);
        invalidateSelf();
    }

    @Override
    public void setColorFilter(ColorFilter cf) {
        clearPaint.setColorFilter(cf);
    }

    public void setTargetRadius(int radius) {
        targetRadius = radius;
    }

    public void setFromRadius(int radius) {
        fromRadius = radius;
        setRadius(fromRadius);
    }

    public void setCenterFrom(NormalisedCenter normalisedCenter) {
        this.normalisedCenter = normalisedCenter;
    }

    @Override
    public int getOpacity() {
        return clearPaint.getAlpha();
    }

    protected int getRadius() {
        return radius;
    }

    protected void setRadius(int radius) {
        this.radius = radius;
        invalidateSelf();
    }

    public void startReveal(Animator.AnimatorListener animatorListener) {
        Animator animator = generateRevealAnimator();
        animator.addListener(animatorListener);
        animator.start();
    }

    private Animator generateRevealAnimator() {
        Animator animator = ObjectAnimator.ofInt(this, RADIUS, fromRadius, targetRadius);
        animator.setDuration(REVEAL_DURATION);
        animator.setInterpolator(ACCELERATE_INTERPOLATOR);
        return animator;
    }

    public void startHide(Animator.AnimatorListener animatorListener) {
        Animator animator = generateHideAnimator();
        animator.addListener(animatorListener);
        animator.start();
    }

    private Animator generateHideAnimator() {
        Animator animator = ObjectAnimator.ofInt(this, RADIUS, targetRadius, fromRadius);
        animator.setDuration(HIDE_DURATION);
        animator.setInterpolator(ACCELERATE_INTERPOLATOR);
        return animator;
    }

    public boolean isRunning() {
        return radius != fromRadius && radius != targetRadius;
    }
}
