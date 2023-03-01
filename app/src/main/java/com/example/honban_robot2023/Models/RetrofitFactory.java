package com.example.honban_robot2023.Models;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *  API取得のためのretrofitを作成する。
 *  セキュリティレベルを手動で設定し、証明書のない
 *  HTTPS通信でもエラーを出さないようにする。
 */
public class RetrofitFactory {

    /**
     * 証明書が無くてもエラーを出さない、セキュリティレベルの低い
     * ビルド済みのretrofitオブジェクトを作成する。
     *
     * @param baseUrl WebページのURL。/で終わる必要がある
     * @return ビルド済みの {@link Retrofit}
     */
    public static Retrofit getApiClient(String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getHttpClient())
                .build();
    }

    private static OkHttpClient getHttpClient() {

        // return new OkHttpClient.Builder()
        // 通常のBuilderの代わりに、カスタマイズしたBuilderを使う
        return getUnsafeOkHttpClient()
                .readTimeout((15 * 1000), TimeUnit.MILLISECONDS)
                .writeTimeout((20 * 1000), TimeUnit.MILLISECONDS)
                .connectTimeout((20 * 1000), TimeUnit.MILLISECONDS)
                .build();
    }

    private static OkHttpClient.Builder getUnsafeOkHttpClient() {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
            builder.hostnameVerifier((hostname, session) -> true);
            return builder;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}