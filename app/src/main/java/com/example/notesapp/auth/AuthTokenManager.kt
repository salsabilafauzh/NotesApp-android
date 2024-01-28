import android.content.Context

class AuthTokenManager(context: Context) {

    private val sharedPreferences =
        context.getSharedPreferences("Credentials", Context.MODE_PRIVATE)

    fun saveAccessToken(accessToken: String) {
        val editor = sharedPreferences.edit()
        editor.putString("ACCESS_TOKEN", accessToken)
        editor.apply()
    }

    fun getAccessToken(): String {
        return sharedPreferences.getString("ACCESS_TOKEN", "") ?: ""
    }
}
