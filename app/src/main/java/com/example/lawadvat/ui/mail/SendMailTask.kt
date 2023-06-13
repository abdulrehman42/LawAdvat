package com.example.lawadvat.ui.mail

import android.content.Context
import android.os.AsyncTask
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Properties
import javax.mail.AuthenticationFailedException
import javax.mail.Authenticator
import javax.mail.Message
import javax.mail.MessagingException
import javax.mail.PasswordAuthentication
import javax.mail.Session
import javax.mail.Transport
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

class SendMailTask(
    private val context: Context?,
    private val from: String,
    private val to: String,
    private val subject: String,
    private val message: String,
    private val password: String
) {
    @OptIn(DelicateCoroutinesApi::class)
    fun sendMail() {
        GlobalScope.launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) {
                try {
                    val props = Properties()
                    props["mail.smtp.host"] = "smtp.gmail.com"
                    props["mail.smtp.socketFactory.port"] = "465"
                    props["mail.smtp.socketFactory.class"] = "javax.net.ssl.SSLSocketFactory"
                    props["mail.smtp.auth"] = "true"
                    props["mail.smtp.port"] = "465"
                    props["mail.smtp.socketFactory.fallback"] = "false"
                    props.setProperty("mail.smtp.quitwait", "false")
                    val session = Session.getDefaultInstance(props, object : Authenticator() {
                        override fun getPasswordAuthentication(): PasswordAuthentication {
                            return PasswordAuthentication(from, password)
                        }
                    })

                    val mm = MimeMessage(session)
                    mm.setFrom(InternetAddress(from))
                    mm.addRecipient(Message.RecipientType.TO, InternetAddress(to))
                    mm.subject = subject
                    mm.setText(message)
                    Transport.send(mm)
                    showToastOnMainThread("Email sent successfully!")
                } catch (e: AuthenticationFailedException) {
                    showToastOnMainThread("There is an error in either email id or Google app password. Please check them carefully")
                } catch (e: MessagingException) {
                    e.printStackTrace()
                    showToastOnMainThread("Some error occurred while sending mail")
                }
            }
        }
    }

    private fun showToastOnMainThread(message: String) {
        Handler(Looper.getMainLooper()).post {
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        }
    }
}
