package com.lin.infrastructure.persistence.entity


import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

import scala.beans.BeanProperty

/**
 *
 * 用户数据表user_info
 *
 * @author linzihao
 */
@Table("user_info")
class UserInfoPO {


  @Id
  @BeanProperty
  var id: Int = _

  @BeanProperty
  var account: String = _

  @BeanProperty
  var password: String = _

  @BeanProperty
  var nickname: String = _

  @BeanProperty
  var signature: String = _

  @BeanProperty
  var sex: Int = _


  @BeanProperty
  var telephone: String = _

  //@Field("real_name")
  @BeanProperty
  var realName: String = _

  @BeanProperty
  var email: String = _

  @BeanProperty
  var intro: String = _

  //@Field("head_img")
  @BeanProperty
  var headImg: String = _

  @BeanProperty
  var shengxiao: String = _

  @BeanProperty
  var age: Int = _
  /**
   * 星座
   */
  @BeanProperty
  var constellation: String = _

  //@Field("school_tag")
  @BeanProperty
  var schoolTag: String = _
  /**
   * 职业
   */
  @BeanProperty
  var vocation: String = _

  @BeanProperty
  var nation: String = _

  //@Field("province_code")
  @BeanProperty
  var provinceCode: String = _


  @BeanProperty
  var provinceName: String = _


  @BeanProperty
  var cityCode: String = _


  @BeanProperty
  var cityName: String = _


  @BeanProperty
  var areaCode: String = _


  @BeanProperty
  var areaName: String = _


  @BeanProperty
  var streetCode: String = _


  @BeanProperty
  var streetName: String = _


  @BeanProperty
  var userState: String = _


}
