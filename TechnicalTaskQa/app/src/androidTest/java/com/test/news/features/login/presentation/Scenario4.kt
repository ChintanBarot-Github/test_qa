/*
Scenario 4 - user opens app next time (when previously logged in)
Given - the user opens app next time (when previously logged in)
Then - user is taken straight to the news screen
 */
package com.test.news.features.login.presentation

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.test.news.R
import com.test.news.features.news.presentation.NewsActivity
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class Scenario4 {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityScenarioRule(NewsActivity::class.java)

    @Test
    fun scenario4() {

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
}
