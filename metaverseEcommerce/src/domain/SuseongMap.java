package domain;

public class SuseongMap {
	
	/*
	 * Field
	 */
	private int emdCd;
	private String emdNn;


	/*
	 * Method
	 */
	public int getEmdCd() {
		return emdCd;
	}
	public void setEmdCd(int emdCd) {
		this.emdCd = emdCd;
	}
	public String getEmdNn() {
		return emdNn;
	}
	public void setEmdNn(String emdNn) {
		this.emdNn = emdNn;
	}
	@Override
	public String toString() {
		return "SuseongMap [emdCd=" + emdCd + ", emdNn=" + emdNn + "]";
	}
	

	
}
