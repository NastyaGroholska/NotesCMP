package com.ahrokholska.permission

typealias InternalPerm = com.mohamedrejeb.calf.permissions.Permission

enum class Permission(internal val permission: InternalPerm) {
    Gallery(InternalPerm.Gallery)
}