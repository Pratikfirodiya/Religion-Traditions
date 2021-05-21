package com.example.myapplication.utils;


import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import ernestoyaquello.com.verticalstepperform.Step;

public class UserSteps extends Step<String> {

    private TextView userNameView;

    public UserSteps(String step1) {
        super(step1);
    }

    @Override
    protected View createStepContentLayout() {
        // Here we generate the view that will be used by the library as the content of the step.
        // In this case we do it programmatically, but we could also do it by inflating an XML layout.
        userNameView = new TextView(getContext());
        userNameView.setSingleLine(true);
        userNameView.setText("svdhbuvsdbadyvay");
        userNameView.setHint("Your Name");

//        userNameView.addTextChangedListener(new TextWatcher() {
//
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                // Whenever the user updates the user name text, we update the state of the step.
//                // The step will be marked as completed only if its data is valid, which will be
//                // checked automatically by the form with a call to isStepDataValid().
//                markAsCompletedOrUncompleted(true);
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
//
        return userNameView;
    }





    @Override
    public String getStepData() {
        // We get the step's data from the value that the user has typed in the EditText view.
        String userName =  userNameView.getText().toString();
       return userName != null ? userName.toString() : "";
    }

    @Override
    public String getStepDataAsHumanReadableString() {
        // Because the step's data is already a human-readable string, we don't need to convert it.
        // However, we return "(Empty)" if the text is empty to avoid not having any text to display.
        // This string will be displayed in the subtitle of the step whenever the step gets closed.
        String userName = getStepData();
        return !userName.isEmpty() ? userName : "(Empty)";
    }

    @Override
    protected void onStepOpened(boolean animated) {
        // This will be called automatically whenever the step gets opened.
    }

    @Override
    protected void onStepClosed(boolean animated) {
        // This will be called automatically whenever the step gets closed.
    }

    @Override
    protected void onStepMarkedAsCompleted(boolean animated) {
        // This will be called automatically whenever the step is marked as completed.
    }

    @Override
    protected void onStepMarkedAsUncompleted(boolean animated) {
        // This will be called automatically whenever the step is marked as uncompleted.
    }

    @Override
    public void restoreStepData(String stepData) {
        // To restore the step after a configuration change, we restore the text of its EditText view.
        userNameView.setText(stepData);
    }

    @Override
    protected IsDataValid isStepDataValid(String stepData) {
        return null;
    }
}