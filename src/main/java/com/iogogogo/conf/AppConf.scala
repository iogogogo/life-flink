package com.iogogogo.conf

import java.util.concurrent.ConcurrentHashMap

import com.iogogogo.util.ClientConf

/**
  * Created by tao.zeng on 2019/1/21.
  */
class AppConf extends ClientConf[AppConf] {

  override val configs: ConcurrentHashMap[String, Any] = getConfigs("xxx")

  def host: String = get("name").getOrElse("")

  def password: String = get("password").getOrElse("")
}

object AppConf extends AppConf
