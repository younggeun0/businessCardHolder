package client.vo;

public class DetailVO {
	
	String fileName;
	String memo;
	
	public DetailVO(String fileName, String memo) {
		this.fileName = fileName;
		this.memo = memo;
	}
	public String getFileName() {
		return fileName;
	}
	public String getMemo() {
		return memo;
	}
	@Override
	public String toString() {
		return "DetailVO [fileName=" + fileName + ", memo=" + memo + "]";
	}
}
