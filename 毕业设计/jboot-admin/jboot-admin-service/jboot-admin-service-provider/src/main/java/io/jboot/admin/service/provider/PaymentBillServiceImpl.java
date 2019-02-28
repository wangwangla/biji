package io.jboot.admin.service.provider;
import io.jboot.admin.service.api.PaymentBillService;
import io.jboot.admin.service.entity.model.PaymentBill;
import io.jboot.aop.annotation.Bean;
import io.jboot.core.rpc.annotation.JbootrpcService;
import io.jboot.service.JbootServiceBase;

import javax.inject.Singleton;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

@Bean
@Singleton
@JbootrpcService
public class PaymentBillServiceImpl extends JbootServiceBase<PaymentBill> implements PaymentBillService {

	@Override
	public Page<? extends Model> paginate(int page, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

}