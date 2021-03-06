package com.artemstukalenko.tournaments.task.service.implementators;

import com.artemstukalenko.tournaments.task.dao.UserDAO;
import com.artemstukalenko.tournaments.task.dao.UserRoleDAO;
import com.artemstukalenko.tournaments.task.entity.User;
import com.artemstukalenko.tournaments.task.entity.UserRole;
import com.artemstukalenko.tournaments.task.service.UserRoleService;
import com.artemstukalenko.tournaments.task.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleDAO userRoleDAO;

    @Autowired
    private UserService userService;

    @Override
    public List<UserRole> getAllUserRoles() {
        return userRoleDAO.getAllUserRoles();
    }

    @Override
    public UserRole findRoleById(int roleId) {
        return userRoleDAO.findRoleById(roleId);
    }

    @Override
    public boolean addOrUpdateRole(UserRole roleToAdd) {
        return userRoleDAO.addOrUpdateRole(roleToAdd);
    }

    @Override
    @Transactional
    public boolean deleteRoleById(int roleId) {

        List<User> usersWithThatRole = userService.findUsersByUserRoleId(roleId);

        for (User user : usersWithThatRole) {
            userService.deleteUserById(user.getUserId());
        }

        return userRoleDAO.deleteRoleById(roleId);
    }

}
