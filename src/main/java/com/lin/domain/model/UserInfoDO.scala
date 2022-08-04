package com.lin.domain.model

import com.lin.domain.assembler.UserAssembler
import com.lin.domain.repository.UserRepository
import com.lin.infrastructure.persistence.entity.{FriendGroupPO, FriendPO}
import com.lin.infrastructure.utils.SpringUtils

import java.time.LocalDate
import reactor.core.publisher.Mono

import scala.beans.BeanProperty




/**
 *
 * Scala的@BeanProperty可以取代Lombok
 * @author linzihao
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

  @BeanProperty var friends: List[FriendPO] = _

  @BeanProperty var friendGroups: List[FriendGroupPO] = _

  var userRepository: UserRepository = SpringUtils.getBean(classOf[UserRepository])

  var userAssembler: UserAssembler = SpringUtils.getBean(classOf[UserAssembler])

  def save: Mono[UserInfoDO] = {
    userRepository.save(userAssembler.toPO(this))
      .map(userAssembler.toDO(_))
  }


}
