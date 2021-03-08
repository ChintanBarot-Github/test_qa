/*
Scenario 3 - user login succeed (credentials provided below)
Given - the user provided right user name and password
When - login button is clicked
Then - user is taken to the news screen
*/
package com.test.news.features.login.presentation

import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.test.news.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class Scenario3 {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityScenarioRule(LoginActivity::class.java)

    @Test
    fun scenario3() {
        val appCompatEditText = onView(
            allOf(
                withId(R.id.editTextUserName),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.inputLayoutUserName),
                        0
                    ),
                    0
                )
            )
        )
        appCompatEditText.perform(scrollTo(), replaceText("user"), closeSoftKeyboard())

        val appCompatEditText2 = onView(
            allOf(
                withId(R.id.editTextPassword),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.inputLayoutPassword),
                        0
                    ),
                    0
                )
            )
        )
        appCompatEditText2.perform(scrollTo(), replaceText("password"), closeSoftKeyboard())

        val appCompatButton = onView(
            allOf(
                withId(R.id.buttonLogin), withText("Login"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.ScrollView")),
                        0
                    ),
                    3
                )
            )
        )
        appCompatButton.perform(scrollTo(), click())

        val progressBar = onView(
            allOf(
                withId(R.id.progressBar),
                isDisplayed()
            )
        )

        val recyclerView = onView(
            allOf(
                withId(R.id.recyclerViewNews),
                isDisplayed()
            )
        )

        progressBar.check(matches(isDisplayed()))

        Thread.sleep(4000)

        recyclerView.check(matches(isDisplayed()))
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
