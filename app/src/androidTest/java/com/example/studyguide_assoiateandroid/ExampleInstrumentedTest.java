package com.example.studyguide_assoiateandroid;

import android.content.Context;
import android.view.Gravity;
import android.view.View;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.studyguide_assoiateandroid.UserInterface.DrawerLayoutEx;
import com.example.studyguide_assoiateandroid.UserInterface.MaterialComponentSHRINE.ShrineMain;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.swipeLeft;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.Visibility.VISIBLE;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class ExampleInstrumentedTest {
//*****************************************************************************************************
    // info - https://developer.android.com/training/testing/ui-testing/espresso-testing
    private String stringToBetyped;

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule = new ActivityScenarioRule<>(MainActivity.class);

    @Before
    public void initValidString() {
        // Specify a valid string.
        stringToBetyped = "Espresso";
    }

   @Test
    public void validPasswordTest(){
        // this allows you to find a view using multiple parameters
       //onView(allOf(withId(R.id.button_signin), withText("Sign-in")));
       //                                          not(withText("Sign-out"))));

       onView(withText("Material Components - Shrine")).perform(click());

       // Type text and then press the button.
       onView(withId(R.id.password_edit_text))
               .perform(typeText(stringToBetyped), closeSoftKeyboard());
       onView(withId(R.id.next_button)).perform(click());

       onView(withId(R.id.product_recycler_view)).check(matches(isDisplayed()));

       // use to find elements in a spinner or adapter
       //onData(allOf(is(instanceOf(Map.class)), hasEntry(equalTo(LongListActivity.ROW_TEXT), is("test input"))));
   }

    @Test
    public void drawerTest(){
        onView(withText("Navigation Drawer")).perform(click());

        // Open Drawer to click on navigation.
        onView(withId(R.layout.activity_drawer_layout_ex)).perform(ViewActions.swipeRight());
        onView(withId(R.id.nav_view)).check(matches(isDisplayed()));
        onView(withId(R.id.nav_gallery)).perform(click());
        onView(withId(R.id.text_gallery)).check(matches(isDisplayed()));

    }
   //************************ espresso intnents ******************************
    // espresso intents info - https://developer.android.com/training/testing/espresso/intents.html?authuser=1

    @Before
    public void setUp() {
        Intents.init();
    }

    @After
    public void tearDown() {
        Intents.release();
    }

    @Test
    public void verifyMessageSentToMessageActivity() {

        // Types a message into a EditText element.
//        onView(withId(R.id.edit_message))
//                .perform(typeText(MESSAGE), closeSoftKeyboard());

        // Clicks a button to send the message to another
        // activity through an explicit intent.
//        onView(withId(R.id.send_message)).perform(click());

        // Verifies that the DisplayMessageActivity received an intent
        // with the correct package name and message.
//        intended(allOf(
//                hasComponent(hasShortClassName(".DisplayMessageActivity")),
//                toPackage(PACKAGE_NAME),
//                hasExtra(MainActivity.EXTRA_MESSAGE, MESSAGE)));

    }

//    *********************************** webview ***************************************
    // info - https://developer.android.com/training/testing/espresso/web.html


//@Test
//public void typeTextInInput_clickButton_SubmitsForm() {
//    // Lazily launch the Activity with a custom start Intent per test
//    try (ActivityScenario.launch(withWebFormIntent())) {
//
//        // Enable JavaScript.
//        onWebView().forceJavascriptEnabled();
//
//        // Selects the WebView in your layout.
//        // If you have multiple WebViews you can also use a
//        // matcher to select a given WebView, onWebView(withId(R.id.web_view)).
//        onWebView()
//                // Find the input element by ID
//                .withElement(findElement(Locator.ID, "text_input"))
//                // Clear previous input
//                .perform(clearElement())
//                // Enter text into the input element
//                .perform(DriverAtoms.webKeys(MACCHIATO))
//                // Find the submit button
//                .withElement(findElement(Locator.ID, "submitBtn"))
//                // Simulate a click via JavaScript
//                .perform(webClick())
//                // Find the response element by ID
//                .withElement(findElement(Locator.ID, "response"))
//                // Verify that the response page contains the entered text
//                .check(webMatches(getText(), containsString(MACCHIATO)));
//    }
}