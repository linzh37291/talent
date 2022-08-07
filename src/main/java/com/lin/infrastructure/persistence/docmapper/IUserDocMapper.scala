package com.lin.infrastructure.persistence.docmapper

import com.lin.infrastructure.persistence.doc.UserInfoDoc
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
trait IUserDocMapper extends ReactiveMongoRepository[UserInfoDoc, String] {
  def findByAccount(account: String): Mono[UserInfoDoc]
}
