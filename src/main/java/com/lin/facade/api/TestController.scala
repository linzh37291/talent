package com.nova.felixchat.facade.api

import com.nova.felixchat.application.service.IUserService
import com.nova.felixchat.domain.model.UserInfoDO
import com.nova.felixchat.facade.model.factory.impl.{UserAddRequestFactory, UserResponseFactory}
import com.nova.felixchat.facade.model.request.UserAddRequest
import com.nova.felixchat.infrastructure.commons.ResultData
import javax.validation.Valid
import javax.validation.constraints.NotBlank
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation._

@RestController
@Validated
class TestController {

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
    var info = new UserInfoDO
    info.account = account
    userServiceImpl.addUserInfo(info)
      .map(userResponseTransfer.toDTO(_))
      .map(ResultData.getSuccessData(_))

  }


  @GetMapping(Array("/test/user/add"))
  def add(@NotBlank(message = "账号不能为空") account: String) = {
    var info = new UserInfoDO
    info.account = account
    userServiceImpl.addUserInfo(info)
      .map(userResponseTransfer.toDTO(_))
      .map(ResultData.getSuccessData(_))
  }

}
