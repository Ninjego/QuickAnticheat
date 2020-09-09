package me.ninjego.HergonAlerts.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.ninjego.HergonAlerts.AlertsMain;

public class VLCommand implements CommandExecutor {

	public static AlertsMain plugin;
	
	public VLCommand(AlertsMain plugin) {
		VLCommand.plugin = plugin;
		VLCommand.plugin.getCommand("vl").setExecutor(this);
	}
	
	@Override	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage("You cannot execute this command");
			return true;
		}
		
		Player player = (Player) sender;
		
		if(player.hasPermission("HergonAlerts.checkvl")) {
			if(args.length == 0) {
				player.sendMessage(ChatColor.RED + "Invalid Usage: /vl [Player]");
				return true;
			}
			Player plr = Bukkit.getPlayer(args[0]);
			
			if(plr != null) {
				int Criticals = AlertsMain.CriticalsVL.get(plr.getName());
				int Reach = AlertsMain.ReachVL.get(plr.getName());
				int NoFall = AlertsMain.NoFallVL.get(plr.getName());
				
				int Amount = Criticals + Reach + NoFall;
				
				player.sendMessage(ChatColor.GRAY + args[0] + "'s VL is " + ChatColor.RED + Amount);
			}
		} else {
			player.sendMessage(ChatColor.RED + "You cannot execute this command");
			return true;
		}
		
		return false;
	}
	
}
