package dk.nillerr.kobray.samples.kobrayshop.androidx

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner

inline fun <reified V : ViewModel> viewModel(owner: ViewModelStoreOwner, noinline factory: () -> V): V {
    return viewModel(V::class.java, owner, factory)
}

fun <V : ViewModel> viewModel(modelClass: Class<V>, owner: ViewModelStoreOwner, factory: () -> V): V {
    val modelName = modelClass.name

    val viewModelProvider = ViewModelProvider(owner, object : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(mc: Class<T>): T {
            if (mc.isAssignableFrom(modelClass)) {
                @Suppress("UNCHECKED_CAST")
                return factory() as T
            }

            throw IllegalArgumentException("This ViewModelFactory can only construct instances of type '${modelClass.canonicalName}', but an instance of type '${mc.canonicalName}' was requested.")
        }
    })

    return viewModelProvider.get(modelName, modelClass)
}
