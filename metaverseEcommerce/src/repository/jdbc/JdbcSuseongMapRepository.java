package repository.jdbc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.SuseongMap;

public class JdbcSuseongMapRepository extends DAO{
	
	/*
	 * Field
	 */
	private static JdbcSuseongMapRepository jdbcSuseongMapRepository = null;

	
	/*
	 * Constructor
	 */
	private JdbcSuseongMapRepository() {}

	/*
	 * Method
	 */
	public static JdbcSuseongMapRepository getMapRepository() {
		if (jdbcSuseongMapRepository == null) {
			jdbcSuseongMapRepository = new JdbcSuseongMapRepository();
		}
		return jdbcSuseongMapRepository;
	}
	
	public List<SuseongMap> selectAll() {
		List<SuseongMap> list = new ArrayList<SuseongMap>();
		
		try {
			connect();
			
			String sql = "SELECT * FROM suseong_map";
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				SuseongMap sMap = new SuseongMap();
				
				sMap.setEmdCd(rs.getInt(1));
				sMap.setEmdNn(rs.getString(2));
				
				list.add(sMap);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}

}
