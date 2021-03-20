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
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red,
			float green, float blue, float alpha) {
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

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
	}
}