package io.jboot.admin.service.provider;


import io.jboot.admin.service.api.InstitutionCategoryService;
import io.jboot.admin.service.entity.model.InstitutionCategory;
import io.jboot.admin.service.entity.model.StaffCategory;
import io.jboot.aop.annotation.Bean;
import io.jboot.core.rpc.annotation.JbootrpcService;
import io.jboot.db.model.Columns;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import io.jboot.service.JbootServiceBase;

import javax.inject.Singleton;

@Bean
@Singleton
@JbootrpcService
public class InstitutionCategoryServiceImpl extends JbootServiceBase<InstitutionCategory> implements InstitutionCategoryService {

	@Override
	public Page<InstitutionCategory> findPage(int pageNumber, int pageSize) {
		 Columns columns = Columns.create();
	     return DAO.paginateByColumns(pageNumber, pageSize, columns.getList());
	}

	@Override
	public Page<? extends Model> paginate(int page, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}
}