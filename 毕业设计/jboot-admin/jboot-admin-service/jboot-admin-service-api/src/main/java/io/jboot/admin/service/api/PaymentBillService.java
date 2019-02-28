package io.jboot.admin.service.api;


import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

import io.jboot.admin.service.entity.model.PaymentBill;

import java.util.List;

public interface PaymentBillService  {

    /**
     * find model by primary key
     *
     * @param id
     * @return
     */
    public PaymentBill findById(Object id);


    /**
     * find all model
     *
     * @return all <PaymentBill
     */
    public List<PaymentBill> findAll();


    /**
     * delete model by primary key
     *
     * @param id
     * @return success
     */
    public boolean deleteById(Object id);


    /**
     * delete model
     *
     * @param model
     * @return
     */
    public boolean delete(PaymentBill model);


    /**
     * save model to database
     *
     * @param model
     * @return
     */
    public boolean save(PaymentBill model);


    /**
     * save or update model
     *
     * @param model
     * @return if save or update success
     */
    public boolean saveOrUpdate(PaymentBill model);


    /**
     * update data model
     *
     * @param model
     * @return
     */
    public boolean update(PaymentBill model);


    /**
     * 分页
     *
     * @param page
     * @param pageSize
     * @return
     */
    public Page<? extends Model> paginate(int page, int pageSize);


    public void join(Page<? extends Model> page, String joinOnField);

    public void join(Page<? extends Model> page, String joinOnField, String[] attrs);

    public void join(Page<? extends Model> page, String joinOnField, String joinName);

    public void join(Page<? extends Model> page, String joinOnField, String joinName, String[] attrs);

    public void join(List<? extends Model> models, String joinOnField);

    public void join(List<? extends Model> models, String joinOnField, String[] attrs);

    public void join(List<? extends Model> models, String joinOnField, String joinName);

    public void join(List<? extends Model> models, String joinOnField, String joinName, String[] attrs);

    public void join(Model model, String joinOnField);

    public void join(Model model, String joinOnField, String[] attrs);

    public void join(Model model, String joinOnField, String joinName);

    public void join(Model model, String joinOnField, String joinName, String[] attrs);

    public void keep(Model model, String... attrs);

    public void keep(List<? extends Model> models, String... attrs);

}