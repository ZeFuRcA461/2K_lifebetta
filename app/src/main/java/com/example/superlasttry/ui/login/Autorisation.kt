package com.example.superlasttry.ui.login

import kotlinx.coroutines.*
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.superlasttry.MainActivity
import com.example.superlasttry.R
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

class Autorisation : AppCompatActivity() {
    private val client = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_autorisation)

        val usernameEditText = findViewById<EditText>(R.id.gmail)
        val passwordEditText = findViewById<EditText>(R.id.password)
        val loginButton = findViewById<Button>(R.id.loginButton)

        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val tokenClientId = "rxnK9nqgOveJiJwxkpd1jNnRIrRUdhK23khvsjeA"
                    val tokenClientSecret = "eW7k3PZM4lypHuIIuVMjsgS62LYd4am5hSarL61YrVzX1iqSZOWycEHVf3pW8JlqaObhI0xSg7bVDYK3RxS0xtBNCwbg3CuZakZ82m55R4Vc1svsKoMExj47oSUn5w3r"
                    val tokenFormBody: RequestBody = FormBody.Builder()
                        .add("grant_type", "password")
                        .add("username", username)
                        .add("password", password)
                        .build()

                    val tokenRequest = Request.Builder()
                        .url("https://sso.pnu.edu.ru/o/token/")
                        .post(tokenFormBody)
                        .addHeader("Authorization", "Basic ${base64encode("$tokenClientId:$tokenClientSecret")}")
                        .build()

                    val tokenResponse = client.newCall(tokenRequest).execute()
                    val tokenResponseBody = tokenResponse.body
                    val tokenStringBody = tokenResponseBody?.string() ?: ""
                    Log.d("TokenResponse", "Response JSON: $tokenStringBody")
                    val tokenJson = JSONObject(tokenStringBody)

                    if (tokenJson.has("access_token")) {
                        val accessToken = tokenJson.getString("access_token")

                        val studentRequest = Request.Builder()
                            .url("https://portal.pnu.edu.ru/portal_api/oauth/get_stud_info/?token=$accessToken")
                            .build()

                        val studentResponse = client.newCall(studentRequest).execute()
                        val studentResponseBody = studentResponse.body
                        val studentStringBody = studentResponseBody?.string() ?: ""
                        val studentJsonArray = JSONArray(studentStringBody)
                        val studentJsonObject = studentJsonArray.getJSONObject(0)

                        val personalInfo = studentJsonObject.getJSONObject("1.personal_info")
                        val studyCards = studentJsonObject.getJSONArray("2.study_cards")

                        val firstName = personalInfo.getString("first_name")
                        val lastName = personalInfo.getString("last_name")
                        val groupName = studyCards.getJSONObject(0).getJSONObject("group").getString("name")

                        withContext(Dispatchers.Main) {
                            val intent = Intent(this@Autorisation, MainActivity::class.java)
                            intent.putExtra("firstName", firstName)
                            intent.putExtra("lastName", lastName)
                            intent.putExtra("groupName", groupName)
                            startActivity(intent)
                        }
                    } else {
                        showToast("Ошибка: Неверный логин или пароль")
                    }

                    tokenResponseBody?.close()


                } catch (e: IOException) {
                    e.printStackTrace()
                    showToast("Ошибка при выполнении запроса")
                }
            }
        }
    }

    private fun base64encode(text: String): String {
        val data = text.toByteArray()
        return Base64.encodeToString(data, Base64.NO_WRAP)
    }

    private fun showToast(message: String) {
        runOnUiThread {
            Toast.makeText(this@Autorisation, message, Toast.LENGTH_SHORT).show()
        }
    }
}
