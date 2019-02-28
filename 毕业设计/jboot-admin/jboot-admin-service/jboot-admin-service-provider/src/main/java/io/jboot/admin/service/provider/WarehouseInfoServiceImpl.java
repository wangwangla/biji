package io.jboot.admin.service.provider;

import io.jboot.admin.service.api.WarehouseInfoService;
import io.jboot.admin.service.entity.model.WarehouseInfo;
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
public class WarehouseInfoServiceImpl extends JbootServiceBase<WarehouseInfo> implements WarehouseInfoService {

	@Override
	public Page<WarehouseInfo> findPage(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<WarehouseInfo> findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<? extends Model> paginate(int page, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

}