package me.ninjego.HergonAlerts.checks;

import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

import me.ninjego.HergonAlerts.AlertsMain;

public class NoFall implements Listener {

	  @SuppressWarnings("deprecation")
	@EventHandler
	  public void onMove(PlayerMoveEvent e) {
	    Player p = e.getPlayer();
	    Location from = e.getFrom().clone();
	    Location to = e.getTo().clone();
	    Vector vec = to.toVector();
	    double i = vec.distance(from.toVector());
	    if (i == 0.0D)
	      return; 
	    if (p.getGameMode().equals(GameMode.CREATIVE))
	      return; 
	    if (p.getVehicle() != null)
	      return; 
	    if (from.getY() < to.getY())
	      return; 
	    if (p.getFallDistance() == 0.0F && 
	      i > 0.50D && p.isOnGround()) {
	    	incrementValue(AlertsMain.NoFallVL, p.getName());
		      for(Player plr : Bukkit.getOnlinePlayers()) {
		    	  if(AlertsMain.StaffEnabled.contains(plr.getName())) {
		    		  
		    		  plr.sendMessage(ChatColor.GRAY + p.getName() + " is possibly using " + ChatColor.RED + "NoFall" + ChatColor.GRAY + " [" + ChatColor.RED + "VL: " + ChatColor.DARK_RED + AlertsMain.NoFallVL.get(p.getName()) + ChatColor.GRAY + "]");
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
