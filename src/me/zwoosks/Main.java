package me.zwoosks;

import me.zwoosks.AnvilGUI.AnvilClickEvent;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
 
public class Main extends JavaPlugin implements Listener{
       
        static Main instance;
       
        @Override
        public void onEnable() {
                Main.instance = this;
                Bukkit.getPluginManager().registerEvents(this, this);
                new Inventario(this);
        }
       
        public static Main getInstance() {
                return instance;
        }
       
        @EventHandler
        public void onSneak(PlayerToggleSneakEvent event){
//        	Bukkit.broadcastMessage(Datos.usuarios.get(0));
                if(event.isSneaking()){
                        final Player player = event.getPlayer();
                        AnvilGUI gui = new AnvilGUI(player, new AnvilGUI.AnvilClickEventHandler() {
                               
                                @Override
                                public void onAnvilClick(AnvilClickEvent event) {
                                        if(event.getSlot() == AnvilGUI.AnvilSlot.OUTPUT){
                                        	event.setWillClose(true);
                                            event.setWillDestroy(true);
                                            //
                                            Integer num1 = Datos.num1.get(player.getName());
                                            Integer num2 = Datos.num2.get(player.getName());
                                            String res = Integer.toString(num1+num2);
                                                if(ChatColor.stripColor(event.getName()).equalsIgnoreCase(res)) {
                                                	Datos.usuarios.add(player.getName());
                                                	Bukkit.broadcastMessage("debug super");
                                                } else {
                                                	player.kickPlayer(ChatColor.RED + "" + ChatColor.BOLD + "No has pasado el chapta.\n"
                                                			+ ChatColor.WHITE + "¿No sabes cómo pasarlo? Entra en el siguiente enlace: "
                                                			+ ChatColor.AQUA + ChatColor.UNDERLINE + "https://www.example.com/");
                                                }
                                        }else{
                                                event.setWillClose(false);
                                                event.setWillDestroy(false);
                                        }
                                }
                                @Override
                                public void onInventoryClose(InventoryCloseEvent e) {
                                	player.sendMessage("debuggggggg");
                                }
                        });
                        ItemStack i = new ItemStack(Material.PAPER,1);
                        ItemMeta im = i.getItemMeta();
                        Integer num1 = Datos.num1.get(player.getName());
                        Integer num2 = Datos.num2.get(player.getName());
                        im.setDisplayName("¿Cuánto es " + num1 + "+" + num2 + "?");
                        i.setItemMeta(im);
                        gui.setSlot(AnvilGUI.AnvilSlot.INPUT_LEFT, i);
                       
                        gui.open();
                }
        }
}