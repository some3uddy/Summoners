package com.mlh.summoners.client.models

import com.mlh.summoners.entities.ScarabEntity
import com.mojang.blaze3d.matrix.MatrixStack
import com.mojang.blaze3d.vertex.IVertexBuilder
import net.minecraft.client.renderer.entity.model.EntityModel
import net.minecraft.client.renderer.model.ModelRenderer
import net.minecraft.util.math.MathHelper
import kotlin.math.PI

class ScarabModel : EntityModel<ScarabEntity>() {
    private val head = ModelRenderer(this)
    private val rightWing = ModelRenderer(this)
    private val leftWing = ModelRenderer(this)
    private val body = ModelRenderer(this)
    private val leftFrontLeg = ModelRenderer(this)
    private val leftMiddleLeg = ModelRenderer(this)
    private val leftBackLeg = ModelRenderer(this)
    private val rightFrontLeg = ModelRenderer(this)
    private val rightMiddleLeg = ModelRenderer(this)
    private val rightBackLeg = ModelRenderer(this)

    init {
        textureWidth = 16
        textureHeight = 16

        head.setRotationPoint(0.0f, 20.15f, -7.7f)
        head.setTextureOffset(0, 0).addBox(-2.5f, 0.0f, -4.8f, 1.0f, 1.0f, 3.0f, 0.0f, false)
        head.setTextureOffset(0, 0).addBox(1.0f, 0.0f, -5.8f, 1.0f, 1.0f, 1.0f, 0.0f, false)
        head.setTextureOffset(0, 0).addBox(1.5f, 0.0f, -4.8f, 1.0f, 1.0f, 3.0f, 0.0f, false)
        head.setTextureOffset(0, 0).addBox(-2.0f, 0.0f, -5.8f, 1.0f, 1.0f, 1.0f, 0.0f, false)
        head.setTextureOffset(0, 0).addBox(-2.0f, -2.0f, -2.3f, 4.0f, 4.0f, 4.0f, 0.0f, false)

        rightWing.setRotationPoint(0.0f, 17.5f, -3.5f)
        rightWing.setTextureOffset(0, 0).addBox(-3.0f, 0.0f, 0.0f, 3.0f, 4.0f, 11.0f, 0.0f, false)

        leftWing.setRotationPoint(0.0f, 17.5f, -3.5f)
        leftWing.setTextureOffset(0, 0).addBox(0.0f, 0.0f, 0.0f, 3.0f, 4.0f, 11.0f, 0.0f, false)

        body.setRotationPoint(0.0f, 20.35f, -2.0f)
        body.setTextureOffset(0, 0).addBox(-2.5f, -2.35f, -2.0f, 5.0f, 5.0f, 11.0f, 0.0f, false)
        body.setTextureOffset(0, 0).addBox(-2.5f, -2.65f, -5.0f, 5.0f, 5.0f, 3.0f, 0.0f, false)


        leftFrontLeg.setRotationPoint(2.0f, 22.0f, -5.5f)
        val leftFrontLegRotation = ModelRenderer(this)
        leftFrontLegRotation.setRotationPoint(0.0f, 0.5f, -0.5f)
        leftFrontLeg.addChild(leftFrontLegRotation)
        setRotationAngle(leftFrontLegRotation, 0.0f, 0.3054f, 0.1745f)
        leftFrontLegRotation.setTextureOffset(0, 0).addBox(0.0f, -1.0f, 0.0f, 3.0f, 1.0f, 1.0f, 0.0f, true)
        val leftFrontLegSubRotation = ModelRenderer(this)
        leftFrontLegSubRotation.setRotationPoint(2.7364f, -0.5051f, 0.5f)
        leftFrontLegRotation.addChild(leftFrontLegSubRotation)
        setRotationAngle(leftFrontLegSubRotation, 0.0f, 0.0f, 0.48f)
        leftFrontLegSubRotation.setTextureOffset(0, 0).addBox(0.0f, -0.55f, -0.5f, 3.0f, 1.0f, 1.0f, 0.0f, true)

        leftMiddleLeg.setRotationPoint(2.0f, 22.0f, -0.5f)
        val leftMiddleLegRotation = ModelRenderer(this)
        leftMiddleLegRotation.setRotationPoint(0.0f, 0.5f, -0.5f)
        leftMiddleLeg.addChild(leftMiddleLegRotation)
        setRotationAngle(leftMiddleLegRotation, 0.0f, 0.0f, 0.1745f)
        leftMiddleLegRotation.setTextureOffset(0, 0).addBox(0.0f, -1.0f, 0.0f, 3.0f, 1.0f, 1.0f, 0.0f, true)
        val leftMiddleLegSubRotation = ModelRenderer(this)
        leftMiddleLegSubRotation.setRotationPoint(2.7364f, -0.5051f, 0.5f)
        leftMiddleLegRotation.addChild(leftMiddleLegSubRotation)
        setRotationAngle(leftMiddleLegSubRotation, 0.0f, 0.0f, 0.48f)
        leftMiddleLegSubRotation.setTextureOffset(0, 0).addBox(0.0f, -0.55f, -0.5f, 3.0f, 1.0f, 1.0f, 0.0f, true)


        leftBackLeg.setRotationPoint(2.0f, 22.0f, 4.5f)
        val leftBackLegRotation = ModelRenderer(this)
        leftBackLegRotation.setRotationPoint(0.0f, 0.5f, -0.5f)
        leftBackLeg.addChild(leftBackLegRotation)
        setRotationAngle(leftBackLegRotation, 0.0f, -0.3054f, 0.1745f)
        leftBackLegRotation.setTextureOffset(0, 0).addBox(0.0f, -1.0f, 0.0f, 3.0f, 1.0f, 1.0f, 0.0f, true)
        val leftBackLegSubRotation = ModelRenderer(this)
        leftBackLegSubRotation.setRotationPoint(2.7364f, -0.5051f, 0.5f)
        leftBackLegRotation.addChild(leftBackLegSubRotation)
        setRotationAngle(leftBackLegSubRotation, 0.0f, 0.0f, 0.48f)
        leftBackLegSubRotation.setTextureOffset(0, 0).addBox(0.0f, -0.55f, -0.5f, 3.0f, 1.0f, 1.0f, 0.0f, true)

        rightFrontLeg.setRotationPoint(-2.0f, 22.0f, -5.5f)
        val rightFrontLegRotation = ModelRenderer(this)
        rightFrontLegRotation.setRotationPoint(0.0f, 0.5f, -0.5f)
        rightFrontLeg.addChild(rightFrontLegRotation)
        setRotationAngle(rightFrontLegRotation, 0.0f, -0.3054f, -0.1745f)
        rightFrontLegRotation.setTextureOffset(0, 0).addBox(-3.0f, -1.0f, 0.0f, 3.0f, 1.0f, 1.0f, 0.0f, false)
        val rightFrontLegSubRotation = ModelRenderer(this)
        rightFrontLegSubRotation.setRotationPoint(-2.7364f, -0.5051f, 0.5f)
        rightFrontLegRotation.addChild(rightFrontLegSubRotation)
        setRotationAngle(rightFrontLegSubRotation, 0.0f, 0.0f, -0.48f)
        rightFrontLegSubRotation.setTextureOffset(0, 0).addBox(-3.0f, -0.55f, -0.5f, 3.0f, 1.0f, 1.0f, 0.0f, false)

        rightMiddleLeg.setRotationPoint(-2.0f, 22.0f, -0.5f)
        val rightMiddleLegRotation = ModelRenderer(this)
        rightMiddleLegRotation.setRotationPoint(0.0f, 0.5f, -0.5f)
        rightMiddleLeg.addChild(rightMiddleLegRotation)
        setRotationAngle(rightMiddleLegRotation, 0.0f, 0.0f, -0.1745f)
        rightMiddleLegRotation.setTextureOffset(0, 0).addBox(-3.0f, -1.0f, 0.0f, 3.0f, 1.0f, 1.0f, 0.0f, false)
        val rightMiddleLegSubRotation = ModelRenderer(this)
        rightMiddleLegSubRotation.setRotationPoint(-2.7364f, -0.5051f, 0.5f)
        rightMiddleLegRotation.addChild(rightMiddleLegSubRotation)
        setRotationAngle(rightMiddleLegSubRotation, 0.0f, 0.0f, -0.48f)
        rightMiddleLegSubRotation.setTextureOffset(0, 0).addBox(-3.0f, -0.55f, -0.5f, 3.0f, 1.0f, 1.0f, 0.0f, false)


        rightBackLeg.setRotationPoint(-2.0f, 22.0f, 4.5f)
        val rightBackLegRotation = ModelRenderer(this)
        rightBackLegRotation.setRotationPoint(0.0f, 0.5f, -0.5f)
        rightBackLeg.addChild(rightBackLegRotation)
        setRotationAngle(rightBackLegRotation, 0.0f, 0.3054f, -0.1745f)
        rightBackLegRotation.setTextureOffset(0, 0).addBox(-3.0f, -1.0f, 0.0f, 3.0f, 1.0f, 1.0f, 0.0f, false)
        val rightBackLegSubRotation = ModelRenderer(this)
        rightBackLegSubRotation.setRotationPoint(-2.7364f, -0.5051f, 0.5f)
        rightBackLegRotation.addChild(rightBackLegSubRotation)
        setRotationAngle(rightBackLegSubRotation, 0.0f, 0.0f, -0.48f)
        rightBackLegSubRotation.setTextureOffset(0, 0).addBox(-3.0f, -0.55f, -0.5f, 3.0f, 1.0f, 1.0f, 0.0f, false)
    }

    override fun render(matrixStack: MatrixStack, buffer: IVertexBuilder, packedLight: Int, packedOverlay: Int, red: Float, green: Float, blue: Float, alpha: Float) {
        head.render(matrixStack, buffer, packedLight, packedOverlay)
        rightWing.render(matrixStack, buffer, packedLight, packedOverlay)
        leftWing.render(matrixStack, buffer, packedLight, packedOverlay)
        body.render(matrixStack, buffer, packedLight, packedOverlay)
        rightFrontLeg.render(matrixStack, buffer, packedLight, packedOverlay)
        leftFrontLeg.render(matrixStack, buffer, packedLight, packedOverlay)
        leftMiddleLeg.render(matrixStack, buffer, packedLight, packedOverlay)
        leftBackLeg.render(matrixStack, buffer, packedLight, packedOverlay)
        rightMiddleLeg.render(matrixStack, buffer, packedLight, packedOverlay)
        rightBackLeg.render(matrixStack, buffer, packedLight, packedOverlay)
    }

    private fun setRotationAngle(modelRenderer: ModelRenderer, x: Float, y: Float, z: Float) {
        modelRenderer.rotateAngleX = x
        modelRenderer.rotateAngleY = y
        modelRenderer.rotateAngleZ = z
    }

    override fun setRotationAngles(entity: ScarabEntity, limbSwing: Float, limbSwingAmount: Float, ageInTicks: Float, netHeadYaw: Float, headPitch: Float) {
        val a = PI.toFloat() / 180f * 0.5f
        head.rotateAngleX = headPitch * a
        head.rotateAngleY = netHeadYaw * a

        val b = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount * 0.75f
        leftFrontLeg.rotateAngleY = b
        rightMiddleLeg.rotateAngleY = b
        leftBackLeg.rotateAngleY = b

        val c = MathHelper.cos(limbSwing * 0.6662f + PI.toFloat()) * 1.4f * limbSwingAmount * 0.75f
        rightFrontLeg.rotateAngleY = c
        leftMiddleLeg.rotateAngleY = c
        rightBackLeg.rotateAngleY = c
    }


}