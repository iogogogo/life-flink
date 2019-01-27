package com.iogogogo.conf

import java.util.concurrent.ConcurrentHashMap

import com.iogogogo.util.ClientConf

/**
  * Created by tao.zeng on 2019/1/27.
  */
class MySQLConf extends ClientConf[MySQLConf] {
  override val configs: ConcurrentHashMap[String, Any] = getConfigs("mysql")

  def url(): String = get[String]("url").getOrElse("")

  def username(): String = get[String]("username").getOrElse("")

  def password(): String = get[String]("password").getOrElse("")
}

object MySQLConf extends MySQLConf
