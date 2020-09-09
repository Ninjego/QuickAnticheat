package me.ninjego.HergonAlerts.checks;

import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import me.ninjego.HergonAlerts.AlertsMain;
import me.ninjego.HergonAlerts.Distance;

public class Reach implements Listener {

	public Location EntityIHit;
	public Location PlayerDistance;
	
	public Player thePlayer;
	
	@EventHandler
	public void PlrDistance(PlayerMoveEvent e) {
		Player player = (Player) e.getPlayer();
		
		thePlayer = player;
		
		PlayerDistance = player.getLocation();
	}
	
	@EventHandler
	public void HitEvent(EntityDamageByEntityEvent e) {
		EntityIHit = e.getEntity().getLocation();
		
		
		
		Distance distance = new Distance(PlayerDistance, EntityIHit);
		
		if(distance.getxDiff() > 3.38D || distance.getzDiff() > 3.38D ) {
			  if(distance.getxDiff() > 3.38D || distance.getzDiff() > 3.38D)
				  e.setCancelled(true);
			  incrementValue(AlertsMain.ReachVL, thePlayer.getName());
		      for(Player plr : Bukkit.getOnlinePlayers()) {
		    	  if(AlertsMain.StaffEnabled.contains(plr.getName())) {
		    		  plr.sendMessage(ChatColor.GRAY + thePlayer.getName() + " is possibly using " + ChatColor.RED + "Reach" + ChatColor.GRAY + " [" + ChatColor.RED + "VL: " + ChatColor.DARK_RED + AlertsMain.ReachVL.get(thePlayer.getName()) + ChatColor.GRAY + "]");
		    	  }
		      }
		}
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
