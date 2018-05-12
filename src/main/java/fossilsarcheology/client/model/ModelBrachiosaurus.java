package fossilsarcheology.client.model;

import fossilsarcheology.server.entity.prehistoric.EntityPrehistoric;
import net.ilexiconn.llibrary.client.model.ModelAnimator;
import net.ilexiconn.llibrary.client.model.tools.AdvancedModelRenderer;
import net.ilexiconn.llibrary.server.animation.IAnimatedEntity;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelBrachiosaurus extends ModelPrehistoric {
	public final AdvancedModelRenderer BackThighRight;
	public final AdvancedModelRenderer BackThighLeft;
	public final AdvancedModelRenderer FrontThighRight;
	public final AdvancedModelRenderer FrontThighLeft;
	public final AdvancedModelRenderer LowerBody;
	public final AdvancedModelRenderer BackLegRight;
	public final AdvancedModelRenderer BackLegLeft;
	public final AdvancedModelRenderer FrontLegRight;
	public final AdvancedModelRenderer FrontLegLeft;
	public final AdvancedModelRenderer MidBody;
	public final AdvancedModelRenderer Tail;
	public final AdvancedModelRenderer UpperBody;
	public final AdvancedModelRenderer Neck1;
	public final AdvancedModelRenderer Neck2;
	public final AdvancedModelRenderer Neck3;
	public final AdvancedModelRenderer Neck4;
	public final AdvancedModelRenderer Neck5;
	public final AdvancedModelRenderer headpivot;
	public final AdvancedModelRenderer Head;
	public final AdvancedModelRenderer TopJaw;
	public final AdvancedModelRenderer Crest;
	public final AdvancedModelRenderer JawBottom;
	public final AdvancedModelRenderer Tail2;
	public final AdvancedModelRenderer Tail3;
	private final ModelAnimator animator;

	public ModelBrachiosaurus() {
		this.textureWidth = 128;
		this.textureHeight = 64;
		this.BackThighRight = new AdvancedModelRenderer(this, 50, 21);
		this.BackThighRight.mirror = true;
		this.BackThighRight.setRotationPoint(3.0F, 14.1F, 7.0F);
		this.BackThighRight.addBox(-1.0F, -1.5F, -2.0F, 3, 7, 4, 0.0F);
		this.BackThighLeft = new AdvancedModelRenderer(this, 50, 21);
		this.BackThighLeft.setRotationPoint(-3.0F, 14.1F, 7.0F);
		this.BackThighLeft.addBox(-2.0F, -1.5F, -2.0F, 3, 7, 4, 0.0F);
		this.headpivot = new AdvancedModelRenderer(this, 0, 0);
		this.headpivot.setRotationPoint(0.0F, -0.6F, -2.6F);
		this.setRotateAngle(headpivot, 1.0927506446736497F, 0.0F, 0.0F);
		this.Head = new AdvancedModelRenderer(this, 66, 0);
		this.Head.addBox(-2.0F, -1.0F, -3.7F, 4, 3, 4, 0.0F);
		this.FrontLegRight = new AdvancedModelRenderer(this, 0, 24);
		this.FrontLegRight.mirror = true;
		this.FrontLegRight.setRotationPoint(1.5F, 5.0F, 0.5F);
		this.FrontLegRight.addBox(-1.0F, 0.0F, -1.5F, 2, 5, 3, 0.0F);
		this.JawBottom = new AdvancedModelRenderer(this, 55, 10);
		this.JawBottom.setRotationPoint(0.0F, 0.9F, -2.9F);
		this.JawBottom.addBox(-1.0F, 0.0F, -3.0F, 2, 1, 3, 0.0F);
		this.setRotateAngle(JawBottom, -0.22759093446006054F, -0.0F, 0.0F);
		this.Neck2 = new AdvancedModelRenderer(this, 0, 45);
		this.Neck2.setRotationPoint(0.0F, -1.4F, -2.9F);
		this.Neck2.addBox(-2.0F, -1.5F, -3.2F, 4, 5, 4, 0.0F);
		this.setRotateAngle(Neck2, -0.091106186954104F, -0.0F, 0.0F);
		this.Tail3 = new AdvancedModelRenderer(this, 34, 10);
		this.Tail3.setRotationPoint(0.0F, 0.9F, 4.3F);
		this.Tail3.addBox(-1.0F, -1.5F, 0.0F, 2, 3, 6, 0.0F);
		this.setRotateAngle(Tail3, 0.04293509959906051F, 0.0F, 0.0F);
		this.BackLegRight = new AdvancedModelRenderer(this, 0, 24);
		this.BackLegRight.mirror = true;
		this.BackLegRight.setRotationPoint(0.5F, 5.0F, 0.0F);
		this.BackLegRight.addBox(-1.0F, 0.0F, -1.5F, 2, 5, 3, 0.0F);
		this.Neck1 = new AdvancedModelRenderer(this, 0, 33);
		this.Neck1.setRotationPoint(0.0F, -1.1F, -5.8F);
		this.Neck1.addBox(-3.0F, -3.0F, -3.0F, 6, 6, 5, 0.0F);
		this.setRotateAngle(Neck1, -0.27314402793711257F, -0.0F, 0.0F);
		this.Tail = new AdvancedModelRenderer(this, 0, 13);
		this.Tail.setRotationPoint(0.0F, -2.5F, 4.3F);
		this.Tail.addBox(-2.5F, -2.5F, 0.0F, 5, 5, 5, 0.0F);
		this.setRotateAngle(Tail, -0.136659280431156F, 0.045553093477052F, 0.0F);
		this.Crest = new AdvancedModelRenderer(this, 52, 0);
		this.Crest.setRotationPoint(0.0F, 0.9F, -2.8F);
		this.Crest.addBox(-1.0F, -3.7F, -2.6F, 2, 4, 4, 0.0F);
		this.setRotateAngle(Crest, -0.5918411493512771F, 0.0F, 0.0F);
		this.MidBody = new AdvancedModelRenderer(this, 45, 34);
		this.MidBody.setRotationPoint(0.0F, -0.7F, 0.9F);
		this.MidBody.addBox(-3.5F, -4.5F, -3.9F, 7, 9, 4, 0.0F);
		this.BackLegLeft = new AdvancedModelRenderer(this, 0, 24);
		this.BackLegLeft.setRotationPoint(-0.5F, 5.0F, 0.0F);
		this.BackLegLeft.addBox(-1.0F, 0.0F, -1.5F, 2, 5, 3, 0.0F);
		this.Neck5 = new AdvancedModelRenderer(this, 23, 33);
		this.Neck5.setRotationPoint(0.0F, -0.05F, -6.4F);
		this.Neck5.addBox(-1.0F, -1.5F, -3.0F, 2, 3, 3, 0.0F);
		this.setRotateAngle(Neck5, 0.23806290997202653F, -0.0F, 0.0F);
		this.LowerBody = new AdvancedModelRenderer(this, 73, 48);
		this.LowerBody.setRotationPoint(0.0F, 14.6F, 3.4F);
		this.LowerBody.addBox(-3.0F, -5.0F, 0.0F, 6, 8, 5, 0.0F);
		this.setRotateAngle(LowerBody, -0.31869712141416456F, -0.0F, 0.0F);
		this.TopJaw = new AdvancedModelRenderer(this, 67, 8);
		this.TopJaw.setRotationPoint(0.0F, 0.0F, -2.7F);
		this.TopJaw.addBox(-1.5F, -1.0F, -3.3F, 3, 2, 4, 0.0F);
		this.setRotateAngle(TopJaw, -0.136659280431156F, -0.0F, 0.0F);
		this.FrontThighRight = new AdvancedModelRenderer(this, 50, 21);
		this.FrontThighRight.mirror = true;
		this.FrontThighRight.setRotationPoint(2.5F, 14.0F, -3.0F);
		this.FrontThighRight.addBox(0.0F, -2.0F, -1.5F, 3, 7, 4, 0.0F);
		this.Neck4 = new AdvancedModelRenderer(this, 18, 41);
		this.Neck4.setRotationPoint(0.0F, -0.4F, -6.6F);
		this.Neck4.addBox(-1.5F, -1.5F, -7.0F, 3, 3, 7, 0.0F);
		this.setRotateAngle(Neck4, -0.07365289443416069F, -0.0F, 0.0F);
		this.UpperBody = new AdvancedModelRenderer(this, 44, 48);
		this.UpperBody.setRotationPoint(0.0F, 0.0F, -3.6F);
		this.UpperBody.addBox(-4.0F, -4.5F, -6.0F, 8, 9, 6, 0.0F);
		this.setRotateAngle(UpperBody, -0.045553093477052F, -0.0F, 0.0F);
		this.Neck3 = new AdvancedModelRenderer(this, 17, 53);
		this.Neck3.setRotationPoint(0.0F, 0.9F, -3.1F);
		this.Neck3.addBox(-2.0F, -2.0F, -7.0F, 4, 4, 7, 0.0F);
		this.setRotateAngle(Neck3, -0.06981317007977318F, -0.0F, 0.0F);
		this.FrontThighLeft = new AdvancedModelRenderer(this, 50, 21);
		this.FrontThighLeft.setRotationPoint(-2.5F, 14.0F, -3.0F);
		this.FrontThighLeft.addBox(-3.0F, -2.0F, -1.5F, 3, 7, 4, 0.0F);
		this.FrontLegLeft = new AdvancedModelRenderer(this, 0, 24);
		this.FrontLegLeft.setRotationPoint(-1.5F, 5.0F, 0.5F);
		this.FrontLegLeft.addBox(-1.0F, 0.0F, -1.5F, 2, 5, 3, 0.0F);
		this.Tail2 = new AdvancedModelRenderer(this, 17, 1);
		this.Tail2.setRotationPoint(0.0F, -1.3F, 4.4F);
		this.Tail2.addBox(-1.5F, -1.0F, 0.0F, 3, 4, 5, 0.0F);
		this.setRotateAngle(Tail2, 0.18203784098300857F, 0.05044001538263612F, 0.0F);
		this.Neck5.addChild(this.headpivot);
		this.headpivot.addChild(this.Head);
		this.FrontThighRight.addChild(this.FrontLegRight);
		this.Head.addChild(this.JawBottom);
		this.Neck1.addChild(this.Neck2);
		this.Tail2.addChild(this.Tail3);
		this.BackThighRight.addChild(this.BackLegRight);
		this.UpperBody.addChild(this.Neck1);
		this.LowerBody.addChild(this.Tail);
		this.Head.addChild(this.Crest);
		this.LowerBody.addChild(this.MidBody);
		this.BackThighLeft.addChild(this.BackLegLeft);
		this.Neck4.addChild(this.Neck5);
		this.Head.addChild(this.TopJaw);
		this.Neck3.addChild(this.Neck4);
		this.MidBody.addChild(this.UpperBody);
		this.Neck2.addChild(this.Neck3);
		this.FrontThighLeft.addChild(this.FrontLegLeft);
		this.Tail.addChild(this.Tail2);
		this.updateDefaultPose();
		animator = ModelAnimator.create();
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		animate((IAnimatedEntity) entity, f, f1, f2, f3, f4, f5);
		this.BackThighRight.render(f5);
		this.BackThighLeft.render(f5);
		this.LowerBody.render(f5);
		this.FrontThighRight.render(f5);
		this.FrontThighLeft.render(f5);
	}

	public void animate(IAnimatedEntity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		EntityPrehistoric prehistoric = (EntityPrehistoric) entity;
		animator.update(entity);
		blockMovement(f, f1, f2, f3, f4, f5, (Entity) entity);
		this.resetToDefaultPose();
		setRotationAngles(f, f1, f2, f3, f4, f5, (Entity) entity);
		animator.setAnimation(prehistoric.SPEAK_ANIMATION);
		animator.startKeyframe(10);
		ModelUtils.rotate(animator, JawBottom, 20, 0, 0);
		animator.endKeyframe();
		animator.resetKeyframe(10);
		animator.setAnimation(prehistoric.ATTACK_ANIMATION);
		animator.startKeyframe(20);
		animator.move(FrontThighLeft, 0, -4, 3);
		animator.move(FrontThighRight, 0, -4, 3);
		animator.move(BackThighLeft, 0, 1, 0);
		animator.move(BackThighRight, 0, 1, 0);
		ModelUtils.rotate(animator, LowerBody, -50, 0, 0);
		ModelUtils.rotate(animator, Neck2, 2, 0, 0);
		ModelUtils.rotate(animator, Neck3, 1, 0, 0);
		ModelUtils.rotate(animator, Neck4, 14, 0, 0);
		ModelUtils.rotate(animator, FrontThighRight, -28, 0, 0);
		ModelUtils.rotate(animator, FrontThighLeft, -28, 0, 0);
		ModelUtils.rotate(animator, FrontLegRight, 28, 0, 0);
		ModelUtils.rotate(animator, FrontLegLeft, 28, 0, 0);
		ModelUtils.rotate(animator, BackThighRight, -32, 0, 0);
		ModelUtils.rotate(animator, BackThighLeft, -32, 0, 0);
		ModelUtils.rotate(animator, BackLegRight, 32, 0, 0);
		ModelUtils.rotate(animator, BackLegLeft, 32, 0, 0);
		ModelUtils.rotate(animator, Tail, 0, 0, 0);
		ModelUtils.rotate(animator, Tail2, 16, 0, 0);
		ModelUtils.rotate(animator, Tail3, 14, 0, 0);
		animator.endKeyframe();
		animator.setStaticKeyframe(5);
		animator.endKeyframe();
		animator.resetKeyframe(5);
	}

	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		AdvancedModelRenderer[] tailParts = {this.Tail, this.Tail2, this.Tail3};
		AdvancedModelRenderer[] neckParts = {this.Neck1, this.Neck2, this.Neck3, this.Neck4, this.Neck5, this.Head};
		if (((EntityPrehistoric) entity).isSkeleton()) {
			return;
		}
		ModelUtils.faceTargetMod(Head, f3, f4, 0.16F);
		ModelUtils.faceTargetMod(Neck1, f3, f4, 0.16F);
		ModelUtils.faceTargetMod(Neck2, f3, f4, 0.16F);
		ModelUtils.faceTargetMod(Neck3, f3, f4, 0.16F);
		ModelUtils.faceTargetMod(Neck4, f3, f4, 0.16F);
		ModelUtils.faceTargetMod(Neck5, f3, f4, 0.16F);

		float speed = 0.1F;
		float speed2 = 0.45F;
		float degree = 0.5F;
		{
			float sitProgress = ((EntityPrehistoric) (entity)).sitProgress;
			this.chainWave(tailParts, speed, degree * 0.05F, -3, entity.ticksExisted, 1);
			this.chainSwing(tailParts, speed, degree * 0.15F, -2, entity.ticksExisted, 1);
			this.chainWave(neckParts, speed, degree * 0.05F, 3, entity.ticksExisted, 1);
			this.bob(LowerBody, speed, degree * 0.5F, false, entity.ticksExisted, 1);
			this.walk(UpperBody, speed, degree * 0.05F, true, 0F, 0F, entity.ticksExisted, 1);
			this.walk(MidBody, speed, degree * 0.05F, true, 0F, 0F, entity.ticksExisted, 1);
			this.walk(FrontThighLeft, speed2, degree * 0.35F, true, 0F, 0.2F, f, f1);
			this.walk(FrontThighRight, speed2, degree * 0.35F, false, 0F, -0.2F, f, f1);
			this.walk(BackThighLeft, speed2, degree * 0.35F, false, 0F, -0.2F, f, f1);
			this.walk(BackThighRight, speed2, degree * 0.35F, true, 0F, 0.2F, f, f1);
			this.walk(FrontLegLeft, speed2, degree * 0.3F, true, 0F, 0.2F, f, f1);
			this.walk(FrontLegRight, speed2, degree * 0.3F, false, 0F, -0.2F, f, f1);
			this.walk(BackLegLeft, speed2, degree * 0.3F, false, 0F, 0.2F, f, f1);
			this.walk(BackLegRight, speed2, degree * 0.3F, true, 0F, -0.2F, f, f1);
			sitAnimationRotation(Neck3, sitProgress, -((float) Math.toRadians(2.0D)), 0, 0);
			sitAnimationRotation(BackLegLeft, sitProgress, (float) Math.toRadians(58.0D), 0, 0);
			sitAnimationRotation(Neck2, sitProgress, -((float) Math.toRadians(5.22D)), 0, 0);
			sitAnimationRotation(FrontLegRight, sitProgress, -((float) Math.toRadians(25.0D)), 0, 0);
			sitAnimationRotation(Tail2, sitProgress, (float) Math.toRadians(3D), 0, 0);
			sitAnimationRotation(FrontThighRight, sitProgress, -((float) Math.toRadians(66.0D)), 0, 0);
			sitAnimationRotation(BackThighLeft, sitProgress, (float) Math.toRadians(55.0D), 0, 0);
			sitAnimationRotation(BackLegRight, sitProgress, (float) Math.toRadians(58.0D), 0, 0);
			sitAnimationRotation(BackThighRight, sitProgress, (float) Math.toRadians(55.0D), 0, 0);
			sitAnimationRotation(Tail3, sitProgress, (float) Math.toRadians(2.46D), 0, 0);
			sitAnimationRotation(LowerBody, sitProgress, -((float) Math.toRadians(13.04D)), 0, 0);
			sitAnimationRotation(FrontThighLeft, sitProgress, -((float) Math.toRadians(66.0D)), 0, 0);
			sitAnimationRotation(Neck1, sitProgress, -((float) Math.toRadians(5.43D)), 0, 0);
			sitAnimationRotation(Neck5, sitProgress, (float) Math.toRadians(3.64D), 0, 0);
			sitAnimationRotation(headpivot, sitProgress, (float) Math.toRadians(20D), 0, 0);
			sitAnimationRotation(FrontLegLeft, sitProgress, -((float) Math.toRadians(25.0D)), 0, 0);
			sitAnimationRotation(MidBody, sitProgress, (float) Math.toRadians(5.22D), 0, 0);
			sitAnimationRotation(UpperBody, sitProgress, -((float) Math.toRadians(2.61D)), 0, 0);
			sitAnimationRotation(Tail, sitProgress, ((float) Math.toRadians(15.65D)), 0, 0);
			sitAnimationRotation(Neck4, sitProgress, -((float) Math.toRadians(4.22D)), 0, 0);
			sitAnimationPos(LowerBody, sitProgress, 0, 5.8F, 3.4F);
			sitAnimationPos(FrontThighLeft, sitProgress, 0F, 4F, 2F);
			sitAnimationPos(FrontThighRight, sitProgress, 0F, 4F, 2F);
			sitAnimationPos(BackThighLeft, sitProgress, 0F, 5.1F, 2F);
			sitAnimationPos(BackThighRight, sitProgress, 0F, 5.1F, 2F);
		}
		{
			float sitProgress = ((EntityPrehistoric) (entity)).sleepProgress;
			sitAnimationRotation(LowerBody, sitProgress, -((float) Math.toRadians(13.04D)), 0, 0);
			sitAnimationRotation(Head, sitProgress, (float) Math.toRadians(13.04D), (float) Math.toRadians(5.22D), (float) Math.toRadians(7.83D));
			sitAnimationRotation(TopJaw, sitProgress, -((float) Math.toRadians(7.83D)), 0, 0);
			sitAnimationRotation(UpperBody, sitProgress, -((float) Math.toRadians(2.61D)), (float) Math.toRadians(5.22D), 0);
			sitAnimationRotation(Neck5, sitProgress, (float) Math.toRadians(13.64D), (float) Math.toRadians(26.09D), (float) Math.toRadians(23.48D));
			sitAnimationRotation(Neck1, sitProgress, -((float) Math.toRadians(5.22D)), 0, 0);
			sitAnimationRotation(Neck2, sitProgress, -((float) Math.toRadians(2.61D)), (float) Math.toRadians(13.04D), 0);
			sitAnimationRotation(FrontLegLeft, sitProgress, -((float) Math.toRadians(25.0D)), 0, 0);
			sitAnimationRotation(FrontLegRight, sitProgress, -((float) Math.toRadians(25.0D)), 0, 0);
			sitAnimationRotation(BackThighRight, sitProgress, (float) Math.toRadians(57.39D), (float) Math.toRadians(15.65D), -((float) Math.toRadians(15.65D)));
			sitAnimationRotation(MidBody, sitProgress, (float) Math.toRadians(5.22D), 0, 0);
			sitAnimationRotation(Crest, sitProgress, -((float) Math.toRadians(33.91D)), 0, 0);
			sitAnimationRotation(JawBottom, sitProgress, -((float) Math.toRadians(13.04D)), 0, 0);
			sitAnimationRotation(Neck4, sitProgress, (float) Math.toRadians(7.83D), (float) Math.toRadians(23.48D), (float) Math.toRadians(10.43D));
			sitAnimationRotation(BackLegLeft, sitProgress, (float) Math.toRadians(18.0D), 0, 0);
			sitAnimationRotation(Tail3, sitProgress, (float) Math.toRadians(13.04D), 0, 0);
			sitAnimationRotation(BackThighLeft, sitProgress, (float) Math.toRadians(65.0D), -((float) Math.toRadians(15.65D)), 0);
			sitAnimationRotation(Tail2, sitProgress, (float) Math.toRadians(10.43D), -((float) Math.toRadians(23.48D)), -((float) Math.toRadians(13.04D)));
			sitAnimationRotation(Neck3, sitProgress, -((float) Math.toRadians(10.43D)), (float) Math.toRadians(18.26D), (float) Math.toRadians(7.83D));
			sitAnimationRotation(FrontThighRight, sitProgress, -((float) Math.toRadians(60.0D)), (float) Math.toRadians(7.83D), -((float) Math.toRadians(15.65D)));
			sitAnimationRotation(Tail, sitProgress, -((float) Math.toRadians(15.65D)), -((float) Math.toRadians(2.61D)), 0);
			sitAnimationRotation(BackLegRight, sitProgress, (float) Math.toRadians(18.0D), 0, 0);
			sitAnimationRotation(FrontThighLeft, sitProgress, -((float) Math.toRadians(54.78D)), (float) Math.toRadians(20.87D), (float) Math.toRadians(13.04D));
			sitAnimationPos(LowerBody, sitProgress, 0, 5.8F, 3.4F);
			sitAnimationPos(FrontThighLeft, sitProgress, 0F, 4F, 2F);
			sitAnimationPos(FrontThighRight, sitProgress, 0F, 4F, 2F);
			sitAnimationPos(BackThighLeft, sitProgress, 0F, 5.1F, 2F);
			sitAnimationPos(BackThighRight, sitProgress, 0F, 5.1F, 2F);
		}
		((EntityPrehistoric) entity).chainBuffer.applyChainSwingBuffer((ModelRenderer[]) tailParts);
	}

}
