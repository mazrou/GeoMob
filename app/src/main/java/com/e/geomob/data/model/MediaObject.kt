package com.e.geomob.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "video")
class MediaObject {
    @PrimaryKey
    var id = 0
    var title: String? = null
    var media_url: String? = null
    var thumbnail: String? = null
    var description: String? = null
    var country_id = 0

    constructor(
        id: Int,
        title: String?,
        media_url: String?,
        thumbnail: String?,
        description: String?,
        country_id: Int
    ) {
        this.id = id
        this.title = title
        this.media_url = media_url
        this.thumbnail = thumbnail
        this.description = description
        this.country_id = country_id
    }

    constructor(
        title: String?,
        media_url: String?,
        thumbnail: String?,
        description: String?
    ) {
        this.title = title
        this.media_url = media_url
        this.thumbnail = thumbnail
        this.description = description
    }

    constructor() {}

}

