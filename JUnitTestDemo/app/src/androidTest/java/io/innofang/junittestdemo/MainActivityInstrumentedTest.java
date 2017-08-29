package io.innofang.junittestdemo;

/**
 * Author: Inno Fang
 * Time: 2017/8/29 17:39
 * Description:
 */

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class MainActivityInstrumentedTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Test
    public void onClick() {
        onView(withText("1")).perform(click());
        onView(withText("+")).perform(click());
        onView(withText("2")).perform(click());
        onView(withText("=")).perform(click());
        String result = String.valueOf(1 + 2);
        onView(withId(R.id.result_text_view)).check(matches(withText(result)));

        onView(withId(R.id.result_text_view)).perform(click());
        onView(withText("4")).perform(click());
        onView(withText("*")).perform(click());
        onView(withText("2")).perform(click());
        onView(withText("=")).perform(click());
        result = String.valueOf(4 * 2);
        onView(withId(R.id.result_text_view)).check(matches(withText(result)));

        onView(withId(R.id.result_text_view)).perform(click());
        onView(withText("8")).perform(click());
        onView(withText("-")).perform(click());
        onView(withText("2")).perform(click());
        onView(withText("=")).perform(click());
        result = String.valueOf(8 - 2);
        onView(withId(R.id.result_text_view)).check(matches(withText(result)));

        onView(withId(R.id.result_text_view)).perform(click());


    }


}
