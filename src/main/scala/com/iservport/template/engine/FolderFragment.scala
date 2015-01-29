package com.iservport.template.engine

/**
 * Created by mauriciofernandesdecastro on 15/01/15.
 */
object FolderFragment extends EngineCommons {

  val qualifierTypeDoc = conf.getString("qualifierTypeDoc")

  val qualifierFrag =
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
      |	public List<FolderReadAdapter> folder(UserAuthentication userAuthentication, @RequestParam Integer qualifierValue, Integer pageNumber) {
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
      |""".stripMargin

  println(qualifierFrag)
  write(qualifierFrag)

}
