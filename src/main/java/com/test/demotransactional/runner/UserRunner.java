package com.test.demotransactional.runner;

import com.test.demotransactional.model.User;
import com.test.demotransactional.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

//@Component
public class UserRunner implements CommandLineRunner {

    @Autowired
    private UserServices userServices;

    @Override
    public void run(String... args) throws Exception {

        //database transactional :
        //adalah sebuah konsep dari teknik penyimpanan,
        //dimana ketika proses pertama berhasil, yang berikutnya harus berhasil.
        //jika proses pertama gagal, maka yang berikutnya juga harus gagal atau di rollback
        
        try{

            User user1 = new User("dicka", "senior java developer", 10000l);
            User user2 = new User("denada", "teacher kids school", 60000l);
            userServices.insertUsers(Arrays.asList(user1, user2));

        }catch (RuntimeException e){
            //akan rerturn error size dari nama dan departemen karena size karakter kebesaran
            System.out.print("EXCEPTION IN BATCH 1 ! "+e.getMessage());
        }

        try{

            User user1 = new User("dicka", "senior java developer", 10000l);
            User user2 = new User("denada", "teacher kids school", 60000l);
            userServices.insertUsers(Arrays.asList(user1, user2));

        }catch (RuntimeException e){
            System.out.print("EXCEPTION IN BATCH 2 ! "+e.getMessage());
        }

        System.out.println(userServices.listUsers());
    }
}
