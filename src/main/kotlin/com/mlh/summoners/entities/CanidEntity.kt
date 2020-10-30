package com.mlh.summoners.entities

import com.mlh.summoners.util.CORUNDUM_ITEM
import net.minecraft.block.BlockState
import net.minecraft.entity.*
import net.minecraft.entity.ai.attributes.AttributeModifierMap
import net.minecraft.entity.ai.attributes.Attributes
import net.minecraft.entity.ai.goal.*
import net.minecraft.entity.passive.AnimalEntity
import net.minecraft.entity.passive.TameableEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.crafting.Ingredient
import net.minecraft.network.datasync.DataParameter
import net.minecraft.network.datasync.DataSerializers
import net.minecraft.network.datasync.EntityDataManager
import net.minecraft.util.DamageSource
import net.minecraft.util.SoundEvent
import net.minecraft.util.SoundEvents
import net.minecraft.util.TickRangeConverter
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraft.world.server.ServerWorld
import java.util.*
import java.util.function.Predicate

class CanidEntity(type: EntityType<out CanidEntity>, world: World) : TameableEntity(type, world), IAngerable {

    companion object {
        // change this to summoning leather
        private val TEMPTATION_ITEM = Ingredient.fromItems(CORUNDUM_ITEM)

        // func_233666_p_() -> registerAttributes
        fun getAttributes(): AttributeModifierMap.MutableAttribute = MobEntity.func_233666_p_()
                .createMutableAttribute(Attributes.MAX_HEALTH, 30.0)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.3)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 5.0)
        // add more
        // choose numbers


        // what is this mob hunting if not tamed
        private val TARGET_ENTITIES = Predicate { possibleTarget: LivingEntity ->
            val entitytype = possibleTarget.type
            false//entitytype === EntityType.SHEEP || entitytype === EntityType.RABBIT || entitytype === EntityType.FOX
        }

        private val ANGER_TIME: DataParameter<Int> = EntityDataManager.createKey(CanidEntity::class.java, DataSerializers.VARINT)
    }

    private var angerTarget: UUID? = null
    private val angerTickRange = TickRangeConverter.convertRange(20, 39)

    override fun registerData() {
        super.registerData()

        dataManager.register(ANGER_TIME, 0)
    }

    override fun setTamed(tamed: Boolean) {
        // choose numbers
        super.setTamed(tamed)
        if (tamed) {
            getAttribute(Attributes.MAX_HEALTH)!!.baseValue = 20.0
            this.health = 20.0f
        } else {
            getAttribute(Attributes.MAX_HEALTH)!!.baseValue = 8.0
        }
        getAttribute(Attributes.ATTACK_DAMAGE)!!.baseValue = 4.0
    }

    override fun registerGoals() {
        super.registerGoals()
        val goals = arrayOf(
                1 to SwimGoal(this),
                2 to TemptGoal(this, 1.1, TEMPTATION_ITEM, false),
                3 to LeapAtTargetGoal(this, 0.4f),
                4 to MeleeAttackGoal(this, 1.0, true),
                5 to FollowOwnerGoal(this, 1.0, 10.0f, 2.0f, false),
                6 to WaterAvoidingRandomWalkingGoal(this, 1.0),
                7 to LookAtGoal(this, PlayerEntity::class.java, 8.0f),
                7 to LookRandomlyGoal(this)
        )

        //add sitgoal?
        for ((priority, goal) in goals) {
            goalSelector.addGoal(priority, goal)
        }

        targetSelector.addGoal(1, OwnerHurtByTargetGoal(this))
        targetSelector.addGoal(2, OwnerHurtTargetGoal(this))
        targetSelector.addGoal(3, HurtByTargetGoal(this).setCallsForHelp())
        targetSelector.addGoal(5, NonTamedTargetGoal(this, AnimalEntity::class.java, false, TARGET_ENTITIES))
        //maybe use this for hunting demons? once tamed
        //targetSelector.addGoal(6, NearestAttackableTargetGoal(this, AbstractSkeletonEntity::class.java, false))
        targetSelector.addGoal(7, ResetAngerGoal(this, true))

    }

    // when aging (this mob can't age)
    override fun func_241840_a(p_241840_1_: ServerWorld, p_241840_2_: AgeableEntity): AgeableEntity? = null

    override fun getAngerTime(): Int = dataManager.get(ANGER_TIME)

    override fun setAngerTime(time: Int) {
        dataManager.set(ANGER_TIME, time)
    }

    override fun getAngerTarget(): UUID? = angerTarget

    override fun setAngerTarget(target: UUID?) {
        angerTarget = target
    }

    //set random anger time
    override fun func_230258_H__() {
        // setAngerTime() but proper access syntax
        angerTime = angerTickRange.getRandomWithinRange(rand)
    }

    override fun getAmbientSound(): SoundEvent {
        return if (func_233678_J__()) {
            SoundEvents.ENTITY_WOLF_GROWL
        } else if (rand.nextInt(3) == 0) {
            if (this.isTamed && this.health < 10.0f) {
                SoundEvents.ENTITY_WOLF_WHINE
            } else {
                SoundEvents.ENTITY_WOLF_PANT
            }
        } else {
            SoundEvents.ENTITY_WOLF_AMBIENT
        }
    }

    override fun getExperiencePoints(player: PlayerEntity): Int = 5 + rand.nextInt(5)
    override fun getDeathSound(): SoundEvent = SoundEvents.ENTITY_WOLF_DEATH
    override fun getHurtSound(damageSourceIn: DamageSource): SoundEvent = SoundEvents.ENTITY_WOLF_HURT
    override fun getSoundVolume(): Float = 0.4f

    override fun playStepSound(pos: BlockPos, blockIn: BlockState) {
        playSound(SoundEvents.ENTITY_WOLF_STEP, 0.15f, 1.0f)
    }

}