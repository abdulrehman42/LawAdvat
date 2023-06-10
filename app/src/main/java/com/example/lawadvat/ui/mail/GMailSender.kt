package com.example.lawadvat.ui.mail



internal class GMailSender(private val user: String, private val password: String)/* :
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
        props.put(
            "mail.smtp.socketFactory.class",
            "javax.net.ssl.SSLSocketFactory"
        )
        props.put("mail.smtp.socketFactory.fallback", "false")
        props.setProperty("mail.smtp.quitwait", "false")
        session = Session.getDefaultInstance(props, this)
    }

    protected val passwordAuthentication: PasswordAuthentication
        protected get() = PasswordAuthentication(user, password)

    @Synchronized
    @Throws(Exception::class)
    fun sendMail(subject: String?, body: String, sender: String?, recipients: String) {
        try {
            val message = MimeMessage(session)
            val handler = DataHandler(ByteArrayDataSource(body.toByteArray(), "text/plain"))
            message.setSender(InternetAddress(sender))
            message.setSubject(subject)
            message.setDataHandler(handler)
            if (recipients.indexOf(',') > 0) message.setRecipients(
                Message.RecipientType.TO,
                InternetAddress.parse(recipients)
            ) else message.setRecipient(Message.RecipientType.TO, InternetAddress(recipients))
            Transport.send(message)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    inner class ByteArrayDataSource : DataSource {
        private var data: ByteArray
        private var type: String? = null

        constructor(data: ByteArray, type: String?) : super() {
            this.data = data
            this.type = type
        }

        constructor(data: ByteArray) : super() {
            this.data = data
        }

        fun setType(type: String?) {
            this.type = type
        }

        val contentType: String
            get() = if (type == null) "application/octet-stream" else type

        @get:Throws(IOException::class)
        val inputStream: InputStream
            get() = ByteArrayInputStream(data)
        val name: String
            get() = "ByteArrayDataSource"

        @get:Throws(IOException::class)
        val outputStream: OutputStream
            get() {
                throw IOException("Not Supported")
            }
    }

    companion object {
        init {
            Security.addProvider(JSSEProvider())
        }
    }*/