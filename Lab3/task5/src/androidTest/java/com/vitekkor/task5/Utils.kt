package com.vitekkor.task5

import android.content.Context
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import org.junit.Assert
import java.lang.Thread.sleep

fun openAbout() {
    openContextualActionModeOverflowMenu()
    onView(withText(R.string.title_about))
        .perform(click())
}

fun Int.checkIsDisplayed() {
    onView(withId(this)).check(matches(isDisplayed()))
}

fun Int.click() {
    onView(withId(this)).perform(ViewActions.click())
}

fun clickUpNav() {
    onView(withContentDescription(R.string.nav_app_bar_navigate_up_description)).perform(click())
}

fun <T : AppCompatActivity> ActivityScenario<T>.rotate() {
    this.onActivity { activity ->
        val context = ApplicationProvider.getApplicationContext<Context>()
        val portraitOrientation =
            context.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT
        activity.requestedOrientation =
            if (portraitOrientation)
                ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            else
                ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }
    sleep(1000)
}

fun <T : AppCompatActivity> ActivityScenario<T>.checkBackstackSize(size: Int) {
    repeat(size) {
        pressBackUnconditionally()
    }
    Assert.assertEquals(Lifecycle.State.DESTROYED, this.state)
}