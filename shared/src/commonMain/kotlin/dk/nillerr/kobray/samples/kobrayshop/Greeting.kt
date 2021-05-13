package dk.nillerr.kobray.samples.kobrayshop

import dk.nillerr.kobray.samples.kobrayshop.Platform


class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}
