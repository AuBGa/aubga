package com.aubga.taobao.tbschedule;

import org.slf4j.Logger;

Component("iScheduleTaskDealSingleTest")
public class IScheduleTaskDealSingleTest implements IScheduleTaskDealSingle<TaskModel> {
 
    private static final Logger LOG = LoggerFactory.getLogger(IScheduleTaskDealSingleTest.class);
 
    @Override
    public Comparator<TaskModel> getComparator() {
        return null;
    }
 
    @Override
    public List<TaskModel> selectTasks(String taskParameter, String ownSign, int taskQueueNum,
            List<TaskItemDefine> taskItemList, int eachFetchDataNum) throws Exception {
 
        LOG.info("IScheduleTaskDealSingleTest配置的参数，taskParameter:{}，ownSina:{}，taskQueueNum:{},taskItemList:{}, eachFetchDataNum:{}", taskParameter, ownSign, taskQueueNum, taskItemList, eachFetchDataNum);
 
        LOG.info("IScheduleTaskDealSingleTest选择任务列表开始啦..........");
        List<TaskModel> models = new ArrayList<TaskModel>();
        models.add(new TaskModel(String.valueOf(System.currentTimeMillis()), "taosirTest1"));
        models.add(new TaskModel(String.valueOf(System.currentTimeMillis()), "taosirTest2"));
 
        return models;
 
    }
 
    @Override
    public boolean execute(TaskModel model, String ownSign) throws Exception {
 
        LOG.info("IScheduleTaskDealSingleTest执行开始啦.........." + new Date());
        System.out.println(model);
        return true;
 
    }
 
}
