package com.lin.domain.repository

import com.lin.infrastructure.persistence.entity.UserInfoPO
import reactor.core.publisher.Mono

/**
 *
 * @author linzihao
 */
trait IUserRepository extends IRepository[UserInfoPO] {
  def findByAccount(account: String): Mono[UserInfoPO]
}
