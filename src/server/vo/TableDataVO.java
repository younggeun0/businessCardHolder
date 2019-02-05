package server.vo;

import java.io.Serializable;

public class TableDataVO implements Serializable { 

	String inputDate;
	String memo;
	
	public TableDataVO(String inputDate, String memo) {
		this.inputDate = inputDate;
		this.memo = memo;
	}

	public String getInputDate() {
		return inputDate;
	}

	public String getMemo() {
		return memo;
	}

	@Override
	public String toString() {
		return "TableDataVO [inputDate=" + inputDate + ", memo=" + memo + "]";
	}
}
