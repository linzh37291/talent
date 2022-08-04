package com.lin.application.service

import com.lin.facade.model.request.BaseRequest
import com.lin.infrastructure.commons.ResultData
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient


/**
 * 响应式的WebClient取代HttpClient的作用
 *
 * @author linzihao
 */
@Service
class ReactiveHttpClientService {
  val log = org.slf4j.LoggerFactory.getLogger(classOf[ReactiveHttpClientService])


  // 注入 webclient
  @Autowired
  var webClient: WebClient = _

  def test(): Unit = {

    val resp = webClient.method(HttpMethod.GET)
      .uri("http://baidu.com")
      .retrieve.bodyToMono(classOf[String])
    print("result:{}", resp.block)
  }

  def sendPostRequest(url: String, params: BaseRequest) {
    // 请求体
    // val params = new mutable.HashMap[String, _];
    // webclient发送请求
    webClient.method(HttpMethod.POST).uri(url).bodyValue(params)
      // 转换返回结果：这个可以自己定义，需要有空构造器的bean即可。
      .retrieve.bodyToMono(classOf[ResultData])
      // 请求成功后的响应
      .doOnNext((resp) => log.debug("发送告警消息 [{}]", resp.toString))
      // 发生错误
      .doOnError((throwable: Throwable) =>
        log.error(throwable.getLocalizedMessage))
      // 异步消费；同步时使用blockOptional()即可
      .subscribe


  }

  def sendGetRequest(url: String, params: BaseRequest) {
    // 请求体
    // val params = new mutable.HashMap[String, _];
    // webclient发送请求
    webClient.method(HttpMethod.GET).uri(url).bodyValue(params)
      // 转换返回结果：这个可以自己定义，需要有空构造器的bean即可。
      .retrieve.bodyToMono(classOf[ResultData])
      // 请求成功后的响应
      .doOnNext((resp) => log.debug("发送告警消息 [{}]", resp.toString))
      // 发生错误
      .doOnError((throwable: Throwable) =>
        log.error(throwable.getLocalizedMessage))
      // 异步消费；同步时使用blockOptional()即可
      .subscribe
  }


}
