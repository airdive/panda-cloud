package com.yukong.panda.user.service.impl;

import com.yukong.panda.common.enums.PasswordEncoderEnum;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Description: TODO
 * @Author: liuchuang
 * @Date: 2019-08-13 16:39
 */
public class SysUserServiceImplTest {

    @Test
    public void testPasswd(){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String passwd = "abc123";
        String encode = passwordEncoder.encode(PasswordEncoderEnum.BCRYPT.getValue() + passwd);
        // {bcrypt}$2a$10$qK.LT5T5L8xwyovv2Qt9A.cC0FzmI1csAPP/2rimLIbuI1i9GXScu
        //{bcrypt}$2a$10$2kV7N5klgIu6F0SbHVFpJuvh/Yzw/lUsHQEKGd1f1E0qcqSn3Bq3y
        System.out.println(encode);
        System.out.println(passwordEncoder.matches(PasswordEncoderEnum.BCRYPT.getValue()+passwd,encode));

        encode = "$2a$10$2kV7N5klgIu6F0SbHVFpJuvh/Yzw/lUsHQEKGd1f1E0qcqSn3Bq3y";
        System.out.println(passwordEncoder.matches(PasswordEncoderEnum.BCRYPT.getValue()+passwd,encode));

    }
}
