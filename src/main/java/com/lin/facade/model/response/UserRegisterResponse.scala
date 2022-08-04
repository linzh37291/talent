package com.lin.facade.model.response

import java.time.LocalDate
import scala.beans.BeanProperty

/**
 *
 * Scala的注解BeanProperty可以取代Lombok
 *
 * @author linzihao
 */
class UserRegisterResponse {
  @BeanProperty var id: Long = _

  @BeanProperty var account: String = _

  @BeanProperty var password: String = _

  @BeanProperty var nickname: String = _

  @BeanProperty var signature: String = _

  @BeanProperty var sex: Int = _

  @BeanProperty var birthday: LocalDate = _

  @BeanProperty var telephone: String = _

  @BeanProperty var realName: String = _

  @BeanProperty var email: String = _

  @BeanProperty var intro: String = _

  @BeanProperty var headImg: String = _

  @BeanProperty var shengxiao: String = _

  @BeanProperty var age: Int = _
  /**
   * 星座
   */
  @BeanProperty var constellation: String = _

  @BeanProperty var schoolTag: String = _
  /**
   * 职业
   */
  @BeanProperty var vocation: String = _

  @BeanProperty var nation: String = _

  @BeanProperty var provinceCode: String = _

  @BeanProperty var provinceName: String = _

  @BeanProperty var cityCode: String = _

  @BeanProperty var cityName: String = _

  @BeanProperty var areaCode: String = _

  @BeanProperty var areaName: String = _

  @BeanProperty var streetCode: String = _

  @BeanProperty var streetName: String = _

  @BeanProperty var userState: String = _

  @BeanProperty var friendshipPolicy: String = _

  @BeanProperty var friendshipPolicyQuestion: String = _

  @BeanProperty var friendshipPolicyAnswer: String = _

  @BeanProperty var friendshipPolicyPassword: String = _
}
