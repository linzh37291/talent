package com.lin.infrastructure.persistence.entity

import java.time.LocalDate
import com.fasterxml.jackson.annotation.JsonFormat
import com.lin.infrastructure.commons.constants.MongoConstant
import org.springframework.data.mongodb.core.mapping.{DBRef, Document, Field, MongoId}

import scala.beans.BeanProperty
/**
 * @author linzihao
 */
@Document(MongoConstant.USER)
class UserInfoPO {


  @MongoId
  @BeanProperty
  var id: String = _

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

  @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
  @BeanProperty
  var birthday: LocalDate = _

  @BeanProperty
  var telephone: String = _

  @Field("real_name")
  @BeanProperty
  var realName: String = _

  @BeanProperty
  var email: String = _

  @BeanProperty
  var intro: String = _

  @Field("head_img")
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

  @Field("school_tag")
  @BeanProperty
  var schoolTag: String = _
  /**
   * 职业
   */
  @BeanProperty
  var vocation: String = _

  @BeanProperty
  var nation: String = _

  @Field("province_code")
  @BeanProperty
  var provinceCode: String = _

  @Field("province_name")
  @BeanProperty
  var provinceName: String = _

  @Field("city_code")
  @BeanProperty
  var cityCode: String = _

  @Field("city_name")
  @BeanProperty
  var cityName: String = _

  @Field("area_code")
  @BeanProperty
  var areaCode: String = _

  @Field("area_name")
  @BeanProperty
  var areaName: String = _

  @Field("street_code")
  @BeanProperty
  var streetCode: String = _

  @Field("street_name")
  @BeanProperty
  var streetName: String = _

  @Field("user_state")
  @BeanProperty
  var userState: String = _
  /**
   * 好友策略ID
   */
  @Field("friendship_policy")
  @BeanProperty
  var friendshipPolicy: String = _
  /**
   * 好友策略问题
   */
  @Field("friendship_policy_question")
  @BeanProperty
  var friendshipPolicyQuestion: String = _
  /**
   * 好友策略答案
   */
  @Field("friendship_policy_answer")
  @BeanProperty
  var friendshipPolicyAnswer: String = _
  /**
   * 好友策略密码
   */
  @Field("friendship_policy_password")
  @BeanProperty
  var friendshipPolicyPassword: String = _

  @DBRef
  @BeanProperty
  var friends: List[FriendPO] = _

  @DBRef
  @BeanProperty
  var friendGroups: List[FriendGroupPO] = _

}
