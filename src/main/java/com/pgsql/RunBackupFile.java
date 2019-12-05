package com.pgsql;

import java.io.IOException;
import java.net.URL;

public class RunBackupFile {
	
	public void run() {
		try{
			 Runtime r = Runtime.getRuntime();
			 Process p;
			 System.out.println("---START---");
			 URL bkURL = this.getClass().getResource("/ccatdb_backup.backup");
			 String backupFilePath = bkURL.getPath().substring(1,bkURL.getPath().length());
			 URL exeURL = this.getClass().getResource("/pg_restore.exe");
			 String exePath = exeURL.getPath().substring(1,exeURL.getPath().length());
			 System.out.println("BackupFilepath ="+bkURL.getPath().substring(1,bkURL.getPath().length()));
			 System.out.println("pg_restore exe ="+exeURL.getPath().substring(1,exeURL.getPath().length()));
			 
			 //Local creds
			 /* String[] cmd = {
				    //"C:\\Program Files\\PostgreSQL\\9.4\\bin\\pg_restore.exe",
				    exePath,
				    "--host", "localhost",
				    "--port", "5432",
				    "--username", "postgres",
				    "--dbname", "RestoreBackup_db",//"restore_backup_test",
				    "--role", "postgres",
				    "--no-password",
				    "--verbose",
				    backupFilePath //"D:\\ccatdb_backup.backup"
					};*/
			 
			 // Remote creds 
			 String[] cmd = {
					    exePath,
					    "--host", "172.30.20.16",
					    "--port", "5432",
					    "--username", "ccatdbuser",
					    "--dbname", "ccatdb",
					    "--role", "postgres",
					    //"--no-password",
					    "--password", "ccatdbuser",
					    "--verbose",
					    backupFilePath
						};
			 p = r.exec(cmd);
			 System.out.println("---DONE---");
		}catch(Exception e){
			System.out.println("---EXCEPTION OCCURRED---");
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws IOException {
		RunBackupFile obj=new RunBackupFile();
		obj.run();
	}
}
