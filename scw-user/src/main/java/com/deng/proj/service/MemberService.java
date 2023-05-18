package com.deng.proj.service;

import com.deng.proj.entity.TMember;
import com.deng.proj.entity.TMemberAddress;

import java.util.List;

public interface MemberService {
    public void registerUser(TMember member);
    public void registerUser1(TMember member);
    public TMember login(String username, String password);
    public List<TMemberAddress> findAddressByMemberId(Integer memberId);
    //根据用户id，获取用户信息
    public TMember findTmemberById(Integer id);
}
