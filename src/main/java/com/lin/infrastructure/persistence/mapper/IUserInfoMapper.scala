package com.lin.infrastructure.persistence.mapper


import com.lin.infrastructure.persistence.entity.UserInfoPO
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

/**
 *
 * 用户存储接口
 *
 * @author linzihao
 */
@Repository
trait IUserInfoMapper extends ReactiveCrudRepository[UserInfoPO, Long] {


  @Query("SELECT * FROM user_info WHERE account = :account") def findByAccount(account: String): Mono[UserInfoPO]

  @Query("UPDATE user_info SET nickname = :name WHERE id = :id") def updateNameById(nickname: String, id: Long): Mono[Integer]

}
