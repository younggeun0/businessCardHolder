package client.dao;

public class ClientDAO {
	
	private ClientDAO bch_dao;

	private ClientDAO() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public ClientDAO getInstance() {
		
		if (bch_dao == null) {
			bch_dao = new ClientDAO();
		}
		
		return bch_dao;
	}
	
	
	
}
