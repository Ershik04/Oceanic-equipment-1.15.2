
package net.mcreator.oceanicequipment.itemgroup;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;

import net.mcreator.oceanicequipment.item.FishoilItem;
import net.mcreator.oceanicequipment.OceanicEquipmentModElements;

@OceanicEquipmentModElements.ModElement.Tag
public class OceanicequpmentItemGroup extends OceanicEquipmentModElements.ModElement {
	public OceanicequpmentItemGroup(OceanicEquipmentModElements instance) {
		super(instance, 5);
	}

	@Override
	public void initElements() {
		tab = new ItemGroup("taboceanicequpment") {
			@OnlyIn(Dist.CLIENT)
			@Override
			public ItemStack createIcon() {
				return new ItemStack(FishoilItem.block, (int) (1));
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return true;
			}
		}.setBackgroundImageName("item_search.png");
	}
	public static ItemGroup tab;
}
