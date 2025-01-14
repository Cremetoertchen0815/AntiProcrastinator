package com.egotec.starterproject.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id

@Entity
open class TodoEntity() {
    @Id
    @GeneratedValue
    var id: Long? = null

    var name: String? = null

    var content: String? = null

    var done: Boolean = false

    constructor(id: Long?, name: String?, content: String?, done: Boolean) : this() {
        this.id = id
        this.name = name
        this.content = content
        this.done = done
    }
}