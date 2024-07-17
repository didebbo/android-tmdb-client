package com.example.themoviedbclient.domain.repository.detail.item

interface DetailItemRepository {
    fun getImageFullPath(path: String): String
}