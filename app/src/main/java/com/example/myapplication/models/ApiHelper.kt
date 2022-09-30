package com.example.myapplication.models

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.internal.GsonBuildConfig
import com.google.gson.reflect.TypeToken
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okio.IOException
import java.lang.reflect.Type
import java.util.*
import java.util.Base64
import kotlin.system.measureTimeMillis

open class ApiHelper {
    companion object ApiParams {
        var description = ""
        private val CLIENT_ID: String = "F3LG6ePr75EwbCeVq8yF8WFXZLrbavgzfpXpNMvNVJ254Njben"
        private val CLIENT_SECRET: String = "vyGRGAXfsEtFjSAGKn7kBkCbTwg5wVp4uwx4kM83TDaLgjyQK3"

        @RequiresApi(Build.VERSION_CODES.O)
        private val authBase64: String =
            Base64.getEncoder().encodeToString("$CLIENT_ID:$CLIENT_SECRET".toByteArray())
        protected val okCLient: OkHttpClient = OkHttpClient()
        private var gson: Gson = GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
//            .setFieldNamingStrategy(FieldNamingPolicy.UPPER_CASE_WITH_UNDERSCORES)
            .serializeSpecialFloatingPointValues().setPrettyPrinting()
            .serializeNulls().create()

        private var accessToken: AccessToken = AccessToken("", Date())
        private var commandes: List<COMMANDE> =
            listOf()
//                COMMANDE(
//                    false, "", "",
//                    LIVRAISON(0, C"", "", "", "", ""),
//                    "", 0, 0, ""
//                )

        private var references: REFERENCES = REFERENCES(
            mutableListOf(STAppel1("", 0, "")),
            mutableListOf(STAppel1("", 0, "")),
            mutableListOf(STAppel1("", 0, "")),
            mutableListOf(STAppel1("", 0, "")),
            mutableListOf(STAppel3("", 0, "", "")),
            mutableListOf(STAppel1("", 0, "")),
            mutableListOf(STAppel1("", 0, "")),
            mutableListOf(STAppel1("", 0, "")),
            mutableListOf(STAppel2("", 0, "", "", "")),
        )
        var resECEC: ECECModel = ECECModel(commandes, "", 0, references)

        fun test() {
            println("$authBase64")
        }

        fun authenticate() {
            if (!(accessToken.TOKEN == "" || accessToken.EXPIRATION < Date())) {
                println("hold old token")
                println(Date())
                description = "hold old token"
            } else {
                println("get new token")
                val request = Request.Builder()
                    .url("https://api.thiriez-literie.fr/auth/token?grant_type=password&username=DEMOWS-THIRIEZ-PROD@YOPMAIL.COM&password=azerty123")
                    .addHeader("Content-Type", "application/json")
                    .addHeader(
                        "Authorization",
                        "Basic $authBase64"
                    )
                    .build()
                okCLient.newCall(request).execute().use { response ->
                    if (!response.isSuccessful) throw IOException("Unexpected code $response")
                    else {
                        val type: Type = object : TypeToken<ApiResponse<AuthToken>>() {}.type
                        val apiResponse: ApiResponse<AuthToken> =
                            gson.fromJson(response.body!!.string(), type)

                        println(apiResponse)
                        println(apiResponse.description)
                        println(apiResponse.data.ACCESSTOKEN.EXPIRATION)
                        description = apiResponse.description
                        accessToken = apiResponse.data.ACCESSTOKEN
                    }
                }
            }
        }

        private var timeToCall = 0
        fun getTimeToCall(): Int {
            return timeToCall
        }

        fun getECEC() {
            if ((accessToken.TOKEN == "" || accessToken.EXPIRATION < Date())) {
                println("need new token")
                description = "need new token"
            } else {
                println("get ECEC")
                val payload = "{\"ACTION\":\"ETAT\"}"
                val requestBody: RequestBody = payload.toRequestBody()

                val request = Request.Builder()
                    .method("POST", requestBody)
                    .url("https://api.thiriez-literie.fr/otl")
                    .addHeader("Content-Type", "application/json")
                    .addHeader("API", "ECEC")
                    .addHeader(
                        "Authorization",
                        "Bearer ${accessToken.TOKEN}"
                    )
//                .addHeader(
//                    "Authorization",
//                    "Bearer ${accessToken.token}"
//                )
                    .build()

                var responseTmp: String = ""
                val elapsed = measureTimeMillis {
                    okCLient.newCall(request).execute().use { response ->
                        if (!response.isSuccessful) throw IOException("Unexpected code $response")
                        else {
                            responseTmp = response.body!!.string()
                        }
                    }
                }
                println("Mesure de temps : $elapsed")
                timeToCall = elapsed.toInt()
                val type: Type = object : TypeToken<ApiResponse<ECECModel>>() {}.type
                val apiResponse: ApiResponse<ECECModel> =
                    gson.fromJson(responseTmp, type)

                description = apiResponse.description
                println("Nombre de résultats: ${apiResponse.data.NOMBRE_DE_RESULTATS}")
                println("Date de génération: ${apiResponse.data.DATE_GENERATION}")

//            resECEC = apiResponse.data
            }
        }
    }
}

data class ApiResponse<T>(
    val code: Int,
    val description: String,
    val data: T
)

data class AccessToken(
    val TOKEN: String,
    val EXPIRATION: Date
)

data class AuthToken(
    val ACCESSTOKEN: AccessToken
)