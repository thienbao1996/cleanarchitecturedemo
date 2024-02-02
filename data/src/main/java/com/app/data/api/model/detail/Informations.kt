package com.app.data.api.model.detail

data class Informations(
    val URLImage: String = "",
    @Transient var URLLogoChannel: String = "",
    @Transient var URLMedias: String = "",
    @Transient var consumptionPlatform: String = "",
    @Transient var contentID: String = "",
    @Transient var displayPersoButtons: Boolean = false,
    @Transient var editorialTitle: String = "",
    @Transient var fbkWatchTag: String = "",
    @Transient var idRevision: String = "",
    @Transient var sharingURL: String = "",
    val subtitle: String = "",
    val summary: String = "",
    @Transient var thirdTitle: String = "",
    val title: String = "",
    @Transient var type: String = ""
)