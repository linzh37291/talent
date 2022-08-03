package com.nova.felixchat.infrastructure.persistence.mapper

import com.nova.felixchat.infrastructure.persistence.entity.UserInfoPO
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

/**
 * Spring Data JPA
 * 领域特点语言DSL
 *
 * @author linzihao
 */
@Repository
trait IUserMapper extends ReactiveMongoRepository[UserInfoPO, String] {
  def findByAccount(account: String): Mono[UserInfoPO]
}
