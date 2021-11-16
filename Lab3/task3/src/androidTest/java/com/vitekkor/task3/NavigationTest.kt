package com.vitekkor.task3

import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso
import androidx.test.core.app.launchActivity
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class NavigationTest {
    @Test
    fun mainTest() {
        var scenario = launchActivity<MainActivity>()

        R.id.fragment1.checkIsDisplayed()

        R.id.bnToSecond.click()
        R.id.fragment2.checkIsDisplayed()

        openAbout()
        R.id.activity_about.checkIsDisplayed()
        Espresso.pressBack()
        R.id.fragment2.checkIsDisplayed()

        R.id.bnToThird.click()
        R.id.fragment3.checkIsDisplayed()

        openAbout()
        R.id.activity_about.checkIsDisplayed()
        Espresso.pressBack()
        R.id.fragment3.checkIsDisplayed()

        R.id.bnToSecond.click()
        R.id.fragment2.checkIsDisplayed()

        R.id.bnToFirst.click()
        R.id.fragment1.checkIsDisplayed()

        Espresso.pressBackUnconditionally()
        assertEquals(Lifecycle.State.DESTROYED, scenario.state)

        scenario = launchActivity()

        repeat(5) {
            R.id.bnToSecond.click()
            R.id.fragment2.checkIsDisplayed()

            R.id.bnToThird.click()
            R.id.fragment3.checkIsDisplayed()

            R.id.bnToFirst.click()
            R.id.fragment1.checkIsDisplayed()
        }

        Espresso.pressBackUnconditionally()
        assertEquals(Lifecycle.State.DESTROYED, scenario.state)
    }

    @Test
    fun testWithRotation() {
        val scenario = launchActivity<MainActivity>()

        R.id.fragment1.checkIsDisplayed()

        R.id.bnToSecond.click()
        R.id.fragment2.checkIsDisplayed()
        scenario.rotate()
        R.id.fragment2.checkIsDisplayed()

        openAbout()
        R.id.activity_about.checkIsDisplayed()
        scenario.rotate()
        R.id.activity_about.checkIsDisplayed()
        Espresso.pressBack()
        R.id.fragment2.checkIsDisplayed()

        R.id.bnToThird.click()
        scenario.rotate()
        R.id.fragment3.checkIsDisplayed()

        openAbout()
        R.id.activity_about.checkIsDisplayed()
        Espresso.pressBack()
        R.id.fragment3.checkIsDisplayed()

        R.id.bnToSecond.click()
        scenario.rotate()
        R.id.fragment2.checkIsDisplayed()

        R.id.bnToFirst.click()
        scenario.rotate()
        R.id.fragment1.checkIsDisplayed()

        Espresso.pressBackUnconditionally()
        assertEquals(Lifecycle.State.DESTROYED, scenario.state)
    }

    @Test
    fun testBackStack() {
        var scenario = launchActivity<MainActivity>()
        R.id.bnToSecond.click()
        scenario.checkBackstackSize(2)
        scenario = launchActivity()

        R.id.bnToSecond.click()
        R.id.bnToThird.click()
        scenario.checkBackstackSize(3)
        scenario = launchActivity()

        R.id.bnToSecond.click()
        R.id.bnToThird.click()
        R.id.bnToFirst.click()
        scenario.checkBackstackSize(1)
        scenario = launchActivity()

        R.id.bnToSecond.click()
        R.id.bnToThird.click()
        openAbout()
        scenario.checkBackstackSize(4)

        scenario = launchActivity()

        repeat(5) {
            R.id.bnToSecond.click()
            R.id.bnToThird.click()
            R.id.bnToFirst.click()
        }
        scenario.checkBackstackSize(1)
    }

    @Test
    fun testBackstackWithRotation() {
        var scenario = launchActivity<MainActivity>()

        scenario.rotate()
        R.id.bnToSecond.click()
        scenario.checkBackstackSize(2)

        scenario = launchActivity()

        scenario.rotate()
        R.id.bnToSecond.click()
        R.id.bnToThird.click()
        scenario.checkBackstackSize(3)

        scenario = launchActivity()

        openAbout()
        scenario.rotate()
        scenario.checkBackstackSize(2)

        scenario = launchActivity()

        R.id.bnToSecond.click()
        scenario.rotate()
        R.id.bnToThird.click()
        scenario.rotate()
        openAbout()
        scenario.rotate()
        scenario.checkBackstackSize(4)

        scenario = launchActivity()
        R.id.bnToSecond.click()
        scenario.rotate()
        R.id.bnToThird.click()
        scenario.rotate()
        openAbout()
        scenario.rotate()
        Espresso.pressBack()
        scenario.checkBackstackSize(3)
    }

    @Test
    fun testNavUp() {
        launchActivity<MainActivity>()
        R.id.bnToSecond.click()
        clickUpNav()
        R.id.fragment1.checkIsDisplayed()

        R.id.bnToSecond.click()
        R.id.bnToThird.click()
        clickUpNav()
        R.id.fragment2.checkIsDisplayed()

        clickUpNav()
        R.id.fragment1.checkIsDisplayed()

        openAbout()
        clickUpNav()
        R.id.fragment1.checkIsDisplayed()

        R.id.bnToSecond.click()
        openAbout()
        clickUpNav()
        R.id.fragment2.checkIsDisplayed()

        R.id.bnToThird.click()
        openAbout()
        clickUpNav()
        R.id.fragment3.checkIsDisplayed()
    }

    @Test
    fun testBackstackNavUp() {
        var scenario = launchActivity<MainActivity>()
        R.id.bnToSecond.click()
        clickUpNav()
        scenario.checkBackstackSize(1)

        scenario = launchActivity()

        R.id.bnToSecond.click()
        R.id.bnToThird.click()
        clickUpNav()
        scenario.checkBackstackSize(2)
    }
}