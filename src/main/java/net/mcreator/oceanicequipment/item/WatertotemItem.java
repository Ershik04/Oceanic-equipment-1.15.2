
package net.mcreator.oceanicequipment.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.world.World;
import net.minecraft.item.Rarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.entity.Entity;
import net.minecraft.block.BlockState;

import net.mcreator.oceanicequipment.procedures.WatertotemItemInInventoryTickProcedure;
import net.mcreator.oceanicequipment.itemgroup.OceanicequpmentItemGroup;
import net.mcreator.oceanicequipment.OceanicEquipmentModElements;

import java.util.Map;
import java.util.HashMap;

@OceanicEquipmentModElements.ModElement.Tag
public class WatertotemItem extends OceanicEquipmentModElements.ModElement {
	@ObjectHolder("oceanic_equipment:watertotem")
	public static final Item block = null;
	public WatertotemItem(OceanicEquipmentModElements instance) {
		super(instance, 24);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}
	public static class ItemCustom extends Item {
		public ItemCustom() {
			super(new Item.Properties().group(OceanicequpmentItemGroup.tab).maxStackSize(1).rarity(Rarity.RARE));
			setRegistryName("watertotem");
		}

		@Override
		public int getItemEnchantability() {
			return 0;
		}

		@Override
		public int getUseDuration(ItemStack itemstack) {
			return 0;
		}

		@Override
		public float getDestroySpeed(ItemStack par1ItemStack, BlockState par2Block) {
			return 1F;
		}

		@Override
		public void inventoryTick(ItemStack itemstack, World world, Entity entity, int slot, boolean selected) {
			super.inventoryTick(itemstack, world, entity, slot, selected);
			double x = entity.getPosX();
			double y = entity.getPosY();
			double z = entity.getPosZ();
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("entity", entity);
				WatertotemItemInInventoryTickProcedure.executeProcedure($_dependencies);
			}
		}
	}
}
