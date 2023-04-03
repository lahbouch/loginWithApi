package com.example.loginwithapi

import android.content.Context
import android.os.AsyncTask
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.URL
import java.net.URLConnection
import java.net.URLEncoder

class AuthTask(
    val context : Context,
    val progressBar : ProgressBar,
    val loginStatus : TextView,
    val roleStatus : TextView,
    val flag : Int
) : AsyncTask<String,Unit,String>() {


    val POST_METHOD = 1
    val GET_METHOD = 0


    override fun onPreExecute() {
        progressBar.visibility = View.VISIBLE
        super.onPreExecute()
    }

    override fun doInBackground(vararg p0: String?): String {

        val username = p0[0]
        val password = p0[1]


        try {
            if (flag == GET_METHOD) {
                val url: URL = URL("http://10.0.2.2/api?username=$username&password=$password")


                val connection = url.openConnection()
                connection.connectTimeout = 1000


                val reader = BufferedReader(InputStreamReader(connection.getInputStream()))
                val sb = StringBuffer("")
                var line = ""


                while (reader.readLine().run {
                        line = this
                    } != null) {

                    sb.append(line)
                    break
                }

                reader.close()
                return sb.toString()
            }

            else{
                val link = "http://10.0.2.2/api/authentifierpost.php";
                var data = URLEncoder.encode("username","UTF-8")+"="+URLEncoder.encode(username,"UTF-8");
                data+="&"+URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");


                val url :URL = URL(link)
                val conn : URLConnection = url.openConnection()
                conn.doOutput = true

                val wr : OutputStreamWriter = OutputStreamWriter((conn.getOutputStream()))

                wr.write(data)
                wr.flush()


                val reader = BufferedReader(InputStreamReader(conn.getInputStream()))
                val sb = StringBuffer("")
                var line = ""


                while (reader.readLine().run {
                        line = this
                    } != null) {

                    sb.append(line)
                    break
                }

                return sb.toString()
            }

        } catch (e: Exception) {
            Log.e("TAG", "doInBackground: ${e.stackTrace}")

            return e.message!!
        }






    }


    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
        Log.e("TAG", "onPostExecute: " )
        progressBar.visibility = View.GONE
        loginStatus.text = "login successfully"
        roleStatus.text = result.toString()
        loginStatus.visibility = View.VISIBLE
        roleStatus.visibility = View.VISIBLE

    }

}