package com.iogogogo.util

import java.io.File
import java.util.Map.Entry
import java.util.concurrent.ConcurrentHashMap

import com.typesafe.config.{Config, ConfigFactory, ConfigValue}
import org.apache.commons.lang3.StringUtils
import org.slf4j.{Logger, LoggerFactory}

/**
  * Created by tao.zeng on 2019/1/21.
  */
trait ClientConf[C <: ClientConf[C]] {

  val configs: ConcurrentHashMap[String, Any]
  val logger: Logger = LoggerFactory.getLogger(this.getClass)

  def getConfigs(key: String, fileName: String = "application.properties"): ConcurrentHashMap[String, Any] = {
    val hashMap = new ConcurrentHashMap[String, Any]()
    try {
      val config: Config = loadFromResource(key, fileName)
      for (entry <- config.entrySet().toArray) {
        val obj: Entry[String, ConfigValue] = entry.asInstanceOf[Entry[String, ConfigValue]]
        hashMap.put(obj.getKey, obj.getValue.unwrapped())
      }
    } catch {
      case ex: Exception =>
        logger.warn(ex.getMessage)
    }
    hashMap
  }

  def get[V](key: String): Option[V] = {
    configs.get(key) match {
      case null | None => None
      case x => Some(x.asInstanceOf[V])
    }
  }

  private def loadFromResource(key: String, fileName: String): Config = {
    val file = new File(".", fileName)
    if (file.exists()) {
      loadConfig(file).getConfig(key)
    } else {
      ConfigFactory.load(fileName).getConfig(key)
    }
  }

  def loadConfig(fileName: String): Config = {
    val name: String = if (StringUtils.isEmpty(fileName)) "application.properties" else fileName
    val file = new File(".", name)
    if (file.exists()) {
      loadConfig(file)
    } else {
      ConfigFactory.load(name)
    }
  }

  private def loadConfig(file: File): Config = {
    ConfigFactory.parseFile(file)
  }
}
