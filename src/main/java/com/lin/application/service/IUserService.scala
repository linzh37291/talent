package com.lin.application.service


import com.lin.business.domain.UserInfoDO
import reactor.core.publisher.Mono

/**
 * 应用层定义了软件要完成的任务，并且指挥表达领域概念的对象来解决问题。该层所负责的工作对业务来说意义重大，也是与其他系统的应用层进行交互的必要通道。
 *
 * 应用层要尽量简单。它不包含任务业务规则或知识，只是为了下一层的领域对象协助任务、分配工作。它没有反映业务情况的状态，但它可以具有反映用户或程序的某个任务的进展状态。
 *
 * 应用层主要负责组织整个应用的流程，是面向用例设计的。该层非常适合处理事务，日志和安全等。相对于领域层，应用层应该是很薄的一层。它只是协调领域层对象执行实际的工作。
 *
 * 应用层中主要组件是Service，因为主要职责是协调各组件工作，所以通常会与多个组件交互，如其他Service，Domain、Factory等等。
 *
 * @author linzihao
 */
trait IUserService {
  def login(userInfoDO: UserInfoDO): Mono[UserInfoDO]

  def addUserInfo(userInfoDO: UserInfoDO): Mono[UserInfoDO]

  def register(userInfoDO: UserInfoDO): Mono[UserInfoDO]
}
