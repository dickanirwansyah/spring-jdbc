package com.test.demotransactional.services;

import com.test.demotransactional.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServices {

    private static final Logger log = LoggerFactory.getLogger(UserServices.class);

    @Autowired
    private JdbcTemplate jdbc;

    @Transactional
    public void insertUsers(List<User> users){
        for (User user : users){
            jdbc.update("insert into user(name, dept, salary) values (?, ?, ?)",
                    preparedStatement -> {
                        preparedStatement.setString(1, user.getName());
                        preparedStatement.setString(2, user.getDept());
                        preparedStatement.setLong(3, user.getSalary());
                    });
            log.info(String.format("insert : | %s | %s | %s | ", user.getName(), user.getDept(), user.getSalary()));
        }
    }


    public List<User> listUsers(){
        List<User> userList =  jdbc.query("select name, dept, salary from user", (resultSet, i) -> new User(
                resultSet.getString("name"),
                resultSet.getString("dept"),
                resultSet.getLong("salary")));
        log.info("list : "+userList);
        return userList;
    }

}
