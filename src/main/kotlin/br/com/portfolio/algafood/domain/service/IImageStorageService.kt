package br.com.portfolio.algafood.domain.service

import java.io.InputStream
import java.util.UUID


interface IImageStorageService {
    fun store(image: Image)
    fun generateFileName(originalName: String): String {
        return UUID.randomUUID().toString() + "_" + originalName
    }

    data class Image(val fileName: String, val inputStream: InputStream)
}
