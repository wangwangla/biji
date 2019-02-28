package io.jboot.admin.controller.category;

import com.jfinal.plugin.activerecord.Page;

import io.jboot.admin.base.rest.datatable.DataTable;
import io.jboot.admin.base.web.base.BaseController;
import io.jboot.admin.service.api.InstitutionCategoryService;
import io.jboot.admin.service.api.WaregouseCategoryService;
import io.jboot.admin.service.entity.model.InstitutionCategory;
import io.jboot.admin.service.entity.model.WaregouseCategory;
import io.jboot.core.rpc.annotation.JbootrpcService;
import io.jboot.web.controller.annotation.RequestMapping;

@RequestMapping("/category/institutioncategory")
public class InstitutionCategoryController extends BaseController {
	@JbootrpcService
    private InstitutionCategoryService dataService;

    /**
     * index
     */
    public void index() {
        render("main.html");
    }

    /**
     * 表格数据
     */
    public void tableData() {
        int pageNumber = getParaToInt("pageNumber", 1);
        int pageSize = getParaToInt("pageSize", 30);
        Page<InstitutionCategory> dataPage = dataService.findPage(pageNumber, pageSize);
        renderJson(new DataTable<InstitutionCategory>(dataPage));
    }

}
