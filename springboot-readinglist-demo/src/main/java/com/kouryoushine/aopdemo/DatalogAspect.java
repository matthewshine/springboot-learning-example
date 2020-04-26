package com.kouryoushine.aopdemo;

import org.apache.commons.beanutils.PropertyUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

@Aspect
//@Component
public class DatalogAspect {

    @Autowired
    private ActionDao actionDao;



    //切入点：aopdemo报下所有对象的save方法
    @Pointcut("execution(public * com.kouryoushine.aopdemo.*.save*(..))")
    public void save(){

    }

    //切入点：aopdemo报下所有对象的delete方法
    @Pointcut("execution(public * com.kouryoushine.aopdemo.*.delete*(..))")
    public void delete(){

    }

    /**
     * 需要在update操作前后分别获取更新前后的值
     * @param pjp
     * @return
     */


    @Around("save() ||delete()")
    public Object addOperLog(ProceedingJoinPoint pjp) throws  Throwable{
        System.out.println("enter aspect");
        Class<?> cls = pjp.getTarget().getClass();
        Object service = pjp.getTarget();
        String methodName = pjp.getSignature().getName(); //获取方法名称
        Object[] args = pjp.getArgs();
        Object returnobj =null;
        Object oldObj =null;
        //记录operator time
        Date operateTime = new Date();
        ActionType actionType=null;
        Long id=null;
        Action action = new Action();
        try {
            if("save".equals(methodName)){
                //insert or  update
                Object obj = pjp.getArgs()[0];
                id= Long.valueOf(PropertyUtils.getProperty(obj,"id").toString());
                if(id==null){
                    actionType= ActionType.INSERT;
                    List<ChangeItem> changeItems = DiffUtil.getInsertChangeItems(obj);
                    action.getChanges().addAll(changeItems);
                    action.setObjectClass(obj.getClass().getName());
                }else{
                    actionType = ActionType.UPDATE; //新旧值对比
                    oldObj =DiffUtil.getObjectById(pjp.getTarget(),id);
                    action.setObjectClass(oldObj.getClass().getName());
                    action.setObjectId(id);
                }
            }else  if("delete".equals(methodName)){
                //delete
                actionType= ActionType.DELETE;
                oldObj =DiffUtil.getObjectById(pjp.getTarget(),id);
                ChangeItem changeItem = DiffUtil.getDeleteChangeItem(oldObj);
                action.getChanges().add(changeItem);
                action.setObjectId(Long.valueOf(pjp.getArgs()[0].toString()));
                action.setObjectClass(oldObj.getClass().getName());
            }

            action.setOperator("admin");
            action.setOperateTime(new Date());
//            actionDao.save(action);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

                //TO After ACTION

            returnobj = pjp.proceed(pjp.getArgs());

        action.setActionType(actionType);
        if(ActionType.INSERT==actionType){
            Object newId  = PropertyUtils.getProperty(returnobj,"id");
            action.setObjectId(Long.valueOf(newId.toString()));
        }else if(ActionType.UPDATE==actionType){
            Object newobj = DiffUtil.getObjectById(pjp,id);
            List<ChangeItem> changeItems = DiffUtil.getChangeItems(oldObj,newobj);
            action.getChanges().addAll(changeItems);
        }

        return  null;
    }

}
