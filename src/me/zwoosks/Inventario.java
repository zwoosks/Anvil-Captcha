package me.zwoosks;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.Inventory;

public class Inventario implements Listener {
	
	public Inventario(Main plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	
	@EventHandler
	public void onClose(InventoryCloseEvent e) {
		try {
			Bukkit.broadcastMessage("debugasdad");
			if(e.getPlayer() instanceof Player) {
				
				Player player = (Player) e.getPlayer();
				
				Inventory inv = e.getInventory();
				
				if(inv instanceof AnvilInventory) {
					if(Datos.usuarios.contains(player.getName())) {
						
						// Hacer lo conveniente si ha pasado
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a&l¡Has pasado la prueba!"));
						
					} else {
						
						player.kickPlayer(ChatColor.RED + "" + ChatColor.BOLD + "No has pasado el chapta.\n"
                    			+ ChatColor.WHITE + "¿No sabes cómo pasarlo? Entra en el siguiente enlace: "
                    			+ ChatColor.AQUA + ChatColor.UNDERLINE + "https://www.example.com/");
						
					}
					Datos.usuarios.remove(player.getName());
					Datos.num1.remove(player.getName());
					Datos.num2.remove(player.getName());
					
				}
				
			}
		} catch(Exception ex) {}
		
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		
		int min = 0;
		int max = 5;
		Random rand = new Random();
		int rand1 = rand.nextInt((max - min) + 1) + min;
		int rand2 = rand.nextInt((max - min) + 1) + min;
		Datos.num1.put(e.getPlayer().getName(), rand1);
		Datos.num2.put(e.getPlayer().getName(), rand2);
		
	}
	
}
