package com.example.notesapp.API

class Notes(id: String?, title: String?, body: String?, tags: Array<String>?, owner: String?) {
    private var id: String
    private var title: String
    private var body: String
    private var tags: Array<String>
    private var owner: String

    init {
        this.id = id!!
        this.title = title!!
        this.body = body!!
        this.tags = tags!!
        this.owner = owner!!
    }



}