package java0.nio01;


import io.netty.handler.codec.json.JsonObjectDecoder;
import jdk.nashorn.internal.runtime.regexp.joni.Config;
import netscape.javascript.JSObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import javax.swing.text.html.parser.Entity;
import java.nio.charset.StandardCharsets;

public class HttpClient01 {
    public static void main(String[] args) {
        doGetHttpReq();
    }

    //Http Get
    public static void doGetHttpReq(){
        //创建一个http客户端实例
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        //创建get请求
        HttpGet httpGet = new HttpGet("http://localhost:8801");
        //创建响应模型
        CloseableHttpResponse response = null;
        try {
            //配置基础信息
            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectTimeout(5000)
                    .setConnectionRequestTimeout(5000)
                    .setSocketTimeout(5000)
                    .setRedirectsEnabled(true).build();
            httpGet.setConfig(requestConfig);

            //由客服端执行get请求
            response = httpClient.execute(httpGet);

            //从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();
            if (responseEntity != null) {
                System.out.println("响应内容长度为: " + responseEntity.getContentLength());
                System.out.println("响应内容为:" + EntityUtils.toString(responseEntity, StandardCharsets.UTF_8));
            }

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                //释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
