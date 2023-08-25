package com.dozone.wehagopro.repository.mybatis;

import com.dozone.wehagopro.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MyBatisItemRepository {

    private final ItemMapper itemMapper;
    private final PasswordEncoder passwordEncoder;

//    public Item2 selectIdPw(String userid, String password) {
//        return itemMapper.selectIdPw(userid, password);
//    }

    public Login selectIdEmail(String email, String name) {
        return itemMapper.selectIdEmail(email, name);
    }

    public Login selectIdPhone(String username, String phone) {
        return itemMapper.selectIdPhone(username, phone);
    }

    public Login selectPwEmail(String id, String email) {
        return itemMapper.selectPwEmail(id, email);
    }

    public Login selectPwPhone(String id, String phone) {
        return itemMapper.selectPwPhone(id, phone);
    }

    public int updatePw(String id, String npw) {
        return itemMapper.updatePw(id, passwordEncoder.encode(npw));
    }

    public UserNoPwDto selectUserNoPw(String t_user_id) {
        System.out.println("t_user_id" + t_user_id);
        return itemMapper.selectUserNoPw(t_user_id);
    }

    public void updateUserNo(int t_user_no, int t_employee_no) {
        itemMapper.updateUserNo(t_user_no, t_employee_no);
    }

    public void insertNotice(String t_user_name, int t_company_no, String t_notice_title, String t_notice_content) {
        itemMapper.insertNotice(t_user_name, t_company_no, t_notice_title, t_notice_content);
    }

    public List<NoticeSelectDto> selectNotice(int t_company_no) {
        return itemMapper.selectNotice(t_company_no);
    }

    public  void updateNotice(String t_notice_title, String t_notice_content, int t_notice_no) {
        itemMapper.updateNotice(t_notice_title, t_notice_content, t_notice_no);
    }

    public void deleteNotice(int t_notice_no){
        itemMapper.deleteNotice(t_notice_no);
    }

    public void deleteUser(int t_user_no, String t_user_delete_reason){
        itemMapper.deleteUser(t_user_no, t_user_delete_reason);
    }
}

