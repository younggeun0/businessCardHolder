package businessCardHolder.dao;

public class BCHDao {
	
	private BCHDao bch_dao;

	private BCHDao() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public BCHDao getInstance() {
		
		if (bch_dao == null) {
			bch_dao = new BCHDao();
		}
		
		return bch_dao;
	}
	
	
	
}
