package barrera.alejandro.busdown.viewmodel

import barrera.alejandro.busdown.model.entity.Contact

class EmailFormatter {
    fun transformEmailsToContacts(emails: List<String>): List<Contact> = emails.map { Contact(0, it) }

    fun formatEmails(unformattedEmails: String): List<String> {
        val formattedEmails = unformattedEmails.replace(" ", "").split(",")

        return if (formattedEmails.last() == "") formattedEmails.dropLast(1) else formattedEmails
    }
}