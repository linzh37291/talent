package com.lin.application.service

import com.lin.domain.model.UserInfoDO
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

/**
 * @author linzihao
 */
@Service
class UserServiceImpl extends IUserService {

  override def addUserInfo(userInfoDO: UserInfoDO): Mono[UserInfoDO] = {
    userInfoDO.save
  }

  override def login(userInfoDO: UserInfoDO): Mono[UserInfoDO] = ???

  override def register(userInfoDO: UserInfoDO): Mono[UserInfoDO] = {
    userInfoDO.save
  }
}
