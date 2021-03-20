
package net.mcreator.oceanicequipment.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.world.World;
import net.minecraft.item.UseAction;
import net.minecraft.item.Rarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.Food;
import net.minecraft.entity.LivingEntity;

import net.mcreator.oceanicequipment.procedures.FishoilFoodEatenProcedure;
import net.mcreator.oceanicequipment.itemgroup.OceanicequpmentItemGroup;
import net.mcreator.oceanicequipment.OceanicEquipmentModElements;

import java.util.Map;
import java.util.HashMap;

@OceanicEquipmentModElements.ModElement.Tag
public class FishoilItem extends OceanicEquipmentModElements.ModElement {
	@ObjectHolder("oceanic_equipment:fishoil")
	public static final Item block = null;
	public FishoilItem(OceanicEquipmentModElements instance) {
		super(instance, 4);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new FoodItemCustom());
	}
	public static class FoodItemCustom extends Item {
		public FoodItemCustom() {
			super(new Item.Properties().group(OceanicequpmentItemGroup.tab).maxStackSize(1).rarity(Rarity.COMMON)
					.food((new Food.Builder()).hunger(4).saturation(0.3f).setAlwaysEdible().build()));
			setRegistryName("fishoil");
		}

		@Override
		public int getUseDuration(ItemStack stack) {
			return 16;
		}

		@Override
		public UseAction getUseAction(ItemStack itemstack) {
			return UseAction.DRINK;
		}

		@Override
		public net.minecraft.util.SoundEvent getEatSound() {
			return net.minecraft.util.SoundEvents.ENTITY_GENERIC_DRINK;
		}

		@Override
		public ItemStack onItemUseFinish(ItemStack itemstack, World world, LivingEntity entity) {
			ItemStack retval = super.onItemUseFinish(itemstack, world, entity);
			double x = entity.getPosX();
			double y = entity.getPosY();
			double z = entity.getPosZ();
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("entity", entity);
				FishoilFoodEatenProcedure.executeProcedure($_dependencies);
			}
			return retval;
		}
	}
}
