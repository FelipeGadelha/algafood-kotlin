package br.com.portfolio.algafood.infra.service

import br.com.portfolio.algafood.domain.service.IImageStorageService
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.util.FileCopyUtils

import java.nio.file.Files
import java.nio.file.Path

@Service
class LocalImageStorageService : IImageStorageService {
    @Value("\${algafood.storage.local}")
    private val directory: Path? = null
    override fun store(image: IImageStorageService.Image) {
        try {
            val path: Path = filePath(image.fileName)
            FileCopyUtils.copy(image.inputStream, Files.newOutputStream(path))
        } catch (e: Exception) {
            System.err.println(e.cause)
            throw RuntimeException("Não foi possivel armazenar arquivo.", e.cause)
        }
    }

    private fun filePath(fileName: String): Path = directory!!.resolve(Path.of(fileName))
}