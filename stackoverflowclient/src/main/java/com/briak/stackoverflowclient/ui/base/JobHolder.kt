package com.briak.stackoverflowclient.ui.base

import kotlinx.coroutines.experimental.Job

interface JobHolder {
    val job: Job
}