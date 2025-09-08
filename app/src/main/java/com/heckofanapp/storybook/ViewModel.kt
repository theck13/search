package com.heckofanapp.storybook

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModel() : ViewModel() {
    var atEndSplash: Boolean by mutableStateOf(false)
}

@Suppress("UNCHECKED_CAST")
class ViewModelFactory() : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return com.heckofanapp.storybook.ViewModel() as T
    }
}
