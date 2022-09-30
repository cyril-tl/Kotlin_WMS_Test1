package com.example.myapplication.models

import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.cfg.MapperConfig
import com.fasterxml.jackson.databind.introspect.AnnotatedField
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod
import com.fasterxml.jackson.databind.introspect.AnnotatedParameter

class ApiTLParsingStrategy : PropertyNamingStrategy() {
    override fun nameForConstructorParameter(
        config: MapperConfig<*>?,
        ctorParam: AnnotatedParameter?,
        defaultName: String?
    ): String {
        return super.nameForConstructorParameter(config, ctorParam, convert(defaultName))
    }

    private fun convert(defaultName: String?): String? {
        return when (defaultName) {
            "code"-> "code"
            "description"-> "description"
            "data"-> "data"
            else ->  defaultName?.uppercase()
        }
    }
}