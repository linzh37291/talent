package com.nova.felixchat.application.service

import com.nova.felixchat.domain.model.UserInfoDO
import reactor.core.publisher.Mono


trait IUserService {
  def login(userInfo: UserInfoDO): Mono[UserInfoDO]

  def addUserInfo(userInfoDomain: UserInfoDO): Mono[UserInfoDO]
}
