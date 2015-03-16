package org.devoxx4kids.chicago.spellingmod;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

@Mod(modid = SpellingQuizMod.MODID, version = SpellingQuizMod.VERSION, guiFactory="org.devoxx4kids.chicago.spellingmod.SpellingQuizGuiFactory")
public class SpellingQuizMod
{
    public static final String MODID = "spelling-quiz-mod";
    public static final String VERSION = "1.0";
    
    @Mod.Instance("spelling-quiz-mod")
    public static SpellingQuizMod instance;
    
    public static Configuration configFile;
    
    public static Map<String, Item> spellingWordMap;
    
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
      spellingWordMap = new HashMap<String, Item>();
      configFile = new Configuration(event.getSuggestedConfigurationFile());

      
      syncConfig();
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
		// some example code
    	FMLCommonHandler.instance().bus().register(instance);
    	MinecraftForge.EVENT_BUS.register(new SpellingWordEventListener());
    }
    
    public static void syncConfig() {
    	
    	if(!configFile.getConfigFile().exists())
    	{
    		Iterator itemIt = Item.itemRegistry.iterator();
        	while(itemIt.hasNext())
        	{
        		Item currentItem = (Item) itemIt.next();
        		String currentItemName = Item.itemRegistry.getNameForObject(currentItem);
        		
        		String currentItemSpellingWord = configFile.getString(currentItemName, Configuration.CATEGORY_GENERAL, currentItemName, "");
        		
        	}
    	}
    	ConfigCategory category = configFile.getCategory(Configuration.CATEGORY_GENERAL);
    	for(Map.Entry<String, Property> currentEntry: category.entrySet())
    	{
    		String currentItemName = currentEntry.getKey();
    		String currentItemSpellingWord = currentEntry.getValue().getString();
    		if(!currentItemSpellingWord.equals(currentItemName))
    		{
    			spellingWordMap.put(currentItemSpellingWord, (Item)Item.itemRegistry.getObject(currentItemName));
    		}
    	}
    	
    	
    	
        if(configFile.hasChanged())
          configFile.save();
      }
    
    @SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent eventArgs) {
      if(eventArgs.modID.equals("spelling-quiz-mod"))
        syncConfig();
    }
}
