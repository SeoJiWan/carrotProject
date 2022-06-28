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
			
			String sql = "SELECT * FROM suseong_map ORDER BY emd_cd";
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
	
	public SuseongMap selectOneByName(String emdNn) {
		SuseongMap sMap = null;
		
		try {
			connect();
			
			String sql = "SELECT * FROM suseong_map WHERE emd_nn = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, emdNn);
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				sMap = new SuseongMap();
				
				sMap.setEmdCd(rs.getInt(1));
				sMap.setEmdNn(rs.getString(2));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return sMap;
	} 
	
	public SuseongMap selectOneByCode(int code) {
		SuseongMap sMap = null;
		
		try {
			connect();
			
			String sql = "SELECT * FROM suseong_map WHERE emd_cd = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, code);
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				sMap = new SuseongMap();
				
				sMap.setEmdCd(rs.getInt(1));
				sMap.setEmdNn(rs.getString(2));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return sMap;
	} 

}
