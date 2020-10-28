package JAVA000.WEEK02.com.poseidon;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpClient {
  private void getLocal(){
    CloseableHttpClient httpClient = HttpClientBuilder.create().build();
    // 创建Get请求
    HttpGet httpGet = new HttpGet("https://www.baidu.com");

    // 响应模型
    CloseableHttpResponse response = null;
    try {
      // 由客户端执行(发送)Get请求
      response = httpClient.execute(httpGet);
      // 从响应模型中获取响应实体
      HttpEntity responseEntity = response.getEntity();
      System.out.println("响应状态为:" + response.getStatusLine());
      if (responseEntity != null) {
        System.out.println("响应内容长度为:" + responseEntity.getContentLength());
        System.out.println("响应内容为:" + EntityUtils.toString(responseEntity));
      }
    } catch (ClientProtocolException e) {
      e.printStackTrace();
    } catch (ParseException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        // 释放资源
        if (httpClient != null) {
          httpClient.close();
        }
        if (response != null) {
          response.close();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

  }

  public static void main(String[] args) {
    HttpClient httpClient = new HttpClient();
    httpClient.getLocal();
  }
}
