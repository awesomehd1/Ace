package net.warvale.ace;

import net.warvale.ace.listeners.PlayerListener;
import net.warvale.ace.permissions.PermissionsManager;
import net.warvale.ace.ranks.RankListener;
import net.warvale.ace.sql.SQLConnection;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public class Main extends JavaPlugin {
    private static Main instance;
    private static PermissionsManager pm;
    private SQLConnection db;

    @Override
    public void onEnable(){
        instance = this;
        init();
        Bukkit.getPluginManager().registerEvents(new PlayerListener(this, pm), this);
        Bukkit.getPluginManager().registerEvents(new RankListener(this), this);
    }

    @Override
    public void onDisable(){
        pm.clear();
    }

    private void init(){
        db = new SQLConnection(getConfig().getString("hostname"), getConfig().getInt("port"), getConfig().getString("database"), getConfig().getString("username"), getConfig().getString("password"));
        try {
            db.openConnection(); } catch(Exception e) {
            getLogger().log(Level.WARNING, "Could not establish connection to database, exception: "+e);
            return;
        }
        pm = new PermissionsManager(this);
        loadConfiguration();
    }

    public void loadConfiguration() {
        getConfig().options().copyDefaults(true);
        saveConfig();
    }


    public SQLConnection getDb() {
        return db;
    }

    public static Main get(){
        return instance;
    }
}
