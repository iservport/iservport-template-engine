package com.iservport.template

/**
 * Created by mauriciofernandesdecastro on 14/01/15.
 */
object Engine extends App {

  val controllerName = "report"

  val qualifierTypeDoc = "categoria"

  println(
    s"""
      |	// Qualificadores
      |
      |	/**
      |	 * Lista qualificadores.
      |	 *
      |	 * GET		/app/report/qualifier
      |	 */
      |	@PreAuthorize("hasAuthority('ROLE_USER')")
      |	@RequestMapping(value={"/qualifier"}, method=RequestMethod.GET)
      |	@ResponseBody
      |	public List<QualifierAdapter> qualifier(UserAuthentication userAuthentication) {
      |		return super.qualifier(userAuthentication.getEntityId());
      |	}
      |
      |	// Pastas
      |
      |	/**
      |	 * Lista pastas pastas por $qualifierTypeDoc.
      |	 *
      |	 * GET       /app/$controllerName/folder?categoryId
      |	 */
      |	@PreAuthorize("hasAuthority('ROLE_USER')")
      |	@RequestMapping(value={"/folder"}, method=RequestMethod.GET, params={"qualifierValue"})
      |	@ResponseBody
      |	public Page<FolderReadAdapter> folder(UserAuthentication userAuthentication, @RequestParam Integer qualifierValue, Integer pageNumber) {
      |		return super.folder(userAuthentication.getEntityId(), qualifierValue, pageNumber);
      |	}
      |
      |	/**
      |	 * Nova pasta.
      |	 *
      |	 * POST		/app/$controllerName/folder?qualifierValue
      |	 */
      |	@PreAuthorize("hasAuthority('ROLE_USER')")
      |	@RequestMapping(value={"/folder"}, method=RequestMethod.POST , params={"qualifierValue"})
      |	@ResponseBody
      |	public FolderReadAdapter folderNew(@RequestParam Integer qualifierValue) {
      |		return super.folderNew(qualifierValue);
      |	}
      |
      |	/**
      |	 * Pasta.
      |	 *
      |	 * GET 	/app/$controllerName/folder?folderId
      |	 */
      |	@PreAuthorize("hasAuthority('ROLE_USER')")
      |	@RequestMapping(value={"/folder"}, method=RequestMethod.GET, params={"folderId"})
      |	@ResponseBody
      |	public FolderReadAdapter folderOpen(@RequestParam Integer folderId) {
      |		return super.folderOpen(folderId);
      |	}
      |
      |	/**
      |	 * Atualizar pasta.
      |	 *
      |	 * PUT 	/app/$controllerName/folder
      |	 */
      |	@PreAuthorize("hasAuthority('ROLE_USER')")
      |	@RequestMapping(value={"/folder"}, method=RequestMethod.PUT, consumes="application/json")
      |	@ResponseBody
      |	public FolderReadAdapter folder(@RequestBody FolderReadAdapter command) {
      |		return super.folder(command);
      |	}
      |
      |""".stripMargin)

  val packageName = "com.iservport.indicator"

  val serviceCreateParam = "entityId"

  val domainClassReference = "indicatorFolder"
  val serviceCaption = "pasta de indicadores"
  val serviceCaptionPlural = "pasta de indicadores"
  val serviceCaptionGenderDigit = "a"

  val adapterFields = Seq(
    ("Integer", "entityId", "entity.id")
    , ("Integer", "categoryId", "category.id")
    , ("String", "folderCode", "folderCode")
    , ("String", "folderName", "folderName")
    , ("String", "folderDecorationUrl", "folderDecorationUrl")
    , ("String", "formatterPrefix", "formatterPrefix")
  )

  val domainClass = domainClassReference.capitalize
  val serviceClass = s"${domainClass}ReadAdapter"
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
      |
      |	private ${domainClassReference.capitalize} adaptee;
      |""".stripMargin)

  for ((t,n,f) <- adapterFields) {
    println(
      s"""|	private $t $n;
        |""".stripMargin)
  }
  println(
    """|	/**
      |	 * Construtor.
      |	 *
      |	 * @param id""".stripMargin)
  for ((t,n,f) <- adapterFields) {
    println(
      s"""|	 * @param $n;""".stripMargin)
  }
  println (
    s"""|	 */
      |	public $serviceClass(
      |		Integer id""".stripMargin)
  for ((t,n,f) <- adapterFields) {
    println(
    s"""|		, $t $n""".stripMargin)
  }
  println(
    s"""|		) {
      |		super();
      |		this.id = id;""".stripMargin)
  for ((t,n,f) <- adapterFields) {
    println(
      s"""|		this.$n = $n;""".stripMargin)
  }
  println(
    """|	}
      |""".stripMargin)
  // getters
  println(
    s"""|	/**
      |	 * Adaptee contructor.
      |	 *
      |	 * @param adaptee
      |	 */
      |	public $serviceClass(${domainClassReference.capitalize} $domainClassReference) {
      |		super();
      |		this.adaptee = $domainClassReference;
      |	}
      |
      |	/**
      |	 * Adaptee builder.
      |	 *
      |	 * @param xxx
      |	 */
      |	public $serviceClass build(xxx){
      |		if (adaptee==null) {
      |			throw new SaveEntityException(0,"Null $domainClassReference cannot be persisted.",0);
      |		}
      |		return new $serviceClass(adaptee.getId() """.stripMargin)
  for ((t,n,f) <- adapterFields) {
    println(
      s"""|		, get${f.capitalize}()""".stripMargin)
  }
  println(s"""|		);
  |	}
  |
  |	public ${domainClassReference.capitalize} getAdaptee() {
  |		return adaptee;
  |	}
  |
  |	public Integer getId() {
  |		return id;
  |	}
  |""".stripMargin)
  for ((t,n,f) <- adapterFields) {
    println(
      s"""|	public $t get${n.capitalize}() {
        |		return $n;
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

  println(
    s"""
      |	@Query(
      |			"select new "
      |			+ "$packageName.repository.$serviceClass"
      |			+ "( $domainClassReference.id" """.stripMargin)
  for ((t,n,f) <- adapterFields) {
    println(s"""|			+ ", $domainClassReference.$f" """.stripMargin)
  }
  println(s"""|			+ ") "
      |			+ "from ${domainClassReference.capitalize} $domainClassReference "
      |			+ "where $domainClassReference.id = ?1 "
      |			)
      |""".stripMargin)

  val domainResource = s"${domainClassReference}Resource"

  println(
    s"""
      |		// Métodos do controlador Angular para ${serviceCaptionPlural.capitalize}
      |
      |		$$scope.${domainClassReference}={};
      |		$$scope.$domainResource = $$resource(baseUrl + "${domainClassReference}" ,{reportId:"@reportId"}, {
      |			save: { method: 'PUT' },
      |			create: { method: 'POST' },
      |			get: { method: 'GET', isArray: false },
      |			query: { method: 'GET', isArray: false }
      |		});
      |
      |		$$scope.$serviceCreateParam =0;
      |		$$scope.list${domainClass}s = function($serviceCreateParam) {
      |			$$scope.${domainClassReference}s = $$scope.$domainResource.query({$serviceCreateParam: $serviceCreateParam});
      |			$$scope.$serviceCreateParam = $serviceCreateParam;
      |			$$scope.${domainClassReference}s.$$promise.then(function(data) {
      |				if (data.length===1) {
      |				}
      |			})
      |		};
      |
      |		$$scope.new${domainClass} = function() {
      |			$$scope.followUp = $$scope.$domainResource.create({$serviceCreateParam: $$scope.$serviceCreateParam});
      |		};
      |		$$scope.get${domainClass} = function(id) {
      |			$$scope.message= [];
      |			$$scope.${domainClassReference} = $$scope.$domainResource.get({${domainClassReference}Id: id});
      |		};
      |		$$scope.update${domainClass} = function() {
      |
      |			$$scope.${domainClassReference} = $$scope.$domainResource.save($$scope.${domainClassReference});
      |			$$scope.${domainClassReference}.$$promise.then(
      |					function(data, getReponseHeaders) {
      |						//$$("#modalBody").modal('hide');
      |					},
      |					function(data, getReponseHeaders) {
      |						console.log(data);
      |						if(data.status === 302) {
      |							$$scope.message= data.data;
      |							$$scope.message.exist=true;
      |						}
      |					}
      |			);
      |		};
      |	// FIM dos métodos do controlador angular
      |""".stripMargin)
println(
"""
  |	// FIM da classe
  |""".stripMargin)

}
