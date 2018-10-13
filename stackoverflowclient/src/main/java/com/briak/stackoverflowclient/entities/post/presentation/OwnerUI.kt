package com.briak.stackoverflowclient.entities.post.presentation

data class OwnerUI(
        var profileImage: String,

        var displayName: String
) {
    constructor(): this ("", "")
}