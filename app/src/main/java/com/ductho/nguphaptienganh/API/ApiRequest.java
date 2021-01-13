package com.ductho.nguphaptienganh.API;

import com.ductho.nguphaptienganh.Model.ApiResponse;

import io.reactivex.rxjava3.core.Maybe;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiRequest {
    @GET("api.php")
    Maybe<ApiResponse> fetchQuestions(
            @Query("amount") int i);
}
