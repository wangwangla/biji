package io.jboot.admin.service.provider;


import io.jboot.admin.service.api.WaregouseOutService;
import io.jboot.admin.service.entity.model.WaregouseOut;
import io.jboot.aop.annotation.Bean;
import io.jboot.core.rpc.annotation.JbootrpcService;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import io.jboot.service.JbootServiceBase;

import java.util.List;

import javax.inject.Singleton;

@Bean
@Singleton
@JbootrpcService
public class WaregouseOutServiceImpl extends JbootServiceBase<WaregouseOut> implements WaregouseOutService {

	@Override
	public Page<WaregouseOut> findPage(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<WaregouseOut> findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<? extends Model> paginate(int page, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

}