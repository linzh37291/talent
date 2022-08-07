package com.lin.facade.model.response

import com.fasterxml.jackson.annotation.JsonProperty
import com.lin.infrastructure.persistence.doc.AddressDoc

import scala.beans.BeanProperty

/**
 *
 * Scala的@BeanProperty可以取代Lombok
 *
 * @author linzihao
 */
class UserResponse extends BaseResponse with Serializable {

  private val serialVersionUID: Long = 1L

  @BeanProperty var account: String = _

  @BeanProperty var nickname: String = _

  @BeanProperty var signature: String = _

  @BeanProperty var sex: Int = _

  @BeanProperty var birthday: String = _

  @BeanProperty var telephone: String = _

  @BeanProperty var name: String = _

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

  @BeanProperty var nation: AddressDoc = _

  @BeanProperty var province: AddressDoc = _

  @BeanProperty var city: AddressDoc = _

  @BeanProperty var area: AddressDoc = _

  @BeanProperty var street: AddressDoc = _
}
