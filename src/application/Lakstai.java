package application;

public class Lakstai {
	private int id ;
	private int user; 
	private String medziaga;
	private String lakstoStoris;
	private String lakstoMatmenys;
	private int likutis;
	private String reikiaPapildyti;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getMedziaga() {
		return medziaga;
	}
	public void setMedziaga(String medziaga) {
		this.medziaga = medziaga;
	}
	public String getLakstoStoris() {
		return lakstoStoris;
	}
	public void setLakstoStoris(String lakstoStoris) {
		this.lakstoStoris = lakstoStoris;
	}
	public String getLakstoMatmenys() {
		return lakstoMatmenys;
	}
	public void setLakstoMatmenys(String lakstoMatmenys) {
		this.lakstoMatmenys = lakstoMatmenys;
	}
	public int getLikutis() {
		return likutis;
	}
	public void setLikutis(int likutis) {
		this.likutis = likutis;
	}
	public String getReikiaPapildyti() {
		return reikiaPapildyti;
	}
	public void setReikiaPapildyti(String reikiaPapildyti) {
		this.reikiaPapildyti = reikiaPapildyti;
	}
	
	
	public Lakstai(int id, String medziaga, String lakstoStoris, String lakstoMatmenys, int likutis,
			String reikiaPapildyti) {
		this.id = id;
		this.medziaga = medziaga;
		this.lakstoStoris = lakstoStoris;
		this.lakstoMatmenys = lakstoMatmenys;
		this.likutis = likutis;
		this.reikiaPapildyti = reikiaPapildyti;
	}
	
	
	
}


