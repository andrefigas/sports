package andrefigas.com.github.sports.singleton.di

import andrefigas.com.github.sports.model.services.EndPoints
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class AppModule {

    companion object {
        const val URL = "https://618d3aa7fe09aa001744060a.mockapi.io/"
    }

    @Provides
    fun provideApi() = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .baseUrl(URL).build().create(EndPoints::class.java)
}