package js_fb5.w_hs.migrations;

import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.foundation.NSArray;

import er.extensions.jdbc.ERXSQLHelper.ColumnIndex;
import er.extensions.migration.ERXMigrationDatabase;
import er.extensions.migration.ERXMigrationIndex;
import er.extensions.migration.ERXMigrationTable;
import er.extensions.migration.ERXModelVersion;

public class MyEOModel0 extends ERXMigrationDatabase.Migration {
	@Override
	public NSArray<ERXModelVersion> modelDependencies() {
		return null;
	}
  
	@Override
	public void downgrade(EOEditingContext editingContext, ERXMigrationDatabase database) throws Throwable {
		// DO NOTHING
	}

	@Override
	public void upgrade(EOEditingContext editingContext, ERXMigrationDatabase database) throws Throwable {
		ERXMigrationTable meinUserTable = database.newTableNamed("MeinUser");
		meinUserTable.newIntegerColumn("id", NOT_NULL);
		meinUserTable.newStringColumn("vorName", 100, NOT_NULL);
		meinUserTable.create();
	 	meinUserTable.setPrimaryKey("id");

	}
}