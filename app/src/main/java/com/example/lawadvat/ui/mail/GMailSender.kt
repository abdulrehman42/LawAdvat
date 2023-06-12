import com.example.lawadvat.ui.mail.JSSEProvider
import java.io.ByteArrayInputStream
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.security.Security
import java.util.Properties
import javax.activation.DataHandler
import javax.activation.DataSource
import javax.mail.Message
import javax.mail.PasswordAuthentication
import javax.mail.Session
import javax.mail.Transport
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

internal class GMailSender(private val user: String, private val password: String) :
    javax.mail.Authenticator() {
    private val mailhost = "smtp.gmail.com"
    private val session: Session

    init {
        val props = Properties()
        props.setProperty("mail.transport.protocol", "smtp")
        props.setProperty("mail.host", mailhost)
        props.put("mail.smtp.auth", "true")
        props.put("mail.smtp.port", "465")
        props.put("mail.smtp.socketFactory.port", "465")
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory")
        props.put("mail.smtp.socketFactory.fallback", "false")
        props.setProperty("mail.smtp.quitwait", "false")
        session = Session.getDefaultInstance(props, this)
    }

    @Synchronized
    @Throws(Exception::class)
    fun sendMail(subject: String?, body: String, sender: String?, recipients: String) {
        try {
            val message = MimeMessage(session)
            val handler = DataHandler(ByteArrayDataSource(body.toByteArray(), "text/plain"))
            message.setFrom(InternetAddress(sender))
            message.setSubject(subject)
            message.setDataHandler(handler)
            if (recipients.indexOf(',') > 0) {
                message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(recipients)
                )
            } else {
                message.setRecipient(Message.RecipientType.TO, InternetAddress(recipients))
            }
            Transport.send(message)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    inner class ByteArrayDataSource(private val data: ByteArray, private var type: String?) : DataSource {
        override fun getInputStream(): InputStream {
            return ByteArrayInputStream(data)
        }

        override fun getOutputStream(): OutputStream {
            throw IOException("Not Supported")
        }

        override fun getContentType(): String {
            return type ?: "application/octet-stream"
        }

        override fun getName(): String {
            return "ByteArrayDataSource"
        }
    }

    companion object {
        init {
            Security.addProvider(JSSEProvider())
        }
    }
}