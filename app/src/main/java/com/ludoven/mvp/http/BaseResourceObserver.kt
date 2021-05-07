package com.ludoven.mvp.http

import android.util.Log
import com.google.gson.JsonParseException
import io.reactivex.Observer
import org.json.JSONException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.UnknownHostException
import java.text.ParseException

abstract class BaseResourceObserver<T> : Observer<T> {
    /**
     * 打印日志到控制台
     * throwable.printStackTrace();
     * 如果你某个地方不想使用全局错误处理,
     * 则重写 onError(Throwable) 并将 super.onError(e); 删掉
     * 如果你不仅想使用全局错误处理,还想加入自己的逻辑,
     * 则重写 onError(Throwable) 并在 super.onError(e); 后面加入自己的逻辑
     */
    override fun onError(throwable: Throwable) {
        val apiException = requestHandle(throwable)
        Log.e("TAG", "requestHandle: " + apiException.code + apiException.message)
    }

    override fun onComplete() {}

    /**
     * 统一处理Throwable
     * @param e e
     * @return msg
     */
    private fun requestHandle(e: Throwable): ApiException {
        val ex: ApiException

        when (e) {
            is HttpException -> {
                ex = ApiException(e, ERROR.HTTP_ERROR)
                when (e.code()) {
                    UNAUTHORIZED, FORBIDDEN, NOT_FOUND, REQUEST_TIMEOUT, GATEWAY_TIMEOUT, INTERNAL_SERVER_ERROR, BAD_GATEWAY, SERVICE_UNAVAILABLE ->                     //均视为网络错误
                        ex.message = "Network Error"
                    else -> ex.message = "Network Error"
                }
            }
            is ServerException -> {
                ex = ApiException(e, e.code)
                ex.message = e.message
            }
            is JSONException,is ParseException,is JsonParseException->{
                ex = ApiException(e, ERROR.PARSE_ERROR)
                ex.message = "Parsing error"
            }
            is ConnectException->{
                ex = ApiException(e, ERROR.NETWORD_ERROR)
                ex.message = "Connection failed"
            }
            is ApiException->{
                ex = ApiException(e, e.code)
                //后台异常，在这里你可以toast弹窗或者进行其他处理，具体需根据业务结合
                if (ex.code == 401 || ex.code == 403) {
                    ex.message = "The account has been offline. Please login again"
                } else {
                    ex.message = "An error occurred"
                }
            }
            is UnknownHostException->{
                ex = ApiException(e, ERROR.NETWORD_ERROR)
                ex.message = "No network"
            }
            else->{
                ex = ApiException(e, ERROR.UNKNOWN)
                ex.message = "Unknown Mistake"
            }
        }

        return ex
    }

    /**
     * 约定异常
     */
    internal object ERROR {
        /**
         * 未知错误
         */
        const val UNKNOWN = 1000

        /**
         * 解析错误
         */
        const val PARSE_ERROR = 1001

        /**
         * 网络错误
         */
        const val NETWORD_ERROR = 1002

        /**
         * 协议出错
         */
        const val HTTP_ERROR = 1003
    }

    companion object {
        /*========================= HttpException 异常 code ==========================*/
        private const val UNAUTHORIZED = 401
        private const val FORBIDDEN = 403
        private const val NOT_FOUND = 404
        private const val REQUEST_TIMEOUT = 408
        private const val INTERNAL_SERVER_ERROR = 500
        private const val BAD_GATEWAY = 502
        private const val SERVICE_UNAVAILABLE = 503
        private const val GATEWAY_TIMEOUT = 504
    }
}