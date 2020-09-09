package me.ninjego.HergonAlerts.checks;

import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffectType;

import me.ninjego.HergonAlerts.AlertsMain;

public class Criticals implements Listener {
	
	public Player player;

	@EventHandler
	public void Damage(EntityDamageByEntityEvent e) {
		if(isCritical(player)) {
			if(player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType().isSolid() && (player.getLocation().getY() % 1.0 == 0.0 || player.getLocation().getY() % 0.5 == 0.0)) {
				incrementValue(AlertsMain.CriticalsVL, player.getName());
			      for(Player plr : Bukkit.getOnlinePlayers()) {
			    	  if(AlertsMain.StaffEnabled.contains(plr.getName())) {
			    		  
			    		  plr.sendMessage(ChatColor.GRAY + player.getName() + " is possibly using " + ChatColor.RED + "Criticals" + ChatColor.GRAY + " [" + ChatColor.RED + "VL: " + ChatColor.DARK_RED + AlertsMain.CriticalsVL.get(player.getName()) + ChatColor.GRAY + "]");
			    	  }
			      }
			}
		}
	}
	
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		player = (Player) e.getPlayer();
	}
	
	@SuppressWarnings("deprecation")
	private boolean isCritical(Player player)
    {
        return
                player.getFallDistance() > 0.0F &&
                !player.isOnGround() &&
                !player.isInsideVehicle() &&
                !player.hasPotionEffect(PotionEffectType.BLINDNESS) &&
                player.getLocation().getBlock().getType() != Material.LADDER &&
                player.getLocation().getBlock().getType() != Material.VINE;
    }
	
	public static<K> void incrementValue(Map<K,Integer> map, K key)
	{
		Integer count = map.get(key);
		if (count == null) {
			map.put(key, 1);
		}
		else {
			map.put(key, count + 1);
		}
	}
}
