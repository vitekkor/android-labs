package com.example.myapplication

import android.content.Context
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.view.Gravity
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.DrawerActions
import androidx.test.espresso.contrib.DrawerMatchers.isClosed
import androidx.test.espresso.contrib.NavigationViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.Matchers.not
import org.junit.Assert
import java.lang.Thread.sleep
import kotlin.random.Random

private fun openAboutViaBottomNav() {
    onView(withId(R.id.nav_view)).perform(click())
}

private fun openAboutViaDrawer() {
    // Open Drawer to click on navigation.
    onView(withId(R.id.drawer_layout))
        .check(matches(isClosed(Gravity.LEFT))) // Left Drawer should be closed.
        .perform(DrawerActions.open()); // Open Drawer

    // Start the screen of your activity.
    onView(withId(R.id.drawer_nav_view))
        .perform(NavigationViewActions.navigateTo(R.id.aboutActivity))
}

private fun openAboutViaOptions() {
    openContextualActionModeOverflowMenu()
    onView(withText(R.string.title_about))
        .perform(click())
}

fun openAbout() = when (Random.nextInt(3)) {
    0 -> openAboutViaOptions()
    1 -> openAboutViaDrawer()
    else -> openAboutViaBottomNav()
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