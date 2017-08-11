package net.warvale.ace.permissions;

import net.warvale.ace.Main;
import net.warvale.ace.ranks.RankManager;
import org.apache.commons.lang.StringUtils;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;

public class PermissionsManager {
    private Main plugin;
    public PermissionsManager(Main plugin){
        this.plugin = plugin;
    }
    public HashMap<UUID, PermissionAttachment> playerPermissions = new HashMap<>();

    public void setup(Player player){
        if(playerPermissions.containsKey(player.getUniqueId())){
            playerPermissions.remove(player.getUniqueId());
        }
        PermissionAttachment attachment = player.addAttachment(plugin);
        this.playerPermissions.put(player.getUniqueId(), attachment);
        permissionsSetter(player.getUniqueId());
    }

    private void permissionsSetter(UUID uuid){
        PermissionAttachment attachment = this.playerPermissions.get(uuid);
        try {
            for (String rank : RankManager.getAllRanks()){
                for (String permission : RankManager.getPermissions(RankManager.getRankId(rank))){
                    if(!(StringUtils.isEmpty(permission) || permission.equals(" "))){attachment.setPermission(permission, true);}
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void clear(){
        playerPermissions.clear();
    }

    public void remove(Player player){
        playerPermissions.remove(player.getUniqueId());
    }
}
