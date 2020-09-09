package me.ninjego.HergonAlerts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import me.ninjego.HergonAlerts.checks.*;
import me.ninjego.HergonAlerts.commands.*;

public class AlertsMain extends JavaPlugin implements Listener {
	
	public static List<String> StaffEnabled = new ArrayList<String>();
	
	public static HashMap<String,Integer> NoFallVL = new HashMap<String,Integer>();
	public static HashMap<String,Integer> ReachVL = new HashMap<String,Integer>();
	public static HashMap<String,Integer> CriticalsVL = new HashMap<String,Integer>();
	
	@Override
	public void onEnable() {
		this.saveDefaultConfig();
		Commands();
		addListeners();
	}

	public void addListeners() {
		this.getServer().getPluginManager().registerEvents(new NoFall(), this);
		this.getServer().getPluginManager().registerEvents(this, this);
		this.getServer().getPluginManager().registerEvents(new Reach(), this);
		this.getServer().getPluginManager().registerEvents(new Criticals(), this);
	}
	
	public void Commands() {
		new AlertsCommand(this);
		new VLCommand(this);
	}
	
	@EventHandler
	public void OnJoin(PlayerJoinEvent e) {
		Player player = (Player) e.getPlayer();
		
		NoFallVL.put(player.getName(), 0);
		ReachVL.put(player.getName(), 0);
		CriticalsVL.put(player.getName(), 0);
	}
	
}
