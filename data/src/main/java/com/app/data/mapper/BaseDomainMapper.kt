package com.app.data.mapper

import android.util.Log

abstract class BaseDomainMapper<API, DOMAIN> {

    abstract val tag: String

    abstract fun toDomain(api: API): DOMAIN

    fun toDomain(apiList: List<API>): List<DOMAIN> {
        val domainList = mutableListOf<DOMAIN>()
        apiList.forEach { api ->
            try {
                domainList.add(toDomain(api))
            } catch (e: Exception) {
                Log.e(tag, "the object $api has been ignored", e)
            }
        }
        return domainList
    }

    protected fun <VALUE> VALUE?.consolidateValue(fieldName: String): VALUE {
        if (this == null) {
            throw MandatoryFieldException(fieldName)
        } else {
            return this
        }
    }

    protected fun String?.consolidateValue(fieldName: String): String {
        if (this.isNullOrEmpty()) {
            throw MandatoryFieldException(fieldName)
        } else {
            return this
        }
    }

    class MandatoryFieldException(fieldName: String) :
        Exception("The object cannot be mapped because the field $fieldName is mandatory")
}