package io.jboot.admin.service.provider;

import io.jboot.admin.service.api.WaregouseCategoryService;
import io.jboot.admin.service.entity.model.WaregouseCategory;
import io.jboot.aop.annotation.Bean;
import io.jboot.core.rpc.annotation.JbootrpcService;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import io.jboot.service.JbootServiceBase;

import javax.inject.Singleton;
@Bean
@Singleton
@JbootrpcService
public class WaregouseCategoryServiceImpl extends JbootServiceBase<WaregouseCategory> implements WaregouseCategoryService {

	@Override
	public Page<WaregouseCategory> findPage(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		return DAO.paginate(pageNumber, pageSize);
	}

	@Override
	public Page<? extends Model> paginate(int page, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

}