package com.lin.business.domain

import com.lin.business.repository.UserRepository
import com.lin.infrastructure.persistence.doc.{FriendDoc, FriendGroupDoc}
import com.lin.infrastructure.utils.SpringUtil

import java.time.LocalDate
import scala.beans.BeanProperty


/**
 * Domain负责业务逻辑，
 * 调用Repository对象来执行数据库操作。Domain没有数据库操作，
 * 具体的数据库操作是通过调用Repository对象完成的。
 *
 * 领域对象是具有属性和行为的对象，是有状态的，数据和行为都是可以重用的。
 *
 * 在很多Java项目中，Service类是无状态的，而且一般是单例，业务逻辑直接写到Service类里面，这种方式本质上是面向过程的编程方式，丢失了面向对象的所有好处，重用性极差。
 *
 * 应用“贫血模型”会把属于对象的数据和行为分离，领域对象不再是一个整体，破坏了领域模型。
 *
 * @Author linzihao
 */
class UserInfoDO extends BaseDO {

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

  @BeanProperty var friends: List[FriendDoc] = _

  @BeanProperty var friendGroups: List[FriendGroupDoc] = _

  var userRepository: UserRepository = SpringUtil.getBean(classOf[UserRepository])

  def save() = {
    userRepository.save(this)
  }


}
