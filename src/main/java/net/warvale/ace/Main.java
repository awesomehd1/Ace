package net.warvale.ace;

import net.warvale.ace.sql.SQLConnection;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public class Main extends JavaPlugin {
    private static Main instance;

    private SQLConnection db;

    @Override
    public void onEnable(){
        instance = this;
        init();
    }

    @Override
    public void onDisable(){

    }

    private void init(){
        db = new SQLConnection(getConfig().getString("hostname"), getConfig().getInt("port"), getConfig().getString("database"), getConfig().getString("username"), getConfig().getString("password"));
        try {
            db.openConnection(); } catch(Exception e) {
            getLogger().log(Level.WARNING, "Could not establish connection to database, exception: "+e);
            return;
        }
    }

    public SQLConnection getDb() {
        return db;
    }

    public static Main get(){
        return instance;
    }
}
