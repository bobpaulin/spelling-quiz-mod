package org.devoxx4kids.chicago.spellingmod;

import java.util.Map;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.event.ServerChatEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class SpellingWordEventListener {
	
	@SubscribeEvent
	public void giveItemsForSpellingWords(ServerChatEvent event)
	{
		
		if(event.message.equals("spelling words"))
		{
			
			for(Map.Entry<String, Item> currentEntry: SpellingQuizMod.spellingWordMap.entrySet())
			{
				StringBuilder spellingWordList = new StringBuilder();
				spellingWordList.append("Spelling Word: ");
				String spellingWord = currentEntry.getKey();
				spellingWordList.append(spellingWord);
				spellingWordList.append(" - ");
				spellingWordList.append(Item.itemRegistry.getNameForObject(currentEntry.getValue()));
				event.player.addChatMessage(new ChatComponentText(
						EnumChatFormatting.GREEN + spellingWordList.toString()));
			}
			
			
			
		}
		else
		{
			Item spellingItem = SpellingQuizMod.spellingWordMap.get(event.message);
			if (spellingItem != null) 
			{
				event.player.inventory.addItemStackToInventory(new ItemStack(spellingItem, 1));
				event.player.addChatMessage(new ChatComponentText(
						EnumChatFormatting.GREEN + "Spelling Word Correct!"));
			}
			else
			{
				event.player.addChatMessage(new ChatComponentText(
						EnumChatFormatting.RED + "Spelling Word Incorrect!"));
			}
		}
	}

}
