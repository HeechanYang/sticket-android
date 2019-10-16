package com.sticket.app.sticket.retrofit.client.service;

import com.sticket.app.sticket.retrofit.dto.response.asset.SimpleAssetResponse;
import com.sticket.app.sticket.retrofit.message.ApiMessasge;
import com.sticket.app.sticket.models.Asset;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface AssetService {

    @GET("sticket/api/normal/assets")
    Call<List<SimpleAssetResponse>> getAllAssets();

    @GET("sticket/api/normal/assets/{assetId}")
    Call<Asset> getAssetById(@Path("assetId") int assetId);

    @GET("sticket/api/normal/assets")
    Call<List<SimpleAssetResponse>> searchAssets(@Query("authorId") int authorId, @Query("buyerId") int buyerId,
                            @Query("landmark") String landmark, @Query("themeId") int themeId);

    @GET("sticket/api/normal/assets/today")
    Call<List<SimpleAssetResponse>> getTodayAssets(@Query("page") int page);

    @GET("sticket/api/normal/assets/popular")
    Call<List<SimpleAssetResponse>> getPopularAssets(@Query("page") int page);

    @GET("sticket/api/normal/assets/new")
    Call<List<SimpleAssetResponse>> getNewAssets(@Query("page") int page);

    @POST("sticket/api/normal/assets/{assetId}/like")
    Call<ApiMessasge> likeAsset(@Path("assetId") int assetId);

    @GET("sticket/api/normal/assets/{assetId}/like")
    Call<ApiMessasge> checkAssetLike();

    @GET("sticket/api/normal/assets/like")
    Call<List<SimpleAssetResponse>> getMyLikeAssets();

    @POST("sticket/api/normal/assets/{assetId}/purchase")
    Call<ApiMessasge> PurchaseAsset(@Path("assetId") int assetId);

    @GET("sticket/api/normal/assets/{assetId}/purchase")
    Call<ApiMessasge> checkAssetPurchase();

    @GET("sticket/api/normal/assets/purchase")
    Call<List<SimpleAssetResponse>> getMyPurchaseAssets();

}
