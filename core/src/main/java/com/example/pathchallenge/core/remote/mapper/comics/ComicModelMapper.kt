package com.example.pathchallenge.core.remote.mapper.comics

import com.example.pathchallenge.core.domain.model.comics.Comic
import com.example.pathchallenge.core.remote.model.comics.ComicDto
import com.example.pathchallenge.core.remote.model.comics.Thumbnail
import com.example.pathchallenge.core.util.Constants
import com.example.pathchallenge.mapper.RemoteModelMapper
import javax.inject.Inject

class ComicModelMapper @Inject constructor() : RemoteModelMapper<ComicDto, Comic> {
    override fun mapFromModel(model: ComicDto): Comic {
        return Comic(
            id = model.id,
            image = mapImageUrl(model.thumbnail),
            title = model.title,
        )
    }

    private fun mapImageUrl(thumbnailDto: Thumbnail): String {
        return Constants.IMAGE_URL_FORMAT.format(
            thumbnailDto.path.replace("http", "https"),
            thumbnailDto.extension
        )
    }
}