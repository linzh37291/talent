package com.lin.application.service


import com.lin.domain.model.UserInfoDO
import reactor.core.publisher.Mono

/**
 * @author linzihao
 */
trait IUserService {
  def login(userInfoDO: UserInfoDO): Mono[UserInfoDO]

  def addUserInfo(userInfoDO: UserInfoDO): Mono[UserInfoDO]

  def register(userInfoDO: UserInfoDO): Mono[UserInfoDO]
}
