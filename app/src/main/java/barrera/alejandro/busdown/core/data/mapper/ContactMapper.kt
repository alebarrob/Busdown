package barrera.alejandro.busdown.core.data.mapper

import barrera.alejandro.busdown.core.domain.model.Contact

fun String.toContact(): Contact {
    return Contact(
        id = 0,
        email = this
    )
}