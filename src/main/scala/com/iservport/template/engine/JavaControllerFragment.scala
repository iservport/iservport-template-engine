package com.iservport.template.engine

/**
 * Created by mauriciofernandesdecastro on 18/01/15.
 */
object JavaControllerFragment extends EngineCommons {

  import scala.collection.JavaConversions._

  val packageName = conf.getString("packageName")

  val serviceCreateParam = conf.getString("serviceCreateParam")

  val domainClassReference = conf.getString("domainClassReference")
  val serviceCaption = conf.getString("serviceCaption")
  val serviceCaptionPlural = conf.getString("serviceCaptionPlural")
  val serviceCaptionGenderDigit = conf.getString("serviceCaptionGenderDigit")

  val adapterFields = conf.getStringList("adapterFields")

  val serviceClass = s"${domainClassReference.capitalize}ReadAdapter"
  val serviceReadParam = s"${domainClassReference}Id"

  println(
    s"""
      |	// ${serviceCaptionPlural.capitalize}
      |
      |	/**
      |	 * Lista $serviceCaptionPlural.
      |	 *
      |	 * GET		/app/$controllerName/$domainClassReference?$serviceCreateParam
      |	 */
      |	@PreAuthorize("hasAuthority('ROLE_USER')")
      |	@RequestMapping(value={"/$domainClassReference"}, method=RequestMethod.GET , params={"$serviceCreateParam"})
      |	@ResponseBody
      |	public Page<$serviceClass> ${domainClassReference}(@RequestParam Integer $serviceCreateParam
      |			, @RequestParam(required = false, defaultValue = "0") Integer pageNumber) {
      |		return super.${domainClassReference}($serviceCreateParam, pageNumber);
      |	}
      |
      |	/**
      |	 * Nov$serviceCaptionGenderDigit $serviceCaption.
      |	 *
      |	 * POST		/app/$controllerName/$domainClassReference?$serviceCreateParam
      |	 */
      |	@PreAuthorize("hasAuthority('ROLE_USER')")
      |	@RequestMapping(value={"/$domainClassReference"}, method=RequestMethod.POST , params={"$serviceCreateParam"})
      |	@ResponseBody
      |	public $serviceClass ${domainClassReference}New(@RequestParam Integer $serviceCreateParam) {
      |		return super.${domainClassReference}New($serviceCreateParam);
      |	}
      |
      |	/**
      |	 * ${serviceCaption.capitalize}.
      |	 *
      |	 * GET 	/app/$controllerName/$domainClassReference?$serviceReadParam
      |	 */
      |	@PreAuthorize("hasAuthority('ROLE_USER')")
      |	@RequestMapping(value={"/$domainClassReference"}, method=RequestMethod.GET, params={"$serviceReadParam"})
      |	@ResponseBody
      |	public $serviceClass ${domainClassReference}Open(@RequestParam Integer $serviceReadParam) {
      |		return super.${domainClassReference}Open($serviceReadParam);
      |	}
      |
      |	/**
      |	 * Atualizar $serviceCaption.
      |	 *
      |	 * PUT 	/app/$controllerName/$domainClassReference
      |	 */
      |	@PreAuthorize("hasAuthority('ROLE_USER')")
      |	@RequestMapping(value={"/$domainClassReference"}, method=RequestMethod.PUT, consumes="application/json")
      |	@ResponseBody
      |	public $serviceClass $domainClassReference(@RequestBody $serviceClass command) {
      |		return super.$domainClassReference(command);
      |	}
      |
      |""".stripMargin)

  // Classe de serviço

  println(
    s"""
      |package $packageName.repository;
      |
      |import java.io.Serializable;
      |import java.util.Date;
      |import java.util.List;
      |
      |/**
      | * Adaptador para leitura de $serviceCaptionPlural.
      | *
      | * @author mauriciofernandesdecastro
      | */
      |public class $serviceClass
      |	implements Serializable
      |{
      |
      |	private static final long serialVersionUID = 1L;
      |
      |	private Integer id;
      |""".stripMargin)

  for (a:String <- adapterFields; b <- a.split(",")) {
    println(
      s"""|	private ${b(0)} ${b(1)};
        |""".stripMargin)
  }
  println(
    """|	/**
      |	 * Construtor.
      |	 *
      |	 * @param id""".stripMargin)
  for (a:String <- adapterFields; b <- a.split(",")) {
    println(
      s"""|	 * @param ${b(1)};""".stripMargin)
  }
  println (
    s"""|	 */
      |	public $serviceClass(
      |		Integer id""".stripMargin)
  for (a:String <- adapterFields; b <- a.split(",")) {
    println(
      s"""|		, ${b(0)} ${b(1)}""".stripMargin)
  }
  println(
    s"""|		) {
      |		super();
      |		this.id = id;""".stripMargin)
  for (a:String <- adapterFields; b <- a.split(",")) {
    println(
      s"""|		this.${b(1)} = ${b(1)};""".stripMargin)
  }
  println(
    """|	}
      |""".stripMargin)
  // getters
  println(
    """|	public Integer getId() {
      |		return id;
      |	}
      |""".stripMargin)
  for (a:String <- adapterFields; b:String <- a.split(",")) {
    println(
      s"""|	public ${b(0)} get${b(1)}() {
        |		return ${b(1)};
        |	}
        |""".stripMargin)
  }
  // encerra classe
  println(
    s"""|	@Override
      |	public int hashCode() {
      |		return 31 + ((id == null) ? 0 : id.hashCode());
      |	}
      |
      |	@Override
      |	public boolean equals(Object obj) {
      |		if (this == obj) return true;
      |		if (obj == null) return false;
      |		if (getClass() != obj.getClass()) return false;
      |		$serviceClass other = ($serviceClass) obj;
      |		if (id == null) {
      |			if (other.id != null) return false;
      |		} else if (!id.equals(other.id)) return false;
      |		return true;
      |	}
      |
      |}""".stripMargin)

  println(
    """
      |	// FIM da classe
      |""".stripMargin)
}
