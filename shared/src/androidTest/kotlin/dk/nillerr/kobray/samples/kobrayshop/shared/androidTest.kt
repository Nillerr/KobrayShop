package dk.nillerr.kobray.samples.kobrayshop.shared

import dk.nillerr.kobray.samples.kobrayshop.Greeting
import org.junit.Assert.assertTrue
import org.junit.Test

class AndroidGreetingTest {

    @Test
    fun testExample() {
        assertTrue("Check Android is mentioned", Greeting().greeting().contains("Android"))
    }
}