package client.vo;

import java.io.Serializable;

public class TableDataVO implements Serializable { 

	String bcNum;
	String inputDate;
	String memo;
	
	public TableDataVO(String bcNum, String inputDate, String memo) {
		this.bcNum = bcNum;
		this.inputDate = inputDate;
		this.memo = memo;
	}

	public String getBcNum() {
		return bcNum;
	}

	public String getInputDate() {
		return inputDate;
	}

	public String getMemo() {
		return memo;
	}

	@Override
	public String toString() {
		return "TableDataVO [bcNum=" + bcNum + ", inputDate=" + inputDate + ", memo=" + memo + "]";
	}
}
