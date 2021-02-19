package com.example.studyguide_assoiateandroid.UserInterface;

public class CustomViewAccessibility {
    //**************************************************************************************
    // info from - https://developer.android.com/guide/topics/ui/accessibility/custom-views

    /*

    @Override
public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent event) {
    // Call the super implementation to populate its text to the event, which
    // calls onPopulateAccessibilityEvent() on API Level 14 and up.
    boolean completed = super.dispatchPopulateAccessibilityEvent(event);

    // In case this is running on a API revision earlier that 14, check
    // the text content of the event and add an appropriate text
    // description for this custom view:
    CharSequence text = getText();
    if (!TextUtils.isEmpty(text)) {
        event.getText().add(text);
        return true;
    }
    return completed;
}

****************************************************

ViewCompat.setAccessibilityDelegate(new AccessibilityDelegateCompat() {
    @Override
    public void onPopulateAccessibilityEvent(View host, AccessibilityEvent event) {
        super.onPopulateAccessibilityEvent(host, event);
        // We call the super implementation to populate its text for the
        // event. Then we add our text not present in a super class.
        // Very often you only need to add the text for the custom view.
        CharSequence text = getText();
        if (!TextUtils.isEmpty(text)) {
            event.getText().add(text);
        }
    }
    @Override
    public void onInitializeAccessibilityEvent(View host, AccessibilityEvent event) {
        super.onInitializeAccessibilityEvent(host, event);
        // We call the super implementation to let super classes
        // set appropriate event properties. Then we add the new property
        // (checked) which is not supported by a super class.
        event.setChecked(isChecked());
    }
    @Override
    public void onInitializeAccessibilityNodeInfo(View host,
            AccessibilityNodeInfoCompat info) {
        super.onInitializeAccessibilityNodeInfo(host, info);
        // We call the super implementation to let super classes set
        // appropriate info properties. Then we add our properties
        // (checkable and checked) which are not supported by a super class.
        info.setCheckable(true);
        info.setChecked(isChecked());
        // Quite often you only need to add the text for the custom view.
        CharSequence text = getText();
        if (!TextUtils.isEmpty(text)) {
            info.setText(text);
        }
    }
}

************************************************
// Assumes that the widget is designed to select text when tapped and select
// all text when long-tapped. In its strings.xml file, this app has set
// "select" to "Select" and "select_all" to "Select all", respectively.
ViewCompat.replaceAccessibilityAction(WIDGET, ACTION_CLICK,
        context.getString(R.string.select),
        (view, commandArguments) -> {
            selectText();
        });

ViewCompat.replaceAccessibilityAction(WIDGET, ACTION_LONG_CLICK,
        context.getString(R.string.select_all),
        (view, commandArguments) -> {
            selectAllText();
        });

   ********************************************************

   class CustomTouchView extends View {

    public CustomTouchView(Context context) {
        super(context);
    }

    boolean downTouch = false;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);

        // Listening for the down and up touch events
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downTouch = true;
                return true;

            case MotionEvent.ACTION_UP:
                if (downTouch) {
                    downTouch = false;
                    performClick(); // Call this method to handle the response, and
                                    // thereby enable accessibility services to
                                    // perform this action for a user who cannot
                                    // click the touchscreen.
                    return true;
                }
        }
        return false; // Return false for other touch events
    }

    @Override
    public boolean performClick() {
        // Calls the super implementation, which generates an AccessibilityEvent
        // and calls the onClick() listener on the view, if any
        super.performClick();

        // Handle the action for the custom click here

        return true;
    }
}
     */
}
