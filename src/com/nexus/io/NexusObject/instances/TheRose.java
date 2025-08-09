package com.nexus.io.NexusObject.instances;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.player.PlayerInteractEvent;
import org.joml.Random;

import com.nexus.epsilon.NexusPlayerActions;
import com.nexus.epsilon.NexusWorldEvents;
import com.nexus.io.NexusObject.AbstractNexusObject;

public class TheRose extends AbstractNexusObject
{

	public TheRose() 
	{
		super("The Rose", "the_rose_object", Material.ROSE_BUSH, true, 
				"&r&7&oThe altar of the heart, a gift unto others.",
				"&r&7&oRoses remind us of the friendships we meet on our path.",
				"&r&7&oTo lovers, they reflect harmony, care, and the will to love,",
				"&r&7&o  even when the world distorts what love should be.",  
				"&r&7&oTo receive one is sacred; to grow them, a calling;",  
				"&r&7&o  to give one, a confession. Not always romantic—",  
				"&r&b&oThe Rose mirrors love in all its forms.",
				"&r&7&oKindness incarnate: &c4&62&7 | &e1&a2&7 | &91&d6&7; A reminder of why we exist.",  
				"&r&7&o  It is still; whole; grounded; warm; Nurturing,",  
				"&r&7&o  yet sharp if mishandled, and a dagger in disguise at worst.",  
				"&r&7&oIt is consent, and the contract of understanding in clarity.",
				"&r&c&oThere will be conflict, yes, so meet it with respect.", 
				"&r&7&oLove asks nothing to be proven. It is not earned, but lived..",
				"&r&7&l&o  and some will spend lifetimes without learning to live it.",
				"",
				"&r&7&oNot out of punishment,",
				"&r&7&l&o  But of the Universe's will and promise to all.",
				"",
				"&r&f&o— &eThe Curator"
);
		
	}

	@Override
	public boolean Cast(PlayerInteractEvent e) 
	{
		if (!NexusPlayerActions.rightClickBlock(e)) return false;
		List<Block> blocks = NexusWorldEvents.getNearbyBlocks(e.getClickedBlock().getLocation(), 5);
		Random rand = new Random();
		for (int i = 0; i <= 6; ++i) 
		{
			blocks.get(rand.nextInt(blocks.size())).applyBoneMeal(BlockFace.UP);
		}
		
		
		return true;
	}

}
