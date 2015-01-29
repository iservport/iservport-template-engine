package com.iservport.template.engine
/**
 * Created by mauriciofernandesdecastro on 29/01/15.
 */
object LangJs extends App {

//  val lines = fromFile("file.txt").getLines

  val sourceText =
    """
      |<#assign instrumentResult_1="Resultado do controle de instrumentos" />
      |<#assign instrumentResult_2="Resultados do controle de instrumentos" />
      |<#assign instrumentResult_s="Resultado" />
      |
      |<#assign instrumentResult_internalNumber="Número" />
      |<#assign instrumentResult_complete="Progresso" />
      |<#assign instrumentResult_nextCheckDate="Próxima verificação" />
      |
      |<#assign instrumentResult_followUpDesc="Análise" />
      |<#assign instrumentResult_instrumentControl="Controle" />
      |<#assign instrumentResult_issueDate="Data de emissão" />
      |<#assign instrumentResult_partner="Origem" />
      |<#assign instrumentResult_documentReference="Documento" />
      |<#assign instrumentResult_owner="Responsável" />
      |<#assign instrumentResult_resolution="Situação" />
      |
      |
    """.stripMargin

  val ptrn = "<#assign (.*)_(.*)=(.*) />".r

  for (l <- sourceText.split("\\n")) {
    l match {
      case ptrn(x, "1", z) => println("ONE=" + z)
      case ptrn(x, "2", z) => println("TWO=" + z)
      case ptrn(x, y, z) => println(y.toUpperCase + "=" + z)
      case _ =>
    }

  }

}
