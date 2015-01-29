package com.iservport.template.engine

import java.io.FileWriter

import com.typesafe.config.ConfigFactory

/**
 * Created by mauriciofernandesdecastro on 18/01/15.
 */
trait EngineCommons extends App {

  val conf = ConfigFactory.load("staffMember.conf")

  val controllerName = conf.getString("controllerName")

  def write(fragment:String): Unit = {
    val userHome = conf.getString("user.home")
    val outputPath = conf.getString("outputPath")
    val fw = new FileWriter(userHome+outputPath+controllerName+".txt")
    fw.write(fragment)
    fw.close()
  }

}
