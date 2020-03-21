package com.baize.job.service.impl;

import com.baize.job.dao.JobInfoDao;
import com.baize.job.pojo.JobInfo;
import com.baize.job.service.JobInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class JobInfoServiceImpl implements JobInfoService {

    @Autowired
    private JobInfoDao jobInfoDao;

    @Override
    @Transactional
    public void save(JobInfo jobInfo) {
        //查询原有的数据
        //根据url和发布时间查询数据 判断查询结果是否为空，如果为空表示招聘信息不存在或者已经更新，需要更新或新增数据库
        JobInfo param = new JobInfo();
        param.setUrl(jobInfo.getUrl());
        param.setTime(jobInfo.getTime());
        //判断数据库中是否有已存在的数据
        List<JobInfo> list = this.findJobInfo(param);
        //已存在执行更新
        if (list.size() == 0){
            this.jobInfoDao.saveAndFlush(jobInfo);
        }

            //不存在执行新增
    }

    @Override
    public List<JobInfo> findJobInfo(JobInfo jobInfo) {
        //设置查询条件
        Example example = Example.of(jobInfo);
        //执行查询
        List list = this.jobInfoDao.findAll(example);
        return list;
    }
}
