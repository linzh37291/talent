package com.lin.facade.api


import com.lin.application.service.IUserService
import com.lin.business.domain.UserInfoDO
import com.lin.facade.converter.{UserAddRequestFactory, UserResponseFactory}
import com.lin.facade.model.request.UserAddRequest
import com.lin.infrastructure.commons.ResultData
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation._

import javax.validation.Valid
import javax.validation.constraints.NotBlank

/**
 * @author linzihao
 */
@RestController
@Validated
class TestController {
  val logger = LoggerFactory.getLogger(this.getClass)

  @Autowired var userServiceImpl: IUserService = _

  @Autowired var userAddRequestTransfer: UserAddRequestFactory = _

  @Autowired var userResponseTransfer: UserResponseFactory = _


  @PostMapping(Array("/test/user/put"))
  def putUser(@Valid @RequestBody request: UserAddRequest) = {
    userServiceImpl.addUserInfo(userAddRequestTransfer.toDO(request))
      .map(userResponseTransfer.toDTO(_))
      .map(ResultData.getSuccessData(_))
  }

  @GetMapping(Array("/test/user/put/{account}"))
  def putName(@PathVariable("account") account: String) = {
    //print("成功請求！！！")
    var info = new UserInfoDO
    info.account = account
    userServiceImpl.addUserInfo(info)
      .map(userResponseTransfer.toDTO(_))
      .map(ResultData.getSuccessData(_))

  }


  @GetMapping(Array("/test/user/add"))
  def add(@NotBlank(message = "账号不能为空") account: String) = {
    //print("成功請求！！！")
    var info = new UserInfoDO
    info.account = account
    userServiceImpl.addUserInfo(info)
      .map(userResponseTransfer.toDTO(_))
      .map(ResultData.getSuccessData(_))
  }

}
