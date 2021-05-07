package com.ludoven.mvp.base



data class BaseBean<T>(
    var data: T?,
    var meta: MetaBean
)
data class MetaBean (
    var success : Boolean,
    var message:String?
)
