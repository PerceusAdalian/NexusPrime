package com.nexus.io.EchoTag;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.nexus.epsilon.NexusPrintUtils;

public class EchoTagAssignmentHandler implements Listener
{
	@EventHandler
	public void ItemPickupEvent(EntityPickupItemEvent e) 
	{
		if (e.getEntity() instanceof Player player) 
		{
			ItemStack stack = e.getItem().getItemStack();
			
			EchoBindableFilter filter = new EchoBindableFilter();
			
			if (!filter.isBindable(stack)) //Check for bindable items, skip otherwise not from above filter hashset.
			{
				return;
			}
			
			if (!EchoNamespace.isEchoBound(stack)) //This block checks for if the item in question is already echo bound and will disallow pickup if so.
			{
				//Continues with registering the item's echo key if it's not already echo bound.
				ItemMeta meta = stack.getItemMeta();
				
				if (meta == null) return;
				
				List<String> itemDescription = new ArrayList<>();
				itemDescription.add(NexusPrintUtils.ColorParser("&r&7&oThis item is Echo Bound to: &r&e&l" + player.getName()));
				itemDescription.add("\n");
				meta.setLore(itemDescription); 
				stack.setItemMeta(meta); //Display the owner's name on the item as Echo Bound.
				
				EchoNamespace.setEchoNamespace(stack, player); //Sets the key to the player's UUID on the item.
				
				NexusPrintUtils.Print(player, "This " + stack.getType().toString() + "'s echo is now bound to you."); //Player registration confirmation.
				return;
			}
			
			String ownerKey = EchoNamespace.getEchoOwner(stack);
			if (ownerKey != null) 
			{
				UUID ownerID = UUID.fromString(ownerKey);
				if (ownerID != null && !player.getUniqueId().equals(ownerID)) 
				{
					e.setCancelled(true);
					NexusPrintUtils.Print(player, "&c&oThis item's echo is bound to another and cannot be picked up.");
				}
				return; //Two return checks in the event either the player's UUID or the namespacedkey is null.
			}
			return;
		}
	}
}
