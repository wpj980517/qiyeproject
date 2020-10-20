package itcast.service.impl;

import itcast.dao.ILogDao;
import itcast.domain.sysLog;
import itcast.service.ILogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LogServiceImpl implements ILogService {

    @Autowired
    private ILogDao dao;

    @Override
    public void saveLog(sysLog sysLog) {
        dao.save(sysLog);
    }
}
