package me.ninjego.HergonAlerts.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.ninjego.HergonAlerts.AlertsMain;


public class AlertsCommand implements CommandExecutor {

	public static AlertsMain plugin;
	
	public AlertsCommand(AlertsMain plugin) {
		AlertsCommand.plugin = plugin;
	    AlertsCommand.plugin.getCommand("halerts").setExecutor(this);
	}
	
	@Override	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage("You cannot execute this command");
			return true;
		}
		
		Player player = (Player) sender;
		
		if(player.hasPermission("HergonAlerts.use")) {
			if(AlertsMain.StaffEnabled.contains(player.getName())) {
				AlertsMain.StaffEnabled.remove(player.getName());
			    player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("turnoff-message") + " " + ChatColor.RED + "OFF"));
			    return true;
			}
			AlertsMain.StaffEnabled.add(player.getName());
			player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("turnon-message") + " " + ChatColor.GREEN + "ON"));
			return true;
		}
		
		return false;
	}
	
	
	
}
