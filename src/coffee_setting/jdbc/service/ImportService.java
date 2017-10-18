package coffee_setting.jdbc.service;

import coffee_setting.Config;
import coffee_setting.dao.DatabaseDao;

public class ImportService implements DbService{
	
	private static final ImportService Instance = new ImportService();
	
	
	public static ImportService getInstance() {
		return Instance;
	}
	public ImportService() {}

	@Override
	public void service() {
		DatabaseDao.getInstance().executeUpdateSQL("SET FOREIGN_KEY_CHECKS = 0");
		DatabaseDao.getInstance().executeUpdateSQL("use " + Config.DB_NAME);
		
		for(String tableName : Config.TABLE_NAME){
			DatabaseDao.getInstance().executeUpdateSQL(String.format("LOAD DATA LOCAL INFILE '%s' INTO TABLE %s" , Config.getFilePath(tableName, false),tableName));
		}
		DatabaseDao.getInstance().executeUpdateSQL("SET FOREIGN_KEY_CHECKS = 1");
		
	}

}
