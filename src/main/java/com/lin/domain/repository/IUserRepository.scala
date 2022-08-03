package com.nova.felixchat.domain.repository

import com.nova.felixchat.infrastructure.persistence.entity.UserInfoPO
import reactor.core.publisher.Mono

/**
 *
 * @author linzihao
 */
trait IUserRepository extends IRepository[UserInfoPO] {
  def findByAccount(account: String): Mono[UserInfoPO]
}
