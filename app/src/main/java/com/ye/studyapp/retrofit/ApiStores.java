package com.ye.studyapp.retrofit;

import com.ye.studyapp.model.CookBookClassifyModel;
import com.ye.studyapp.model.CookBookDetailModel;
import com.ye.studyapp.model.CookBookListModel;
import com.ye.studyapp.model.HomeModel;
import com.ye.studyapp.model.PhotoModel;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by yedeman
 * on 2016/6/16.
 */
public interface ApiStores {
    //baseUrl
    String API_SERVER_URL = "http://apis.baidu.com/";
    String NEWS_API_KEY="333d6a6a5cef89ecae0c97301c531931";

    @GET("showapi_open_bus/channel_news/search_news")
    Observable<HomeModel> loadData(@Header("apikey") String apikey, @Query("page") int page);

    @GET("tngou/gallery/list")
    Observable<PhotoModel> loadPhotoData(@Header("apikey") String apikey, @Query("page") int page);

    //菜品分类
    @GET("tngou/cook/classify")
    Observable<CookBookClassifyModel> loadClassifyData(@Header("apikey") String apikey);
    //菜品列表
    @GET("tngou/cook/list")
    Observable<CookBookListModel> loadListData(@Header("apikey") String apikey,@Query("id") int id,@Query("page") int page);
    //菜品详情
    @GET("tngou/cook/show")
    Observable<CookBookDetailModel> loadCookBookDetailData(@Header("apikey") String apikey, @Query("id") int id);
}
