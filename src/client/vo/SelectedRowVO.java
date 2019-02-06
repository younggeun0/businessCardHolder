package client.vo;

import java.io.Serializable;

public class SelectedRowVO implements Serializable {
	
	String inputDate;
	String memo;
	
	public SelectedRowVO(String inputDate, String memo) {
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
		return "SelectedRowVO [inputDate=" + inputDate + ", memo=" + memo + "]";
	}
}
