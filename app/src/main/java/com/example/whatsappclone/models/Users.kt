package com.example.whatsappclone.models

class Users() {
    var display_name: String? = null
    var image: String? = null
    var thum_image: String? = null
    var status: String? = null

    constructor(
        display_name: String,
        status: String,
        thum_image: String,
        image: String
    ) : this() {
        this.display_name = display_name
        this.image = image
        this.status = status
        this.thum_image = thum_image
    }
}