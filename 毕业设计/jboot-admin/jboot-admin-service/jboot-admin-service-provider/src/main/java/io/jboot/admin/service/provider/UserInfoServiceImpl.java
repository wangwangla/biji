package io.jboot.admin.service.provider;

import io.jboot.admin.service.api.UserInfoService;
import io.jboot.admin.service.entity.model.UserInfo;
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
public class UserInfoServiceImpl extends JbootServiceBase<UserInfo> implements UserInfoService {

	@Override
	public Page<UserInfo> findPage(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		return DAO.paginate(pageNumber, pageSize);
	}

	@Override
	public List<UserInfo> findByName(String name) {
		// TODO Auto-generated method stub
		Columns columns = Columns.create();
	    columns.eq("user_name", name);
	    return DAO.findListByColumns(columns);
		/*return DAO.findAll();*/
	}

	@Override
	public Page<UserInfo> findByStatus(int pageSize, int pageNumber, String name) {
		Columns columns = Columns.create();
		columns.eq("userName", name);
	    return DAO.paginateByColumns(pageNumber, pageSize, columns.getList());	
	}

	@Override
	public boolean hasUser(String loginName) {
		// TODO Auto-generated method stub
		
		return findByName(loginName).size()>0;
	}
	
	public static void main(String[] args) {
		
	}

	@Override
	public Page<? extends Model> paginate(int page, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}
}