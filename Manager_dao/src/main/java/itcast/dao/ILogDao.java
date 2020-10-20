package itcast.dao;

import itcast.domain.sysLog;
import org.apache.ibatis.annotations.Insert;

public interface ILogDao {

    @Insert("insert into syslog(visitTime,username,ip,url,executionTime,method) values(#{visitTime},#{username},#{ip},#{url},#(executionTime},#{method})")
    public void save(sysLog sysLog);
}
