package server.vo;

public class DetailBCVO {
	
	private String fileName;
	private String memo;
	
	public DetailBCVO(String fileName, String memo) {
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
		return "DetailBCVO [fileName=" + fileName + ", memo=" + memo + "]";
	}
}
