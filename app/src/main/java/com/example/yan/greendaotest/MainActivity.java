package com.example.yan.greendaotest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.yan.greendaotest.bean.User;
import com.example.yan.greendaotest.bean.UserDB;

import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UserDB userDB = new UserDB(this);
        userDB.insertUser(new User(null,"sd",new Random().nextInt(9999),12));

        //userDB.deleteUser(new Long(11));
        userDB.updateUser(new Long(27));

        List<User> users = userDB.queryUserList();
        for (User user1 : users) {
            Log.i("name", user1.getName()+"   "+user1.getAge()+"   "+user1.getId());
        }
    }


}
