export interface MPLOGINLOGVO {
  id: any; // 登录日志ID
  empName: any; // 工号姓名
  org: any; // 机构
  roleName: any; // 角色名称
  loginTime: any; // 登录时间
  terminalType: any; // 终端类型
  ipAddress: any; // IP地址
}

export interface MPLOGINLOGForm {
  id?: string | number; // 登录日志ID
  empName?: any; // 工号姓名
  org?: any; // 机构
  roleName?: any; // 角色名称
  loginTime?: any; // 登录时间
  terminalType?: any; // 终端类型
  ipAddress?: any; // IP地址
}

export interface MPLOGINLOGQuery extends PageQuery {
  empName?: any; // 工号姓名
  org?: any; // 机构
  roleName?: any; // 角色名称
  loginTime?: any; // 登录时间
  terminalType?: any; // 终端类型
}