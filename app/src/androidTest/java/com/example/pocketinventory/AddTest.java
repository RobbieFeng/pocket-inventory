package com.example.pocketinventory;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.widget.DatePicker;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.PickerActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;

import java.util.Calendar;

@RunWith(AndroidJUnit4.class)
    public class AddTest {

    private AutoSignInRule autoSignInRule = new AutoSignInRule();

    private ActivityScenarioRule<HomePageActivity> activityRule =
            new ActivityScenarioRule<>(HomePageActivity.class);

    @Rule
    public TestRule chain = RuleChain.outerRule(autoSignInRule).around(activityRule);

    @After
    @Before
    public void deleteItems() {
        ItemDB.getInstance().deleteAllItems();
    }
    public static void setDate(int datePickerLaunchViewId, int year, int monthOfYear, int dayOfMonth) {
        onView(withParent(withId(R.id.date_of_purchase_text))).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(year, monthOfYear, dayOfMonth));
        onView(withText("OK")).perform(click());
    }
        @Test
        public void testAddItemAndCheckModel() {
            // Click the "Add Item" button
            Espresso.onView(ViewMatchers.withId(R.id.add_item)).perform(ViewActions.click());

            // Fill in item details
            Espresso.onView(ViewMatchers.withId(R.id.make_edit_text)).perform(ViewActions.typeText("Apple"));
            Espresso.onView(ViewMatchers.withId(R.id.model_edit_text)).perform(ViewActions.typeText("iPhone"));
            Espresso.onView(ViewMatchers.withId(R.id.estimated_value_edit_text)).perform(ViewActions.typeText("1800"));
            Espresso.onView(withId(R.id.serial_number_edit_text)).perform(ViewActions.typeText("1234567890"));

            Espresso.onView(ViewMatchers.withId(R.id.description_edit_text)).perform(ViewActions.typeText("xxxy"));
            AddTest.setDate(R.id.date_of_purchase_edit_text,2023,9,15);
            Espresso.onView(withId(R.id.tag_edit_text)).perform(typeText("expensive, fast, durable"));

            Espresso.onView(ViewMatchers.withId(R.id.add_button)).perform(ViewActions.click());
            
        }
    }


