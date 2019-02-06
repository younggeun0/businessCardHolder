package server.vo;

public class InsertVO {
	
	private String memo;
	private String fileName;
	
	public InsertVO(String memo, String fileName) {
		this.memo = memo;
		this.fileName = fileName;
	}

	public String getMemo() {
		return memo;
	}

	public String getFileName() {
		return fileName;
	}

	@Override
	public String toString() {
		return "InsertVO [memo=" + memo + ", fileName=" + fileName + "]";
	}

}
