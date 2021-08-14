package com.gabriel.aranias.mareu.model;

import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;

import com.gabriel.aranias.mareu.R;

public enum Room {
    BOWSER(R.string.bowser, R.drawable.bowser, R.color.bowser),
    HARMONIE(R.string.harmonie, R.drawable.harmonie, R.color.harmonie),
    LARRY(R.string.larry, R.drawable.larry, R.color.larry),
    LUDWIG(R.string.ludwig, R.drawable.ludwig, R.color.ludwig),
    LUIGI(R.string.luigi, R.drawable.luigi, R.color.luigi),
    MARIO(R.string.mario, R.drawable.mario, R.color.mario),
    PEACH(R.string.peach, R.drawable.peach, R.color.peach),
    ROI_BOO(R.string.roi_boo, R.drawable.roi_boo, R.color.roi_boo),
    WALUIGI(R.string.waluigi, R.drawable.waluigi, R.color.waluigi),
    YOSHI(R.string.yoshi, R.drawable.yoshi, R.color.yoshi);

    @StringRes
            private final int name;
    @DrawableRes
            private final int icon;
    @ColorRes
            private final int color;


    Room(@StringRes int name, @DrawableRes int icon,@ColorRes int color) {
        this.name = name;
        this.icon = icon;
        this.color = color;
    }

    @StringRes
    public int getName() {
        return name;
    }
    @DrawableRes
    public int getIcon() {
        return icon;
    }
    @ColorRes
    public int getColor() {
        return color;
    }
}
