package andrefigas.com.github.sports.model.services

import andrefigas.com.github.sports.model.entities.Category
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface EndPoints {

    @GET("api/sports")
    fun provideCategories() : Single<List<Category>>

}