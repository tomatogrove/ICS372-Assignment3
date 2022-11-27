package edu.metrostate.cardealer.activity.util;

import android.text.Editable;
import android.text.TextWatcher;

// class taken from https://stackoverflow.com/questions/11134144/android-edittext-onchange-listener
public abstract class TextChangedListener implements TextWatcher {

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {}

    @Override
    public void afterTextChanged(Editable s) {}

}
