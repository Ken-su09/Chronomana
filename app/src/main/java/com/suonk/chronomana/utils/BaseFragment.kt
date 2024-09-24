package com.suonk.chronomana.utils

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

abstract class BaseFragment(contentLayoutId: Int) : Fragment(contentLayoutId) {

    fun <T> safeCollectFlow(flow: Flow<T>, result: (T) -> Unit) {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                flow.collect {
                    result.invoke(it)
                }
            }
        }
    }
}