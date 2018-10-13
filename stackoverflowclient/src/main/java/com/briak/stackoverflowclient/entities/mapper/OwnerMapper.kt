package com.briak.stackoverflowclient.entities.mapper

import com.briak.stackoverflowclient.entities.post.presentation.OwnerUI
import com.briak.stackoverflowclient.entities.post.server.Owner
import javax.inject.Inject

open class OwnerMapper @Inject constructor() : Mapper<Owner, OwnerUI>() {
    override fun map(value: Owner): OwnerUI {
        val ownerUI = OwnerUI()
        ownerUI.profileImage = value.profileImage
        ownerUI.displayName = value.displayName

        return ownerUI
    }

    override fun reverseMap(value: OwnerUI): Owner {
        val owner = Owner()
        owner.profileImage = value.profileImage
        owner.displayName = value.displayName

        return owner
    }
}