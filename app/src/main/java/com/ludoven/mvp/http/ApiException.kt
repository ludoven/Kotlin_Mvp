package com.ludoven.mvp.http

class ApiException(throwable: Throwable?, var code: Int) :
    Exception(throwable) {
    override var message: String? = null

}

class ServerException : java.lang.RuntimeException() {
    var code = 0
    override var message: String? = null
}