export interface YJEMERTEAMVO {
  id: any; // 应急队伍ID
  unit: any; // 单位
  teamName: any; // 队伍名称
  leader: any; // 负责人
  leaderPhone: any; // 负责人电话
  createBy: any; // 创建人
  createTime: any; // 创建时间
}

export interface YJEMERTEAMForm {
  id?: string | number; // 应急队伍ID
  unit?: any; // 单位
  teamName?: any; // 队伍名称
  leader?: any; // 负责人
  leaderPhone?: any; // 负责人电话
  createBy?: any; // 创建人
  createTime?: any; // 创建时间
}

export interface YJEMERTEAMQuery extends PageQuery {
  unit?: any; // 单位
  teamName?: any; // 队伍名称
  leader?: any; // 负责人
  leaderPhone?: any; // 负责人电话
  createBy?: any; // 创建人
}