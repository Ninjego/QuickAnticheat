package me.ninjego.HergonAlerts;

import org.bukkit.Location;
import org.bukkit.event.player.PlayerMoveEvent;

public class Distance {

	private Location to, from;
	private Double xDiff, yDiff, zDiff;
	
	public Distance(PlayerMoveEvent e) {
		this(e.getFrom(), e.getTo());
	}
	
	public Distance(Location a, Location b) {
		this.from = a;
		this.to = b;
		
		this.xDiff = Math.abs(a.getX() - b.getX());
		this.yDiff = Math.abs(a.getY() - b.getY());
		this.zDiff = Math.abs(a.getZ() - b.getZ());
		
	}

	public Location getTo() {
		return to;
	}

	public Location getFrom() {
		return from;
	}

	public Double getxDiff() {
		return xDiff;
	}

	public Double getyDiff() {
		return yDiff;
	}

	public Double getzDiff() {
		return zDiff;
	}
	
	
}
