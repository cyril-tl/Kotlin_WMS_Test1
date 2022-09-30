package com.example.myapplication.models
//
//import com.fasterxml.jackson.annotation.JsonFormat
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties
//import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
//import com.fasterxml.jackson.module.kotlin.readValue
//import okhttp3.OkHttpClient
//import okhttp3.Request
//import okhttp3.RequestBody
//import okhttp3.RequestBody.Companion.toRequestBody
//import okio.IOException
//import java.util.*
//import kotlin.system.measureTimeMillis
//
//class ApiHelperJackson {
//    companion object ApiParams {
//        private val okCLient: OkHttpClient = OkHttpClient()
//        private var mapper = jacksonObjectMapper()
//
//        private var accessToken: AccessToken = AccessToken("", Date())
//        private var commandes: List<Commande> =
//            listOf(
//                Commande(
//                    false, "", "",
//                    Livraison(0, "", "", "", "", ""),
//                    "", 0, 0, ""
//                )
//            )
//
//        private var references: References = References(
//            mutableListOf(STAppel1("", 0, "")),
//            mutableListOf(STAppel1("", 0, "")),
//            mutableListOf(STAppel1("", 0, "")),
//            mutableListOf(STAppel1("", 0, "")),
//            mutableListOf(STAppel3("", 0, "", "")),
//            mutableListOf(STAppel1("", 0, "")),
//            mutableListOf(STAppel1("", 0, "")),
//            mutableListOf(STAppel1("", 0, "")),
//            mutableListOf(STAppel2("", 0, "", "", "")),
//        )
//
//        var resECEC: ECECModel = ECECModel(commandes, "", 0, references)
//
//        fun authenticate() {
//            if (!(accessToken.token == "" || accessToken.expiration < Date())) {
//                println("hold old token")
//                println(Date())
//            } else {
//                println("get new token")
//                val request = Request.Builder()
//                    .url("https://api.thiriez-literie.fr/auth/token?grant_type=password&username=DEMOWS-THIRIEZ-PROD@YOPMAIL.COM&password=azerty123")
//                    .addHeader("Content-Type", "application/json")
//                    .addHeader(
//                        "Authorization",
//                        "Basic RjNMRzZlUHI3NUV3YkNlVnE4eUY4V0ZYWkxyYmF2Z3pmcFhwTk12TlZKMjU0TmpiZW46dnlHUkdBWGZzRXRGalNBR0tuN2tCa0NiVHdnNXdWcDR1d3g0a004M1REYUxnanlRSzM="
//                    )
//                    .build()
//                okCLient.newCall(request).execute().use { response ->
//                    if (!response.isSuccessful) throw IOException("Unexpected code $response")
//                    mapper.propertyNamingStrategy = ApiTLParsingStrategy()
//                    val apiResponse: ApiResponse<AuthToken> =
//                        mapper.readValue(response.body!!.string())
//                    println(apiResponse.data.accessToken.expiration)
//                    accessToken = apiResponse.data.accessToken
//                }
//            }
//        }
//
//        private var timeToCall = 0
//        fun getTimeToCall(): Int {
//            return timeToCall
//        }
//
//        fun getECEC() {
//            println("get ECEC")
//            val payload = "{\"ACTION\":\"ETAT\"}"
//            val requestBody: RequestBody = payload.toRequestBody()
//
//            val request = Request.Builder()
//                .method("POST", requestBody)
//                .url("https://api.thiriez-literie.fr/otl")
//                .addHeader("Content-Type", "application/json")
//                .addHeader("API", "ECEC")
//                .addHeader(
//                    "Authorization",
//                    "Bearer eyAiYWxnIjoiSFMyNTYiLCAidHlwIjoiSldUIiB9.eyAiZXhwIjoiMjAyMi0wOS0yN1QxNzo1MzoyNy43NzkiLCAiSURDb21wdGUiOjM3LCAiQ29kZUNFR0lEIjoiREVNT1dTIiwgIlR5cGVDb21wdGUiOiJDIiwgIkJhc2UiOiJQUk9EIiwgIkFkbWluIjp0cnVlLCAiUG91dm9pciI6MywgIkJ5cGFzcyI6dHJ1ZSwgIlBlQXV0b3Jpc2VzIjpbICJCMkIiLCAiR01BTyIsICJHUEFPIiwgIk9UTCIsICJXTVMiIF0sICJGb25jQXV0b3Jpc2VzIjpbICJCMkJfIiwgIkdNQU9fIiwgIkdQQU9fIiwgIk9UTF8iLCAiT1RMX0FETUlOSVNUUkFUSU9OIiwgIk9UTF9QQVJBTVMiLCAiV01TXyIgXSB9.XZsALFNrpWTwpr0NI56mhJaVmM5x6jq_YqSTmlktgHs="
//                )
////                .addHeader(
////                    "Authorization",
////                    "Bearer ${accessToken.token}"
////                )
//                .build()
//            var responsebis: String
//            val elapsed = measureTimeMillis {
//                okCLient.newCall(request).execute().use { response ->
//                    if (!response.isSuccessful) throw IOException("Unexpected code $response")
////                println(response.body!!.string())
//                    mapper.propertyNamingStrategy = ApiTLParsingStrategy()
//                    responsebis = response.body!!.string()
//                }
////                println("Nombre de résultats: ${apiResponse.data.nombre_de_resultats}")
////                println("references: ${apiResponse.data.references}")
//            }
//            val apiResponse: ApiResponse<ECECModel> = mapper.readValue(responsebis)
//            println("description ${apiResponse.description}")
//            println("Mesure de temps : $elapsed")
//            timeToCall = elapsed.toInt()
////            var smalllist = apiResponse.data.commandes!!.first()
////            apiResponse.data.commandes = listOf(smalllist)
//            resECEC = apiResponse.data
//        }
//    }
//}
//
////data class ApiResponse<T>(val code: Int, val description: String, val data: T)
////data class AccessToken(
////    val token: String,
//////                        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-ddTHH:mm a z")
//////                        val expiration: Date
////    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
////    val expiration: Date
////)
////
////@JsonIgnoreProperties(ignoreUnknown = true)
////data class AuthToken( //@JsonProperty("ACcESSTOKEN")
////    val accessToken: AccessToken
////)
//
//
