package io.jboot.admin.service.provider;


import io.jboot.admin.service.api.InstitutionInfoService;
import io.jboot.admin.service.entity.model.InstitutionInfo;
import io.jboot.aop.annotation.Bean;
import io.jboot.core.rpc.annotation.JbootrpcService;
import io.jboot.db.model.Columns;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import io.jboot.service.JbootServiceBase;

import java.util.List;

import javax.inject.Singleton;

@Bean
@Singleton
@JbootrpcService
public class InstitutionInfoServiceImpl extends JbootServiceBase<InstitutionInfo> implements InstitutionInfoService {

	@Override
	public Page<InstitutionInfo> findPage(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		return DAO.paginate(pageNumber, pageSize);
	}

	@Override
	public List<InstitutionInfo> findByName(String institutionName) {
		// TODO Auto-generated method stub
		 Columns columns = Columns.create();
	     columns.eq("institutionName", institutionName);
	     return DAO.findListByColumns(columns);
	}

	@Override
	public Page<? extends Model> paginate(int page, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

}