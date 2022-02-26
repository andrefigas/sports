package andrefigas.com.github.sports.model.services

import okhttp3.*
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.IOException
import java.io.InputStreamReader

class OkHttpMockInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val responseString = readFile("src//test//assets//sports.json")
        return Response.Builder()
            .code(200)
            .message(responseString)
            .request(chain.request())
            .protocol(Protocol.HTTP_1_0)
            .body(ResponseBody.create(MediaType.parse("application/json"), responseString))
            .addHeader("content-type", "application/json")
            .build()
    }

    companion object {
        fun readFile(path: String?): String {
            val sb = StringBuilder()
            var strLine: String?
            try {
                BufferedReader(InputStreamReader(FileInputStream(path), "UTF-8")).use { reader ->
                    while (reader.readLine().also { strLine = it } != null) {
                        sb.append(strLine)
                    }
                }
            } catch (ignore: IOException) {
                ignore.printStackTrace()
                //ignore
            }
            return sb.toString()
        }
    }
}