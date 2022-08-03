package com.lin.application.service


import com.lin.domain.model.UserInfoDO
import reactor.core.publisher.Mono

/**
 * @author linzihao
 */
trait IUserService {
  def login(userInfo: UserInfoDO): Mono[UserInfoDO]

  def addUserInfo(userInfoDomain: UserInfoDO): Mono[UserInfoDO]
}
