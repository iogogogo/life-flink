package com.iogogogo.conf

import java.util.concurrent.ConcurrentHashMap

import com.iogogogo.util.ClientConf
import com.typesafe.config.Config

/**
  * Created by tao.zeng on 2019/1/21.
  */
class AppConf extends ClientConf[AppConf] {

  override val configs: ConcurrentHashMap[String, Any] = getConfigs("xxx")

  def configList: Config = loadConfig(null)

  def host: String = get("name").getOrElse("")

  def password: String = get("password").getOrElse("")
}

object AppConf extends AppConf
