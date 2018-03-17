package com.longti.upjc.formdata;
/**
 * 消息响应头
 * @author 杨阳
 * 201706291523
 */
public class Head {
	private String version="1.0";	//version 版本号由2 位数字及标点组成，电
									//投系统默认使用版本号为1.0，若版本修正，
									//或变更，将提前告知；
	private String venderId="1001";//此代码为京东提供给游戏侧的唯一编号
									//注意：商户编号将在上线前，由京东给出生产
									//环境的配置，包含此商户编码；
	private String gameId="500";	//游戏编号将在上线前，由京东给出生产
									//环境的配置，包含此游戏编码；
	private String uuId;			//消息序列号，请求方必须保证序号唯一，最
									//大长度：100 位，由英文字母和数字组成；
									//京东将对此序号做唯一性校验；
									//同一通讯的请求与响应保持消息编号一
									//致；比如游戏方发起用户账户查询的请求，
									//在请求的消息头中uuid 为
									//jd10120723150001, 则京东系统处理请求
									//之后返回响应，在响应的消息头中uuid 同
									//为jd10120723150001，京东发起的主动请
									//求亦是如此。
	private String timestamp;		//当前时间戳，格式：yyyyMMddHHmmss，
									//如：20120723090101；
	private String md;				//MD5(venderId+gameId+uuid+timestamp+
									//key)，key 为双方协定密钥;
									//l uuid 即为头部uuid 标签的值；
									//l timestamp 需要使用该请求对应消息
									//头中的“timestamp”标签的值，而不
									//能使用当前时间戳，否则计算的MD5
									//值将不一致，导致检验失败。
									//l key 为双方协定的密钥，此密钥将在上
									//线前，由双方协定产生；
	
	public String getUuId() {
		return uuId;
	}
	public void setUuId(String uuId) {
		this.uuId = uuId;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getMd() {
		return md;
	}
	public void setMd(String md) {
		this.md = md;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getVenderId() {
		return venderId;
	}
	public void setVenderId(String venderId) {
		this.venderId = venderId;
	}
	public String getGameId() {
		return gameId;
	}
	public void setGameId(String gameId) {
		this.gameId = gameId;
	}
	
	
	
}
