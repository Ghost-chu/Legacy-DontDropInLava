package com.mcsunnyside.ghost_chu;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.golde.bukkit.corpsereborn.Main;
import org.golde.bukkit.corpsereborn.CorpseAPI.events.CorpseClickEvent;
import org.golde.bukkit.corpsereborn.CorpseAPI.events.CorpseSpawnEvent;


public class VoidCorpseProtect extends JavaPlugin implements Listener {
	ArrayList<String> List = new ArrayList<String>();
	org.golde.bukkit.corpsereborn.Main api = null;
	@Override
    public void onEnable(){
            getLogger().info("Successfully loaded");
            Bukkit.getPluginManager().registerEvents(this,this);
            api = (org.golde.bukkit.corpsereborn.Main) Bukkit.getPluginManager().getPlugin("CorpseReborn");
    }

    @EventHandler
    public void event(CorpseSpawnEvent e) {
    	for (String string : List) {
			if(string.equals(e.getCorpse().getCorpseName())) {
			//Found in void!
			getLogger().info("Found player in void, Keeping Inventory and remove the Corpse...");
			Main.getPlugin().corpses.removeCorpse(e.getCorpse());
			List.remove(e.getCorpse().getCorpseName());
			}
		}
    }
    @EventHandler(priority=EventPriority.HIGHEST)
    public void DeathEvent(PlayerDeathEvent e) {
    	if(e.getEntity().getLocation().getBlockY()<1) {
    		List.add(e.getEntity().getName());
    		e.setKeepInventory(true);
    	}else {
    		e.setKeepInventory(false);
    	}
    }
    @EventHandler
    public void ClickEvent(CorpseClickEvent e) {
    	if(e.getClicker().getName()!=e.getCorpse().getCorpseName()) {
    		getLogger().warning("Player "+e.getClicker().getName()+" clicked Player "+e.getCorpse().getCorpseName()+"'s Corpse.");
    	}
    	
    }
}
