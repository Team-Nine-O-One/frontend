package com.imeanttobe.app901.ui.dev

import androidx.lifecycle.ViewModel
import com.imeanttobe.app901.api.repo.MemoRepoImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DevSectionViewModel
    @Inject
    constructor(
        private val memoRepo: MemoRepoImpl,
    ) : ViewModel()
