package com.example.pathchallenge.mapper

public interface RemoteModelMapper<in M, out D> {
    public fun mapFromModel(model: M?): D

    public fun mapModelList(models: List<M>): List<D> {
        return models.mapTo(mutableListOf(), ::mapFromModel)
    }
}