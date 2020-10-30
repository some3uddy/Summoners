package com.mlh.summoners.client.models

import com.mlh.summoners.entities.CanidEntity
import com.mojang.blaze3d.matrix.MatrixStack
import com.mojang.blaze3d.vertex.IVertexBuilder
import net.minecraft.client.renderer.entity.model.EntityModel
import net.minecraft.client.renderer.model.ModelRenderer
import net.minecraft.util.math.MathHelper

class CanidModel : EntityModel<CanidEntity>() {
    private val head = ModelRenderer(this)
    private val body = ModelRenderer(this)
    private val frontBody = ModelRenderer(this)
    private val legBackLeft = ModelRenderer(this)
    private val legBackRight = ModelRenderer(this)
    private val legFrontLeft = ModelRenderer(this)
    private val legFrontRight = ModelRenderer(this)
    private val tail = ModelRenderer(this)

    init {
        textureWidth = 64
        textureHeight = 32
        
        head.setRotationPoint(1.0f, 12.0f, -10.1f)
        head.setTextureOffset(0, 0).addBox(-4.0f, -3.5f, -2.0f, 6.0f, 7.0f, 4.0f, 0.0f, false)
        head.setTextureOffset(16, 14).addBox(0.0f, -5.5f, 0.0f, 2.0f, 2.0f, 1.0f, 0.0f, false)
        head.setTextureOffset(16, 14).addBox(-4.0f, -5.5f, 0.0f, 2.0f, 2.0f, 1.0f, 0.0f, false)
        head.setTextureOffset(0, 10).addBox(-2.5f, 0.23f, -6.0f, 3.0f, 3.0f, 4.0f, 0.0f, false)
        head.setTextureOffset(0, 0).addBox(-3.5f, -2.5f, -3.0f, 5.0f, 6.0f, 1.0f, 0.0f, false)

        body.setRotationPoint(0.0f, 12.0f, 3.0f)
        val bodyRotation = ModelRenderer(this)
        bodyRotation.setRotationPoint(0.0f, -1.9f, -0.275f)
        body.addChild(bodyRotation)
        setRotationAngle(bodyRotation, 1.5708f, 0.0f, 0.0f)
        bodyRotation.setTextureOffset(18, 14).addBox(-3.0f, -4.75f, -5.375f, 6.0f, 11.0f, 7.0f, 0.0f, false)
        bodyRotation.setTextureOffset(0, 0).addBox(-0.5f, -4.75f, 1.375f, 1.0f, 8.0f, 1.0f, 0.0f, false)

        frontBody.setRotationPoint(0.0f, 12.0f, -2.0f)
        val frontBodyRotation = ModelRenderer(this)
        frontBodyRotation.setRotationPoint(0.0f, 0.0f, -0.025f)
        frontBody.addChild(frontBodyRotation)
        setRotationAngle(frontBodyRotation, 1.5708f, 0.0f, 0.0f)
        frontBodyRotation.setTextureOffset(21, 0).addBox(-3.5f, -7.0f, -3.975f, 7.0f, 7.0f, 8.0f, 0.0f, false)
        frontBodyRotation.setTextureOffset(0, 0).addBox(-0.5f, -6.0f, 3.775f, 1.0f, 6.0f, 1.0f, 0.0f, false)

        legBackLeft.setRotationPoint(1.5f, 15.0f, 7.0f)
        legBackLeft.setTextureOffset(0, 18).addBox(-1.0f, 0.0f, -1.0f, 2.0f, 9.0f, 2.0f, 0.0f, false)

        legBackRight.setRotationPoint(-1.5f, 15.0f, 7.0f)
        legBackRight.setTextureOffset(0, 18).addBox(-1.0f, 0.0f, -1.0f, 2.0f, 9.0f, 2.0f, 0.0f, false)

        legFrontLeft.setRotationPoint(1.5f, 15.0f, -7.0f)
        legFrontLeft.setTextureOffset(0, 18).addBox(-1.0f, 0.0f, -1.0f, 2.0f, 9.0f, 2.0f, 0.0f, false)

        legFrontRight.setRotationPoint(-1.5f, 15.0f, -7.0f)
        legFrontRight.setTextureOffset(0, 18).addBox(-1.0f, 0.0f, -1.0f, 2.0f, 9.0f, 2.0f, 0.0f, false)

        tail.setRotationPoint(0.0f, 10.5f, 9.0f)
        tail.setTextureOffset(9, 18).addBox(-1.5f, -1.0f, 0.0f, 3.0f, 9.0f, 2.0f, 0.0f, false)
    }

    private fun setRotationAngle(modelRenderer: ModelRenderer, x: Float, y: Float, z: Float) {
        modelRenderer.rotateAngleX = x
        modelRenderer.rotateAngleY = y
        modelRenderer.rotateAngleZ = z
    }

    override fun render(matrixStack: MatrixStack, buffer: IVertexBuilder, packedLight: Int, packedOverlay: Int, red: Float, green: Float, blue: Float, alpha: Float) {
        head.render(matrixStack, buffer, packedLight, packedOverlay)
        body.render(matrixStack, buffer, packedLight, packedOverlay)
        frontBody.render(matrixStack, buffer, packedLight, packedOverlay)
        legBackLeft.render(matrixStack, buffer, packedLight, packedOverlay)
        legBackRight.render(matrixStack, buffer, packedLight, packedOverlay)
        legFrontLeft.render(matrixStack, buffer, packedLight, packedOverlay)
        legFrontRight.render(matrixStack, buffer, packedLight, packedOverlay)
        tail.render(matrixStack, buffer, packedLight, packedOverlay)
    }

    override fun setRotationAngles(entityIn: CanidEntity, limbSwing: Float, limbSwingAmount: Float, ageInTicks: Float, netHeadYaw: Float, headPitch: Float) {
        head.rotateAngleX = headPitch * (Math.PI.toFloat() / 180f)
        head.rotateAngleY = netHeadYaw * (Math.PI.toFloat() / 180f)
        legBackRight.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount
        legBackLeft.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f + Math.PI.toFloat()) * 1.4f * limbSwingAmount
        legFrontRight.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f + Math.PI.toFloat()) * 1.4f * limbSwingAmount
        legFrontLeft.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount
    }
}