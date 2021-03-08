/*
Scenario 1 - user opens the android app first time (when not logged in yet)
Given - the user opens app for the first time (when not logged in yet)
Then - login screen with user name and password entries and login button is displayed
*/
package com.test.news.features.login.presentation

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.test.news.R
import org.hamcrest.Matchers.allOf
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class Scenario1 {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityScenarioRule(LoginActivity::class.java)

    @Test
    fun scenario1() {
        val linearLayout = onView(
            allOf(
                withId(R.id.inputLayoutUserName),
                withParent(withParent(IsInstanceOf.instanceOf(android.widget.ScrollView::class.java))),
                isDisplayed()
            )
        )
        linearLayout.check(matches(isDisplayed()))

        val linearLayout2 = onView(
            allOf(
                withId(R.id.inputLayoutPassword),
                withParent(withParent(IsInstanceOf.instanceOf(android.widget.ScrollView::class.java))),
                isDisplayed()
            )
        )
        linearLayout2.check(matches(isDisplayed()))

        val button = onView(
            allOf(
                withId(R.id.buttonLogin), withText("LOGIN"),
                withParent(withParent(IsInstanceOf.instanceOf(android.widget.ScrollView::class.java))),
                isDisplayed()
            )
        )
        button.check(matches(isDisplayed()))
    }
}
