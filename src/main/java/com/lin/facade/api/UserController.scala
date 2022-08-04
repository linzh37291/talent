package com.lin.facade.api

import com.lin.application.service.IUserService
import com.lin.domain.model.UserInfoDO
import com.lin.facade.model.factory.{UserAddRequestFactory, UserResponseFactory}
import com.lin.facade.model.request.UserLoginRequest
import com.lin.infrastructure.utils.{BeanUtils, JsonUtil}
import lombok.extern.slf4j.Slf4j

import javax.validation.Valid
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation._


/**
 *
 * Scala的@BeanProperty可以取代Lombok
 * @author linzihao
 */
@RestController
@Validated
class UserController {
  val logger = LoggerFactory.getLogger(this.getClass)
  @Autowired var userServiceImpl: IUserService = _

  @Autowired var userAddRequestTransfer: UserAddRequestFactory = _

  @Autowired var userResponseTransfer: UserResponseFactory = _

  @Autowired var userService: IUserService = _

  /**
   * （1）登录接口
   */
  @PostMapping(Array("/login")) def login(@RequestBody @Valid userLoginRequest: UserLoginRequest) = {
    logger.info("登录信息输入参数：[{}]", JsonUtil.toJson(userLoginRequest))
    val userInfo = new UserInfoDO
    BeanUtils.copyProperties(userLoginRequest, userInfo)
    //userService.login(userInfo).map(ResultData.getSuccessData(_)).onErrorReturn(ResultData.getFailResult(_))
  }

  //  /**
  //   * （2）登出接口
  //   */
  //  @GetMapping(Array("/logout/{user_id}")) def logout(@PathVariable("user_id") userId: Long): ResultData[_] = {
  //    val resultData: ResultData[_] = new ResultData[_]
  //    if (userService.logout(userId)) {
  //      resultData.setCode("0000")
  //      resultData.setMsg("登出成功")
  //    }
  //    else {
  //      resultData.setCode("1000")
  //      resultData.setMsg("登出失败")
  //    }
  //    return resultData
  //  }
  //
//    /**
//     * （3）注册接口
//     */
//    @PostMapping(Array("/register")) def register(@RequestBody @Valid userRegisterRequest: UserRegisterRequest): ResultData[_] = {
//      val resultData: ResultData[_] = new ResultData[_]
//      val userInfoDO = new UserInfoDO
//      BeanUtils.copyProperties(userRegisterRequest, userInfoDO)
//      //调用用户注册接口
//      val userRegisterRes: UserRegisterRequest = userService.register(userInfoDO)
//      if (userRegisterRes != null) {
//        val userRegisterResponse: UserRegisterResponse = new UserRegisterResponse
//        userRegisterResponse.setUser_id(userRegisterRes.getUserId)
//        userRegisterResponse.setStatus(userRegisterRes.getState)
//        resultData.setCode("0000")
//        resultData.setMsg("注册成功")
//        resultData.setData(userRegisterRes)
//      }
//      else {
//        resultData.setCode("1000")
//        resultData.setMsg("注册失败")
//      }
//      return resultData
//    }


}
