package com.lin.domain.repository

import com.lin.domain.model.UserInfoDO
import reactor.core.publisher.Mono

/**
 *
 * Repository层的作用是屏蔽所有持久化层的细节,可以同时操作多个数据库
 *
 * @author linzihao
 */
trait IUserRepository extends IRepository[UserInfoDO] {
  def findByAccount(account: String): Mono[UserInfoDO]
}
