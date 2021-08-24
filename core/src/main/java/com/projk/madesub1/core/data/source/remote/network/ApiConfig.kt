package com.projk.madesub1.core.data.source.remote.network

import com.projk.madesub1.core.BuildConfig

class ApiConfig {
    companion object {
        const val token: String = BuildConfig.KEY
        const val imgUrl: String = BuildConfig.IMG
    }
}