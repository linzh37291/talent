package com.nova.felixchat.facade.model.response

import com.fasterxml.jackson.annotation.JsonProperty
import com.nova.felixchat.infrastructure.persistence.entity.AddressPO

import scala.beans.BeanProperty


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

  @BeanProperty var nation: AddressPO = _

  @BeanProperty var province: AddressPO = _

  @BeanProperty var city: AddressPO = _

  @BeanProperty var area: AddressPO = _

  @BeanProperty var street: AddressPO = _
}
