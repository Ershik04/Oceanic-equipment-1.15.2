
package net.mcreator.oceanicequipment.entity;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.DamageSource;
import net.minecraft.network.IPacket;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.LeapAtTargetGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.Entity;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.block.BlockState;

import net.mcreator.oceanicequipment.item.DeepfragmentItem;
import net.mcreator.oceanicequipment.OceanicEquipmentModElements;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mojang.blaze3d.matrix.MatrixStack;

@OceanicEquipmentModElements.ModElement.Tag
public class WaterorkEntity extends OceanicEquipmentModElements.ModElement {
	public static EntityType entity = null;
	public WaterorkEntity(OceanicEquipmentModElements instance) {
		super(instance, 3);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@Override
	public void initElements() {
		entity = (EntityType.Builder.<CustomEntity>create(CustomEntity::new, EntityClassification.MONSTER).setShouldReceiveVelocityUpdates(true)
				.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(CustomEntity::new).size(0.6f, 1.8f)).build("water_orc")
						.setRegistryName("water_orc");
		elements.entities.add(() -> entity);
		elements.items.add(() -> new SpawnEggItem(entity, -10053121, -10066177, new Item.Properties().group(ItemGroup.MISC))
				.setRegistryName("water_orc_spawn_egg"));
	}

	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public void registerModels(ModelRegistryEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(entity, renderManager -> {
			return new MobRenderer(renderManager, new Modelcustom_model(), 0.5f) {
				{
					this.addLayer(new GlowingLayer<>(this));
				}
				@Override
				public ResourceLocation getEntityTexture(Entity entity) {
					return new ResourceLocation("oceanic_equipment:textures/water.png");
				}
			};
		});
	}
	public static class CustomEntity extends MonsterEntity {
		public CustomEntity(FMLPlayMessages.SpawnEntity packet, World world) {
			this(entity, world);
		}

		public CustomEntity(EntityType<CustomEntity> type, World world) {
			super(type, world);
			experienceValue = 50;
			setNoAI(false);
			enablePersistence();
		}

		@Override
		public IPacket<?> createSpawnPacket() {
			return NetworkHooks.getEntitySpawningPacket(this);
		}

		@Override
		protected void registerGoals() {
			super.registerGoals();
			this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.2, false));
			this.targetSelector.addGoal(2, new NearestAttackableTargetGoal(this, PlayerEntity.class, false, false));
			this.targetSelector.addGoal(3, new NearestAttackableTargetGoal(this, ServerPlayerEntity.class, false, false));
			this.targetSelector.addGoal(4, new NearestAttackableTargetGoal(this, IronGolemEntity.class, false, false));
			this.goalSelector.addGoal(5, new RandomWalkingGoal(this, 1));
			this.targetSelector.addGoal(6, new HurtByTargetGoal(this));
			this.goalSelector.addGoal(7, new LeapAtTargetGoal(this, (float) 0.5));
			this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
			this.goalSelector.addGoal(9, new SwimGoal(this));
		}

		@Override
		public CreatureAttribute getCreatureAttribute() {
			return CreatureAttribute.ILLAGER;
		}

		@Override
		public boolean canDespawn(double distanceToClosestPlayer) {
			return false;
		}

		protected void dropSpecialItems(DamageSource source, int looting, boolean recentlyHitIn) {
			super.dropSpecialItems(source, looting, recentlyHitIn);
			this.entityDropItem(new ItemStack(DeepfragmentItem.block, (int) (1)));
		}

		@Override
		public net.minecraft.util.SoundEvent getAmbientSound() {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.water.ambient"));
		}

		@Override
		public void playStepSound(BlockPos pos, BlockState blockIn) {
			this.playSound((net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.water.ambient")), 0.15f,
					1);
		}

		@Override
		public net.minecraft.util.SoundEvent getHurtSound(DamageSource ds) {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.hurt"));
		}

		@Override
		public net.minecraft.util.SoundEvent getDeathSound() {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("ambient.cave"));
		}

		@Override
		protected void registerAttributes() {
			super.registerAttributes();
			if (this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED) != null)
				this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3);
			if (this.getAttribute(SharedMonsterAttributes.MAX_HEALTH) != null)
				this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(140);
			if (this.getAttribute(SharedMonsterAttributes.ARMOR) != null)
				this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(0);
			if (this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE) == null)
				this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
			this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(20);
		}
	}

	@OnlyIn(Dist.CLIENT)
	private static class GlowingLayer<T extends Entity, M extends EntityModel<T>> extends LayerRenderer<T, M> {
		public GlowingLayer(IEntityRenderer<T, M> er) {
			super(er);
		}

		public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, T entitylivingbaseIn, float limbSwing,
				float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
			IVertexBuilder ivertexbuilder = bufferIn.getBuffer(RenderType.getEyes(new ResourceLocation("oceanic_equipment:textures/water.png")));
			this.getEntityModel().render(matrixStackIn, ivertexbuilder, 15728640, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
		}
	}

	// Made with Blockbench 3.8.0
	// Exported for Minecraft version 1.15
	// Paste this class into your mod and generate all required imports
	public static class Modelcustom_model extends EntityModel<Entity> {
		private final ModelRenderer body;
		private final ModelRenderer right_arm;
		private final ModelRenderer left_arm;
		private final ModelRenderer right_hand;
		private final ModelRenderer left_hand;
		private final ModelRenderer right_leg;
		private final ModelRenderer left_leg;
		private final ModelRenderer right_leg2;
		private final ModelRenderer left_leg2;
		private final ModelRenderer right_leg3;
		private final ModelRenderer left_leg3;
		private final ModelRenderer head;
		public Modelcustom_model() {
			textureWidth = 16;
			textureHeight = 16;
			body = new ModelRenderer(this);
			body.setRotationPoint(0.0F, 24.0F, 0.0F);
			body.setTextureOffset(0, 0).addBox(-6.0F, -37.0F, -4.0F, 12.0F, 17.0F, 8.0F, 0.0F, false);
			right_arm = new ModelRenderer(this);
			right_arm.setRotationPoint(0.0F, 24.0F, 0.0F);
			right_arm.setTextureOffset(0, 0).addBox(6.0F, -35.0F, -3.0F, 7.0F, 19.0F, 6.0F, 0.0F, false);
			left_arm = new ModelRenderer(this);
			left_arm.setRotationPoint(0.0F, 24.0F, 0.0F);
			left_arm.setTextureOffset(0, 0).addBox(-13.0F, -35.0F, -3.0F, 7.0F, 19.0F, 6.0F, 0.0F, false);
			right_hand = new ModelRenderer(this);
			right_hand.setRotationPoint(0.0F, 24.0F, 0.0F);
			right_hand.setTextureOffset(0, 0).addBox(6.0F, -16.0F, -5.0F, 7.0F, 6.0F, 10.0F, 0.0F, false);
			left_hand = new ModelRenderer(this);
			left_hand.setRotationPoint(-19.0F, 24.0F, 0.0F);
			left_hand.setTextureOffset(0, 0).addBox(6.0F, -16.0F, -5.0F, 7.0F, 6.0F, 10.0F, 0.0F, false);
			right_leg = new ModelRenderer(this);
			right_leg.setRotationPoint(-19.0F, 24.0F, 0.0F);
			right_leg.setTextureOffset(0, 0).addBox(14.0F, -20.0F, -3.0F, 4.0F, 13.0F, 6.0F, 0.0F, false);
			left_leg = new ModelRenderer(this);
			left_leg.setRotationPoint(-19.0F, 24.0F, 0.0F);
			left_leg.setTextureOffset(0, 0).addBox(20.0F, -20.0F, -3.0F, 4.0F, 13.0F, 6.0F, 0.0F, false);
			right_leg2 = new ModelRenderer(this);
			right_leg2.setRotationPoint(0.0F, 24.0F, 0.0F);
			right_leg2.setTextureOffset(0, 0).addBox(0.0F, -7.0F, -4.0F, 6.0F, 5.0F, 8.0F, 0.0F, false);
			left_leg2 = new ModelRenderer(this);
			left_leg2.setRotationPoint(0.0F, 24.0F, 0.0F);
			left_leg2.setTextureOffset(0, 0).addBox(-6.0F, -7.0F, -4.0F, 6.0F, 5.0F, 8.0F, 0.0F, false);
			right_leg3 = new ModelRenderer(this);
			right_leg3.setRotationPoint(0.0F, 24.0F, 0.0F);
			right_leg3.setTextureOffset(0, 0).addBox(-6.0F, -2.0F, -4.0F, 6.0F, 2.0F, 11.0F, 0.0F, false);
			left_leg3 = new ModelRenderer(this);
			left_leg3.setRotationPoint(0.0F, 24.0F, 0.0F);
			left_leg3.setTextureOffset(0, 0).addBox(0.0F, -2.0F, -4.0F, 6.0F, 2.0F, 11.0F, 0.0F, false);
			head = new ModelRenderer(this);
			head.setRotationPoint(-19.0F, 24.0F, 0.0F);
			head.setTextureOffset(0, 0).addBox(12.0F, -48.0F, -7.0F, 14.0F, 11.0F, 13.0F, 0.0F, false);
		}

		@Override
		public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue,
				float alpha) {
			body.render(matrixStack, buffer, packedLight, packedOverlay);
			right_arm.render(matrixStack, buffer, packedLight, packedOverlay);
			left_arm.render(matrixStack, buffer, packedLight, packedOverlay);
			right_hand.render(matrixStack, buffer, packedLight, packedOverlay);
			left_hand.render(matrixStack, buffer, packedLight, packedOverlay);
			right_leg.render(matrixStack, buffer, packedLight, packedOverlay);
			left_leg.render(matrixStack, buffer, packedLight, packedOverlay);
			right_leg2.render(matrixStack, buffer, packedLight, packedOverlay);
			left_leg2.render(matrixStack, buffer, packedLight, packedOverlay);
			right_leg3.render(matrixStack, buffer, packedLight, packedOverlay);
			left_leg3.render(matrixStack, buffer, packedLight, packedOverlay);
			head.render(matrixStack, buffer, packedLight, packedOverlay);
		}

		public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}

		public void setRotationAngles(Entity e, float f, float f1, float f2, float f3, float f4) {
		}
	}
}
