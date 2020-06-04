package com.forezp.api.entity.sys_role;

import com.forezp.api.entity.sys_permission.SysPermission;
import lombok.Data;

import java.util.List;

@Data
public class SysRoleTwo extends SysRole {
    List<SysPermission> sysPermissionList;
}
