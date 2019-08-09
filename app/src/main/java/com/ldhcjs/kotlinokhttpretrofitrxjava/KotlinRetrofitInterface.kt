package com.ldhcjs.kotlinokhttpretrofitrxjava

import retrofit2.http.GET
import io.reactivex.Single
import retrofit2.http.Path

// https://api.github.com/repos/ldhcjs/GetPackagesName/contributors
// https://api.github.com/repos/[owner]/[repo]/contributors

interface KotlinRetrofitInterface {
    @GET("repos/{owner}/{repo}/contributors")
    fun requestContributors(
        @Path("owner") owner:String,
        @Path("repo") repo:String
    ) : Single<Array<Contributors>>
}