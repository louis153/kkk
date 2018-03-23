package com.longti.upjc.util;

public class ErrorMessage {
	public static class ErrInfo{
		private String code;
		private String message;
		public ErrInfo(String strCode,String strMessage){
			this.code=(strCode);
			this.message=(strMessage);
		}
		public String getMessage() {
			return message;
		}
		public String getCode() {
			return code;
		}
	}
	public static ErrInfo SUCCESS= new ErrInfo("000000","操作成功");
	public static ErrInfo FAIL= new ErrInfo("000001","操作失败");
	public static ErrInfo DUPLICATE= new ErrInfo("000002","账号重复");
	public static ErrInfo NO_REC= new ErrInfo("000005","空记录");
	public static ErrInfo ACCIDENT= new ErrInfo("999999","系统异常");
	
	public static ErrInfo CANCEL=new ErrInfo("000006","比赛已经取消");
	public static ErrInfo SDANDARD_ERR=new ErrInfo("000007", "大小分标准发生变化");
	public static ErrInfo END_MATCH=new ErrInfo("000008", "已经有比赛截止投注了，<br/>请重新确认您的选择");
	
	public static ErrInfo END_SELL=new ErrInfo("000009", "比赛已停止销售，请重新选择");
	public static ErrInfo ERR_OVERFLOW=new ErrInfo("000010","投注超出上限，请重新选择" );
	public static ErrInfo ERR_OVERMATCH=new ErrInfo("000011","单个玩法投注超出上限，请重新选择" );
		
	public static ErrInfo OVER_INVCODE=new ErrInfo("300000", "绑定邀请码超24小时");
	public static ErrInfo BINDED_INVCODE=new ErrInfo("300001", "已绑定邀请码");
	public static ErrInfo MUTUALBIND_INVCODE=new ErrInfo("300002", "两个用户不能相互绑定邀请码，请重新填写");
	public static ErrInfo NO_INVCODE=new ErrInfo("300003", "邀请码不存在");
	
}
