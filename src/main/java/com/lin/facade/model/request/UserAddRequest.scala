package com.lin.facade.model.request

import com.fasterxml.jackson.annotation.JsonProperty
import com.lin.infrastructure.persistence.entity.AddressPO

import javax.validation.constraints.NotBlank
import scala.beans.BeanProperty

/**
 * 新增用户请求参数
 * Scala的@BeanProperty可以取代Lombok
 * @author linzihao
 */
class UserAddRequest extends BaseRequest {

  @NotBlank(message = "账号不能为空")
  @BeanProperty var account: String = _

  @BeanProperty var password: String = _

  @BeanProperty var nickname: String = _

  @BeanProperty var signature: String = _

  @BeanProperty var sex: Int = _

  @BeanProperty var birthday: String = _

  @BeanProperty var telephone: String = _

  @JsonProperty("real_name")
  @BeanProperty var realName: String = _

  @BeanProperty var email: String = _

  @BeanProperty var intro: String = _

  @JsonProperty("head_img")
  @BeanProperty var headImg: String = _

  @BeanProperty var shengxiao: String = _

  @BeanProperty var age: Int = _
  /**
   * 星座
   */
  @BeanProperty var constellation: String = _

  @JsonProperty("school_tag")
  @BeanProperty var schoolTag: String = _
  /**
   * 职业
   */
  @BeanProperty var vocation: String = _

  @BeanProperty var nation: AddressPO = _

  @BeanProperty var province: AddressPO = _

  @BeanProperty var city: AddressPO = _

  @BeanProperty var area: AddressPO = _

  @BeanProperty var street: AddressPO = _
}
