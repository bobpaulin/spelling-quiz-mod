package org.devoxx4kids.chicago.spellingmod;

import java.util.List;

import cpw.mods.fml.client.config.GuiConfig;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;

public class SpellingQuizGui extends GuiConfig {
	
	public SpellingQuizGui(GuiScreen parent) 
	{
		super(parent, new ConfigElement(SpellingQuizMod.configFile.getCategory(Configuration.CATEGORY_GENERAL)).getChildElements(),
		        "spelling-quiz-mod", false, false, GuiConfig.getAbridgedConfigPath(SpellingQuizMod.configFile.toString()));
	}


}
